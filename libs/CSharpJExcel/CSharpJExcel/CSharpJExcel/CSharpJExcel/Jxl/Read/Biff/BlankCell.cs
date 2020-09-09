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

using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A blank cell.  Despite the fact that this cell has no contents, it
	 * has formatting information applied to it
	 */
	public class BlankCell : CellValue
		{
		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 * @param fr the available formats
		 * @param si the sheet
		 */
		public BlankCell(Record t,FormattingRecords fr,SheetImpl si)
			: base(t,fr,si)
			{
			}

		/**
		 * Returns the contents of this cell as an empty string
		 *
		 * @return the value formatted into a string
		 */
		public override string getContents()
			{
			return string.Empty;
			}

		/**
		 * Accessor for the cell type
		 *
		 * @return the cell type
		 */
		public override CellType getType()
			{
			return CellType.EMPTY;
			}
		}
	}


