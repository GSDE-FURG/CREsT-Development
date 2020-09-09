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
using CSharpJExcel.Jxl;
using CSharpJExcel.Jxl.Biff;
using CSharpJExcel.Jxl.Biff.Drawing;
using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Jxl.Write;



namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A writable Font record.  This class intercepts any set accessor calls 
	 * and throws and exception if the Font is already initialized
	 */
	public class WritableFontRecord : FontRecord
		{
		/**
		 * Constructor, used when creating a new font for writing out.
		 * 
		 * @param bold the bold indicator
		 * @param ps the point size
		 * @param us the underline style
		 * @param fn the name
		 * @param it italicised indicator
		 * @param c  the colour
		 * @param ss the script style
		 */
		protected WritableFontRecord(string fn, int ps, int bold, bool it,
							   int us, int ci, int ss)
			: base(fn, ps, bold, it, us, ci, ss)
			{
			}

		/**
		 * Publicly available copy constructor
		 *
		 * @param the font to copy
		 */
		protected WritableFontRecord(CSharpJExcel.Jxl.Format.Font f)
			: base(f)
			{
			}


		/**
		 * Sets the point size for this font, if the font hasn't been initialized
		 * 
		 * @param pointSize the point size
		 * @exception WriteException, if this font is already in use elsewhere
		 */
		public virtual void setPointSize(int pointSize)
			{
			if (isInitialized())
				{
				throw new JxlWriteException(JxlWriteException.formatInitialized);
				}

			base.setFontPointSize(pointSize);
			}

		/**
		 * Sets the bold style for this font, if the font hasn't been initialized
		 * 
		 * @param boldStyle the bold style
		 * @exception WriteException, if this font is already in use elsewhere
		 */
		public virtual void setBoldStyle(int boldStyle)
			{
			if (isInitialized())
				{
				throw new JxlWriteException(JxlWriteException.formatInitialized);
				}

			base.setFontBoldStyle(boldStyle);
			}

		/**
		 * Sets the italic indicator for this font, if the font hasn't been 
		 * initialized
		 * 
		 * @param italic the italic flag
		 * @exception WriteException, if this font is already in use elsewhere
		 */
		public virtual void setItalic(bool italic)
			{
			if (isInitialized())
				{
				throw new JxlWriteException(JxlWriteException.formatInitialized);
				}

			base.setFontItalic(italic);
			}

		/**
		 * Sets the underline style for this font, if the font hasn't been 
		 * initialized
		 * 
		 * @param us the underline style
		 * @exception WriteException, if this font is already in use elsewhere
		 */
		public virtual void setUnderlineStyle(int us)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);

			base.setFontUnderlineStyle(us);
			}

		/**
		 * Sets the colour for this font, if the font hasn't been 
		 * initialized
		 * 
		 * @param colour the colour
		 * @exception WriteException, if this font is already in use elsewhere
		 */
		public virtual void setColour(int colour)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);

			base.setFontColour(colour);
			}

		/**
		 * Sets the script style (eg. superscript, subscript) for this font, 
		 * if the font hasn't been initialized
		 * 
		 * @param scriptStyle the colour
		 * @exception WriteException, if this font is already in use elsewhere
		 */
		public virtual void setScriptStyle(int scriptStyle)
			{
			if (isInitialized())
				throw new JxlWriteException(JxlWriteException.formatInitialized);

			base.setFontScriptStyle(scriptStyle);
			}

		/** 
		 * Sets the struck out flag
		 *
		 * @param so TRUE if the font is struck out, false otherwise
		 * @exception WriteException, if this font is already in use elsewhere
		 */
		public virtual void setStruckout(bool os)
			{
			if (isInitialized())
				{
				throw new JxlWriteException(JxlWriteException.formatInitialized);
				}
			base.setFontStruckout(os);
			}
		}
	}
