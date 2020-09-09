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


namespace CSharpJExcel.Jxl
	{
	/**
	 * Class which represents an Excel header or footer.
	 */
	public sealed class HeaderFooter : CSharpJExcel.Jxl.Biff.HeaderFooter
		{
		/**
		 * The contents - a simple wrapper around a string buffer
		 */
		public new class Contents : CSharpJExcel.Jxl.Biff.HeaderFooter.Contents
			{
			/**
			 * The constructor
			 */
			public Contents()
				: base()
				{
				}

			/**
			 * Constructor used when reading worksheets.  The string contains all
			 * the formatting (but not alignment characters
			 *
			 * @param s the format string
			 */
			public Contents(string s)
				: base(s)
				{
				}

			/**
			 * Copy constructor
			 *
			 * @param copy the contents to copy
			 */
			public Contents(Contents copy)
				: base(copy)
				{
				}

			/**
			 * Appends the text to the string buffer
			 *
			 * @param txt the text to append
			 */
			public override void append(string txt)
				{
				base.append(txt);
				}

			/**
			 * Turns bold printing on or off. Bold printing
			 * is initially off. Text subsequently appended to
			 * this object will be bolded until this method is
			 * called again.
			 */
			public override void toggleBold()
				{
				base.toggleBold();
				}

			/**
			 * Turns underline printing on or off. Underline printing
			 * is initially off. Text subsequently appended to
			 * this object will be underlined until this method is
			 * called again.
			 */
			public override void toggleUnderline()
				{
				base.toggleUnderline();
				}

			/**
			 * Turns italics printing on or off. Italics printing
			 * is initially off. Text subsequently appended to
			 * this object will be italicized until this method is
			 * called again.
			 */
			public override void toggleItalics()
				{
				base.toggleItalics();
				}

			/**
			 * Turns strikethrough printing on or off. Strikethrough printing
			 * is initially off. Text subsequently appended to
			 * this object will be striked out until this method is
			 * called again.
			 */
			public override void toggleStrikethrough()
				{
				base.toggleStrikethrough();
				}

			/**
			 * Turns double-underline printing on or off. Double-underline printing
			 * is initially off. Text subsequently appended to
			 * this object will be double-underlined until this method is
			 * called again.
			 */
			public override void toggleDoubleUnderline()
				{
				base.toggleDoubleUnderline();
				}

			/**
			 * Turns superscript printing on or off. Superscript printing
			 * is initially off. Text subsequently appended to
			 * this object will be superscripted until this method is
			 * called again.
			 */
			public override void toggleSuperScript()
				{
				base.toggleSuperScript();
				}

			/**
			 * Turns subscript printing on or off. Subscript printing
			 * is initially off. Text subsequently appended to
			 * this object will be subscripted until this method is
			 * called again.
			 */
			public override void toggleSubScript()
				{
				base.toggleSubScript();
				}

			/**
			 * Turns outline printing on or off (Macintosh only).
			 * Outline printing is initially off. Text subsequently appended
			 * to this object will be outlined until this method is
			 * called again.
			 */
			public override void toggleOutline()
				{
				base.toggleOutline();
				}

			/**
			 * Turns shadow printing on or off (Macintosh only).
			 * Shadow printing is initially off. Text subsequently appended
			 * to this object will be shadowed until this method is
			 * called again.
			 */
			public override void toggleShadow()
				{
				base.toggleShadow();
				}

			/**
			 * Sets the font of text subsequently appended to this
			 * object.. Previously appended text is not affected.
			 * <p/>
			 * <strong>Note:</strong> no checking is performed to
			 * determine if fontName is a valid font.
			 *
			 * @param fontName name of the font to use
			 */
			public override void setFontName(string fontName)
				{
				base.setFontName(fontName);
				}

			/**
			 * Sets the font size of text subsequently appended to this
			 * object. Previously appended text is not affected.
			 * <p/>
			 * Valid point sizes are between 1 and 99 (inclusive). If
			 * size is outside this range, this method returns false
			 * and does not change font size. If size is within this
			 * range, the font size is changed and true is returned.
			 *
			 * @param size The size in points. Valid point sizes are
			 * between 1 and 99 (inclusive).
			 * @return true if the font size was changed, false if font
			 * size was not changed because 1 > size > 99.
			 */
			public override bool setFontSize(int size)
				{
				return base.setFontSize(size);
				}

			/**
			 * Appends the page number
			 */
			public override void appendPageNumber()
				{
				base.appendPageNumber();
				}

			/**
			 * Appends the total number of pages
			 */
			public override void appendTotalPages()
				{
				base.appendTotalPages();
				}

			/**
			 * Appends the current date
			 */
			public override void appendDate()
				{
				base.appendDate();
				}

			/**
			 * Appends the current time
			 */
			public override void appendTime()
				{
				base.appendTime();
				}

			/**
			 * Appends the workbook name
			 */
			public override void appendWorkbookName()
				{
				base.appendWorkbookName();
				}

			/**
			 * Appends the worksheet name
			 */
			public override void appendWorkSheetName()
				{
				base.appendWorkSheetName();
				}

			/**
			 * Clears the contents of this portion
			 */
			public override void clear()
				{
				base.clear();
				}

			/**
			 * Queries if the contents are empty
			 *
			 * @return TRUE if the contents are empty, FALSE otherwise
			 */
			public override bool empty()
				{
				return base.empty();
				}
			}

		/**
		 * Default constructor.
		 */
		public HeaderFooter()
			: base()
			{
			}

		/**
		 * Copy constructor
		 *
		 * @param hf the item to copy
		 */
		public HeaderFooter(HeaderFooter hf)
			: base(hf)
			{
			}

		/**
		 * Constructor used when reading workbooks to separate the left, right
		 * a central part of the strings into their constituent parts
		 *
		 * @param s the header string
		 */
		public HeaderFooter(string s)
			: base(s)
			{
			}

		/**
		 * Retrieves a <code>string</code>ified
		 * version of this object
		 *
		 * @return the header string
		 */
		public override string ToString()
			{
			return base.ToString();
			}

		/**
		 * Accessor for the contents which appear on the right hand side of the page
		 *
		 * @return the right aligned contents
		 */
		public Contents getRight()
			{
			return (Contents)base.getRightText();
			}

		/**
		 * Accessor for the contents which in the centre of the page
		 *
		 * @return the centrally  aligned contents
		 */
		public Contents getCentre()
			{
			return (Contents)base.getCentreText();
			}

		/**
		 * Accessor for the contents which appear on the left hand side of the page
		 *
		 * @return the left aligned contents
		 */
		public Contents getLeft()
			{
			return (Contents)base.getLeftText();
			}

		/**
		 * Clears the contents of the header/footer
		 */
		public override void clear()
			{
			base.clear();
			}

		/**
		 * Creates internal class of the appropriate type
		 *
		 * @return the created contents
		 */
		protected override CSharpJExcel.Jxl.Biff.HeaderFooter.Contents createContents()
			{
			return new Contents();
			}

		/**
		 * Creates internal class of the appropriate type
		 *
		 * @param s the string to create the contents
		 * @return the created contents
		 */
		protected override CSharpJExcel.Jxl.Biff.HeaderFooter.Contents createContents(string s)
			{
			return new Contents(s);
			}

		/**
		 * Creates internal class of the appropriate type
		 *
		 * @param c the contents to copy
		 * @return the new contents
		 */
		protected override CSharpJExcel.Jxl.Biff.HeaderFooter.Contents createContents(CSharpJExcel.Jxl.Biff.HeaderFooter.Contents c)
			{
			return new Contents((Contents)c);
			}
		}
	}
