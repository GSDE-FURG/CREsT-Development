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


using CSharpJExcel.Jxl.Write.Biff;
using CSharpJExcel.Jxl.Biff;
using CSharpJExcel.Interop;


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * A custom user defined number format, which may be instantiated within user
	 * applications in order to present numerical values to the appropriate level
	 * of accuracy.
	 * The string format used to create a number format adheres to the standard
	 * java specification, and JExcelAPI makes the necessary modifications so
	 * that it is rendered in Excel as the nearest possible equivalent.
	 * Once created, this may be used within a CellFormat object, which in turn
	 * is a parameter passed to the constructor of the Number cell
	 */
	public class NumberFormat : NumberFormatRecord, DisplayFormat
		{
		/**
		 * Pass in to the constructor to bypass the format validation
		 */
		public static readonly NonValidatingFormat COMPLEX_FORMAT = new NumberFormatRecord.NonValidatingFormat();

		// Some format strings

		/**
		 * Constant format string for the Euro currency symbol where it precedes
		 * the format
		 */
		public static readonly string CURRENCY_EURO_PREFIX = "[$€-2]";

		/**
		 * Constant format string for the Euro currency symbol where it precedes
		 * the format
		 */
		public static readonly string CURRENCY_EURO_SUFFIX = "[$€-1]";

		/**
		 * Constant format string for the UK pound sign
		 */
		public static readonly string CURRENCY_POUND = "£";

		/**
		 * Constant format string for the Japanese Yen sign
		 */
		public static readonly string CURRENCY_JAPANESE_YEN = "[$¥-411]";

		/**
		 * Constant format string for the US Dollar sign
		 */
		public static readonly string CURRENCY_DOLLAR = "[$$-409]";

		/**
		 * Constant format string for three digit fractions
		 */
		public static readonly string FRACTION_THREE_DIGITS = "???/???";

		/**
		 * Constant format string for fractions as halves
		 */
		public static readonly string FRACTION_HALVES = "?/2";

		/**
		 * Constant format string for fractions as quarter
		 */
		public static readonly string FRACTION_QUARTERS = "?/4";

		/**
		 * Constant format string for fractions as eighths
		 */
		public static readonly string FRACTIONS_EIGHTHS = "?/8";

		/**
		 * Constant format string for fractions as sixteenths
		 */
		public static readonly string FRACTION_SIXTEENTHS = "?/16";

		/**
		 * Constant format string for fractions as tenths
		 */
		public static readonly string FRACTION_TENTHS = "?/10";

		/**
		 * Constant format string for fractions as hundredths
		 */
		public static readonly string FRACTION_HUNDREDTHS = "?/100";

		/**
		 * Constructor, taking in the Java compliant number format
		 *
		 * @param format the format string
		 */
		public NumberFormat(string format)
			: base(format)
			{

			// Verify that the format is valid
			DecimalFormat df = new DecimalFormat(format);
			}

		/**
		 * Constructor, taking in the non-Java compliant number format.  This
		 * may be used for currencies and more complex custom formats, which
		 * will not be subject to the standard validation rules.
		 * As there is no validation, there is a resultant risk that the
		 * generated Excel file will be corrupt
		 *
		 * USE THIS CONSTRUCTOR ONLY IF YOU ARE CERTAIN THAT THE NUMBER FORMAT
		 * YOU ARE USING IS EXCEL COMPLIANT
		 *
		 * @param format the format string
		 * @param dummy dummy parameter
		 */
		public NumberFormat(string format, NonValidatingFormat dummy)
			: base(format, dummy)
			{
			}
		}
	}
