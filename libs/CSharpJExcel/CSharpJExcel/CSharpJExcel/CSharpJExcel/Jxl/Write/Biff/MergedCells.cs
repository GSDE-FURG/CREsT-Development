/*********************************************************************
 *
 *      Copyright (C) 2003 Andrew Khan
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
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * Contains all the merged cells, and the necessary logic for checking
	 * for intersections and for handling very large amounts of merging
	 */
	public class MergedCells
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(MergedCells.class);

		/**
		 * The list of merged cells
		 */
		private ArrayList ranges;

		/**
		 * The sheet containing the cells
		 */
		private WritableSheet sheet;

		/** 
		 * The maximum number of ranges per sheet
		 */
		private const int maxRangesPerSheet = 1020;

		/**
		 * Constructor
		 */
		public MergedCells(WritableSheet ws)
			{
			ranges = new ArrayList();
			sheet = ws;
			}

		/**
		 * Adds the range to the list of merged cells.  Does no checking
		 * at this stage
		 *
		 * @param range the range to add
		 */
		public void add(Range r)
			{
			ranges.Add(r);
			}

		/**
		 * Used to adjust the merged cells following a row insertion
		 */
		public void insertRow(int row)
			{
			// Adjust any merged cells
			foreach (SheetRangeImpl sr in ranges)
				sr.insertRow(row);
			}

		/**
		 * Used to adjust the merged cells following a column insertion
		 */
		public void insertColumn(int col)
			{
			foreach (SheetRangeImpl sr in ranges)
				sr.insertColumn(col);
			}

		/**
		 * Used to adjust the merged cells following a column removal
		 */
		public void removeColumn(int col)
			{
			foreach (SheetRangeImpl sr in ranges)
				{
				if (sr.getTopLeft().getColumn() == col &&
					sr.getBottomRight().getColumn() == col)
					{
					// The column with the merged cells on has been removed, so get
					// rid of it from the list
					ranges.Remove(sr);
//					i.Remove();
					}
				else
					sr.removeColumn(col);
				}
			}

		/**
		 * Used to adjust the merged cells following a row removal
		 */
		public void removeRow(int row)
			{
			foreach (SheetRangeImpl sr in ranges)
				{
				if (sr.getTopLeft().getRow() == row &&
					sr.getBottomRight().getRow() == row)
					{
					// The row with the merged cells on has been removed, so get
					// rid of it from the list
					ranges.Remove(sr);
//					i.Remove();
					}
				else
					sr.removeRow(row);
				}
			}

		/**
		 * Gets the cells which have been merged on this sheet
		 *
		 * @return an array of range objects
		 */
		public Range[] getMergedCells()
			{
			Range[] cells = new Range[ranges.Count];

			for (int i = 0; i < cells.Length; i++)
				{
				cells[i] = (Range)ranges[i];
				}

			return cells;
			}

		/**
		 * Unmerges the specified cells.  The Range passed in should be one that
		 * has been previously returned as a result of the getMergedCells method
		 *
		 * @param r the range of cells to unmerge
		 */
		public void unmergeCells(Range r)
			{
			int index = ranges.IndexOf(r);
			if (index != -1)
				ranges.Remove(index);
			}

		/**
		 * Called prior to writing out in order to check for intersections
		 */
		private void checkIntersections()
			{
			ArrayList newcells = new ArrayList(ranges.Count);

			foreach (SheetRangeImpl r in ranges)
				{
				// Check that the range doesn't intersect with any existing range
				bool intersects = false;
				foreach (SheetRangeImpl range in newcells)
					{
					if (range.intersects(r))
						{
						//logger.warn("Could not merge cells " + r +
						//            " as they clash with an existing set of merged cells.");

						intersects = true;
						break;
						}
					}

				if (!intersects)
					newcells.Add(r);
				}

			ranges = newcells;
			}

		/**
		 * Checks the cell ranges for intersections, or if the merged cells
		 * contains more than one item of data
		 */
		private void checkRanges()
			{
			try
				{
				SheetRangeImpl range = null;

				// Check all the ranges to make sure they only contain one entry
				for (int i = 0; i < ranges.Count; i++)
					{
					range = (SheetRangeImpl)ranges[i];

					// Get the cell in the top left
					Cell tl = range.getTopLeft();
					Cell br = range.getBottomRight();
					bool found = false;

					for (int c = tl.getColumn(); c <= br.getColumn(); c++)
						{
						for (int r = tl.getRow(); r <= br.getRow(); r++)
							{
							Cell cell = sheet.getCell(c, r);
							if (cell.getType() != CellType.EMPTY)
								{
								if (!found)
									{
									found = true;
									}
								else
									{
									//logger.warn("Range " + range +
									//            " contains more than one data cell.  " +
									//            "Setting the other cells to blank.");
									Blank b = new Blank(c, r);
									sheet.addCell(b);
									}
								}
							}
						}
					}
				}
			catch (WriteException e)
				{
				// This should already have been checked - bomb out
				Assert.verify(false);
				}
			}

		/**
		 * @exception IOException
		 */
		public void write(File outputFile)
			{
			if (ranges.Count == 0)
				{
				return;
				}

			WorkbookSettings ws =
			  ((WritableSheetImpl)sheet).getWorkbookSettings();

			if (!ws.getMergedCellCheckingDisabled())
				{
				checkIntersections();
				checkRanges();
				}

			// If they will all fit into one record, then create a single
			// record, write them and get out
			if (ranges.Count < maxRangesPerSheet)
				{
				MergedCellsRecord mcr = new MergedCellsRecord(ranges);
				outputFile.write(mcr);
				return;
				}

			int numRecordsRequired = ranges.Count / maxRangesPerSheet + 1;
			int pos = 0;

			for (int i = 0; i < numRecordsRequired; i++)
				{
				int numranges = System.Math.Min(maxRangesPerSheet, ranges.Count - pos);

				ArrayList cells = new ArrayList(numranges);
				for (int j = 0; j < numranges; j++)
					{
					cells.Add(ranges[pos + j]);
					}

				MergedCellsRecord mcr = new MergedCellsRecord(cells);
				outputFile.write(mcr);

				pos += numranges;
				}
			}
		}
	}
