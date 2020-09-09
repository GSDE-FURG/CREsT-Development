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

namespace CSharpJExcel.Jxl.Format
	{
	/**
	 * Interface for cell formats
	 */
	public interface CellFormat
		{
		/**
		 * Gets the format used by this format
		 * 
		 * @return the format
		 */
		Format getFormat();

		/**
		 * Gets the font information used by this format
		 *
		 * @return the font
		 */
		Font getFont();

		/**
		 * Gets whether or not the contents of this cell are wrapped
		 * 
		 * @return TRUE if this cell's contents are wrapped, FALSE otherwise
		 */
		bool getWrap();

		/**
		 * Gets the horizontal cell alignment
		 *
		 * @return the alignment
		 */
		Alignment getAlignment();

		/**
		 * Gets the vertical cell alignment
		 *
		 * @return the alignment
		 */
		VerticalAlignment getVerticalAlignment();

		/**
		 * Gets the orientation
		 *
		 * @return the orientation
		 */
		Orientation getOrientation();

		/**
		 * Gets the line style for the given cell border
		 * If a border type of ALL or NONE is specified, then a line style of
		 * NONE is returned
		 *
		 * @param border the cell border we are interested in
		 * @return the line style of the specified border
		 */
		BorderLineStyle getBorder(Border border);

		/**
		 * Gets the line style for the given cell border
		 * If a border type of ALL or NONE is specified, then a line style of
		 * NONE is returned
		 *
		 * @param border the cell border we are interested in
		 * @return the line style of the specified border
		 */
		BorderLineStyle getBorderLine(Border border);

		/**
		 * Gets the colour for the given cell border
		 * If a border type of ALL or NONE is specified, then a line style of
		 * NONE is returned
		 * If the specified cell does not have an associated line style, then
		 * the colour the line would be is still returned
		 *
		 * @param border the cell border we are interested in
		 * @return the line style of the specified border
		 */
		Colour getBorderColour(Border border);

		/**
		 * Determines if this cell format has any borders at all.  Used to
		 * set the new borders when merging a group of cells
		 *
		 * @return TRUE if this cell has any borders, FALSE otherwise
		 */
		bool hasBorders();

		/**
		 * Gets the background colour used by this cell
		 *
		 * @return the foreground colour
		 */
		Colour getBackgroundColour();

		/**
		 * Gets the pattern used by this cell format
		 *
		 * @return the background pattern
		 */
		Pattern getPattern();

		/**
		 * Gets the indentation of the cell text
		 *
		 * @return the indentation
		 */
		int getIndentation();

		/**
		 * Gets the shrink to fit flag
		 *
		 * @return TRUE if this format is shrink to fit, FALSE otherise
		 */
		bool isShrinkToFit();

		/**
		 * Accessor for whether a particular cell is locked
		 *
		 * @return TRUE if this cell is locked, FALSE otherwise
		 */
		bool isLocked();
		}
	}
