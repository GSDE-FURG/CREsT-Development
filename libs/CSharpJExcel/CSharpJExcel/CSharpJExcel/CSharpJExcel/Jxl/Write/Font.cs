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
	 * A class which is instantiated when the user application wishes to specify
	 * the font for a particular cell
	 *
	 * @deprecated Renamed to writable font
	 */
	public class Font : WritableFont
		{
		/**
		 * objects created with this font name will be rendered within Excel as ARIAL
		 * fonts
		 * @deprecated
		 */
		public static readonly FontName ARIAL = WritableFont.ARIAL;
		/**
		 * objects created with this font name will be rendered within Excel as TIMES
		 * fonts
		 * @deprecated
		 */
		public static readonly FontName TIMES = WritableFont.TIMES;

		// The bold styles

		/**
		 * Indicates that this font should not be presented as bold
		 * @deprecated
		 */
		public static readonly BoldStyle NO_BOLD = WritableFont.NO_BOLD;
		/**
		 * Indicates that this font should be presented in a BOLD style
		 * @deprecated
		 */
		public static readonly BoldStyle BOLD = WritableFont.BOLD;

		// The underline styles
		/**
		 * @deprecated
		 */
		public static readonly UnderlineStyle NO_UNDERLINE =
		  UnderlineStyle.NO_UNDERLINE;

		/**
		 * @deprecated
		 */
		public static readonly UnderlineStyle SINGLE = UnderlineStyle.SINGLE;

		/**
		 * @deprecated
		 */
		public static readonly UnderlineStyle DOUBLE = UnderlineStyle.DOUBLE;

		/**
		 * @deprecated
		 */
		public static readonly UnderlineStyle SINGLE_ACCOUNTING = UnderlineStyle.SINGLE_ACCOUNTING;

		/**
		 * @deprecated
		 */
		public static readonly UnderlineStyle DOUBLE_ACCOUNTING = UnderlineStyle.DOUBLE_ACCOUNTING;

		// The script styles
		public static readonly ScriptStyle NORMAL_SCRIPT = ScriptStyle.NORMAL_SCRIPT;
		public static readonly ScriptStyle SUPERSCRIPT = ScriptStyle.SUPERSCRIPT;
		public static readonly ScriptStyle SUBSCRIPT = ScriptStyle.SUBSCRIPT;

		/**
		 * Creates a default font, vanilla font of the specified face and with
		 * default point size.
		 *
		 * @param fn the font name
		 * @deprecated Use jxl.write.WritableFont
		 */
		public Font(FontName fn)
			: base(fn)
			{
			}

		/**
		 * Constructs of font of the specified face and of size given by the
		 * specified point size
		 *
		 * @param ps the point size
		 * @param fn the font name
		 * @deprecated use jxl.write.WritableFont
		 */
		public Font(FontName fn,int ps)
			: base(fn,ps)
			{
			}

		/**
		 * Creates a font of the specified face, point size and bold style
		 *
		 * @param ps the point size
		 * @param bs the bold style
		 * @param fn the font name
		 * @deprecated use jxl.write.WritableFont
		 */
		public Font(FontName fn,int ps,BoldStyle bs)
			: base(fn,ps,bs)
			{
			}

		/**
		 * Creates a font of the specified face, point size, bold weight and
		 * italicised option.
		 *
		 * @param ps the point size
		 * @param bs the bold style
		 * @param italic italic flag
		 * @param fn the font name
		 * @deprecated use jxl.write.WritableFont
		 */
		public Font(FontName fn,int ps,BoldStyle bs,bool italic)
			: base(fn,ps,bs,italic)
			{
			}

		/**
		 * Creates a font of the specified face, point size, bold weight,
		 * italicisation and underline style
		 *
		 * @param ps the point size
		 * @param bs the bold style
		 * @param us underscore flag
		 * @param fn font name
		 * @param it italic flag
		 * @deprecated use jxl.write.WritableFont
		 */
		public Font(FontName fn,
					int ps,
					BoldStyle bs,
					bool it,
					UnderlineStyle us)
			: base(fn,ps,bs,it,us)
			{
			}


		/**
		 * Creates a font of the specified face, point size, bold style,
		 * italicisation, underline style and colour
		 *
		 * @param ps the point size
		 * @param bs the bold style
		 * @param us the underline style
		 * @param fn the font name
		 * @param it italic flag
		 * @param c the colour
		 * @deprecated use jxl.write.WritableFont
		 */
		public Font(FontName fn,
					int ps,
					BoldStyle bs,
					bool it,
					UnderlineStyle us,
					Colour c)
			: base(fn,ps,bs,it,us,c)
			{
			}


		/**
		 * Creates a font of the specified face, point size, bold style,
		 * italicisation, underline style, colour, and script
		 * style (superscript/subscript)
		 *
		 * @param ps the point size
		 * @param bs the bold style
		 * @param us the underline style
		 * @param fn the font name
		 * @param it the italic flag
		 * @param c the colour
		 * @param ss the script style
		 * @deprecated use jxl.write.WritableFont
		 */
		public Font(FontName fn,
					int ps,
					BoldStyle bs,
					bool it,
					UnderlineStyle us,
					Colour c,
					ScriptStyle ss)
			: base(fn,ps,bs,it,us,c,ss)
			{
			}
		}
	}


