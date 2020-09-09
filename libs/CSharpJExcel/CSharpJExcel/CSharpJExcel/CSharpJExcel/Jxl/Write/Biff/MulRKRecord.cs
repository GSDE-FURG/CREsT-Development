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
	 * Contains an array of RK numbers
	 */
	class MulRKRecord : WritableRecordData
		{
		/**
		 * The row  containing these numbers
		 */
		private int row;
		/**
		 * The first column these rk number occur on
		 */
		private int colFirst;
		/**
		 * The last column these rk number occur on
		 */
		private int colLast;
		/**
		 * The array of rk numbers
		 */
		private int[] rknumbers;
		/**
		 * The array of xf indices
		 */
		private int[] xfIndices;

		/**
		 * Constructs the rk numbers from the integer cells
		 * 
		 * @param numbers A list of jxl.write.Number objects
		 */
		public MulRKRecord(IList numbers)
			: base(Type.MULRK)
			{
			row = ((Number)numbers[0]).getRow();
			colFirst = ((Number)numbers[0]).getColumn();
			colLast = colFirst + numbers.Count - 1;

			rknumbers = new int[numbers.Count];
			xfIndices = new int[numbers.Count];

			for (int i = 0; i < numbers.Count; i++)
				{
				rknumbers[i] = (int)((Number)numbers[i]).getValue();
				xfIndices[i] = ((CellValue)numbers[i]).getXFIndex();
				}
			}

		/**
		 * Gets the binary data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] data = new byte[rknumbers.Length * 6 + 6];

			// Set up the row and the first column
			IntegerHelper.getTwoBytes(row, data, 0);
			IntegerHelper.getTwoBytes(colFirst, data, 2);

			// Add all the rk numbers
			int pos = 4;
			int rkValue = 0;
			byte[] rkBytes = new byte[4];
			for (int i = 0; i < rknumbers.Length; i++)
				{
				IntegerHelper.getTwoBytes(xfIndices[i], data, pos);

				// To represent an int as an Excel RK value, we have to
				// undergo some outrageous jiggery pokery, as follows:

				// Gets the  bit representation of the number
				rkValue = rknumbers[i] << 2;

				// Set the integer bit
				rkValue |= 0x2;
				IntegerHelper.getFourBytes(rkValue, data, pos + 2);

				pos += 6;
				}

			// Write the number of rk numbers in this record
			IntegerHelper.getTwoBytes(colLast, data, pos);

			return data;
			}
		}
	}


