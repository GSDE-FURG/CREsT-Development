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

using System.Collections;
using CSharpJExcel.Jxl.Biff;
using CSharpJExcel.Jxl.Read.Biff;
using CSharpJExcel.Jxl.Biff.Drawing;
using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Interop;
using System.Collections.Generic;
using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl.Biff.Formula;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A transient utility object used to copy sheets.   This 
	 * functionality has been farmed out to a different class
	 * in order to reduce the bloat of the WritableSheetImpl
	 */
	class SheetCopier
		{
		//  private static Logger logger = Logger.getLogger(SheetCopier.class);

		private SheetImpl fromSheet;
		private WritableSheetImpl toSheet;
		private WorkbookSettings workbookSettings;

		// objects used by the sheet
		private TreeSet<ColumnInfoRecord> columnFormats;
		private FormattingRecords formatRecords;
		private ArrayList hyperlinks;
		private MergedCells mergedCells;
		private ArrayList rowBreaks;
		private ArrayList columnBreaks;
		private SheetWriter sheetWriter;
		private ArrayList drawings;
		private ArrayList images;
		private ArrayList conditionalFormats;
		private ArrayList validatedCells;
		private AutoFilter autoFilter;
		private DataValidation dataValidation;
		private ComboBox comboBox;
		private PLSRecord plsRecord;
		private bool chartOnly;
		private ButtonPropertySetRecord buttonPropertySet;
		private int numRows;
		private int maxRowOutlineLevel;
		private int maxColumnOutlineLevel;

		// objects used to maintain state during the copy process
		private Dictionary<int,WritableCellFormat> xfRecords;
		private Dictionary<int,int> fonts;
		private Dictionary<int,int> formats;

		public SheetCopier(Sheet f, WritableSheet t)
			{
			fromSheet = (SheetImpl)f;
			toSheet = (WritableSheetImpl)t;
			workbookSettings = toSheet.getWorkbook().getSettings();
			chartOnly = false;
			}

		public void setColumnFormats(TreeSet<ColumnInfoRecord> cf)
			{
			columnFormats = cf;
			}

		public void setFormatRecords(FormattingRecords fr)
			{
			formatRecords = fr;
			}

		public void setHyperlinks(ArrayList h)
			{
			hyperlinks = h;
			}

		public void setMergedCells(MergedCells mc)
			{
			mergedCells = mc;
			}

		public void setRowBreaks(ArrayList rb)
			{
			rowBreaks = rb;
			}

		public void setColumnBreaks(ArrayList cb)
			{
			columnBreaks = cb;
			}

		public void setSheetWriter(SheetWriter sw)
			{
			sheetWriter = sw;
			}

		public void setDrawings(ArrayList d)
			{
			drawings = d;
			}

		public void setImages(ArrayList i)
			{
			images = i;
			}

		public void setConditionalFormats(ArrayList cf)
			{
			conditionalFormats = cf;
			}

		public void setValidatedCells(ArrayList vc)
			{
			validatedCells = vc;
			}

		public AutoFilter getAutoFilter()
			{
			return autoFilter;
			}

		public DataValidation getDataValidation()
			{
			return dataValidation;
			}

		public ComboBox getComboBox()
			{
			return comboBox;
			}

		public PLSRecord getPLSRecord()
			{
			return plsRecord;
			}

		public bool isChartOnly()
			{
			return chartOnly;
			}

		public ButtonPropertySetRecord getButtonPropertySet()
			{
			return buttonPropertySet;
			}

		/**
		 * Copies a sheet from a read-only version to the writable version.
		 * Performs shallow copies
		 */
		public void copySheet()
			{
			shallowCopyCells();

			// Copy the column info records
			CSharpJExcel.Jxl.Read.Biff.ColumnInfoRecord[] readCirs = fromSheet.getColumnInfos();

			for (int i = 0; i < readCirs.Length; i++)
				{
				CSharpJExcel.Jxl.Read.Biff.ColumnInfoRecord rcir = readCirs[i];
				for (int j = rcir.getStartColumn(); j <= rcir.getEndColumn(); j++)
					{
					ColumnInfoRecord cir = new ColumnInfoRecord(rcir, j,
																formatRecords);
					cir.setHidden(rcir.getHidden());
					columnFormats.Add(cir);
					}
				}

			// Copy the hyperlinks
			Hyperlink[] hls = fromSheet.getHyperlinks();
			for (int i = 0; i < hls.Length; i++)
				{
				WritableHyperlink hr = new WritableHyperlink
				  (hls[i], toSheet);
				hyperlinks.Add(hr);
				}

			// Copy the merged cells
			Range[] merged = fromSheet.getMergedCells();

			for (int i = 0; i < merged.Length; i++)
				mergedCells.add(new SheetRangeImpl((SheetRangeImpl)merged[i], toSheet));

			// Copy the row properties
			try
				{
				CSharpJExcel.Jxl.Read.Biff.RowRecord[] rowprops = fromSheet.getRowProperties();

				for (int i = 0; i < rowprops.Length; i++)
					{
					RowRecord rr = toSheet.getRowRecord(rowprops[i].getRowNumber());
					XFRecord format = rowprops[i].hasDefaultFormat() ?
					  formatRecords.getXFRecord(rowprops[i].getXFIndex()) : null;
					rr.setRowDetails(rowprops[i].getRowHeight(),
									 rowprops[i].matchesDefaultFontHeight(),
									 rowprops[i].isCollapsed(),
									 rowprops[i].getOutlineLevel(),
									 rowprops[i].getGroupStart(),
									 format);
					numRows = System.Math.Max(numRows, rowprops[i].getRowNumber() + 1);
					}
				}
			catch (RowsExceededException e)
				{
				// Handle the rows exceeded exception - this cannot occur since
				// the sheet we are copying from will have a valid number of rows
				Assert.verify(false);
				}

			// Copy the headers and footers
			//    sheetWriter.setHeader(new HeaderRecord(si.getHeader()));
			//    sheetWriter.setFooter(new FooterRecord(si.getFooter()));

			// Copy the page breaks
			int[] rowbreaks = fromSheet.getRowPageBreaks();

			if (rowbreaks != null)
				{
				for (int i = 0; i < rowbreaks.Length; i++)
					rowBreaks.Add(rowbreaks[i]);
				}

			int[] columnbreaks = fromSheet.getColumnPageBreaks();

			if (columnbreaks != null)
				{
				for (int i = 0; i < columnbreaks.Length; i++)
					columnBreaks.Add(columnbreaks[i]);
				}

			// Copy the charts
			sheetWriter.setCharts(fromSheet.getCharts());

			// Copy the drawings
			DrawingGroupObject[] dr = fromSheet.getDrawings();
			for (int i = 0; i < dr.Length; i++)
				{
				if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.Drawing)
					{
					WritableImage wi = new WritableImage
					  (dr[i], toSheet.getWorkbook().getDrawingGroup());
					drawings.Add(wi);
					images.Add(wi);
					}
				else if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.Comment)
					{
					CSharpJExcel.Jxl.Biff.Drawing.Comment c =
					  new CSharpJExcel.Jxl.Biff.Drawing.Comment(dr[i],
												   toSheet.getWorkbook().getDrawingGroup(),
												   workbookSettings);
					drawings.Add(c);

					// Set up the reference on the cell value
					CellValue cv = (CellValue)toSheet.getWritableCell(c.getColumn(),
																	   c.getRow());
					Assert.verify(cv.getCellFeatures() != null);
					cv.getWritableCellFeatures().setCommentDrawing(c);
					}
				else if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.Button)
					{
					CSharpJExcel.Jxl.Biff.Drawing.Button b =
					  new CSharpJExcel.Jxl.Biff.Drawing.Button
					  (dr[i],
					   toSheet.getWorkbook().getDrawingGroup(),
					   workbookSettings);
					drawings.Add(b);
					}
				else if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.ComboBox)
					{
					CSharpJExcel.Jxl.Biff.Drawing.ComboBox cb =
					  new CSharpJExcel.Jxl.Biff.Drawing.ComboBox
					  (dr[i],
					   toSheet.getWorkbook().getDrawingGroup(),
					   workbookSettings);
					drawings.Add(cb);
					}
				else if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.CheckBox)
					{
					CSharpJExcel.Jxl.Biff.Drawing.CheckBox cb =
					  new CSharpJExcel.Jxl.Biff.Drawing.CheckBox
					  (dr[i],
					   toSheet.getWorkbook().getDrawingGroup(),
					   workbookSettings);
					drawings.Add(cb);
					}

				}

			// Copy the data validations
			DataValidation rdv = fromSheet.getDataValidation();
			if (rdv != null)
				{
				dataValidation = new DataValidation(rdv,
													toSheet.getWorkbook(),
													toSheet.getWorkbook(),
													workbookSettings);
				uint objid = dataValidation.getComboBoxObjectId();
				if (objid != 0)
					comboBox = (ComboBox)drawings[(int)objid];
				}

			// Copy the conditional formats
			ConditionalFormat[] cf = fromSheet.getConditionalFormats();
			if (cf.Length > 0)
				{
				for (int i = 0; i < cf.Length; i++)
					conditionalFormats.Add(cf[i]);
				}

			// Get the autofilter
			autoFilter = fromSheet.getAutoFilter();

			// Copy the workspace options
			sheetWriter.setWorkspaceOptions(fromSheet.getWorkspaceOptions());

			// Set a flag to indicate if it contains a chart only
			if (fromSheet.getSheetBof().isChart())
				{
				chartOnly = true;
				sheetWriter.setChartOnly();
				}

			// Copy the environment specific print record
			if (fromSheet.getPLS() != null)
				{
				if (fromSheet.getWorkbookBof().isBiff7())
					{
					//logger.warn("Cannot copy Biff7 print settings record - ignoring");
					}
				else
					{
					plsRecord = new PLSRecord(fromSheet.getPLS());
					}
				}

			// Copy the button property set
			if (fromSheet.getButtonPropertySet() != null)
				{
				buttonPropertySet = new ButtonPropertySetRecord
				  (fromSheet.getButtonPropertySet());
				}

			// Copy the outline levels
			maxRowOutlineLevel = fromSheet.getMaxRowOutlineLevel();
			maxColumnOutlineLevel = fromSheet.getMaxColumnOutlineLevel();
			}

		/**
		 * Copies a sheet from a read-only version to the writable version.
		 * Performs shallow copies
		 */
		public void copyWritableSheet()
			{
			shallowCopyCells();

			/*
			// Copy the column formats
			Iterator cfit = fromWritableSheet.columnFormats.iterator();
			while (cfit.hasNext())
			{
			  ColumnInfoRecord cv = new ColumnInfoRecord
				((ColumnInfoRecord) cfit.next());
			  columnFormats.Add(cv);
			}

			// Copy the merged cells
			Range[] merged = fromWritableSheet.getMergedCells();

			for (int i = 0; i < merged.Length; i++)
			{
			  mergedCells.Add(new SheetRangeImpl((SheetRangeImpl)merged[i], this));
			}

			// Copy the row properties
			try
			{
			  RowRecord[] copyRows = fromWritableSheet.rows;
			  RowRecord row = null;
			  for (int i = 0; i < copyRows.Length ; i++)
			  {
				row = copyRows[i];
        
				if (row != null &&
					(!row.isDefaultHeight() ||
					 row.isCollapsed()))
				{
				  RowRecord rr = getRowRecord(i);
				  rr.setRowDetails(row.getRowHeight(), 
								   row.matchesDefaultFontHeight(),
								   row.isCollapsed(), 
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
			rowBreaks = new ArrayList(fromWritableSheet.rowBreaks);

			// Copy the vertical page breaks
			columnBreaks = new ArrayList(fromWritableSheet.columnBreaks);

			// Copy the data validations
			DataValidation rdv = fromWritableSheet.dataValidation;
			if (rdv != null)
			{
			  dataValidation = new DataValidation(rdv, 
												  workbook,
												  workbook, 
												  workbookSettings);
			}

			// Copy the charts 
			sheetWriter.setCharts(fromWritableSheet.getCharts());

			// Copy the drawings
			DrawingGroupobject[] dr = si.getDrawings();
			for (int i = 0 ; i < dr.Length ; i++)
			{
			  if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.Drawing)
			  {
				WritableImage wi = new WritableImage(dr[i], 
													 workbook.getDrawingGroup());
				drawings.Add(wi);
				images.Add(wi);
			  }

			  // Not necessary to copy the comments, as they will be handled by
			  // the deep copy of the individual cells
			}

			// Copy the workspace options
			sheetWriter.setWorkspaceOptions(fromWritableSheet.getWorkspaceOptions());

			// Copy the environment specific print record
			if (fromWritableSheet.plsRecord != null)
			{
			  plsRecord = new PLSRecord(fromWritableSheet.plsRecord);
			}

			// Copy the button property set
			if (fromWritableSheet.buttonPropertySet != null)
			{
			  buttonPropertySet = new ButtonPropertySetRecord
				(fromWritableSheet.buttonPropertySet);
			}
			*/
			}

		/**
		 * Imports a sheet from a different workbook, doing a deep copy
		 */
		public void importSheet()
			{
			xfRecords = new Dictionary<int,WritableCellFormat>();
			fonts = new Dictionary<int,int>();
			formats = new Dictionary<int,int>();

			deepCopyCells();

			// Copy the column info records
			CSharpJExcel.Jxl.Read.Biff.ColumnInfoRecord[] readCirs = fromSheet.getColumnInfos();

			for (int i = 0; i < readCirs.Length; i++)
				{
				CSharpJExcel.Jxl.Read.Biff.ColumnInfoRecord rcir = readCirs[i];
				for (int j = rcir.getStartColumn(); j <= rcir.getEndColumn(); j++)
					{
					ColumnInfoRecord cir = new ColumnInfoRecord(rcir, j);
					int xfIndex = cir.getXfIndex();
					XFRecord cf = null;
					if (!xfRecords.ContainsKey(xfIndex))
						{
						// TODO: CML -- what does THIS actually achieve unless it has side-effects?
						CellFormat readFormat = fromSheet.getColumnView(j).getFormat();
						WritableCellFormat wcf = copyCellFormat(readFormat);
						}
					else
						cf = xfRecords[xfIndex];

					cir.setCellFormat(cf);
					cir.setHidden(rcir.getHidden());
					columnFormats.Add(cir);
					}
				}

			// Copy the hyperlinks
			Hyperlink[] hls = fromSheet.getHyperlinks();
			for (int i = 0; i < hls.Length; i++)
				{
				WritableHyperlink hr = new WritableHyperlink(hls[i], toSheet);
				hyperlinks.Add(hr);
				}

			// Copy the merged cells
			Range[] merged = fromSheet.getMergedCells();

			for (int i = 0; i < merged.Length; i++)
				mergedCells.add(new SheetRangeImpl((SheetRangeImpl)merged[i], toSheet));

			// Copy the row properties
			try
				{
				CSharpJExcel.Jxl.Read.Biff.RowRecord[] rowprops = fromSheet.getRowProperties();

				for (int i = 0; i < rowprops.Length; i++)
					{
					RowRecord rr = toSheet.getRowRecord(rowprops[i].getRowNumber());
					XFRecord format = null;
					CSharpJExcel.Jxl.Read.Biff.RowRecord rowrec = rowprops[i];
					if (rowrec.hasDefaultFormat())
						{
						if (!xfRecords.ContainsKey(rowrec.getXFIndex()))
							{
							int rownum = rowrec.getRowNumber();
							CellFormat readFormat = fromSheet.getRowView(rownum).getFormat();
							WritableCellFormat wcf = copyCellFormat(readFormat);
							}
						else
							format = xfRecords[rowrec.getXFIndex()];
						}

					rr.setRowDetails(rowrec.getRowHeight(),
									 rowrec.matchesDefaultFontHeight(),
									 rowrec.isCollapsed(),
									 rowrec.getOutlineLevel(),
									 rowrec.getGroupStart(),
									 format);
					numRows = System.Math.Max(numRows, rowprops[i].getRowNumber() + 1);
					}
				}
			catch (RowsExceededException e)
				{
				// Handle the rows exceeded exception - this cannot occur since
				// the sheet we are copying from will have a valid number of rows
				Assert.verify(false);
				}

			// Copy the headers and footers
			//    sheetWriter.setHeader(new HeaderRecord(si.getHeader()));
			//    sheetWriter.setFooter(new FooterRecord(si.getFooter()));

			// Copy the page breaks
			int[] rowbreaks = fromSheet.getRowPageBreaks();

			if (rowbreaks != null)
				{
				for (int i = 0; i < rowbreaks.Length; i++)
					rowBreaks.Add(rowbreaks[i]);
				}

			int[] columnbreaks = fromSheet.getColumnPageBreaks();

			if (columnbreaks != null)
				{
				for (int i = 0; i < columnbreaks.Length; i++)
					columnBreaks.Add(columnbreaks[i]);
				}

			// Copy the charts
			Chart[] fromCharts = fromSheet.getCharts();
			if (fromCharts != null && fromCharts.Length > 0)
				{
				//logger.warn("Importing of charts is not supported");
				/*
				sheetWriter.setCharts(fromSheet.getCharts());
				IndexMapping xfMapping = new IndexMapping(200);
				for (Iterator i = xfRecords.keySet().iterator(); i.hasNext();)
				{
				  Integer key = (Integer) i.next();
				  XFRecord xfmapping = (XFRecord) xfRecords[key);
				  xfMapping.setMapping(key, xfmapping.getXFIndex());
				}

				IndexMapping fontMapping = new IndexMapping(200);
				for (Iterator i = fonts.keySet().iterator(); i.hasNext();)
				{
				  Integer key = (Integer) i.next();
				  Integer fontmap = (Integer) fonts[key);
				  fontMapping.setMapping(key, fontmap);
				}

				IndexMapping formatMapping = new IndexMapping(200);
				for (Iterator i = formats.keySet().iterator(); i.hasNext();)
				{
				  Integer key = (Integer) i.next();
				  Integer formatmap = (Integer) formats[key);
				  formatMapping.setMapping(key, formatmap);
				}

				// Now reuse the rationalization feature on each chart  to
				// handle the new fonts
				for (int i = 0; i < fromCharts.Length ; i++)
				{
				  fromCharts[i].rationalize(xfMapping, fontMapping, formatMapping);
				}
				*/
				}

			// Copy the drawings
			DrawingGroupObject[] dr = fromSheet.getDrawings();

			// Make sure the destination workbook has a drawing group
			// created in it
			if (dr.Length > 0 && toSheet.getWorkbook().getDrawingGroup() == null)
				toSheet.getWorkbook().createDrawingGroup();

			for (int i = 0; i < dr.Length; i++)
				{
				if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.Drawing)
					{
					WritableImage wi = new WritableImage
					  (dr[i].getX(), dr[i].getY(),
					   dr[i].getWidth(), dr[i].getHeight(),
					   dr[i].getImageData());
					toSheet.getWorkbook().addDrawing(wi);
					drawings.Add(wi);
					images.Add(wi);
					}
				else if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.Comment)
					{
					CSharpJExcel.Jxl.Biff.Drawing.Comment c = new CSharpJExcel.Jxl.Biff.Drawing.Comment(dr[i],
												   toSheet.getWorkbook().getDrawingGroup(),
												   workbookSettings);
					drawings.Add(c);

					// Set up the reference on the cell value
					CellValue cv = (CellValue)toSheet.getWritableCell(c.getColumn(),c.getRow());
					Assert.verify(cv.getCellFeatures() != null);
					cv.getWritableCellFeatures().setCommentDrawing(c);
					}
				else if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.Button)
					{
					CSharpJExcel.Jxl.Biff.Drawing.Button b = new CSharpJExcel.Jxl.Biff.Drawing.Button(dr[i],
					   toSheet.getWorkbook().getDrawingGroup(),
					   workbookSettings);
					drawings.Add(b);
					}
				else if (dr[i] is CSharpJExcel.Jxl.Biff.Drawing.ComboBox)
					{
					CSharpJExcel.Jxl.Biff.Drawing.ComboBox cb = new CSharpJExcel.Jxl.Biff.Drawing.ComboBox(dr[i],
					   toSheet.getWorkbook().getDrawingGroup(),
					   workbookSettings);
					drawings.Add(cb);
					}
				}

			// Copy the data validations
			DataValidation rdv = fromSheet.getDataValidation();
			if (rdv != null)
				{
				dataValidation = new DataValidation(rdv,
													toSheet.getWorkbook(),
													toSheet.getWorkbook(),
													workbookSettings);
				uint objid = dataValidation.getComboBoxObjectId();
				if (objid != 0)
					comboBox = (ComboBox)drawings[(int)objid];
				}

			// Copy the workspace options
			sheetWriter.setWorkspaceOptions(fromSheet.getWorkspaceOptions());

			// Set a flag to indicate if it contains a chart only
			if (fromSheet.getSheetBof().isChart())
				{
				chartOnly = true;
				sheetWriter.setChartOnly();
				}

			// Copy the environment specific print record
			if (fromSheet.getPLS() != null)
				{
				if (fromSheet.getWorkbookBof().isBiff7())
					{
					//logger.warn("Cannot copy Biff7 print settings record - ignoring");
					}
				else
					{
					plsRecord = new PLSRecord(fromSheet.getPLS());
					}
				}

			// Copy the button property set
			if (fromSheet.getButtonPropertySet() != null)
				{
				buttonPropertySet = new ButtonPropertySetRecord
				  (fromSheet.getButtonPropertySet());
				}

			importNames();

			// Copy the outline levels
			maxRowOutlineLevel = fromSheet.getMaxRowOutlineLevel();
			maxColumnOutlineLevel = fromSheet.getMaxColumnOutlineLevel();
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
							if (c.getCellFeatures() != null &&
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
				fonts.Add(fontIndex, f.getFontIndex());

				int formatIndex = xfr.getFormatRecord();
				formats.Add(formatIndex, f.getFormatRecord());

				return f;
				}
			catch (NumFormatRecordsException e)
				{
				//logger.warn("Maximum number of format records exceeded.  Using default format.");

				return WritableWorkbook.NORMAL_STYLE;
				}
			}

		/**
		 * Imports any names defined on the source sheet to the destination workbook
		 */
		private void importNames()
			{
			WorkbookParser fromWorkbook = (WorkbookParser)fromSheet.getWorkbook();
			WritableWorkbook toWorkbook = toSheet.getWorkbook();
			int fromSheetIndex = fromWorkbook.getIndex(fromSheet);
			CSharpJExcel.Jxl.Read.Biff.NameRecord[] nameRecords = fromWorkbook.getNameRecords();
			string[] names = toWorkbook.getRangeNames();

			for (int i = 0; i < nameRecords.Length; i++)
				{
				CSharpJExcel.Jxl.Read.Biff.NameRecord.NameRange[] nameRanges = nameRecords[i].getRanges();

				for (int j = 0; j < nameRanges.Length; j++)
					{
					int nameSheetIndex = fromWorkbook.getExternalSheetIndex(nameRanges[j].getExternalSheet());

					if (fromSheetIndex == nameSheetIndex)
						{
						string name = nameRecords[i].getName();
						if (System.Array.BinarySearch(names, name) < 0)
							{
							toWorkbook.addNameArea(name,
												   toSheet,
												   nameRanges[j].getFirstColumn(),
												   nameRanges[j].getFirstRow(),
												   nameRanges[j].getLastColumn(),
												   nameRanges[j].getLastRow());
							}
						else
							{
							//logger.warn("Named range " + name + " is already present in the destination workbook");
							}

						}
					}
				}
			}

		/**
		 * Gets the number of rows - allows for the case where formatting has
		 * been applied to rows, even though the row has no data
		 *
		 * @return the number of rows
		 */
		public int getRows()
			{
			return numRows;
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

