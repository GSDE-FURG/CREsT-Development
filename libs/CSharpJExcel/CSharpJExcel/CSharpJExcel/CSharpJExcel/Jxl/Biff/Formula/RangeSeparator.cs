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
	 * A "holding" token for a range separator.  This token gets instantiated
	 * when the lexical analyzer can't distinguish a range cleanly, eg in the
	 * case where where one of the identifiers of the range is a formula
	 */
	public class RangeSeparator : BinaryOperator,ParsedThing
		{
		/** 
		 * Constructor
		 */
		public RangeSeparator()
			{
			}

		protected override string getSymbol()
			{
			return ":";
			}

		/**
		 * Abstract method which gets the token for this operator
		 *
		 * @return the string symbol for this token
		 */
		protected override Token getToken()
			{
			return Token.RANGE;
			}

		/**
		 * Gets the precedence for this operator.  Operator precedents run from 
		 * 1 to 5, one being the highest, 5 being the lowest
		 *
		 * @return the operator precedence
		 */
		public override int getPrecedence()
			{
			return 1;
			}

		/**
		 * Overrides the getBytes() method in the base class and prepends the 
		 * memFunc token
		 *
		 * @return the bytes
		 */
		public override byte[] getBytes()
			{
			setVolatile();
			setOperandAlternateCode();

			byte[] funcBytes = base.getBytes();

			byte[] bytes = new byte[funcBytes.Length + 3];
			System.Array.Copy(funcBytes,0,bytes,3,funcBytes.Length);

			// Indicate the mem func 
			bytes[0] = Token.MEM_FUNC.getCode();
			IntegerHelper.getTwoBytes(funcBytes.Length,bytes,1);

			return bytes;
			}
		}
	}
