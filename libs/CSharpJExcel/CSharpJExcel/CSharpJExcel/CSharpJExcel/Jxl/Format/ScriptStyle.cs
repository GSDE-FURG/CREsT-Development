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
	 * Enumeration class which contains the various script styles available 
	 * within the standard Excel ScriptStyle palette
	 * 
	 */
	public sealed class ScriptStyle
		{
		/**
		 * The internal numerical representation of the ScriptStyle
		 */
		private int value;

		/**
		 * The display description for the script style.  Used when presenting the 
		 * format information
		 */
		private string description;

		/**
		 * The list of ScriptStyles
		 */
		private static ScriptStyle[] styles = new ScriptStyle[0];


		/**
		 * Private constructor
		 * 
		 * @param val 
		 * @param s the display description
		 */
		internal ScriptStyle(int val,string s)
			{
			value = val;
			description = s;

			ScriptStyle[] oldstyles = styles;
			styles = new ScriptStyle[oldstyles.Length + 1];
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
		 * Gets the ScriptStyle from the value
		 *
		 * @param val 
		 * @return the ScriptStyle with that value
		 */
		public static ScriptStyle getStyle(int val)
			{
			for (int i = 0; i < styles.Length; i++)
				{
				if (styles[i].getValue() == val)
					{
					return styles[i];
					}
				}

			return NORMAL_SCRIPT;
			}

		// The script styles
		public static readonly ScriptStyle NORMAL_SCRIPT = new ScriptStyle(0,"normal");
		public static readonly ScriptStyle SUPERSCRIPT = new ScriptStyle(1,"super");
		public static readonly ScriptStyle SUBSCRIPT = new ScriptStyle(2,"sub");
		}
	}
