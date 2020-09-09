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
using CSharpJExcel.Interop;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A number record.  This is stored as 8 bytes, as opposed to the
	 * 4 byte RK record
	 */
	class NumberRecord : CellValue,NumberCell
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(NumberRecord.class);

		/**
		 * The value
		 */
		private double value;

		/**
		 * The java equivalent of the excel format
		 */
		private NumberFormat format;

		/**
		 * The formatter to convert the value into a string
		 */
		private static DecimalFormat defaultFormat = new DecimalFormat("#.###");

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 * @param fr the available formats
		 * @param si the sheet
		 */
		public NumberRecord(Record t,FormattingRecords fr,SheetImpl si)
			: base(t,fr,si)
			{
			byte[] data = getRecord().getData();

			value = DoubleHelper.getIEEEDouble(data,6);

			// Now get the number format
			format = fr.getNumberFormat(getXFIndex());
			if (format == null)
				format = defaultFormat;
			}

		/**
		 * Accessor for the value
		 *
		 * @return the value
		 */
		public double getValue()
			{
			return value;
			}

		/**
		 * Returns the contents of this cell as a string
		 *
		 * @return the value formatted into a string
		 */
		public override string getContents()
			{
			return format.format(value);
			}

		/**
		 * Accessor for the cell type
		 *
		 * @return the cell type
		 */
		public override CellType getType()
			{
			return CellType.NUMBER;
			}

		/**
		 * Gets the NumberFormat used to format this cell.  This is the java
		 * equivalent of the Excel format
		 *
		 * @return the NumberFormat used to format the cell
		 */
		public NumberFormat getNumberFormat()
			{
			return format;
			}
		}
	}
