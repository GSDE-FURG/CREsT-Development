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
using CSharpJExcel.Jxl.Biff.Formula;
using CSharpJExcel.Interop;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A number formula record, manufactured out of the Shared Formula
	 * "optimization"
	 */
	public class SharedNumberFormulaRecord : BaseSharedFormulaRecord,NumberCell,FormulaData,NumberFormulaCell
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(SharedNumberFormulaRecord.class);
		/**
		 * The value of this number
		 */
		private double value;
		/**
		 * The cell format
		 */
		private NumberFormat format;
		/**
		 * A handle to the formatting records
		 */
		private FormattingRecords formattingRecords;

		/**
		 * The string format for the double value
		 */
		private static DecimalFormat defaultFormat = new DecimalFormat("#.###");

		/**
		 * Constructs this number
		 *
		 * @param t the data
		 * @param excelFile the excel biff data
		 * @param v the value
		 * @param fr the formatting records
		 * @param es the external sheet
		 * @param nt the name table
		 * @param si the sheet
		 */
		public SharedNumberFormulaRecord(Record t,
										 File excelFile,
										 double v,
										 FormattingRecords fr,
										 ExternalSheet es,
										 WorkbookMethods nt,
										 SheetImpl si)
			: base(t,fr,es,nt,si,excelFile.getPos())
			{
			value = v;
			format = defaultFormat;    // format is set up later from the 
			// SharedFormulaRecord
			}

		/**
		 * Sets the format for the number based on the Excel spreadsheets' format.
		 * This is called from SheetImpl when it has been definitely established
		 * that this cell is a number and not a date
		 *
		 * @param f the format
		 */
		public void setNumberFormat(NumberFormat f)
			{
			if (f != null)
				{
				format = f;
				}
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
		 * Accessor for the contents as a string
		 *
		 * @return the value as a string
		 */
		public override string getContents()
			{
			return !System.Double.IsNaN(value) ? format.format(value) : string.Empty;
			}

		/**
		 * Accessor for the cell type
		 *
		 * @return the cell type
		 */
		public override CellType getType()
			{
			return CellType.NUMBER_FORMULA;
			}

		/**
		 * Gets the raw bytes for the formula.  This will include the
		 * parsed tokens array.  Used when copying spreadsheets
		 *
		 * @return the raw record data
		 * @exception FormulaException
		 */
		public override byte[] getFormulaData()
			{
			if (!getSheet().getWorkbookBof().isBiff8())
				{
				throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
				}

			// Get the tokens, taking into account the mapping from shared
			// formula specific values into normal values
			FormulaParser fp = new FormulaParser
			  (getTokens(),this,
			   getExternalSheet(),getNameTable(),
			   getSheet().getWorkbook().getSettings());
			fp.parse();
			byte[] rpnTokens = fp.getBytes();

			byte[] data = new byte[rpnTokens.Length + 22];

			// Set the standard info for this cell
			IntegerHelper.getTwoBytes(getRow(),data,0);
			IntegerHelper.getTwoBytes(getColumn(),data,2);
			IntegerHelper.getTwoBytes(getXFIndex(),data,4);
			DoubleHelper.getIEEEBytes(value,data,6);

			// Now copy in the parsed tokens
			System.Array.Copy(rpnTokens,0,data,22,rpnTokens.Length);
			IntegerHelper.getTwoBytes(rpnTokens.Length,data,20);

			// Lop off the standard information
			byte[] d = new byte[data.Length - 6];
			System.Array.Copy(data,6,d,0,data.Length - 6);

			return d;
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
