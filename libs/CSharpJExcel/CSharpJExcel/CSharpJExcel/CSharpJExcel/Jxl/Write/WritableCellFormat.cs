/*********************************************************************
*
*      Copyright (C) 2001 Andrew Khan
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


using CSharpJExcel.Jxl.Write.Biff;
using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * A user specified cell format, which may be reused across many cells.
	 * The constructors takes parameters, such as font details and the numerical
	 * date formats, which specify to Excel how cells with this format should be
	 * displayed.
	 * Once a CellFormat has been added to a Cell which has been added to
	 * a sheet, then the CellFormat becomes immutable (to prevent unforeseen
	 * effects on other cells which share the same format).  Attempts to
	 * call the various set... functions on a WritableCellFormat after this
	 * time will result in a runtime exception.
	 */
	public class WritableCellFormat : CellXFRecord
		{
		/**
		 * A default constructor, which uses the default font and format.
		 * This constructor should be used in conjunction with the more
		 * advanced two-phase methods setAlignment, setBorder etc.
		 */
		public WritableCellFormat()
			: this(WritableWorkbook.ARIAL_10_PT, NumberFormats.DEFAULT)
			{
			}

		/**
		 * A CellFormat which specifies the font for cells with this format
		 *
		 * @param font the font
		 */
		public WritableCellFormat(WritableFont font)
			: this(font, NumberFormats.DEFAULT)
			{
			}

		/**
		 * A constructor which specifies a date/number format for Cells which
		 * use this format object
		 *
		 * @param format the format
		 */
		public WritableCellFormat(DisplayFormat format)
			: this(WritableWorkbook.ARIAL_10_PT, format)
			{
			}

		/**
		 * A constructor which specifies the font and date/number format for cells
		 * which wish to use this format
		 *
		 * @param font the font
		 * @param format the date/number format
		 */
		public WritableCellFormat(WritableFont font, DisplayFormat format)
			: base(font, format)
			{
			}

		/**
		 * A public copy constructor which can be used for copy formats between
		 * different sheets
		 * @param format the cell format to copy
		 */
		public WritableCellFormat(CellFormat format)
			: base(format)
			{
			}

		/**
		 * Sets the horizontal alignment for this format
		 *
		 * @param a the alignment
		 * @exception WriteException
		 */
		public override void setAlignment(Alignment a)
			{
			base.setAlignment(a);
			}

		/**
		 * Sets the vertical alignment for this format
		 *
		 * @param va the vertical alignment
		 * @exception WriteException
		 */
		public override void setVerticalAlignment(VerticalAlignment va)
			{
			base.setVerticalAlignment(va);
			}

		/**
		 * Sets the text orientation for this format
		 *
		 * @param o the orientation
		 * @exception WriteException
		 */
		public override void setOrientation(Orientation o)
			{
			base.setOrientation(o);
			}

		/**
		 * Sets the wrap indicator for this format.  If the wrap is set to TRUE, then
		 * Excel will wrap data in cells with this format so that it fits within the
		 * cell boundaries
		 *
		 * @param w the wrap flag
		 * @exception jxl.write.WriteException
		 */
		public override void setWrap(bool w)
			{
			base.setWrap(w);
			}

		/**
		 * Sets the specified border for this format
		 *
		 * @param b the border
		 * @param ls the border line style
		 * @exception jxl.write.WriteException
		 */
		public void setBorder(Border b, BorderLineStyle ls)
			{
			base.setBorder(b, ls, Colour.BLACK);
			}

		/**
		 * Sets the specified border for this format
		 *
		 * @param b the border
		 * @param ls the border line style
		 * @param c the colour of the specified border
		 * @exception jxl.write.WriteException
		 */
		public override void setBorder(Border b, BorderLineStyle ls, Colour c)
			{
			base.setBorder(b, ls, c);
			}

		/**
		 * Sets the background colour for this cell format
		 *
		 * @param c the bacground colour
		 * @exception jxl.write.WriteException
		 */
		public void setBackground(Colour c)
			{
			this.setBackground(c, Pattern.SOLID);
			}

		/**
		 * Sets the background colour and pattern for this cell format
		 *
		 * @param c the colour
		 * @param p the pattern
		 * @exception jxl.write.WriteException
		 */
		public override void setBackground(Colour c, Pattern p)
			{
			base.setBackground(c, p);
			}

		/**
		 * Sets the shrink to fit flag
		 *
		 * @param s shrink to fit flag
		 * @exception WriteException
		 */
		public override void setShrinkToFit(bool s)
			{
			base.setShrinkToFit(s);
			}

		/**
		 * Sets the indentation of the cell text
		 *
		 * @param i the indentation
		 * @exception WriteException
		 */
		public override void setIndentation(int i)
			{
			base.setIndentation(i);
			}


		/**
		 * Sets whether or not this XF record locks the cell.  For this to
		 * have any effect, the sheet containing cells with this format must
		 * also be locke3d
		 *
		 * @param l the locked flag
		 * @exception WriteException
		 */
		public override void setLocked(bool l)
			{
			base.setLocked(l);
			}
		}
	}







