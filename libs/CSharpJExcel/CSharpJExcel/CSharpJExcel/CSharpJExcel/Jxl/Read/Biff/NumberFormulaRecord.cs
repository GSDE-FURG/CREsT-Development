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
using CSharpJExcel.Jxl.Write;
using CSharpJExcel.Jxl.Biff.Formula;
using CSharpJExcel.Interop;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A formula's last calculated value
	 */
	class NumberFormulaRecord : CellValue,NumberCell,FormulaData,NumberFormulaCell
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(NumberFormulaRecord.class);

		/**
		 * The last calculated value of the formula
		 */
		private double value;

		/**
		 * The number format
		 */
		private CSharpJExcel.Interop.NumberFormat format;

		/**
		 * The string format for the double value
		 */
		private readonly DecimalFormat defaultFormat = new DecimalFormat("#.###");

		/**
		 * The formula as an excel string
		 */
		private string formulaString;

		/**
		 * A handle to the class needed to access external sheets
		 */
		private ExternalSheet externalSheet;

		/**
		 * A handle to the name table
		 */
		private WorkbookMethods nameTable;

		/**
		 * The raw data
		 */
		private byte[] data;

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 * @param fr the formatting record
		 * @param es the external sheet
		 * @param nt the name table
		 * @param si the sheet
		 */
		public NumberFormulaRecord(Record t,FormattingRecords fr,
								   ExternalSheet es,WorkbookMethods nt,
								   SheetImpl si)
			: base(t,fr,si)
			{
			externalSheet = es;
			nameTable = nt;
			data = getRecord().getData();

			format = fr.getNumberFormat(getXFIndex());

			if (format == null)
				{
				format = defaultFormat;
				}

			value = DoubleHelper.getIEEEDouble(data,6);
			}

		/**
		 * Interface method which returns the value
		 *
		 * @return the last calculated value of the formula
		 */
		public double getValue()
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
			return !System.Double.IsNaN(value) ? format.format(value) : string.Empty;
			}

		/**
		 * Returns the cell type
		 *
		 * @return The cell type
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
		 */
		public byte[] getFormulaData()
			{
			if (!getSheet().getWorkbookBof().isBiff8())
				{
				throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
				}

			// Lop off the standard information
			byte[] d = new byte[data.Length - 6];
			System.Array.Copy(data,6,d,0,data.Length - 6);

			return d;
			}

		/**
		 * Gets the formula as an excel string
		 *
		 * @return the formula as an excel string
		 * @exception FormulaException
		 */
		public string getFormula()
			{
			if (formulaString == null)
				{
				byte[] tokens = new byte[data.Length - 22];
				System.Array.Copy(data,22,tokens,0,tokens.Length);
				FormulaParser fp = new FormulaParser
				  (tokens,this,externalSheet,nameTable,
				   getSheet().getWorkbook().getSettings());
				fp.parse();
				formulaString = fp.getFormula();
				}

			return formulaString;
			}

		/**
		 * Gets the NumberFormat used to format this cell.  This is the java
		 * equivalent of the Excel format
		 *
		 * @return the NumberFormat used to format the cell
		 */
		public CSharpJExcel.Interop.NumberFormat getNumberFormat()
			{
			return format;
			}
		}
	}