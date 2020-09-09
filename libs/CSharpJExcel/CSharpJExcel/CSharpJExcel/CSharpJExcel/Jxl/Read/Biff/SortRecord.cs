/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan, Al Mantei
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
	 * A storage area for the last Sort dialog box area
	 */
	public class SortRecord : RecordData
		{
		private int col1Size;
		private int col2Size;
		private int col3Size;
		private string col1Name;
		private string col2Name;
		private string col3Name;
		private byte optionFlags;
		private bool sortColumns = false;
		private bool sortKey1Desc = false;
		private bool sortKey2Desc = false;
		private bool sortKey3Desc = false;
		private bool sortCaseSensitive = false;
		/**
		 * Constructs this object from the raw data
		 *
		 * @param r the raw data
		 */
		public SortRecord(Record r)
			: base(Type.SORT)
			{
			byte[] data = r.getData();

			optionFlags = data[0];

			sortColumns = ((optionFlags & 0x01) != 0);
			sortKey1Desc = ((optionFlags & 0x02) != 0);
			sortKey2Desc = ((optionFlags & 0x04) != 0);
			sortKey3Desc = ((optionFlags & 0x08) != 0);
			sortCaseSensitive = ((optionFlags & 0x10) != 0);

			// data[1] contains sort list index - not implemented...

			col1Size = data[2];
			col2Size = data[3];
			col3Size = data[4];
			int curPos = 5;
			if (data[curPos++] == 0x00)
				{
				char[] newData = new char[col1Size];
				for (int count = 0; count < col1Size; count++)
					newData[count] = (char)data[curPos + count];
				col1Name = new string(newData);
				curPos += col1Size;
				}
			else
				{
				col1Name = StringHelper.getUnicodeString(data,col1Size,curPos);
				curPos += col1Size * 2;
				}

			if (col2Size > 0)
				{
				if (data[curPos++] == 0x00)
					{
					char[] newData = new char[col2Size];
					for (int count = 0; count < col2Size; count++)
						newData[count] = (char)data[curPos + count];
					col2Name = new string(newData);
					curPos += col2Size;
					}
				else
					{
					col2Name = StringHelper.getUnicodeString(data,col2Size,curPos);
					curPos += col2Size * 2;
					}
				}
			else
				{
				col2Name = string.Empty;
				}
			if (col3Size > 0)
				{
				if (data[curPos++] == 0x00)
					{
					char[] newData = new char[col3Size];
					for (int count = 0; count < col3Size; count++)
						newData[count] = (char)data[curPos + count];
					col3Name = new string(newData);
					curPos += col3Size;
					}
				else
					{
					col3Name = StringHelper.getUnicodeString(data,col3Size,curPos);
					curPos += col3Size * 2;
					}
				}
			else
				{
				col3Name = string.Empty;
				}
			}

		/**
		 * Accessor for the 1st Sort Column Name
		 *
		 * @return the 1st Sort Column Name
		 */
		public string getSortCol1Name()
			{
			return col1Name;
			}
		/**
		 * Accessor for the 2nd Sort Column Name
		 *
		 * @return the 2nd Sort Column Name
		 */
		public string getSortCol2Name()
			{
			return col2Name;
			}
		/**
		 * Accessor for the 3rd Sort Column Name
		 *
		 * @return the 3rd Sort Column Name
		 */
		public string getSortCol3Name()
			{
			return col3Name;
			}
		/**
		 * Accessor for the Sort by Columns flag
		 *
		 * @return the Sort by Columns flag
		 */
		public bool getSortColumns()
			{
			return sortColumns;
			}
		/**
		 * Accessor for the Sort Column 1 Descending flag
		 *
		 * @return the Sort Column 1 Descending flag
		 */
		public bool getSortKey1Desc()
			{
			return sortKey1Desc;
			}
		/**
		 * Accessor for the Sort Column 2 Descending flag
		 *
		 * @return the Sort Column 2 Descending flag
		 */
		public bool getSortKey2Desc()
			{
			return sortKey2Desc;
			}
		/**
		 * Accessor for the Sort Column 3 Descending flag
		 *
		 * @return the Sort Column 3 Descending flag
		 */
		public bool getSortKey3Desc()
			{
			return sortKey3Desc;
			}
		/**
		 * Accessor for the Sort Case Sensitivity flag
		 *
		 * @return the Sort Case Secsitivity flag
		 */
		public bool getSortCaseSensitive()
			{
			return sortCaseSensitive;
			}
		}
	}
