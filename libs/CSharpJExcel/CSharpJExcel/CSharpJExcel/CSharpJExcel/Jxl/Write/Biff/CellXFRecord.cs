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


using CSharpJExcel.Jxl.Biff;
using CSharpJExcel.Jxl.Format;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A cell XF Record
	 */
	public class CellXFRecord : XFRecord
		{
		/**
		 * Constructor
		 * 
		 * @param fnt the font
		 * @param form the format
		 */
		public CellXFRecord(FontRecord fnt, DisplayFormat form)
			: base(fnt, form)
			{
			setXFDetails(XFRecord.cell, 0);
			}

		/**
		 * Copy constructor.  Invoked when copying formats to handle cell merging
		 * 
		 * @param fmt the format to copy
		 */
		public CellXFRecord(XFRecord fmt)
			: base(fmt)
			{
			setXFDetails(XFRecord.cell, 0);
			}

		/**
		 * A public copy constructor which can be used for copy formats between
		 * different sheets
		 */
		public CellXFRecord(CellFormat format)
			: base(format)
			{
			}

		/**
		 * Sets the alignment for the cell
		 * 
		 * @exception WriteException 
		 * @param a the alignment
		 */
		public virtual void setAlignment(Alignment a)
			{
			if (isInitialized())
				{
				throw new JxlWriteException(JxlWriteException.formatInitialized);
				}
			base.setXFAlignment(a);
			}

		/**
		 * Sets the background for the cell
		 * 
		 * @exception WriteException 
		 * @param c the background colour
		 * @param p the background patter
		 */
		public virtual void setBackground(Colour c, Pattern p)
			{
			if (isInitialized())
				{
				throw new JxlWriteException(JxlWriteException.formatInitialized);
				}
			base.setXFBackground(c, p);
			base.setXFCellOptions(0x4000);
			}

		/**
		 * Sets whether or not this XF record locks the cell
		 * 
		 * @param l the locked flag
		 * @exception WriteException 
		 */
		public virtual void setLocked(bool l)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);
			base.setXFLocked(l);
			base.setXFCellOptions(0x8000);
			}

		/**
		 * Sets the indentation of the cell text
		 *
		 * @param i the indentation
		 */
		public virtual void setIndentation(int i)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);
			base.setXFIndentation(i);
			}

		/**
		 * Sets the shrink to fit flag
		 *
		 * @param b the shrink to fit flag
		 */
		public virtual void setShrinkToFit(bool s)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);
			base.setXFShrinkToFit(s);
			}

		/**
		 * Sets the vertical alignment for cells with this style
		 * 
		 * @exception WriteException 
		 * @param va the vertical alignment
		 */
		public virtual void setVerticalAlignment(VerticalAlignment va)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);

			base.setXFVerticalAlignment(va);
			}

		/**
		 * Sets the text orientation for cells with this style
		 * 
		 * @exception WriteException 
		 * @param o the orientation
		 */
		public virtual void setOrientation(Orientation o)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);

			base.setXFOrientation(o);
			}

		/**
		 * Sets the text wrapping for cells with this style.  If the parameter is
		 * set to TRUE, then data in this cell will be wrapped around, and the
		 * cell's height adjusted accordingly
		 * 
		 * @exception WriteException 
		 * @param w the wrap
		 */
		public virtual void setWrap(bool w)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);

			base.setXFWrap(w);
			}

		/**
		 * Sets the border style for cells with this format
		 * 
		 * @exception WriteException 
		 * @param b the border
		 * @param ls the line for the specified border
		 */
		public virtual void setBorder(Border b, BorderLineStyle ls, Colour c)
			{
			if (isInitialized())
				{
				throw new JxlWriteException(JxlWriteException.formatInitialized);
				}

			if (b == Border.ALL)
				{
				// Apply to all
				base.setXFBorder(Border.LEFT, ls, c);
				base.setXFBorder(Border.RIGHT, ls, c);
				base.setXFBorder(Border.TOP, ls, c);
				base.setXFBorder(Border.BOTTOM, ls, c);
				return;
				}

			if (b == Border.NONE)
				{
				// Apply to all
				base.setXFBorder(Border.LEFT, BorderLineStyle.NONE, Colour.BLACK);
				base.setXFBorder(Border.RIGHT, BorderLineStyle.NONE, Colour.BLACK);
				base.setXFBorder(Border.TOP, BorderLineStyle.NONE, Colour.BLACK);
				base.setXFBorder(Border.BOTTOM, BorderLineStyle.NONE, Colour.BLACK);
				return;
				}

			base.setXFBorder(b, ls, c);
			}
		}
	}

