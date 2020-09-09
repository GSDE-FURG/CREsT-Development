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


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * Exception thrown when parsing a formula
	 */
	public class FormulaException : JXLException
		{
		/**
		 * Inner class containing the message
		 */
		public sealed class FormulaMessage
			{
			/**
			 * The message
			 */
			private string message;

			/**
			 * Constructs this exception with the specified message
			 *
			 * @param m the message
			 */
			internal FormulaMessage(string m)
				{
				message = m;
				}

			/**
			 * Accessor for the message
			 *
			 * @return the message
			 */
			public string getMessage()
				{
				return message;
				}
			}

		/**
		 */
		public static readonly FormulaMessage UNRECOGNIZED_TOKEN = new FormulaMessage("Unrecognized token");

		/**
		 */
		public static readonly FormulaMessage UNRECOGNIZED_FUNCTION = new FormulaMessage("Unrecognized function");

		/**
		 */
		public static readonly FormulaMessage BIFF8_SUPPORTED = new FormulaMessage("Only biff8 formulas are supported");

		/**
		 */
		public static readonly FormulaMessage LEXICAL_ERROR = new FormulaMessage("Lexical error:  ");

		/**
		 */
		public static readonly FormulaMessage INCORRECT_ARGUMENTS = new FormulaMessage("Incorrect arguments supplied to function");

		/**
		 */
		public static readonly FormulaMessage SHEET_REF_NOT_FOUND = new FormulaMessage("Could not find sheet");

		/**
		 */
		public static readonly FormulaMessage CELL_NAME_NOT_FOUND = new FormulaMessage("Could not find named cell");


		/**
		 * Constructs this exception with the specified message
		 *
		 * @param m the message
		 */
		public FormulaException(FormulaMessage m)
			: base(m.getMessage())
			{
			}

		/**
		 * Constructs this exception with the specified message
		 *
		 * @param m the message
		 * @param val the value
		 */
		public FormulaException(FormulaMessage m,int val)
			: base(m.getMessage() + " " + val)
			{
			}

		/**
		 * Constructs this exception with the specified message
		 *
		 * @param m the message
		 * @param val the value
		 */
		public FormulaException(FormulaMessage m,string val)
			: base(m.getMessage() + " " + val)
			{
			}
		}
	}	
