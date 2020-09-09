/*********************************************************************
*
*      Copyright (C) 2007 Andrew Khan
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
	 * Indicates that the function doesn't evaluate to a constant reference
	 */
	class MemArea : SubExpression
		{
		/**
		 * Constructor
		 */
		public MemArea()
			{
			}

		public override void getString(StringBuilder buf)
			{
			ParseItem[] subExpression = getSubExpression();

			if (subExpression.Length == 1)
				{
				subExpression[0].getString(buf);
				}
			else if (subExpression.Length == 2)
				{
				subExpression[1].getString(buf);
				buf.Append(':');
				subExpression[0].getString(buf);
				}
			}

		/** 
		 * Reads the ptg data from the array starting at the specified position
		 *
		 * @param data the RPN array
		 * @param pos the current position in the array, excluding the ptg identifier
		 * @return the number of bytes read
		 */
		public override int read(byte[] data, int pos)
			{
			// For mem areas, the first four bytes are not used
			setLength(IntegerHelper.getInt(data[pos + 4],data[pos + 5]));
			return 6;
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

