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
	 * Enumeration class which contains the various underline styles available 
	 * within the standard Excel UnderlineStyle palette
	 * 
	 */
	public sealed class UnderlineStyle
		{
		/**
		 * The internal numerical representation of the UnderlineStyle
		 */
		private int value;

		/**
		 * The display description for the underline style.  Used when presenting the 
		 * format information
		 */
		private string description;

		/**
		 * The list of UnderlineStyles
		 */
		private static UnderlineStyle[] styles = new UnderlineStyle[0];

		/**
		 * Private constructor
		 * 
		 * @param val 
		 * @param s the display description
		 */
		internal UnderlineStyle(int val,string s)
			{
			value = val;
			description = s;

			UnderlineStyle[] oldstyles = styles;
			styles = new UnderlineStyle[oldstyles.Length + 1];
			System.Array.Copy(oldstyles,0,styles,0,oldstyles.Length);
			styles[oldstyles.Length] = this;
			}

		/**
		 * Gets the value of this style.  This is the value that is written to 
		 * the generated Excel file
		 * 
		 * @return the binary value
		 */
		public int getValue()
			{
			return value;
			}

		/**
		 * Gets the description description for display purposes
		 * 
		 * @return the description description
		 */
		public string getDescription()
			{
			return description;
			}

		/**
		 * Gets the UnderlineStyle from the value
		 *
		 * @param val 
		 * @return the UnderlineStyle with that value
		 */
		public static UnderlineStyle getStyle(int val)
			{
			for (int i = 0; i < styles.Length; i++)
				{
				if (styles[i].getValue() == val)
					{
					return styles[i];
					}
				}

			return NO_UNDERLINE;
			}

		// The underline styles
		public static readonly UnderlineStyle NO_UNDERLINE = new UnderlineStyle(0,"none");
		public static readonly UnderlineStyle SINGLE = new UnderlineStyle(1,"single");
		public static readonly UnderlineStyle DOUBLE = new UnderlineStyle(2,"double");
		public static readonly UnderlineStyle SINGLE_ACCOUNTING = new UnderlineStyle(0x21,"single accounting");
		public static readonly UnderlineStyle DOUBLE_ACCOUNTING = new UnderlineStyle(0x22,"double accounting");
		}
	}


