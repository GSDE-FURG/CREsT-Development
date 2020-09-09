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
	 * Interface which exposes the user font display information to the user
	 */
	public interface Font
		{
		/**
		 * Gets the name of this font
		 *
		 * @return the name of this font
		 */
		string getName();

		/**
		 * Gets the point size for this font, if the font hasn't been initialized
		 * 
		 * @return the point size
		 */
		int getPointSize();

		/**
		 * Gets the bold weight for this font
		 * 
		 * @return the bold weight for this font
		 */
		int getBoldWeight();

		/**
		 * Returns the italic flag
		 * 
		 * @return TRUE if this font is italic, FALSE otherwise
		 */
		bool isItalic();

		/**
		 * Returns the strike-out flag
		 *
		 * @return TRUE if this font is struck-out, FALSE otherwise
		 */
		bool isStruckout();

		/**
		 * Gets the underline style for this font
		 * 
		 * @return the underline style
		 */
		UnderlineStyle getUnderlineStyle();

		/**
		 * Gets the colour for this font
		 * 
		 * @return the colour
		 */
		Colour getColour();

		/**
		 * Gets the script style
		 *
		 * @return the script style
		 */
		ScriptStyle getScriptStyle();
		}
	}

