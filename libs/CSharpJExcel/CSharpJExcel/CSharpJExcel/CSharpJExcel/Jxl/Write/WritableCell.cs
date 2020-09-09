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

using CSharpJExcel.Jxl.Format;


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * The interface for all writable cells
	 */
	public interface WritableCell : Cell
		{
		/**
		 * Sets the cell format for this cell
		 *
		 * @param cf the cell format
		 */
		void setCellFormat(CellFormat cf);

		/**
		 * A deep copy.  The returned cell still needs to be added to the sheet.
		 * By not automatically adding the cell to the sheet, the client program
		 * may change certain attributes, such as the value or the format
		 *
		 * @param col the column which the new cell will occupy
		 * @param row the row which the new cell will occupy
		 * @return  a copy of this cell, which can then be added to the sheet
		 */
		WritableCell copyTo(int col, int row);

		/**
		 * Accessor for the cell features
		 *
		 * @return the cell features or NULL if this cell doesn't have any
		 */
		WritableCellFeatures getWritableCellFeatures();

		/**
		 * Sets the cell features
		 *
		 * @param cf the cell features
		 */
		void setCellFeatures(WritableCellFeatures cf);
		}
	}

