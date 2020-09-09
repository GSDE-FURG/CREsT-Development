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

using System.Text;
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * A nested class to hold range information
	 */
	public class Area3d : Operand,ParsedThing
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(Area3d.class);

		/**
		 * The sheet
		 */
		private int sheet;

		/**
		 * The first column
		 */
		private int columnFirst;

		/**
		 * The first row
		 */
		private int rowFirst;

		/**
		 * The last column
		 */
		private int columnLast;

		/**
		 * The last row
		 */
		private int rowLast;

		/**
		 * Indicates whether the first column is relative
		 */
		private bool columnFirstRelative;

		/**
		 * Indicates whether the first row is relative
		 */
		private bool rowFirstRelative;

		/**
		 * Indicates whether the last column is relative
		 */
		private bool columnLastRelative;

		/**
		 * Indicates whether the last row is relative
		 */
		private bool rowLastRelative;

		/**
		 * A handle to the workbook
		 */
		private ExternalSheet workbook;

		/**
		 * Constructor
		 *
		 * @param es the external sheet
		 */
		public Area3d(ExternalSheet es)
			{
			workbook = es;
			}

		/**
		 * Constructor invoked when parsing a string formula
		 *
		 * @param s the string to parse
		 * @param es the external sheet
		 * @exception FormulaException
		 */
		public Area3d(string s, ExternalSheet es)
			{
			workbook = es;
			int seppos = s.LastIndexOf(":");
			Assert.verify(seppos != -1);
			string endcell = s.Substring(seppos + 1);

			// Get the the start cell details
			int sep = s.IndexOf('!');
			string cellString = s.Substring(sep + 1,seppos);
			columnFirst = CellReferenceHelper.getColumn(cellString);
			rowFirst = CellReferenceHelper.getRow(cellString);

			// Get the sheet index
			string sheetName = s.Substring(0,sep);

			// Remove single quotes, if they exist
			if (sheetName[0] == '\'' &&
				sheetName[sheetName.Length - 1] == '\'')
				{
				sheetName = sheetName.Substring(1,sheetName.Length - 1);
				}

			sheet = es.getExternalSheetIndex(sheetName);

			if (sheet < 0)
				{
				throw new FormulaException(FormulaException.SHEET_REF_NOT_FOUND,
										   sheetName);
				}

			// Get the last cell index
			columnLast = CellReferenceHelper.getColumn(endcell);
			rowLast = CellReferenceHelper.getRow(endcell);

			columnFirstRelative = true;
			rowFirstRelative = true;
			columnLastRelative = true;
			rowLastRelative = true;
			}

		/**
		 * Accessor for the first column
		 *
		 * @return the first column
		 */
		public int getFirstColumn()
			{
			return columnFirst;
			}

		/**
		 * Accessor for the first row
		 *
		 * @return the first row
		 */
		public int getFirstRow()
			{
			return rowFirst;
			}

		/**
		 * Accessor for the last column
		 *
		 * @return the last column
		 */
		public int getLastColumn()
			{
			return columnLast;
			}

		/**
		 * Accessor for the last row
		 *
		 * @return the last row
		 */
		public int getLastRow()
			{
			return rowLast;
			}

		/**
		 * Reads the ptg data from the array starting at the specified position
		 *
		 * @param data the RPN array
		 * @param pos the current position in the array, excluding the ptg identifier
		 * @return the number of bytes read
		 */
		public int read(byte[] data,int pos)
			{
			sheet = IntegerHelper.getInt(data[pos],data[pos + 1]);
			rowFirst = IntegerHelper.getInt(data[pos + 2],data[pos + 3]);
			rowLast = IntegerHelper.getInt(data[pos + 4],data[pos + 5]);
			int columnMask = IntegerHelper.getInt(data[pos + 6],data[pos + 7]);
			columnFirst = columnMask & 0x00ff;
			columnFirstRelative = ((columnMask & 0x4000) != 0);
			rowFirstRelative = ((columnMask & 0x8000) != 0);
			columnMask = IntegerHelper.getInt(data[pos + 8],data[pos + 9]);
			columnLast = columnMask & 0x00ff;
			columnLastRelative = ((columnMask & 0x4000) != 0);
			rowLastRelative = ((columnMask & 0x8000) != 0);

			return 10;
			}

		/**
		 * Gets the string version of this area
		 *
		 * @param buf the area to populate
		 */
		public override void getString(StringBuilder buf)
			{
			CellReferenceHelper.getCellReference
			  (sheet,columnFirst,rowFirst,workbook,buf);
			buf.Append(':');
			CellReferenceHelper.getCellReference(columnLast,rowLast,buf);
			}

		/**
		 * Gets the token representation of this item in RPN
		 *
		 * @return the bytes applicable to this formula
		 */
		public override byte[] getBytes()
			{
			byte[] data = new byte[11];
			data[0] = Token.AREA3D.getCode();

			IntegerHelper.getTwoBytes(sheet,data,1);

			IntegerHelper.getTwoBytes(rowFirst,data,3);
			IntegerHelper.getTwoBytes(rowLast,data,5);

			int grcol = columnFirst;

			// Set the row/column relative bits if applicable
			if (rowFirstRelative)
				grcol |= 0x8000;

			if (columnFirstRelative)
				grcol |= 0x4000;

			IntegerHelper.getTwoBytes(grcol,data,7);

			grcol = columnLast;

			// Set the row/column relative bits if applicable
			if (rowLastRelative)
				grcol |= 0x8000;

			if (columnLastRelative)
				grcol |= 0x4000;

			IntegerHelper.getTwoBytes(grcol,data,9);

			return data;
			}

		/**
		 * Adjusts all the relative cell references in this formula by the
		 * amount specified.  Used when copying formulas
		 *
		 * @param colAdjust the amount to add on to each relative cell reference
		 * @param rowAdjust the amount to add on to each relative row reference
		 */
		public override void adjustRelativeCellReferences(int colAdjust, int rowAdjust)
			{
			if (columnFirstRelative)
				{
				columnFirst += colAdjust;
				}

			if (columnLastRelative)
				{
				columnLast += colAdjust;
				}

			if (rowFirstRelative)
				{
				rowFirst += rowAdjust;
				}

			if (rowLastRelative)
				{
				rowLast += rowAdjust;
				}
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the column was inserted
		 * @param col the column number which was inserted
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public override void columnInserted(int sheetIndex, int col, bool currentSheet)
			{
			if (sheetIndex != sheet)
				{
				return;
				}

			if (columnFirst >= col)
				{
				columnFirst++;
				}

			if (columnLast >= col)
				{
				columnLast++;
				}
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the column was removed
		 * @param col the column number which was removed
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public override void columnRemoved(int sheetIndex, int col, bool currentSheet)
			{
			if (sheetIndex != sheet)
				{
				return;
				}

			if (col < columnFirst)
				{
				columnFirst--;
				}

			if (col <= columnLast)
				{
				columnLast--;
				}
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the row was inserted
		 * @param row the row number which was inserted
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public override void rowInserted(int sheetIndex, int row, bool currentSheet)
			{
			if (sheetIndex != sheet)
				{
				return;
				}

			if (rowLast == 0xffff)
				{
				// area applies to the whole column, so nothing to do
				return;
				}

			if (row <= rowFirst)
				{
				rowFirst++;
				}

			if (row <= rowLast)
				{
				rowLast++;
				}
			}

		/**
		 * Called when a column is inserted on the specified sheet.  Tells
		 * the formula  parser to update all of its cell references beyond this
		 * column
		 *
		 * @param sheetIndex the sheet on which the row was removed
		 * @param row the row number which was removed
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public override void rowRemoved(int sheetIndex, int row, bool currentSheet)
			{
			if (sheetIndex != sheet)
				{
				return;
				}

			if (rowLast == 0xffff)
				{
				// area applies to the whole column, so nothing to do
				return;
				}

			if (row < rowFirst)
				{
				rowFirst--;
				}

			if (row <= rowLast)
				{
				rowLast--;
				}
			}

		/**
		 * Used by subclasses columns/row range to set the range information
		 *
		 * @param sht the sheet containing the area
		 * @param colFirst the first column
		 * @param colLast the last column
		 * @param rwFirst the first row
		 * @param rwLast the last row
		 * @param colFirstRel flag indicating whether the first column is relative
		 * @param colLastRel flag indicating whether the last column is relative
		 * @param rowFirstRel flag indicating whether the first row is relative
		 * @param rowLastRel flag indicating whether the last row is relative
		 */
		protected void setRangeData(int sht,
									int colFirst,
									int colLast,
									int rwFirst,
									int rwLast,
									bool colFirstRel,
									bool colLastRel,
									bool rowFirstRel,
									bool rowLastRel)
			{
			sheet = sht;
			columnFirst = colFirst;
			columnLast = colLast;
			rowFirst = rwFirst;
			rowLast = rwLast;
			columnFirstRelative = colFirstRel;
			columnLastRelative = colLastRel;
			rowFirstRelative = rowFirstRel;
			rowLastRelative = rowLastRel;
			}

		/**
		 * If this formula was on an imported sheet, check that
		 * cell references to another sheet are warned appropriately
		 */
		public override void handleImportedCellReferences()
			{
			setInvalid();
			}
		}
	}
