/*********************************************************************
*
*      Copyright (C) 2006 Andrew Khan
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

using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl;
using CSharpJExcel.Jxl.Biff;
using CSharpJExcel.Jxl.Biff.Drawing;
using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Jxl.Write;
using System.Collections;
using System.Collections.Generic;
using CSharpJExcel.Interop;
using CSharpJExcel.Jxl.Biff.Formula;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A transient utility object used to copy sheets.   This 
	 * functionality has been farmed out to a different class
	 * in order to reduce the bloat of the WritableSheetImpl
	 */
	class WritableSheetCopier
		{
		//  private static Logger logger = Logger.getLogger(SheetCopier.class);

		private WritableSheetImpl fromSheet;
		private WritableSheetImpl toSheet;
		private WorkbookSettings workbookSettings;

		// objects used by the sheet
		private TreeSet<ColumnInfoRecord> fromColumnFormats;
		private TreeSet<ColumnInfoRecord> toColumnFormats;
		private MergedCells fromMergedCells;
		private MergedCells toMergedCells;
		private RowRecord[] fromRows;
		private ArrayList fromRowBreaks;
		private ArrayList fromColumnBreaks;
		private ArrayList toRowBreaks;
		private ArrayList toColumnBreaks;
		private DataValidation fromDataValidation;
		private DataValidation toDataValidation;
		private SheetWriter sheetWriter;
		private ArrayList fromDrawings;
		private ArrayList toDrawings;
		private ArrayList toImages;
		private WorkspaceInformationRecord fromWorkspaceOptions;
		private PLSRecord fromPLSRecord;
		private PLSRecord toPLSRecord;
		private ButtonPropertySetRecord fromButtonPropertySet;
		private ButtonPropertySetRecord toButtonPropertySet;
		private ArrayList fromHyperlinks;
		private ArrayList toHyperlinks;
		private ArrayList validatedCells;
		private int numRows;
		private int maxRowOutlineLevel;
		private int maxColumnOutlineLevel;


		private bool chartOnly;
		private FormattingRecords formatRecords;
		
		// objects used to maintain state during the copy process
		private Dictionary<int,WritableCellFormat> xfRecords;
// TODO: CML - I think these two can be squashed
		private Dictionary<int,int> fonts = new Dictionary<int,int>();
		private Dictionary<int,int> formats = new Dictionary<int,int>();

		public WritableSheetCopier(WritableSheet f, WritableSheet t)
			{
			fromSheet = (WritableSheetImpl)f;
			toSheet = (WritableSheetImpl)t;
			workbookSettings = toSheet.getWorkbook().getSettings();
			chartOnly = false;
			}

		public void setColumnFormats(TreeSet<ColumnInfoRecord> fcf, TreeSet<ColumnInfoRecord> tcf)
			{
			fromColumnFormats = fcf;
			toColumnFormats = tcf;
			}

		public void setMergedCells(MergedCells fmc, MergedCells tmc)
			{
			fromMergedCells = fmc;
			toMergedCells = tmc;
			}

		public void setRows(RowRecord[] r)
			{
			fromRows = r;
			}

		public void setValidatedCells(ArrayList vc)
			{
			validatedCells = vc;
			}

		public void setRowBreaks(ArrayList frb, ArrayList trb)
			{
			fromRowBreaks = frb;
			toRowBreaks = trb;
			}

		public void setColumnBreaks(ArrayList fcb, ArrayList tcb)
			{
			fromColumnBreaks = fcb;
			toColumnBreaks = tcb;
			}

		public void setDrawings(ArrayList fd, ArrayList td, ArrayList ti)
			{
			fromDrawings = fd;
			toDrawings = td;
			toImages = ti;
			}

		public void setHyperlinks(ArrayList fh, ArrayList th)
			{
			fromHyperlinks = fh;
			toHyperlinks = th;
			}

		public void setWorkspaceOptions(WorkspaceInformationRecord wir)
			{
			fromWorkspaceOptions = wir;
			}

		public void setDataValidation(DataValidation dv)
			{
			fromDataValidation = dv;
			}

		public void setPLSRecord(PLSRecord plsr)
			{
			fromPLSRecord = plsr;
			}

		public void setButtonPropertySetRecord(ButtonPropertySetRecord bpsr)
			{
			fromButtonPropertySet = bpsr;
			}

		public void setSheetWriter(SheetWriter sw)
			{
			sheetWriter = sw;
			}


		public DataValidation getDataValidation()
			{
			return toDataValidation;
			}

		public PLSRecord getPLSRecord()
			{
			return toPLSRecord;
			}

		public bool isChartOnly()
			{
			return chartOnly;
			}

		public ButtonPropertySetRecord getButtonPropertySet()
			{
			return toButtonPropertySet;
			}

		/**
		 * Copies a sheet from a read-only version to the writable version.
		 * Performs shallow copies
		 */
		public void copySheet()
			{
			shallowCopyCells();

			// Copy the column formats
			foreach (ColumnInfoRecord cv in fromColumnFormats)
				toColumnFormats.Add(cv);

			// Copy the merged cells
			Range[] merged = fromMergedCells.getMergedCells();

			for (int i = 0; i < merged.Length; i++)
				toMergedCells.add(new SheetRangeImpl((SheetRangeImpl)merged[i],toSheet));

			try
				{
				RowRecord row = null;
				RowRecord newRow = null;
				for (int i = 0; i < fromRows.Length; i++)
					{
					row = fromRows[i];

					if (row != null &&
						(!row.isDefaultHeight() ||
						 row.isCollapsed()))
						{
						newRow = toSheet.getRowRecord(i);
						newRow.setRowDetails(row.getRowHeight(),
											 row.matchesDefaultFontHeight(),
											 row.isCollapsed(),
											 row.getOutlineLevel(),
											 row.getGroupStart(),
											 row.getStyle());
						}
					}
				}
			catch (RowsExceededException e)
				{
				// Handle the rows exceeded exception - this cannot occur since
				// the sheet we are copying from will have a valid number of rows
				Assert.verify(false);
				}

			// Copy the horizontal page breaks
			toRowBreaks = new ArrayList(fromRowBreaks);

			// Copy the vertical page breaks
			toColumnBreaks = new ArrayList(fromColumnBreaks);

			// Copy the data validations
			if (fromDataValidation != null)
				{
				toDataValidation = new DataValidation
				  (fromDataValidation,
				   toSheet.getWorkbook(),
				   toSheet.getWorkbook(),
				   toSheet.getWorkbook().getSettings());
				}

			// Copy the charts
			sheetWriter.setCharts(fromSheet.getCharts());

			// Copy the drawings
			foreach (object o in fromDrawings)
				{
				if (o is CSharpJExcel.Jxl.Biff.Drawing.Drawing)
					{
					WritableImage wi = new WritableImage
					  ((CSharpJExcel.Jxl.Biff.Drawing.Drawing)o,
					   toSheet.getWorkbook().getDrawingGroup());
					toDrawings.Add(wi);
					toImages.Add(wi);
					}

				// Not necessary to copy the comments, as they will be handled by
				// the deep copy of the individual cells
				}

			// Copy the workspace options
			sheetWriter.setWorkspaceOptions(fromWorkspaceOptions);

			// Copy the environment specific print record
			if (fromPLSRecord != null)
				{
				toPLSRecord = new PLSRecord(fromPLSRecord);
				}

			// Copy the button property set
			if (fromButtonPropertySet != null)
				{
				toButtonPropertySet = new ButtonPropertySetRecord(fromButtonPropertySet);
				}

			// Copy the hyperlinks
			foreach (WritableHyperlink hyperlink in fromHyperlinks)
				{
				WritableHyperlink hr = new WritableHyperlink(hyperlink, toSheet);
				toHyperlinks.Add(hr);
				}
			}

		/**
		 * Performs a shallow copy of the specified cell
		 */
		private WritableCell shallowCopyCell(Cell cell)
			{
			CellType ct = cell.getType();
			WritableCell newCell = null;

			if (ct == CellType.LABEL)
				{
				newCell = new Label((LabelCell)cell);
				}
			else if (ct == CellType.NUMBER)
				{
				newCell = new Number((NumberCell)cell);
				}
			else if (ct == CellType.DATE)
				{
				newCell = new ExcelDateTime((DateCell)cell);
				}
			else if (ct == CellType.BOOLEAN)
				{
				newCell = new Boolean((BooleanCell)cell);
				}
			else if (ct == CellType.NUMBER_FORMULA)
				{
				newCell = new ReadNumberFormulaRecord((FormulaData)cell);
				}
			else if (ct == CellType.STRING_FORMULA)
				{
				newCell = new ReadStringFormulaRecord((FormulaData)cell);
				}
			else if (ct == CellType.BOOLEAN_FORMULA)
				{
				newCell = new ReadBooleanFormulaRecord((FormulaData)cell);
				}
			else if (ct == CellType.DATE_FORMULA)
				{
				newCell = new ReadDateFormulaRecord((FormulaData)cell);
				}
			else if (ct == CellType.FORMULA_ERROR)
				{
				newCell = new ReadErrorFormulaRecord((FormulaData)cell);
				}
			else if (ct == CellType.EMPTY)
				{
				if (cell.getCellFormat() != null)
					{
					// It is a blank cell, rather than an empty cell, so
					// it may have formatting information, so
					// it must be copied
					newCell = new Blank(cell);
					}
				}

			return newCell;
			}

		/** 
		 * Performs a deep copy of the specified cell, handling the cell format
		 * 
		 * @param cell the cell to copy
		 */
		private WritableCell deepCopyCell(Cell cell)
			{
			WritableCell c = shallowCopyCell(cell);

			if (c == null)
				{
				return c;
				}

			if (c is ReadFormulaRecord)
				{
				ReadFormulaRecord rfr = (ReadFormulaRecord)c;
				bool crossSheetReference = !rfr.handleImportedCellReferences
				  (fromSheet.getWorkbook(),
				   fromSheet.getWorkbook(),
				   workbookSettings);

				if (crossSheetReference)
					{
					try
						{
						//logger.warn("Formula " + rfr.getFormula() +
						//            " in cell " +
						//            CellReferenceHelper.getCellReference(cell.getColumn(),
						//                                                 cell.getRow()) +
						//            " cannot be imported because it references another " +
						//            " sheet from the source workbook");
						}
					catch (FormulaException e)
						{
						//logger.warn("Formula  in cell " +
						//            CellReferenceHelper.getCellReference(cell.getColumn(),
						//                                                 cell.getRow()) +
						//            " cannot be imported:  " + e.Message);
						}

					// Create a new error formula and add it instead
					c = new Formula(cell.getColumn(), cell.getRow(), "\"ERROR\"");
					}
				}

			// Copy the cell format
			CellFormat cf = c.getCellFormat();
			int index = ((XFRecord)cf).getXFIndex();

			WritableCellFormat wcf = null;
			if (!xfRecords.ContainsKey(index))
				wcf = copyCellFormat(cf);
			else
				wcf = xfRecords[index];
			c.setCellFormat(wcf);

			return c;
			}

		/** 
		 * Perform a shallow copy of the cells from the specified sheet into this one
		 */
		public void shallowCopyCells()
			{
			// Copy the cells
			int cells = fromSheet.getRows();
			Cell[] row = null;
			Cell cell = null;
			for (int i = 0; i < cells; i++)
				{
				row = fromSheet.getRow(i);

				for (int j = 0; j < row.Length; j++)
					{
					cell = row[j];
					WritableCell c = shallowCopyCell(cell);

					// Encase the calls to addCell in a try-catch block
					// These should not generate any errors, because we are
					// copying from an existing spreadsheet.  In the event of
					// errors, catch the exception and then bomb out with an
					// assertion
					try
						{
						if (c != null)
							{
							toSheet.addCell(c);

							// Cell.setCellFeatures short circuits when the cell is copied,
							// so make sure the copy logic handles the validated cells        
							if (c.getCellFeatures() != null &
								c.getCellFeatures().hasDataValidation())
								{
								validatedCells.Add(c);
								}
							}
						}
					catch (WriteException e)
						{
						Assert.verify(false);
						}
					}
				}
			numRows = toSheet.getRows();
			}

		/** 
		 * Perform a deep copy of the cells from the specified sheet into this one
		 */
		public void deepCopyCells()
			{
			// Copy the cells
			int cells = fromSheet.getRows();
			Cell[] row = null;
			Cell cell = null;
			for (int i = 0; i < cells; i++)
				{
				row = fromSheet.getRow(i);

				for (int j = 0; j < row.Length; j++)
					{
					cell = row[j];
					WritableCell c = deepCopyCell(cell);

					// Encase the calls to addCell in a try-catch block
					// These should not generate any errors, because we are
					// copying from an existing spreadsheet.  In the event of
					// errors, catch the exception and then bomb out with an
					// assertion
					try
						{
						if (c != null)
							{
							toSheet.addCell(c);

							// Cell.setCellFeatures short circuits when the cell is copied,
							// so make sure the copy logic handles the validated cells        
							if (c.getCellFeatures() != null &
								c.getCellFeatures().hasDataValidation())
								{
								validatedCells.Add(c);
								}
							}
						}
					catch (WriteException e)
						{
						Assert.verify(false);
						}
					}
				}
			}

		/**
		 * Returns an initialized copy of the cell format
		 *
		 * @param cf the cell format to copy
		 * @return a deep copy of the cell format
		 */
		private WritableCellFormat copyCellFormat(CellFormat cf)
			{
			try
				{
				// just do a deep copy of the cell format for now.  This will create
				// a copy of the format and font also - in the future this may
				// need to be sorted out
				XFRecord xfr = (XFRecord)cf;
				WritableCellFormat f = new WritableCellFormat(xfr);
				formatRecords.addStyle(f);

				// Maintain the local list of formats
				int xfIndex = xfr.getXFIndex();
				xfRecords.Add(xfIndex, f);

				int fontIndex = xfr.getFontIndex();
				fonts.Add(fontIndex,f.getFontIndex());

				int formatIndex = xfr.getFormatRecord();
				formats.Add(formatIndex,f.getFormatRecord());

				return f;
				}
			catch (NumFormatRecordsException e)
				{
				//logger.warn("Maximum number of format records exceeded.  Using default format.");

				return WritableWorkbook.NORMAL_STYLE;
				}
			}


		/** 
		 * Accessor for the maximum column outline level
		 *
		 * @return the maximum column outline level, or 0 if no outlines/groups
		 */
		public int getMaxColumnOutlineLevel()
			{
			return maxColumnOutlineLevel;
			}

		/** 
		 * Accessor for the maximum row outline level
		 *
		 * @return the maximum row outline level, or 0 if no outlines/groups
		 */
		public int getMaxRowOutlineLevel()
			{
			return maxRowOutlineLevel;
			}
		}
	}


