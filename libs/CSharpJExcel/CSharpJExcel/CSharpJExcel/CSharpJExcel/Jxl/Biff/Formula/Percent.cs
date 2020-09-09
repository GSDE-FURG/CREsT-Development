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

using System.Text;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * A cell reference in a formula
	 */
	public class Percent : UnaryOperator,ParsedThing
		{
		/** 
		 * Constructor
		 */
		public Percent()
			{
			}

		protected override string getSymbol()
			{
			return "%";
			}

		public override void getString(StringBuilder buf)
			{
			ParseItem[] operands = getOperands();
			operands[0].getString(buf);
			buf.Append(getSymbol());
			}

		/**
		 * If this formula was on an imported sheet, check that
		 * cell references to another sheet are warned appropriately
		 * Does nothing, as operators don't have cell references
		 */
		public override void handleImportedCellReferences()
			{
			ParseItem[] operands = getOperands();
			operands[0].handleImportedCellReferences();
			}

		/**
		 * Abstract method which gets the token for this operator
		 *
		 * @return the string symbol for this token
		 */
		protected override Token getToken()
			{
			return Token.PERCENT;
			}

		/**
		 * Gets the precedence for this operator.  Operator precedents run from 
		 * 1 to 5, one being the highest, 5 being the lowest
		 *
		 * @return the operator precedence
		 */
		public override int getPrecedence()
			{
			return 5;
			}
		}
	}

