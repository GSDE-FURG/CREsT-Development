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


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A number formula record, manufactured out of the Shared Formula
	 * "optimization"
	 */
	public class SharedDateFormulaRecord : BaseSharedFormulaRecord,DateCell,FormulaData,DateFormulaCell
		{
		/**
		 * Re-use the date record to handle all the formatting information and
		 * date calculations
		 */
		private DateRecord dateRecord;

		/**
		 * The double value
		 */
		private double value;

		/**
		 * Constructs this number formula
		 *
		 * @param nfr the number formula records
		 * @param fr the formatting records
		 * @param nf flag indicating whether this uses the 1904 date system
		 * @param si the sheet
		 * @param pos the position
		 */
		public SharedDateFormulaRecord(SharedNumberFormulaRecord nfr,
									   FormattingRecords fr,
									   bool nf,
									   SheetImpl si,
									   int pos)
			: base(nfr.getRecord(),
				fr,
				nfr.getExternalSheet(),
				nfr.getNameTable(),
				si,
				pos)
			{
			dateRecord = new DateRecord(nfr,nfr.getXFIndex(),fr,nf,si);
			value = nfr.getValue();
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
			return dateRecord.getContents();
			}

		/**
		 * Accessor for the cell type
		 *
		 * @return the cell type
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
		 * Gets the date
		 *
		 * @return the date
		 */
		public System.DateTime getDate()
			{
			return dateRecord.getDate();
			}

		/**
		 * Indicates whether the date value contained in this cell refers to a date,
		 * or merely a time
		 *
		 * @return TRUE if the value refers to a time
		 */
		public bool isTime()
			{
			return dateRecord.isTime();
			}

		/**
		 * Gets the DateFormat used to format the cell.  This will normally be
		 * the format specified in the excel spreadsheet, but in the event of any
		 * difficulty parsing this, it will revert to the default date/time format.
		 *
		 * @return the DateFormat object used to format the date in the original
		 * excel cell
		 */
		public CSharpJExcel.Interop.DateFormat getDateFormat()
			{
			return dateRecord.getDateFormat();
			}
		}
	}	

