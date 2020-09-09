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
using System.Collections;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A number record.  This is stored as 8 bytes, as opposed to the 
	 * 4 byte RK record
	 */
	public class MergedCellsRecord : WritableRecordData
		{
		/**
		 * The ranges of all the cells which are merged on this sheet
		 */
		private ArrayList ranges;

		/**
		 * Constructs a merged cell record
		 *
		 * @param ws the sheet containing the merged cells
		 */
		public MergedCellsRecord(ArrayList mc)
			: base(Type.MERGEDCELLS)
			{
			ranges = mc;
			}

		/**
		 * Gets the raw data for output to file
		 * 
		 * @return the data to write to file
		 */
		public override byte[] getData()
			{
			byte[] data = new byte[ranges.Count * 8 + 2];

			// Set the number of ranges
			IntegerHelper.getTwoBytes(ranges.Count, data, 0);

			int pos = 2;
			Range range = null;
			for (int i = 0; i < ranges.Count; i++)
				{
				range = (Range)ranges[i];

				// Set the various cell records
				Cell tl = range.getTopLeft();
				Cell br = range.getBottomRight();

				IntegerHelper.getTwoBytes(tl.getRow(), data, pos);
				IntegerHelper.getTwoBytes(br.getRow(), data, pos + 2);
				IntegerHelper.getTwoBytes(tl.getColumn(), data, pos + 4);
				IntegerHelper.getTwoBytes(br.getColumn(), data, pos + 6);

				pos += 8;
				}

			return data;
			}
		}
	}