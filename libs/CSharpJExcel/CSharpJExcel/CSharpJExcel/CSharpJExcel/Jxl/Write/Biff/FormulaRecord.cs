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
using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Jxl.Biff.Formula;
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A formula record.  Parses the string passed in to deduce the set of
	 * formula records
	 */
	public class FormulaRecord : CellValue, FormulaData
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(FormulaRecord.class);

		/**
		 * The formula to parse
		 */
		private string formulaToParse;

		/**
		 * The formula parser
		 */
		private FormulaParser parser;

		/**
		 * The parsed formula string
		 */
		private string formulaString;

		/**
		 * The parsed formula bytes
		 */
		private byte[] formulaBytes;

		/**
		 * The location where this formula was copied from.  It is used subsequently
		 * to adjust relative cell references
		 */
		private CellValue copiedFrom;

		/**
		 * Constructor
		 * 
		 * @param f the formula to copy
		 */
		public FormulaRecord(int c, int r, string f)
			: base(Type.FORMULA2, c, r)
			{
			formulaToParse = f;
			copiedFrom = null;

//			formulaBytes = new byte[0];		// CML
			}

		/**
		 * Constructor
		 * 
		 * @param f the formula to copy
		 */
		public FormulaRecord(int c, int r, string f, CellFormat st)
			: base(Type.FORMULA, c, r, st)
			{
			formulaToParse = f;
			copiedFrom = null;

//			formulaBytes = new byte[0];		// CML
			}

		/**
		 * Copy constructor for writable formulas
		 * 
		 * @param c the column
		 * @param r the row
		 * @param fr the record to copy
		 */
		public FormulaRecord(int c, int r, FormulaRecord fr)
			: base(Type.FORMULA, c, r, fr)
			{
			copiedFrom = fr;
			formulaBytes = new byte[fr.formulaBytes.Length];
			System.Array.Copy(fr.formulaBytes, 0, formulaBytes, 0, formulaBytes.Length);
			}

		/**
		 * Copy constructor for formulas read in - invoked from writable formulas
		 * 
		 * @param c the column
		 * @param r the row
		 * @param rfr the formula data to copy
		 */
		public FormulaRecord(int c, int r, ReadFormulaRecord rfr)
			: base(Type.FORMULA, c, r, rfr)
			{
			try
				{
				copiedFrom = rfr;
				formulaBytes = rfr.getFormulaBytes();
				}
			catch (FormulaException e)
				{
				// Fail silently
				//logger.error(string.Empty, e);
				}
			}

		/**
		 * Initializes the string and the formula bytes.  In order to get
		 * access to the workbook settings, the object is not initialized until
		 * it is added to the sheet
		 *
		 * @param ws the workbook settings
		 * @param es the external sheet
		 * @param nt the name table
		 */
		private void initialize(WorkbookSettings ws, ExternalSheet es,
								WorkbookMethods nt)
			{
			if (copiedFrom != null)
				{
				initializeCopiedFormula(ws, es, nt);
				return;
				}

			parser = new FormulaParser(formulaToParse, es, nt, ws);

			try
				{
				parser.parse();
				formulaString = parser.getFormula();
				formulaBytes = parser.getBytes();
				}
			catch (FormulaException e)
				{
				//logger.warn(e.Message + " when parsing formula " + formulaToParse + " in cell " +
				//   getSheet().getName() + "!" +
				//     CellReferenceHelper.getCellReference(getColumn(), getRow()));

				try
					{
					// try again, with an error formula
					formulaToParse = "ERROR(1)";
					parser = new FormulaParser(formulaToParse, es, nt, ws);
					parser.parse();
					formulaString = parser.getFormula();
					formulaBytes = parser.getBytes();
					}
				catch (FormulaException e2)
					{
					// fail silently
					//logger.error(string.Empty, e2);
					}
				}
			}

		/**
		 * This formula was copied from a formula already present in the writable 
		 * workbook.  Requires special handling to sort out the cell references

		 * @param ws the workbook settings
		 * @param es the external sheet
		 * @param nt the name table
		 */
		private void initializeCopiedFormula(WorkbookSettings ws,
											 ExternalSheet es, WorkbookMethods nt)
			{
			try
				{
				parser = new FormulaParser(formulaBytes, this, es, nt, ws);
				parser.parse();
				parser.adjustRelativeCellReferences
				  (getColumn() - copiedFrom.getColumn(),
				   getRow() - copiedFrom.getRow());
				formulaString = parser.getFormula();
				formulaBytes = parser.getBytes();
				}
			catch (FormulaException e)
				{
				try
					{
					// try again, with an error formula
					formulaToParse = "ERROR(1)";
					parser = new FormulaParser(formulaToParse, es, nt, ws);
					parser.parse();
					formulaString = parser.getFormula();
					formulaBytes = parser.getBytes();

					}
				catch (FormulaException e2)
					{
					// fail silently
					//logger.error(string.Empty, e2);
					}
				}
			}

		/**
		 * Called when the cell is added to the worksheet.  Overrides the
		 * method in the base class in order to get a handle to the
		 * WorkbookSettings so that this formula may be initialized
		 * 
		 * @param fr the formatting records
		 * @param ss the shared strings used within the workbook
		 * @param s the sheet this is being added to
		 */
		public override void setCellDetails(FormattingRecords fr, SharedStrings ss,WritableSheetImpl s)
			{
			base.setCellDetails(fr, ss, s);
			initialize(s.getWorkbookSettings(), s.getWorkbook(), s.getWorkbook());
			s.getWorkbook().addRCIRCell(this);
			}

		/**
		 * Gets the binary data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] celldata = base.getData();
			byte[] formulaData = getFormulaData();
			byte[] data = new byte[formulaData.Length + celldata.Length];
			System.Array.Copy(celldata, 0, data, 0, celldata.Length);
			System.Array.Copy(formulaData, 0, data, celldata.Length,
							 formulaData.Length);
			return data;
			}

		/**
		 * Returns the content type of this cell
		 * 
		 * @return the content type for this cell
		 */
		public override CellType getType()
			{
			return CellType.ERROR;
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
			return formulaString;
			}

		/**
		 * Gets the raw bytes for the formula.  This will include the
		 * parsed tokens array
		 *
		 * @return the raw record data
		 */
		public byte[] getFormulaData()
			{
			byte[] data = new byte[formulaBytes.Length + 16];
			System.Array.Copy(formulaBytes, 0, data, 16, formulaBytes.Length);

			data[6] = (byte)0x10;
			data[7] = (byte)0x40;
			data[12] = (byte)0xe0;
			data[13] = (byte)0xfc;
			// Set the recalculate on load bit
			data[8] |= 0x02;

			// Set the length of the rpn array
			IntegerHelper.getTwoBytes(formulaBytes.Length, data, 14);

			return data;
			}

		/**
		 * A dummy implementation to keep the compiler quiet.  This object needs
		 * to be instantiated from ReadFormulaRecord
		 *
		 * @param col the column which the new cell will occupy
		 * @param row the row which the new cell will occupy
		 * @return  NOTHING
		 */
		public override WritableCell copyTo(int col, int row)
			{
			Assert.verify(false);
			return null;
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Notifies all
		 * RCIR cells of this change. The default implementation here does nothing
		 *
		 * @param s the sheet on which the column was inserted
		 * @param sheetIndex the sheet index on which the column was inserted
		 * @param col the column number which was inserted
		 */
		public override void columnInserted(Sheet s, int sheetIndex, int col)
			{
			parser.columnInserted(sheetIndex, col, s == getSheet());
			formulaBytes = parser.getBytes();
			}

		/**
		 * Called when a column is removed on the specified sheet.  Notifies all
		 * RCIR cells of this change. The default implementation here does nothing
		 *
		 * @param s the sheet on which the column was inserted
		 * @param sheetIndex the sheet index on which the column was inserted
		 * @param col the column number which was inserted
		 */
		public override void columnRemoved(Sheet s, int sheetIndex, int col)
			{
			parser.columnRemoved(sheetIndex, col, s == getSheet());
			formulaBytes = parser.getBytes();
			}

		/**
		 * Called when a row is inserted on the specified sheet.  Notifies all
		 * RCIR cells of this change. The default implementation here does nothing
		 *
		 * @param s the sheet on which the column was inserted
		 * @param sheetIndex the sheet index on which the column was inserted
		 * @param row the column number which was inserted
		 */
		public override void rowInserted(Sheet s, int sheetIndex, int row)
			{
			parser.rowInserted(sheetIndex, row, s == getSheet());
			formulaBytes = parser.getBytes();
			}

		/**
		 * Called when a row is inserted on the specified sheet.  Notifies all
		 * RCIR cells of this change. The default implementation here does nothing
		 *
		 * @param s the sheet on which the row was removed
		 * @param sheetIndex the sheet index on which the column was removed
		 * @param row the column number which was removed
		 */
		public override void rowRemoved(Sheet s, int sheetIndex, int row)
			{
			parser.rowRemoved(sheetIndex, row, s == getSheet());
			formulaBytes = parser.getBytes();
			}
		}
	}

