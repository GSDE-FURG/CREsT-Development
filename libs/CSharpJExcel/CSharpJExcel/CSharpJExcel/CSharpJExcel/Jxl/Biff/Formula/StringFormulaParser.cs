/*********************************************************************
*
*      Copyright (C) 2002 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
***************************************************************************/

// Port to C# 
// Chris Laforet
// Wachovia, a Wells-Fargo Company
// Feb 2010

using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * Parses a string formula into a parse tree
	 */
	class StringFormulaParser : Parser
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(StringFormulaParser.class);

		/**
		 * The formula string passed to this object
		 */
		private string formula;

		/**
		 * The parsed formula string, as retrieved from the parse tree
		 */
		private string parsedFormula;

		/**
		 * The parse tree
		 */
		private ParseItem root;

		/**
		 * The stack argument used when parsing a function in order to
		 * pass multiple arguments back to the calling method
		 */
		private Stack<ParseItem> arguments;

		/**
		 * The workbook settings
		 */
		private WorkbookSettings settings;

		/**
		 * A handle to the external sheet
		 */
		private ExternalSheet externalSheet;

		/**
		 * A handle to the name table
		 */
		private WorkbookMethods nameTable;

		/**
		 * The parse context
		 */
		private ParseContext parseContext;

		/**
		 * Constructor
		 * @param f
		 * @param ws
		 */
		public StringFormulaParser(string f,ExternalSheet es,WorkbookMethods nt,WorkbookSettings ws,ParseContext pc)
			{
			formula = f;
			settings = ws;
			externalSheet = es;
			nameTable = nt;
			parseContext = pc;
			}

		/**
		 * Parses the list of tokens
		 *
		 * @exception FormulaException
		 */
		public void parse()
			{
			root = parseCurrent(getTokens().GetEnumerator());
			}

		/**
		 * Recursively parses the token array.  Recursion is used in order
		 * to evaluate parentheses and function arguments
		 *
		 * @param iterator an iterator of tokens
		 * @return the root node of the current parse stack
		 * @exception FormulaException if an error occurs
		 */
		private ParseItem parseCurrent(IEnumerator<ParseItem> iterator)
			{
			Stack<ParseItem> stack = new Stack<ParseItem>();
			Stack<Operator> operators = new Stack<Operator>();
			Stack<ParseItem> args = null; // we usually don't need this

			bool parenthesesClosed = false;
			ParseItem lastParseItem = null;
			
			while (!parenthesesClosed && iterator.MoveNext())
				{
				ParseItem pi = iterator.Current;
				if (pi == null)
					break;

				pi.setParseContext(parseContext);

				if (pi is Operand)
					handleOperand((Operand)pi, stack);
				else if (pi is StringFunction)
					handleFunction((StringFunction)pi, iterator, stack);
				else if (pi is Operator)
					{
					Operator op = (Operator)pi;

					// See if the operator is a binary or unary operator
					// It is a unary operator either if the stack is empty, or if
					// the last thing off the stack was another operator
					if (op is StringOperator)
						{
						StringOperator sop = (StringOperator)op;
						if (stack.Count == 0 || lastParseItem is Operator)
							op = sop.getUnaryOperator();
						else
							op = sop.getBinaryOperator();
						}

					if (operators.Count == 0)
						{
						// nothing much going on, so do nothing for the time being
						operators.Push(op);
						}
					else
						{
						Operator op2 = operators.Peek();

						// If the last  operator has a higher precedence then add this to 
						// the operator stack and wait
						if (op2.getPrecedence() < op2.getPrecedence())
							operators.Push(op2);
						else if (op2.getPrecedence() == op2.getPrecedence() && op2 is UnaryOperator)
							{
							// The operators are of equal precedence, but because it is a
							// unary operator the operand isn't available yet, so put it on
							// the stack
							operators.Push(op2);
							}
						else
							{
							// The operator is of a lower precedence so we can sort out
							// some of the items on the stack
							operators.Pop(); // remove the operator from the stack
							op2.getOperands(stack);
							stack.Push(op2);
							operators.Push(op2);
							}
						}
					}
				else if (pi is ArgumentSeparator)
					{
					// Clean up any remaining items on this stack
					while (operators.Count > 0)
						{
						Operator o = operators.Pop();
						o.getOperands(stack);
						stack.Push(o);
						}

					// Add it to the argument stack.  Create the argument stack
					// if necessary.  Items will be stored on the argument stack in
					// reverse order
					if (args == null)
						args = new Stack<ParseItem>();

					args.Push(stack.Pop());
					stack.Clear();
					}
				else if (pi is OpenParentheses)
					{
					ParseItem pi2 = parseCurrent(iterator);
					Parenthesis p = new Parenthesis();
					pi2.setParent(p);
					p.add(pi2);
					stack.Push(p);
					}
				else if (pi is CloseParentheses)
					parenthesesClosed = true;

				lastParseItem = pi;
				}

			while (operators.Count > 0)
				{
				Operator o = operators.Pop();
				o.getOperands(stack);
				stack.Push(o);
				}

			ParseItem rt = (stack.Count > 0) ? (ParseItem)stack.Pop() : null;

			// if the argument stack is not null, then add it to that stack
			// as well for good measure
			if (args != null && rt != null)
				args.Push(rt);

			arguments = args;

			if (stack.Count > 0 || operators.Count > 0)
				{
				//logger.warn("Formula " + formula + " has a non-empty parse stack");
				}

			return rt;
			}

		/**
		 * Gets the list of lexical tokens using the generated lexical analyzer
		 *
		 * @return the list of tokens
		 * @exception FormulaException if an error occurs
		 */
		private IList<ParseItem> getTokens()
			{
			List<ParseItem> tokens = new List<ParseItem>();

			// since a StringReader is not a Stream and cannot be wrapped by StreamReader -- brilliant Microsoft!  Brilliant!
			StreamReader reader = new StreamReader(new MemoryStream(Encoding.ASCII.GetBytes(formula))); 

//			StringReader sr = new StringReader(formula);
			Yylex lex = new Yylex(reader);
			lex.setExternalSheet(externalSheet);
			lex.setNameTable(nameTable);
			try
				{
				ParseItem pi = lex.yylex();
				while (pi != null)
					{
					tokens.Add(pi);
					pi = lex.yylex();
					}
				}
			catch (IOException e)
				{
				//logger.warn(e.ToString());
				}
			catch (Exception e)
				{
				throw new FormulaException(FormulaException.LEXICAL_ERROR,formula + " at char  " + lex.getPos());
				}

			return tokens;
			}

		/**
		 * Gets the formula as a string.  Uses the parse tree to do this, and
		 * does not simply return whatever string was passed in
		 */
		public string getFormula()
			{
			if (parsedFormula == null)
				{
				StringBuilder sb = new StringBuilder();
				root.getString(sb);
				parsedFormula = sb.ToString();
				}

			return parsedFormula;
			}

		/**
		 * Gets the bytes for the formula
		 *
		 * @return the bytes in RPN
		 */
		public byte[] getBytes()
			{
			byte[] bytes = root.getBytes();

			if (root.isVolatile())
				{
				byte[] newBytes = new byte[bytes.Length + 4];
				System.Array.Copy(bytes,0,newBytes,4,bytes.Length);
				newBytes[0] = Token.ATTRIBUTE.getCode();
				newBytes[1] = (byte)0x1;
				bytes = newBytes;
				}

			return bytes;
			}

		/**
		 * Handles the case when parsing a string when a token is a function
		 *
		 * @param sf the string function
		 * @param i  the token iterator
		 * @param stack the parse tree stack
		 * @exception FormulaException if an error occurs
		 */
		private void handleFunction(StringFunction sf,IEnumerator<ParseItem> i,Stack<ParseItem> stack)
			{
			ParseItem pi2 = parseCurrent(i);

			// If the function is unknown, then throw an error
			if (sf.getFunction(settings) == Function.UNKNOWN)
				throw new FormulaException(FormulaException.UNRECOGNIZED_FUNCTION);

			// First check for possible optimized functions and possible
			// use of the Attribute token
			if (sf.getFunction(settings) == Function.SUM && arguments == null)
				{
				// this is handled by an attribute
				Attribute a = new Attribute(sf,settings);
				a.add(pi2);
				stack.Push(a);
				return;
				}

			if (sf.getFunction(settings) == Function.IF)
				{
				// this is handled by an attribute
				Attribute a = new Attribute(sf,settings);

				// Add in the if conditions as a var arg function in
				// the correct order
				VariableArgFunction vaf = new VariableArgFunction(settings);
				object [] items = arguments.ToArray();
				for (int j = 0; j < items.Length; j++)
					{
					ParseItem pi3 = (ParseItem)items[j];
					vaf.add(pi3);
					}

				a.setIfConditions(vaf);
				stack.Push(a);
				return;
				}

			int newNumArgs;

			// Function cannot be optimized.  See if it is a variable argument 
			// function or not
			if (sf.getFunction(settings).getNumArgs() == 0xff)
				{

				// If the arg stack has not been initialized, it means
				// that there was only one argument, which is the
				// returned parse item
				if (arguments == null)
					{
					int numArgs = pi2 != null ? 1 : 0;
					VariableArgFunction vaf = new VariableArgFunction(sf.getFunction(settings),numArgs,settings);

					if (pi2 != null)
						vaf.add(pi2);

					stack.Push(vaf);
					}
				else
					{
					// Add the args to the function in the correct order
					newNumArgs = arguments.Count;
					VariableArgFunction vaf = new VariableArgFunction(sf.getFunction(settings),newNumArgs,settings);

					ParseItem[] args = new ParseItem[newNumArgs];
					for (int j = 0; j < newNumArgs; j++)
						{
						ParseItem pi3 = (ParseItem)arguments.Pop();
						args[newNumArgs - j - 1] = pi3;
						}

					for (int j = 0; j < args.Length; j++)
						vaf.add(args[j]);
					stack.Push(vaf);
					arguments.Clear();
					arguments = null;
					}
				return;
				}

			// Function is a standard built in function
			BuiltInFunction bif = new BuiltInFunction(sf.getFunction(settings),settings);

			newNumArgs = sf.getFunction(settings).getNumArgs();
			if (newNumArgs == 1)
				{
				// only one item which is the returned ParseItem
				bif.add(pi2);
				}
			else
				{
				if ((arguments == null && newNumArgs != 0) ||
					(arguments != null && newNumArgs != arguments.Count))
					{
					throw new FormulaException(FormulaException.INCORRECT_ARGUMENTS);
					}
				// multiple arguments so go to the arguments stack.  
				// Unlike the variable argument function, the args are
				// stored in reverse order
				object[] items = arguments.ToArray();
				for (int j = 0; j < newNumArgs; j++)
					{
					ParseItem pi3 = (ParseItem)items[j];
					bif.add(pi3);
					}
				}
			stack.Push(bif);
			}

		/**
		 * Default behaviour is to do nothing
		 *
		 * @param colAdjust the amount to add on to each relative cell reference
		 * @param rowAdjust the amount to add on to each relative row reference
		 */
		public void adjustRelativeCellReferences(int colAdjust,int rowAdjust)
			{
			root.adjustRelativeCellReferences(colAdjust,rowAdjust);
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the column was inserted
		 * @param col the column number which was inserted
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public virtual void columnInserted(int sheetIndex,int col,bool currentSheet)
			{
			root.columnInserted(sheetIndex,col,currentSheet);
			}


		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the column was removed
		 * @param col the column number which was removed
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public virtual void columnRemoved(int sheetIndex,int col,bool currentSheet)
			{
			root.columnRemoved(sheetIndex,col,currentSheet);
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the column was inserted
		 * @param row the column number which was inserted
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public virtual void rowInserted(int sheetIndex,int row,bool currentSheet)
			{
			root.rowInserted(sheetIndex,row,currentSheet);
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the column was removed
		 * @param row the column number which was removed
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public virtual void rowRemoved(int sheetIndex,int row,bool currentSheet)
			{
			root.rowRemoved(sheetIndex,row,currentSheet);
			}

		/**
		 * Handles operands by pushing them onto the stack
		 *
		 * @param o operand
		 * @param stack stack
		 */
		private void handleOperand(Operand o,Stack<ParseItem> stack)
			{
			if (!(o is IntegerValue))
				{
				stack.Push(o);
				return;
				}

			if (o is IntegerValue)
				{
				IntegerValue iv = (IntegerValue)o;
				if (!iv.isOutOfRange())
					stack.Push(iv);
				else
					{
					// convert to a double
					DoubleValue dv = new DoubleValue(iv.getValue());
					stack.Push(dv);
					}
				}
			}

		/**
		 * If this formula was on an imported sheet, check that
		 * cell references to another sheet are warned appropriately
		 *
		 * @return TRUE if the formula is valid import, FALSE otherwise
		 */
		public bool handleImportedCellReferences()
			{
			root.handleImportedCellReferences();
			return root.isValid();
			}
		}
	}
