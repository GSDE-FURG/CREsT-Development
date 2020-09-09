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

using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl.Write.Biff;
using System.Collections;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * A container for the list of fonts used in this workbook
	 */
	public class Fonts
		{
		/**
		 * The list of fonts
		 */
		private ArrayList fonts;

		/**
		 * The default number of fonts
		 */
		private const int numDefaultFonts = 4;

		/**
		 * Constructor
		 */
		public Fonts()
			{
			fonts = new ArrayList();
			}

		/**
		 * Adds a font record to this workbook.  If the FontRecord passed in has not
		 * been initialized, then its font index is determined based upon the size
		 * of the fonts list.  The FontRecord's initialized method is called, and
		 * it is added to the list of fonts.
		 *
		 * @param f the font to add
		 */
		public void addFont(FontRecord f)
			{
			if (!f.isInitialized())
				{
				int pos = fonts.Count;

				// Remember that the pos with index 4 is skipped
				if (pos >= 4)
					pos++;

				f.initialize(pos);
				fonts.Add(f);
				}
			}

		/**
		 * Used by FormattingRecord for retrieving the fonts for the
		 * hardcoded styles
		 *
		 * @param index the index of the font to return
		 * @return the font with the specified font index
		 */
		public FontRecord getFont(int index)
			{
			// remember to allow for the fact that font index 4 is not used
			if (index > 4)
				{
				index--;
				}

			return (FontRecord)fonts[index];
			}

		/**
		 * Writes out the list of fonts
		 *
		 * @param outputFile the compound file to write the data to
		 * @exception IOException
		 */
		public void write(File outputFile)
			{
			foreach (FontRecord font in fonts)
				outputFile.write(font);
			}

		/**
		 * Rationalizes all the fonts, removing any duplicates
		 *
		 * @return the mappings between new indexes and old ones
		 */
		public IndexMapping rationalize()
			{
			IndexMapping mapping = new IndexMapping(fonts.Count + 1);
			// allow for skipping record 4

			ArrayList newfonts = new ArrayList();
			int numremoved = 0;

			// Preserve the default fonts
			for (int i = 0; i < numDefaultFonts; i++)
				{
				FontRecord fr = (FontRecord)fonts[i];
				newfonts.Add(fr);
				mapping.setMapping(fr.getFontIndex(),fr.getFontIndex());
				}

			// Now do the rest
			bool duplicate = false;
			for (int i = numDefaultFonts; i < fonts.Count; i++)
				{
				FontRecord fr = (FontRecord)fonts[i];

				// Compare to all the fonts currently on the list
				duplicate = false;
				foreach (FontRecord fr2 in newfonts)
					{
					if (fr.Equals(fr2))
						{
						duplicate = true;
						mapping.setMapping(fr.getFontIndex(),mapping.getNewIndex(fr2.getFontIndex()));
						numremoved++;

						break;
						}
					}

				if (!duplicate)
					{
					// Add to the new list
					newfonts.Add(fr);
					int newindex = fr.getFontIndex() - numremoved;
					Assert.verify(newindex > 4);
					mapping.setMapping(fr.getFontIndex(),newindex);
					}
				}

			// Iterate through the remaining fonts, updating all the font indices
			foreach (FontRecord fr in newfonts)
				fr.initialize(mapping.getNewIndex(fr.getFontIndex()));

			fonts = newfonts;

			return mapping;
			}
		}
	}