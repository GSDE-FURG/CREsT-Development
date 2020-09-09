/*********************************************************************
*
*      Copyright (C) 2006 Andrew Khan
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
	 * An error constant
	 */
	class ErrorConstant : Operand,ParsedThing
		{
		/**
		 * The error
		 */
		private FormulaErrorCode error;

		/**
		 * Constructor
		 */
		public ErrorConstant()
			{
			}

		/**
		 * Constructor
		 *
		 * @param s the error constant
		 */
		public ErrorConstant(string s)
			{
			error = FormulaErrorCode.getErrorCode(s);
			}

		/** 
		 * Reads the ptg data from the array starting at the specified position
		 *
		 * @param data the RPN array
		 * @param pos the current position in the array, excluding the ptg identifier
		 * @return the number of bytes read
		 */
		public int read(byte[] data,int pos)
			{
			int code = data[pos];
			error = FormulaErrorCode.getErrorCode(code);
			return 1;
			}

		/**
		 * Gets the token representation of this item in RPN
		 *
		 * @return the bytes applicable to this formula
		 */
		public override byte[] getBytes()
			{
			byte[] data = new byte[2];
			data[0] = Token.ERR.getCode();
			data[1] = (byte)error.getCode();

			return data;
			}

		/**
		 * Abstract method implementation to get the string equivalent of this
		 * token
		 * 
		 * @param buf the string to append to
		 */
		public override void getString(StringBuilder buf)
			{
			buf.Append(error.getDescription());
			}

		/**
		  * If this formula was on an imported sheet, check that
		  * cell references to another sheet are warned appropriately
		  * Does nothing
		  */
		public override void handleImportedCellReferences()
			{
			}
		}
	}
	

