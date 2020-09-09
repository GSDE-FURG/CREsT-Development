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
	 * Enumeration class which contains the various patterns available within
	 * the standard Excel pattern palette
	 */
	public /*final*/ class Pattern
		{
		/**
		 * The internal numerical representation of the colour
		 */
		private int value;

		/**
		 * The textual description
		 */
		private string description;

		/**
		 * The list of patterns
		 */
		private static Pattern[] patterns = new Pattern[0];

		/**
		 * Private constructor
		 * 
		 * @param val 
		 * @param s
		 */
		protected Pattern(int val,string s)
			{
			value = val;
			description = s;

			Pattern[] oldcols = patterns;
			patterns = new Pattern[oldcols.Length + 1];
			System.Array.Copy(oldcols,0,patterns,0,oldcols.Length);
			patterns[oldcols.Length] = this;
			}

		/**
		 * Gets the value of this pattern.  This is the value that is written to 
		 * the generated Excel file
		 * 
		 * @return the binary value
		 */
		public int getValue()
			{
			return value;
			}

		/**
		 * Gets the textual description
		 *
		 * @return the description
		 */
		public string getDescription()
			{
			return description;
			}

		/**
		 * Gets the pattern from the value
		 *
		 * @param val 
		 * @return the pattern with that value
		 */
		public static Pattern getPattern(int val)
			{
			for (int i = 0; i < patterns.Length; i++)
				{
				if (patterns[i].getValue() == val)
					{
					return patterns[i];
					}
				}

			return NONE;
			}

		public static readonly Pattern NONE = new Pattern(0x0,"None");
		public static readonly Pattern SOLID = new Pattern(0x1,"Solid");

		public static readonly Pattern GRAY_50 = new Pattern(0x2,"Gray 50%");
		public static readonly Pattern GRAY_75 = new Pattern(0x3,"Gray 75%");
		public static readonly Pattern GRAY_25 = new Pattern(0x4,"Gray 25%");

		public static readonly Pattern PATTERN1 = new Pattern(0x5,"Pattern 1");
		public static readonly Pattern PATTERN2 = new Pattern(0x6,"Pattern 2");
		public static readonly Pattern PATTERN3 = new Pattern(0x7,"Pattern 3");
		public static readonly Pattern PATTERN4 = new Pattern(0x8,"Pattern 4");
		public static readonly Pattern PATTERN5 = new Pattern(0x9,"Pattern 5");
		public static readonly Pattern PATTERN6 = new Pattern(0xa,"Pattern 6");
		public static readonly Pattern PATTERN7 = new Pattern(0xb,"Pattern 7");
		public static readonly Pattern PATTERN8 = new Pattern(0xc,"Pattern 8");
		public static readonly Pattern PATTERN9 = new Pattern(0xd,"Pattern 9");
		public static readonly Pattern PATTERN10 = new Pattern(0xe,"Pattern 10");
		public static readonly Pattern PATTERN11 = new Pattern(0xf,"Pattern 11");
		public static readonly Pattern PATTERN12 = new Pattern(0x10,"Pattern 12");
		public static readonly Pattern PATTERN13 = new Pattern(0x11,"Pattern 13");
		public static readonly Pattern PATTERN14 = new Pattern(0x12,"Pattern 14");
		}
	}
