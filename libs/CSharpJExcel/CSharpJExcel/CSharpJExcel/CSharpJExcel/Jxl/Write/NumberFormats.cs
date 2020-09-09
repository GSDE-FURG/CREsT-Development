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
	 * Static class which contains the available list of built in Number formats
	 */
	public sealed class NumberFormats
		{
		/**
		 * Inner class which holds the format index
		 */
		private class BuiltInFormat : DisplayFormat, CSharpJExcel.Jxl.Format.Format
			{
			/**
			 * The built in number format index
			 */
			private int index;

			/**
			 * The format string
			 */
			private string formatString;

			/**
			 * Constructor, using the predetermined index
			 *
			 * @param i the index
			 * @param s the string
			 */
			public BuiltInFormat(int i, string s)
				{
				index = i;
				formatString = s;
				}

			/**
			 * Accessor for the format index
			 *
			 * @return the index
			 */
			public int getFormatIndex()
				{
				return index;
				}
			/**
			 * Accessor to determine if this format has been initialized.  Since it is
			 * built in, this will always return TRUE
			 *
			 * @return TRUE, since this is a built in format
			 */
			public bool isInitialized()
				{
				return true;
				}
			/**
			 * Determines whether this format is a built in format
			 *
			 * @return TRUE, since this is a built in numerical format
			 */
			public bool isBuiltIn()
				{
				return true;
				}
			/**
			 * Initializes this format with a dynamically determined index value.
			 * Since this is a built in, and hence the index value is predetermined,
			 * this method has an empty body
			 *
			 * @param pos the pos in the number formats list
			 */
			public void initialize(int pos)
				{
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
			 * Standard hash code method
			 *
			 * @return  the hash code
			 */
			public override int GetHashCode()
				{
				return index;
				}
			}


		// The available built in number formats
		// First describe the fairly bog standard formats

		/**
		 * The default format.  This is equivalent to a number format of '#'
		 */
		public static readonly DisplayFormat DEFAULT = new BuiltInFormat(0x0, "#");
		/**
		 * Formatting for an integer number.  This is equivalent to a DecimalFormat
		 * of "0"
		 */
		public static readonly DisplayFormat INTEGER = new BuiltInFormat(0x1, "0");

		/**
		 * Formatting for a float.  This formats number to two decimal places.  It
		 * is equivalent to a DecimalFormat of "0.00"
		 */
		public static readonly DisplayFormat FLOAT = new BuiltInFormat(0x2, "0.00");

		/**
		 * Formatting for an integer that has a thousands separator.
		 * Equivalent to a DecimalFormat of "#,##0"
		 */
		public static readonly DisplayFormat THOUSANDS_INTEGER =
											 new BuiltInFormat(0x3, "#,##0");

		/**
		 * Formatting for a float that has a thousands separator.
		 * Equivalent to a DecimalFormat of "#,##0.00"
		 */
		public static readonly DisplayFormat THOUSANDS_FLOAT =
											  new BuiltInFormat(0x4, "#,##0.00");

		/**
		 * Formatting for an integer which is presented in accounting format
		 * (ie. deficits appear in parentheses)
		 * Equivalent to a DecimalFormat of "$#,##0;($#,##0)"
		 */
		public static readonly DisplayFormat ACCOUNTING_INTEGER =
										 new BuiltInFormat(0x5, "$#,##0;($#,##0)");

		/**
		 * As ACCOUNTING_INTEGER except that deficits appear coloured red
		 */
		public static readonly DisplayFormat ACCOUNTING_RED_INTEGER =
										  new BuiltInFormat(0x6, "$#,##0;($#,##0)");

		/**
		 * Formatting for an integer which is presented in accounting format
		 * (ie. deficits appear in parentheses)
		 * Equivalent to a DecimalFormat of  "$#,##0;($#,##0)"
		 */
		public static readonly DisplayFormat ACCOUNTING_FLOAT =
										   new BuiltInFormat(0x7, "$#,##0;($#,##0)");

		/**
		 * As ACCOUNTING_FLOAT except that deficits appear coloured red
		 */
		public static readonly DisplayFormat ACCOUNTING_RED_FLOAT =
										 new BuiltInFormat(0x8, "$#,##0;($#,##0)");

		/**
		 * Formatting for an integer presented as a percentage
		 * Equivalent to a DecimalFormat of "0%"
		 */
		public static readonly DisplayFormat PERCENT_INTEGER =
		  new BuiltInFormat(0x9, "0%");

		/**
		 * Formatting for a float percentage
		 * Equivalent to a DecimalFormat "0.00%"
		 */
		public static readonly DisplayFormat PERCENT_FLOAT =
		  new BuiltInFormat(0xa, "0.00%");

		/**
		 * Formatting for exponential or scientific notation
		 * Equivalent to a DecimalFormat "0.00E00"
		 */
		public static readonly DisplayFormat EXPONENTIAL =
		  new BuiltInFormat(0xb, "0.00E00");

		/** 
		 * Formatting for one digit fractions
		 */
		public static readonly DisplayFormat FRACTION_ONE_DIGIT =
		  new BuiltInFormat(0xc, "?/?");

		/** 
		 * Formatting for two digit fractions
		 */
		public static readonly DisplayFormat FRACTION_TWO_DIGITS =
		  new BuiltInFormat(0xd, "??/??");

		// Now describe the more obscure formats

		/**
		 * Equivalent to a DecimalFormat "#,##0;(#,##0)"
		 */
		public static readonly DisplayFormat FORMAT1 =
											new BuiltInFormat(0x25, "#,##0;(#,##0)");

		/**
		 * Equivalent to FORMAT1 except deficits are coloured red
		 */
		public static readonly DisplayFormat FORMAT2 =
										new BuiltInFormat(0x26, "#,##0;(#,##0)");

		/**
		 * Equivalent to DecimalFormat "#,##0.00;(#,##0.00)"
		 */
		public static readonly DisplayFormat FORMAT3 =
									 new BuiltInFormat(0x27, "#,##0.00;(#,##0.00)");

		/**
		 * Equivalent to FORMAT3 except deficits are coloured red
		 */
		public static readonly DisplayFormat FORMAT4 =
									  new BuiltInFormat(0x28, "#,##0.00;(#,##0.00)");

		/**
		 * Equivalent to DecimalFormat "#,##0;(#,##0)"
		 */
		public static readonly DisplayFormat FORMAT5 =
										  new BuiltInFormat(0x29, "#,##0;(#,##0)");

		/**
		 * Equivalent to FORMAT5 except deficits are coloured red
		 */
		public static readonly DisplayFormat FORMAT6 =
										 new BuiltInFormat(0x2a, "#,##0;(#,##0)");

		/**
		 * Equivalent to DecimalFormat "#,##0.00;(#,##0.00)"
		 */
		public static readonly DisplayFormat FORMAT7 =
									 new BuiltInFormat(0x2b, "#,##0.00;(#,##0.00)");

		/**
		 * Equivalent to FORMAT7 except deficits are coloured red
		 */
		public static readonly DisplayFormat FORMAT8 =
									   new BuiltInFormat(0x2c, "#,##0.00;(#,##0.00)");

		/**
		 * Equivalent to FORMAT7
		 */
		public static readonly DisplayFormat FORMAT9 =
									  new BuiltInFormat(0x2e, "#,##0.00;(#,##0.00)");

		/**
		 * Equivalent to DecimalFormat "##0.0E0"
		 */
		public static readonly DisplayFormat FORMAT10 =
		  new BuiltInFormat(0x30, "##0.0E0");

		/**
		 * Forces numbers to be interpreted as text
		 */
		public static readonly DisplayFormat TEXT = new BuiltInFormat(0x31, "@");
		}
	}

