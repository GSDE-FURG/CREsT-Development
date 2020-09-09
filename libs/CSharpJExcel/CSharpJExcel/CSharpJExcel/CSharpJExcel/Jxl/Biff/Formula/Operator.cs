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

using System.Collections.Generic;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * An operator is a node in a parse tree.  Its children can be other
	 * operators or operands
	 * Arithmetic operators and functions are all considered operators
	 */
	public abstract class Operator : ParseItem
		{
		/**
		 * The items which this operator manipulates. There will be at most two
		 */
		private ParseItem[] operands;

		/**
		 * Constructor
		 */
		public Operator()
			{
			operands = new ParseItem[0];
			}

		/**
		 * Tells the operands to use the alternate code
		 */
		protected void setOperandAlternateCode()
			{
			for (int i = 0; i < operands.Length; i++)
				{
				operands[i].setAlternateCode();
				}
			}

		/**
		 * Adds operands to this item
		 */
		public void add(ParseItem n)
			{
			n.setParent(this);

			// Grow the array
			ParseItem[] newOperands = new ParseItem[operands.Length + 1];
			System.Array.Copy(operands,0,newOperands,0,operands.Length);
			newOperands[operands.Length] = n;
			operands = newOperands;
			}

		/** 
		 * Gets the operands for this operator from the stack 
		 */
		public abstract void getOperands(Stack<ParseItem> s);

		/**
		 * Gets the operands ie. the children of the node
		 */
		public ParseItem[] getOperands()
			{
			return operands;
			}

		/**
		 * Gets the precedence for this operator.  Operator precedents run from 
		 * 1 to 5, one being the highest, 5 being the lowest
		 *
		 * @return the operator precedence
		 */
		public abstract int getPrecedence();
		}
	}

