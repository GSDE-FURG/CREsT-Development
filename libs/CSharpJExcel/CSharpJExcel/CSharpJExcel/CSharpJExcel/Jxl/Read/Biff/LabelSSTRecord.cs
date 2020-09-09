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
	 * A label which is stored in the shared string table
	 */
	class LabelSSTRecord : CellValue,LabelCell
		{
		/**
		 * The index into the shared string table
		 */
		private int index;
		/**
		 * The label
		 */
		private string description;

		/**
		 * Constructor.  Retrieves the index from the raw data and looks it up
		 * in the shared string table
		 *
		 * @param stringTable the shared string table
		 * @param t the raw data
		 * @param fr the formatting records
		 * @param si the sheet
		 */
		public LabelSSTRecord(Record t,SSTRecord stringTable,FormattingRecords fr,
							  SheetImpl si)
			: base(t,fr,si)
			{
			byte[] data = getRecord().getData();
			index = IntegerHelper.getInt(data[6],data[7],data[8],data[9]);
			description = stringTable.getString(index);
			}

		/**
		 * Gets the label
		 *
		 * @return the label
		 */
		public string getString()
			{
			return description;
			}

		/**
		 * Gets this cell's contents as a string
		 *
		 * @return the label
		 */
		public override string getContents()
			{
			return description;
			}

		/**
		 * Returns the cell type
		 *
		 * @return the cell type
		 */
		public override CellType getType()
			{
			return CellType.LABEL;
			}
		}
	}
