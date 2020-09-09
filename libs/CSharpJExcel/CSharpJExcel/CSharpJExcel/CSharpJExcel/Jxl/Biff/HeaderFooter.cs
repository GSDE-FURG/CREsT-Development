/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan, Eric Jung
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
using System.Text;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Class which represents an Excel header or footer. Information for this
	 * class came from Microsoft Knowledge Base Article 142136 
	 * (previously Q142136).
	 *
	 * This class encapsulates three internal structures representing the header
	 * or footer contents which appear on the left, right or central part of the 
	 * page
	 */
	public abstract class HeaderFooter
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(HeaderFooter.class);

		// Codes to format text

		/**
		 * Turns bold printing on or off
		 */
		private const string BOLD_TOGGLE = "&B";

		/**
		 * Turns underline printing on or off
		 */
		private const string UNDERLINE_TOGGLE = "&U";

		/**
		 * Turns italic printing on or off
		 */
		private const string ITALICS_TOGGLE = "&I";

		/**
		 * Turns strikethrough printing on or off
		 */
		private const string STRIKETHROUGH_TOGGLE = "&S";

		/**
		 * Turns double-underline printing on or off
		 */
		private const string DOUBLE_UNDERLINE_TOGGLE = "&E";

		/**
		 * Turns superscript printing on or off
		 */
		private const string SUPERSCRIPT_TOGGLE = "&X";

		/**
		 * Turns subscript printing on or off
		 */
		private const string SUBSCRIPT_TOGGLE = "&Y";

		/**
		 * Turns outline printing on or off (Macintosh only)
		 */
		private const string OUTLINE_TOGGLE = "&O";

		/**
		 * Turns shadow printing on or off (Macintosh only)
		 */
		private const string SHADOW_TOGGLE = "&H";

		/**
		 * Left-aligns the characters that follow
		 */
		private const string LEFT_ALIGN = "&L";

		/**
		 * Centres the characters that follow
		 */
		private const string CENTRE = "&C";

		/**
		 * Right-aligns the characters that follow
		 */
		private const string RIGHT_ALIGN = "&R";

		// Codes to insert specific data

		/**
		 * Prints the page number
		 */
		private const string PAGENUM = "&P";

		/**
		 * Prints the total number of pages in the document
		 */
		private const string TOTAL_PAGENUM = "&N";

		/**
		 * Prints the current date
		 */
		private const string DATE = "&D";

		/**
		 * Prints the current time
		 */
		private const string TIME = "&T";

		/**
		 * Prints the name of the workbook
		 */
		private const string WORKBOOK_NAME = "&F";

		/**
		 * Prints the name of the worksheet
		 */
		private const string WORKSHEET_NAME = "&A";

		/**
		 * The contents - a simple wrapper around a string buffer
		 */
		public class Contents
			{
			/**
			 * The buffer containing the header/footer string
			 */
			private StringBuilder contents;

			/**
			 * The constructor
			 */
			protected Contents()
				{
				contents = new StringBuilder();
				}

			/**
			 * Constructor used when reading worksheets.  The string contains all
			 * the formatting (but not alignment characters
			 *
			 * @param s the format string
			 */
			protected Contents(string s)
				{
				contents = new StringBuilder(s);
				}

			/**
			 * Copy constructor
			 *
			 * @param copy the contents to copy
			 */
			protected Contents(Contents copy)
				{
				contents = new StringBuilder(copy.getContents());
				}

			/**
			 * Retrieves a <code>string</code>ified
			 * version of this object
			 *
			 * @return the header string
			 */
			public virtual string getContents()
				{
				return contents != null ? contents.ToString() : string.Empty;
				}

			/**
			 * Internal method which appends the text to the string buffer
			 *
			 * @param txt
			 */
			private void appendInternal(string txt)
				{
				if (contents == null)
					{
					contents = new StringBuilder();
					}

				contents.Append(txt);
				}

			/**
			 * Internal method which appends the text to the string buffer
			 *
			 * @param ch
			 */
			private void appendInternal(char ch)
				{
				if (contents == null)
					{
					contents = new StringBuilder();
					}

				contents.Append(ch);
				}

			/**
			 * Appends the text to the string buffer
			 *
			 * @param txt
			 */
			public virtual void append(string txt)
				{
				appendInternal(txt);
				}

			/**
			 * Turns bold printing on or off. Bold printing
			 * is initially off. Text subsequently appended to
			 * this object will be bolded until this method is
			 * called again.
			 */
			public virtual void toggleBold()
				{
				appendInternal(BOLD_TOGGLE);
				}

			/**
			 * Turns underline printing on or off. Underline printing
			 * is initially off. Text subsequently appended to
			 * this object will be underlined until this method is
			 * called again.
			 */
			public virtual void toggleUnderline()
				{
				appendInternal(UNDERLINE_TOGGLE);
				}

			/**
			 * Turns italics printing on or off. Italics printing
			 * is initially off. Text subsequently appended to
			 * this object will be italicized until this method is
			 * called again.
			 */
			public virtual void toggleItalics()
				{
				appendInternal(ITALICS_TOGGLE);
				}

			/**
			 * Turns strikethrough printing on or off. Strikethrough printing
			 * is initially off. Text subsequently appended to
			 * this object will be striked out until this method is
			 * called again.
			 */
			public virtual void toggleStrikethrough()
				{
				appendInternal(STRIKETHROUGH_TOGGLE);
				}

			/**
			 * Turns double-underline printing on or off. Double-underline printing
			 * is initially off. Text subsequently appended to
			 * this object will be double-underlined until this method is
			 * called again.
			 */
			public virtual void toggleDoubleUnderline()
				{
				appendInternal(DOUBLE_UNDERLINE_TOGGLE);
				}

			/**
			 * Turns superscript printing on or off. Superscript printing
			 * is initially off. Text subsequently appended to
			 * this object will be superscripted until this method is
			 * called again.
			 */
			public virtual void toggleSuperScript()
				{
				appendInternal(SUPERSCRIPT_TOGGLE);
				}

			/**
			 * Turns subscript printing on or off. Subscript printing
			 * is initially off. Text subsequently appended to
			 * this object will be subscripted until this method is
			 * called again.
			 */
			public virtual void toggleSubScript()
				{
				appendInternal(SUBSCRIPT_TOGGLE);
				}

			/**
			 * Turns outline printing on or off (Macintosh only).
			 * Outline printing is initially off. Text subsequently appended
			 * to this object will be outlined until this method is
			 * called again.
			 */
			public virtual void toggleOutline()
				{
				appendInternal(OUTLINE_TOGGLE);
				}

			/**
			 * Turns shadow printing on or off (Macintosh only).
			 * Shadow printing is initially off. Text subsequently appended
			 * to this object will be shadowed until this method is
			 * called again.
			 */
			public virtual void toggleShadow()
				{
				appendInternal(SHADOW_TOGGLE);
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
			public virtual void setFontName(string fontName)
				{
				// Font name must be in quotations
				appendInternal("&\"");
				appendInternal(fontName);
				appendInternal('\"');
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
			public virtual bool setFontSize(int size)
				{
				if (size < 1 || size > 99)
					{
					return false;
					}

				// A two digit number should be used -- even if the
				// leading number is just a zero.
				string fontSize;
				if (size < 10)
					{
					// single-digit -- make two digit
					fontSize = "0" + size;
					}
				else
					{
					fontSize = System.String.Format("{0:d}",size);
					}

				appendInternal('&');
				appendInternal(fontSize);
				return true;
				}

			/**
			 * Appends the page number
			 */
			public virtual void appendPageNumber()
				{
				appendInternal(PAGENUM);
				}

			/**
			 * Appends the total number of pages
			 */
			public virtual void appendTotalPages()
				{
				appendInternal(TOTAL_PAGENUM);
				}

			/**
			 * Appends the current date
			 */
			public virtual void appendDate()
				{
				appendInternal(DATE);
				}

			/**
			 * Appends the current time
			 */
			public virtual void appendTime()
				{
				appendInternal(TIME);
				}

			/**
			 * Appends the workbook name
			 */
			public virtual void appendWorkbookName()
				{
				appendInternal(WORKBOOK_NAME);
				}

			/**
			 * Appends the worksheet name
			 */
			public virtual void appendWorkSheetName()
				{
				appendInternal(WORKSHEET_NAME);
				}

			/**
			 * Clears the contents of this portion
			 */
			public virtual void clear()
				{
				contents = null;
				}

			/**
			 * Queries if the contents are empty
			 *
			 * @return TRUE if the contents are empty, FALSE otherwise
			 */
			public virtual bool empty()
				{
				if (contents == null || contents.Length == 0)
					return true;
				else
					return false;
				}
			}

		/**
		 * The left aligned header/footer contents
		 */
		private Contents left;

		/**
		 * The right aligned header/footer contents
		 */
		private Contents right;

		/**
		 * The centrally aligned header/footer contents
		 */
		private Contents centre;

		/**
		 * Default constructor.
		 */
		protected HeaderFooter()
			{
			left = createContents();
			right = createContents();
			centre = createContents();
			}

		/**
		 * Copy constructor
		 *
		 * @param c the item to copy
		 */
		protected HeaderFooter(HeaderFooter hf)
			{
			left = createContents(hf.left);
			right = createContents(hf.right);
			centre = createContents(hf.centre);
			}

		/**
		 * Constructor used when reading workbooks to separate the left, right
		 * a central part of the strings into their constituent parts
		 */
		protected HeaderFooter(string s)
			{
			if (s == null || s.Length == 0)
				{
				left = createContents();
				right = createContents();
				centre = createContents();
				return;
				}

			int leftPos = s.IndexOf(LEFT_ALIGN);
			int rightPos = s.IndexOf(RIGHT_ALIGN);
			int centrePos = s.IndexOf(CENTRE);

			if (leftPos == -1 && rightPos == -1 && centrePos == -1)
				{
				// When no part is specified, it is the center part
				centre = createContents(s);
				}
			else
				{
				// Left part?
				if (leftPos != -1)
					{
					// We have a left part, find end of left part
					int endLeftPos = s.Length;
					if (centrePos > leftPos)
						{
						// Case centre part behind left part
						endLeftPos = centrePos;
						if (rightPos > leftPos && endLeftPos > rightPos)
							{
							// LRC case
							endLeftPos = rightPos;
							}
						else
							{
							// LCR case
							}
						}
					else
						{
						// Case centre part before left part
						if (rightPos > leftPos)
							{
							// LR case
							endLeftPos = rightPos;
							}
						else
							{
							// *L case
							// Left pos is last


							}
						}
					int start = leftPos + 2;
					left = createContents(s.Substring(start,endLeftPos - start));
					}

				// Right part?
				if (rightPos != -1)
					{
					// Find end of right part
					int endRightPos = s.Length;
					if (centrePos > rightPos)
						{
						// centre part behind right part
						endRightPos = centrePos;
						if (leftPos > rightPos && endRightPos > leftPos)
							{
							// RLC case
							endRightPos = leftPos;
							}
						else
							{
							// RCL case
							}
						}
					else
						{
						if (leftPos > rightPos)
							{
							// RL case
							endRightPos = leftPos;
							}
						else
							{
							// *R case
							// Right pos is last
							}
						}
					int start = rightPos + 2;
					right = createContents(s.Substring(start, endRightPos - start));
					}

				// Centre part?
				if (centrePos != -1)
					{
					// Find end of centre part
					int endCentrePos = s.Length;
					if (rightPos > centrePos)
						{
						// right part behind centre part
						endCentrePos = rightPos;
						if (leftPos > centrePos && endCentrePos > leftPos)
							{
							// CLR case
							endCentrePos = leftPos;
							}
						else
							{
							// CRL case
							}
						}
					else
						{
						if (leftPos > centrePos)
							{
							// CL case
							endCentrePos = leftPos;
							}
						else
							{
							// *C case
							// Centre pos is last
							}
						}
					int start = centrePos + 2;
					centre = createContents(s.Substring(start,endCentrePos - start));
					}
				}


			if (left == null)
				left = createContents();

			if (centre == null)
				centre = createContents();

			if (right == null)
				right = createContents();
			}

		/**
		 * Retrieves a <code>string</code>ified
		 * version of this object
		 *
		 * @return the header string
		 */
		public override string ToString()
			{
			StringBuilder hf = new StringBuilder();
			if (!left.empty())
				{
				hf.Append(LEFT_ALIGN);
				hf.Append(left.getContents());
				}

			if (!centre.empty())
				{
				hf.Append(CENTRE);
				hf.Append(centre.getContents());
				}

			if (!right.empty())
				{
				hf.Append(RIGHT_ALIGN);
				hf.Append(right.getContents());
				}

			return hf.ToString();
			}

		/**
		 * Accessor for the contents which appear on the right hand side of the page
		 *
		 * @return the right aligned contents
		 */
		protected Contents getRightText()
			{
			return right;
			}

		/**
		 * Accessor for the contents which in the centre of the page
		 *
		 * @return the centrally  aligned contents
		 */
		protected Contents getCentreText()
			{
			return centre;
			}

		/**
		 * Accessor for the contents which appear on the left hand side of the page
		 *
		 * @return the left aligned contents
		 */
		protected Contents getLeftText()
			{
			return left;
			}

		/**
		 * Clears the contents of the header/footer
		 */
		public virtual void clear()
			{
			left.clear();
			right.clear();
			centre.clear();
			}

		/**
		 * Creates internal class of the appropriate type
		 */
		protected abstract Contents createContents();

		/**
		 * Creates internal class of the appropriate type
		 */
		protected abstract Contents createContents(string s);

		/**
		 * Creates internal class of the appropriate type
		 */
		protected abstract Contents createContents(Contents c);
		}
	}
