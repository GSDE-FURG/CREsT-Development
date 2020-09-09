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

using System.Text;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * A dummy implementation used for typing information when tokens
	 * are read when parsing strings.  These are then stored by the parser before
	 * being re-stored as the appropriate RPN syntactic equivalent
	 */
	class StringParseItem : ParseItem
		{
		/**
		 * Constructor
		 */
		protected StringParseItem()
			{
			}

		/**
		 * Gets the string representation of this item.  Does nothing here
		 *
		 * @param buf
		 */
		public override void getString(StringBuilder buf)
			{
			}

		/**
		 * Gets the token representation of this item in RPN.  Does nothing here
		 *
		 * @return the bytes applicable to this formula
		 */
		public override byte[] getBytes()
			{
			return new byte[0];
			}

		/**
		 * Default behaviour is to do nothing
		 *
		 * @param colAdjust the amount to add on to each relative cell reference
		 * @param rowAdjust the amount to add on to each relative row reference
		 */
		public override void adjustRelativeCellReferences(int colAdjust, int rowAdjust)
			{
			}

		/**
		 * Default behaviour is to do nothing
		 *
		 * @param sheetIndex the sheet on which the column was inserted
		 * @param col the column number which was inserted
		 * @param currentSheet TRUE if this formula is on the sheet in which the
		 * column was inserted, FALSE otherwise
		 */
		public override void columnInserted(int sheetIndex,int col,bool currentSheet)
			{
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
		public override void columnRemoved(int sheetIndex,int col,bool currentSheet)
			{
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
		public override void rowInserted(int sheetIndex,int row,bool currentSheet)
			{
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
		public override void rowRemoved(int sheetIndex,int row,bool currentSheet)
			{
			}

		/**
		 * If this formula was on an imported sheet, check that
		 * cell references to another sheet are warned appropriately
		 * Does nothing
		 */
		public override void handleImportedCellReferences()
			{
			}
		}
	}