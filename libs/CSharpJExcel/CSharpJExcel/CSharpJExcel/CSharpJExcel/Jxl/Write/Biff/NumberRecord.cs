/*********************************************************************
*
*      Copyright (C) 2001 Andrew Khan
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
using CSharpJExcel.Interop;
using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * The record which contains numerical values.  All values are stored
	 * as 64bit IEEE floating point values
	 */
	public abstract class NumberRecord : CellValue
		{
		/**
		 * The number
		 */
		private double value;

		/**
		 * The java equivalent of the excel format
		 */
		private CSharpJExcel.Interop.NumberFormat format;

		/**
		 * The formatter to convert the value into a string
		 */
		private readonly DecimalFormat defaultFormat = new DecimalFormat("#.###");

		/**
		 * Constructor invoked by the user API
		 * 
		 * @param c the column
		 * @param r the row
		 * @param val the value
		 */
		protected NumberRecord(int c, int r, double val)
			: base(Type.NUMBER, c, r)
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
		protected NumberRecord(int c, int r, double val, CellFormat st)
			: base(Type.NUMBER, c, r, st)
			{
			value = val;
			}

		/**
		 * Constructor used when copying a workbook
		 * 
		 * @param nc the number to copy
		 */
		protected NumberRecord(NumberCell nc)
			: base(Type.NUMBER, nc)
			{
			value = nc.getValue();
			}

		/**
		 * Copy constructor
		 * 
		 * @param c the column
		 * @param r the row
		 * @param nr the record to copy
		 */
		protected NumberRecord(int c, int r, NumberRecord nr)
			: base(Type.NUMBER, c, r, nr)
			{
			value = nr.value;
			}

		/**
		 * Returns the content type of this cell
		 * 
		 * @return the content type for this cell
		 */
		public override CellType getType()
			{
			return CellType.NUMBER;
			}

		/**
		 * Gets the binary data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] celldata = base.getData();
			byte[] data = new byte[celldata.Length + 8];
			System.Array.Copy(celldata, 0, data, 0, celldata.Length);
			DoubleHelper.getIEEEBytes(value, data, celldata.Length);

			return data;
			}

		/**
		 * Quick and dirty function to return the contents of this cell as a string.
		 * For more complex manipulation of the contents, it is necessary to cast
		 * this interface to correct subinterface
		 * 
		 * @return the contents of this cell as a string
		 */
		public override string getContents()
			{
			if (format == null)
				{
				format = ((XFRecord)getCellFormat()).getNumberFormat();
				if (format == null)
					{
					format = defaultFormat;
					}
				}
			return format.format(value);
			}

		/**
		 * Gets the double contents for this cell.
		 * 
		 * @return the cell contents
		 */
		public virtual double getValue()
			{
			return value;
			}

		/**
		 * Sets the value of the contents for this cell
		 * 
		 * @param val the new value
		 */
		public virtual void setValue(double val)
			{
			value = val;
			}

		/**
		 * Gets the NumberFormat used to format this cell.  This is the java 
		 * equivalent of the Excel format
		 *
		 * @return the NumberFormat used to format the cell
		 */
		public virtual CSharpJExcel.Interop.NumberFormat getNumberFormat()
			{
			return null;
			}
		}
	}