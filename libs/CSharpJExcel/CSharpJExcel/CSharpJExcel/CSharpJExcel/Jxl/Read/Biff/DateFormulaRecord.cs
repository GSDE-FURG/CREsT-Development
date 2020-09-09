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
	 * A date formula's last calculated value
	 */
	class DateFormulaRecord : DateRecord,DateCell,FormulaData,DateFormulaCell
		{
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
		 * @param t the basic number formula record
		 * @param fr the formatting records
		 * @param es the external sheet
		 * @param nt the name table
		 * @param nf flag indicating whether the 1904 date system is in use
		 * @param si the sheet
		 */
		public DateFormulaRecord(NumberFormulaRecord t,FormattingRecords fr,
								 ExternalSheet es,WorkbookMethods nt,
								 bool nf,SheetImpl si)
			: base(t,t.getXFIndex(),fr,nf,si)
			{
			externalSheet = es;
			nameTable = nt;
			data = t.getFormulaData();
			}

		/**
		 * Returns the cell type
		 *
		 * @return The cell type
		 */
		public override CellType getType()
			{
			return CellType.DATE_FORMULA;
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

			// Data is already the formula data, so don't do any more manipulation
			return data;
			}

		/**
		 * Gets the formula as an excel string
		 *
		 * @return the formula as an excel string
		 * @exception FormulaException
		 */
		public string getFormula()
			{
			// Note that the standard information was lopped off by the NumberFormula
			// record when creating this formula
			if (formulaString == null)
				{
				byte[] tokens = new byte[data.Length - 16];
				System.Array.Copy(data,16,tokens,0,tokens.Length);
				FormulaParser fp = new FormulaParser
				  (tokens,this,externalSheet,nameTable,
				   getSheet().getWorkbook().getSettings());
				fp.parse();
				formulaString = fp.getFormula();
				}

			return formulaString;
			}

		/**
		 * Interface method which returns the value
		 *
		 * @return the last calculated value of the formula
		 */
		public double getValue()
			{
			return 0;
			}

		/**
		 * Dummy implementation in order to adhere to the NumberCell interface
		 *
		 * @return NULL
		 */
		public NumberFormat getNumberFormat()
			{
			return null;
			}
		}
	}
