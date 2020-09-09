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


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * Static class which contains Excels predefined Date formats
	 */
	public sealed class DateFormats
		{
		/**
		 * Inner class which holds the format index
		 */
		private class BuiltInFormat : DisplayFormat
			{
			/**
			 * The index of this date format
			 */
			private int index;
			/**
			 * The excel format
			 */
			private string formatString;

			/**
			 * Constructor
			 *
			 * @param i the index
			 * @param s the format string
			 */
			public BuiltInFormat(int i,string s)
				{
				index = i;
				formatString = s;
				}

			/**
			 * Gets the format index
			 *
			 * @return the format index
			 */
			public int getFormatIndex()
				{
				return index;
				}

			/**
			 * Interface method which determines whether the index has been set.  For
			 * built ins this is always true
			 *
			 * @return TRUE, since this is a built in format
			 */
			public bool isInitialized()
				{
				return true;
				}
			/**
			 * Initialize this format with the specified position.  Since this is a
			 * built in format, this is always initialized, so this method body for
			 * this is empty
			 *
			 * @param pos the position in the array
			 */
			public void initialize(int pos)
				{
				}
			/**
			 * Determines whether this format is a built in format
			 *
			 * @return TRUE, since this is a built in format
			 */
			public bool isBuiltIn()
				{
				return true;
				}
			/**
			 * Accesses the excel format string which is applied to the cell
			 * Note that this is the string that excel uses, and not the java
			 * equivalent
			 *
			 * @return the cell format string
			 */
			public string getFormatString()
				{
				return formatString;
				}

			/**
			 * Standard equals method
			 *
			 * @param o the object to compare
			 * @return TRUE if the two objects are equal, FALSE otherwise
			 */
			public override bool Equals(object o)
				{
				if (o == this)
					return true;

				if (!(o is BuiltInFormat))
					return false;

				BuiltInFormat bif = (BuiltInFormat)o;

				return index == bif.index;
				}

			/**
			 * Hash code implementation
			 *
			 * @return the hash code
			 */
			public override int GetHashCode()
				{
				return index;
				}
			}

		//  The available built in date formats

		/**
		 * The default format.  This is equivalent to a date format of "M/d/yy"
		 */
		public static readonly DisplayFormat FORMAT1 = new BuiltInFormat(0x0e,"M/d/yy");
		/**
		 * The default format.  This is equivalent to a date format of "M/d/yy"
		 */
		public static readonly DisplayFormat DEFAULT = FORMAT1;

		/**
		 * Equivalent to a date format of "d-MMM-yy"
		 */
		public static readonly DisplayFormat FORMAT2 = new BuiltInFormat(0xf,"d-MMM-yy");

		/**
		 * Equivalent to a date format of "d-MMM"
		 */
		public static readonly DisplayFormat FORMAT3 = new BuiltInFormat(0x10,"d-MMM");

		/**
		 * Equivalent to a date format of "MMM-yy"
		 */
		public static readonly DisplayFormat FORMAT4 = new BuiltInFormat(0x11,"MMM-yy");

		/**
		 * Equivalent to a date format of "h:mm a"
		 */
		public static readonly DisplayFormat FORMAT5 = new BuiltInFormat(0x12,"h:mm a");

		/**
		 * Equivalent to a date format of "h:mm:ss a"
		 */
		public static readonly DisplayFormat FORMAT6 = new BuiltInFormat(0x13,"h:mm:ss a");

		/**
		 * Equivalent to a date format of "H:mm"
		 */
		public static readonly DisplayFormat FORMAT7 = new BuiltInFormat(0x14,"H:mm");

		/**
		 * Equivalent to a date format of "H:mm:ss"
		 */
		public static readonly DisplayFormat FORMAT8 = new BuiltInFormat(0x15,"H:mm:ss");

		/**
		 * Equivalent to a date format of "M/d/yy H:mm"
		 */
		public static readonly DisplayFormat FORMAT9 = new BuiltInFormat(0x16,"M/d/yy H:mm");

		/**
		 * Equivalent to a date format of "mm:ss"
		 */
		public static readonly DisplayFormat FORMAT10 = new BuiltInFormat(0x2d,"mm:ss");

		/**
		 * Equivalent to a date format of "H:mm:ss"
		 */
		public static readonly DisplayFormat FORMAT11 = new BuiltInFormat(0x2e,"H:mm:ss");

		/**
		 * Equivalent to a date format of "mm:ss.S"
		 */
		public static readonly DisplayFormat FORMAT12 = new BuiltInFormat(0x2f,"H:mm:ss");
		}
	}



