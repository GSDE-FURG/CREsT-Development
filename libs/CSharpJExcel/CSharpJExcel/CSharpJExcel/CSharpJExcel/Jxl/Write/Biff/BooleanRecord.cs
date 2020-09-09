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

using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A bool cell's last calculated value
	 */
	public abstract class BooleanRecord : CellValue
		{
		/**
		 * The bool value of this cell.  If this cell represents an error, 
		 * this will be false
		 */
		private bool value;

		/**
		 * Constructor invoked by the user API
		 * 
		 * @param c the column
		 * @param r the row
		 * @param val the value
		 */
		protected BooleanRecord(int c, int r, bool val)
			: base(Type.BOOLERR, c, r)
			{
			value = val;
			}

		/**
		 * Overloaded constructor invoked from the API, which takes a cell
		 * format
		 * 
		 * @param c the column
		 * @param r the row
		 * @param val the value
		 * @param st the cell format
		 */
		protected BooleanRecord(int c, int r, bool val, CellFormat st)
			: base(Type.BOOLERR, c, r, st)
			{
			value = val;
			}

		/**
		 * Constructor used when copying a workbook
		 * 
		 * @param nc the number to copy
		 */
		protected BooleanRecord(BooleanCell nc)
			: base(Type.BOOLERR, nc)
			{
			value = nc.getValue();
			}

		/**
		 * Copy constructor
		 * 
		 * @param c the column
		 * @param r the row
		 * @param br the record to copy
		 */
		protected BooleanRecord(int c, int r, BooleanRecord br)
			: base(Type.BOOLERR, c, r, br)
			{
			value = br.value;
			}

		/**
		 * Interface method which Gets the bool value stored in this cell.  If 
		 * this cell contains an error, then returns FALSE.  Always query this cell
		 *  type using the accessor method isError() prior to calling this method
		 *
		 * @return TRUE if this cell contains TRUE, FALSE if it contains FALSE or
		 * an error code
		 */
		public bool getValue()
			{
			return value;
			}

		/**
		 * Returns the numerical value as a string
		 * 
		 * @return The numerical value of the formula as a string
		 */
		public override string getContents()
			{
			// return Boolean.ToString(value) - only available in 1.4 or later
			return value.ToString();
			}

		/**
		 * Returns the cell type
		 * 
		 * @return The cell type
		 */
		public override CellType getType()
			{
			return CellType.BOOLEAN;
			}

		/**
		 * Sets the value
		 * 
		 * @param val the bool value
		 */
		public virtual void setValue(bool val)
			{
			value = val;
			}

		/**
		 * Gets the binary data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] celldata = base.getData();
			byte[] data = new byte[celldata.Length + 2];
			System.Array.Copy(celldata, 0, data, 0, celldata.Length);

			if (value)
				{
				data[celldata.Length] = 1;
				}

			return data;
			}
		}
	}