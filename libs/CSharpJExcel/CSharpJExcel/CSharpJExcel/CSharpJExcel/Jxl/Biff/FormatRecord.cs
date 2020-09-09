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
using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Jxl.Read.Biff;
using CSharpJExcel.Jxl.Write;
using System.Text;
using CSharpJExcel.Interop;
using System;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * A non-built in format record
	 */
	public class FormatRecord : WritableRecordData, DisplayFormat, CSharpJExcel.Jxl.Format.Format
		{
		/**
		 * The logger
		 */
		//public static Logger logger = Logger.getLogger(FormatRecord.class);

		/**
		 * Initialized flag
		 */
		private bool initialized;

		/**
		 * The raw data
		 */
		private byte[] data;

		/**
		 * The index code
		 */
		private int indexCode;

		/**
		 * The formatting string
		 */
		private string formatString;

		/**
		 * Indicates whether this is a date formatting record
		 */
		private bool date;

		/**
		 * Indicates whether this a number formatting record
		 */
		private bool number;

		/**
		 * The format object
		 */
		private CSharpJExcel.Interop.Format format;

		/**
		 * The date strings to look for
		 */
		private static string[] dateStrings = new string[]
						{
						"dd",
						"mm",
						"yy",
						"hh",
						"ss",
						"m/",
						"/d"
						};

		// Type to distinguish between biff7 and biff8
		public sealed class BiffType
			{
			}

		public static readonly BiffType biff8 = new BiffType();
		public static readonly BiffType biff7 = new BiffType();

		/**
		 * Constructor invoked when copying sheets
		 *
		 * @param fmt the format string
		 * @param refno the index code
		 */
		public FormatRecord(string fmt, int refno)
			: base(Type.FORMAT)
			{
			formatString = fmt;
			indexCode = refno;
			initialized = true;
			}

		/**
		 * Constructor used by writable formats
		 */
		public FormatRecord()
			: base(Type.FORMAT)
			{
			initialized = false;
			}

		/**
		 * Copy constructor - can be invoked by public access
		 *
		 * @param fr the format to copy
		 */
		public FormatRecord(FormatRecord fr)
			: base(Type.FORMAT)
			{
			initialized = false;

			formatString = fr.formatString;
			date = fr.date;
			number = fr.number;
			//    format = (java.text.Format) fr.format.clone();
			}

		/**
		 * Constructs this object from the raw data.  Used when reading in a
		 * format record
		 *
		 * @param t the raw data
		 * @param ws the workbook settings
		 * @param biffType biff type dummy overload
		 */
		public FormatRecord(Record t, WorkbookSettings ws, BiffType biffType)
			: base(t)
			{
			byte[] data = getRecord().getData();
			indexCode = IntegerHelper.getInt(data[0], data[1]);
			initialized = true;

			if (biffType == biff8)
				{
				int numchars = IntegerHelper.getInt(data[2], data[3]);
				if (data[4] == 0)
					formatString = StringHelper.getString(data, numchars, 5, ws);
				else
					formatString = StringHelper.getUnicodeString(data, numchars, 5);
				}
			else
				{
				int numchars = data[2];
				byte[] chars = new byte[numchars];
				System.Array.Copy(data, 3, chars, 0, chars.Length);

				UnicodeEncoding enc = new UnicodeEncoding();
				formatString = enc.GetString(chars);
//				formatString = new string(chars);		uses platform default charset -- should be unicode, right?
				}

			date = false;
			number = false;

			// First see if this is a date format
			for (int i = 0; i < dateStrings.Length; i++)
				{
				string dateString = dateStrings[i];
				if (formatString.IndexOf(dateString) != -1 ||
					formatString.IndexOf(dateString.ToUpper()) != -1)
					{
					date = true;
					break;
					}
				}

			// See if this is number format - look for the # or 0 characters
			if (!date)
				{
				if (formatString.IndexOf('#') != -1 ||
					formatString.IndexOf('0') != -1)
					{
					number = true;
					}
				}
			}

		/**
		 * Used to get the data when writing out the format record
		 *
		 * @return the raw data
		 */
		public override byte[] getData()
			{
			data = new byte[formatString.Length * 2 + 3 + 2];

			IntegerHelper.getTwoBytes(indexCode, data, 0);
			IntegerHelper.getTwoBytes(formatString.Length, data, 2);
			data[4] = (byte)0x1; // unicode indicator
			StringHelper.getUnicodeBytes(formatString, data, 5);

			return data;
			}

		/**
		 * Gets the format index of this record
		 *
		 * @return the format index of this record
		 */
		public int getFormatIndex()
			{
			return indexCode;
			}

		/**
		 * Accessor to see whether this object is initialized or not.
		 *
		 * @return TRUE if this font record has been initialized, FALSE otherwise
		 */
		public bool isInitialized()
			{
			return initialized;
			}

		/**
		 * Sets the index of this record.  Called from the FormattingRecords
		 * object
		 *
		 * @param pos the position of this font in the workbooks font list
		 */

		public void initialize(int pos)
			{
			indexCode = pos;
			initialized = true;
			}

		/**
		 * Replaces all instances of search with replace in the input.  Used for
		 * replacing microsoft number formatting characters with java equivalents
		 *
		 * @param input the format string
		 * @param search the Excel character to be replaced
		 * @param replace the java equivalent
		 * @return the input string with the specified substring replaced
		 */
		protected string replace(string input, string search, string replace)
			{
			string fmtstr = input;
			int pos = fmtstr.IndexOf(search);
			while (pos != -1)
				{
				StringBuilder tmp = new StringBuilder(fmtstr.Substring(0, pos));
				tmp.Append(replace);
				tmp.Append(fmtstr.Substring(pos + search.Length));
				fmtstr = tmp.ToString();
				pos = fmtstr.IndexOf(search);
				}
			return fmtstr;
			}

		/**
		 * Called by the immediate subclass to set the string
		 * once the Java-Excel replacements have been done
		 *
		 * @param s the format string
		 */
		protected void setFormatString(string s)
			{
			formatString = s;
			}

		/**
		 * Sees if this format is a date format
		 *
		 * @return TRUE if this format is a date
		 */
		public bool isDate()
			{
			return date;
			}

		/**
		 * Sees if this format is a number format
		 *
		 * @return TRUE if this format is a number
		 */
		public bool isNumber()
			{
			return number;
			}

		/**
		 * Gets the java equivalent number format for the formatString
		 *
		 * @return The java equivalent of the number format for this object
		 */
		public CSharpJExcel.Interop.NumberFormat getNumberFormat()
			{
			if (format != null && format is CSharpJExcel.Interop.NumberFormat)
				return (CSharpJExcel.Interop.NumberFormat)format;

			try
				{
				string fs = formatString;

				// Replace the Excel formatting characters with java equivalents
				fs = replace(fs, "E+", "E");
				fs = replace(fs, "_)", string.Empty);
				fs = replace(fs, "_", string.Empty);
				fs = replace(fs, "[Red]", string.Empty);
				fs = replace(fs, "\\", string.Empty);

				format = new DecimalFormat(fs);
				}
			catch (Exception e)
				{
				// Something went wrong with the date format - fail silently
				// and return a default value
				format = new DecimalFormat("#.###");
				}

			return (CSharpJExcel.Interop.NumberFormat)format;
			}

		/**
		 * Gets the java equivalent date format for the formatString
		 *
		 * @return The java equivalent of the date format for this object
		 */
		public CSharpJExcel.Interop.DateFormat getDateFormat()
			{
			if (format != null && format is CSharpJExcel.Interop.DateFormat)
				return (CSharpJExcel.Interop.DateFormat)format;

			string fmt = formatString;

			// Replace the AM/PM indicator with an a
			StringBuilder sb = null;
			int pos = fmt.IndexOf("AM/PM");
			while (pos != -1)
				{
				sb = new StringBuilder(fmt.Substring(0, pos));
				sb.Append('a');
				sb.Append(fmt.Substring(pos + 5));
				fmt = sb.ToString();
				pos = fmt.IndexOf("AM/PM");
				}

			// Replace ss.0 with ss.SSS (necessary to always specify milliseconds
			// because of NT)
			pos = fmt.IndexOf("ss.0");
			while (pos != -1)
				{
				sb = new StringBuilder(fmt.Substring(0, pos));
				sb.Append("ss.SSS");

				// Keep going until we run out of zeros
				pos += 4;
				while (pos < fmt.Length && fmt[pos] == '0')
					pos++;

				sb.Append(fmt.Substring(pos));
				fmt = sb.ToString();
				pos = fmt.IndexOf("ss.0");
				}


			// Filter out the backslashes
			sb = new StringBuilder();
			for (int i = 0; i < fmt.Length; i++)
				{
				if (fmt[i] != '\\')
					sb.Append(fmt[i]);
				}

			fmt = sb.ToString();

			// If the date format starts with anything inside square brackets then 
			// filter tham out
			if (fmt[0] == '[')
				{
				int end = fmt.IndexOf(']');
				if (end != -1)
					fmt = fmt.Substring(end + 1);
				}

			// Get rid of some spurious characters that can creep in
			fmt = replace(fmt, ";@", string.Empty);

			// We need to convert the month indicator m, to upper case when we
			// are dealing with dates
			char[] formatBytes = fmt.ToCharArray();

			for (int i = 0; i < formatBytes.Length; i++)
				{
				if (formatBytes[i] == 'm')
					{
					// Firstly, see if the preceding character is also an m.  If so,
					// copy that
					if (i > 0 && (formatBytes[i - 1] == 'm' || formatBytes[i - 1] == 'M'))
						formatBytes[i] = formatBytes[i - 1];
					else
						{
						// There is no easy way out.  We have to deduce whether this an
						// minute or a month?  See which is closest out of the
						// letters H d s or y
						// First, h
						int minuteDist = System.Int32.MaxValue;
						for (int j = i - 1; j > 0; j--)
							{
							if (formatBytes[j] == 'h')
								{
								minuteDist = i - j;
								break;
								}
							}

						for (int j = i + 1; j < formatBytes.Length; j++)
							{
							if (formatBytes[j] == 'h')
								{
								minuteDist = System.Math.Min(minuteDist, j - i);
								break;
								}
							}

						for (int j = i - 1; j > 0; j--)
							{
							if (formatBytes[j] == 'H')
								{
								minuteDist = i - j;
								break;
								}
							}

						for (int j = i + 1; j < formatBytes.Length; j++)
							{
							if (formatBytes[j] == 'H')
								{
								minuteDist = System.Math.Min(minuteDist, j - i);
								break;
								}
							}

						// Now repeat for s
						for (int j = i - 1; j > 0; j--)
							{
							if (formatBytes[j] == 's')
								{
								minuteDist = System.Math.Min(minuteDist, i - j);
								break;
								}
							}
						for (int j = i + 1; j < formatBytes.Length; j++)
							{
							if (formatBytes[j] == 's')
								{
								minuteDist = System.Math.Min(minuteDist, j - i);
								break;
								}
							}
						// We now have the distance of the closest character which could
						// indicate the the m refers to a minute
						// Repeat for d and y
						int monthDist = System.Int32.MaxValue;
						for (int j = i - 1; j > 0; j--)
							{
							if (formatBytes[j] == 'd')
								{
								monthDist = i - j;
								break;
								}
							}

						for (int j = i + 1; j < formatBytes.Length; j++)
							{
							if (formatBytes[j] == 'd')
								{
								monthDist = System.Math.Min(monthDist, j - i);
								break;
								}
							}
						// Now repeat for y
						for (int j = i - 1; j > 0; j--)
							{
							if (formatBytes[j] == 'y')
								{
								monthDist = System.Math.Min(monthDist, i - j);
								break;
								}
							}
						for (int j = i + 1; j < formatBytes.Length; j++)
							{
							if (formatBytes[j] == 'y')
								{
								monthDist = System.Math.Min(monthDist, j - i);
								break;
								}
							}

						if (monthDist < minuteDist)
							{
							// The month indicator is closer, so convert to a capital M
							formatBytes[i] = Char.ToUpper(formatBytes[i]);
							}
						else if ((monthDist == minuteDist) &&
								 (monthDist != System.Int32.MaxValue))
							{
							// They are equidistant.  As a tie-breaker, take the formatting
							// character which precedes the m
							char ind = formatBytes[i - monthDist];
							if (ind == 'y' || ind == 'd')
								{
								// The preceding item indicates a month measure, so convert
								formatBytes[i] = Char.ToUpper(formatBytes[i]);
								}
							}
						}
					}
				}

			try
				{
				this.format = new SimpleDateFormat(new string(formatBytes));
				}
			catch (Exception e)
				{
				// There was a spurious character - fail silently
				this.format = new SimpleDateFormat("dd MM yyyy hh:mm:ss");
				}
			return (CSharpJExcel.Interop.DateFormat)this.format;
			}

		/**
		 * Gets the index code, for use as a hash value
		 *
		 * @return the ifmt code for this cell
		 */
		public int getIndexCode()
			{
			return indexCode;
			}

		/**
		 * Gets the formatting string.
		 *
		 * @return the excel format string
		 */
		public string getFormatString()
			{
			return formatString;
			}

		/**
		 * Indicates whether this formula is a built in
		 *
		 * @return FALSE
		 */
		public bool isBuiltIn()
			{
			return false;
			}

		/**
		 * Standard hash code method
		 * @return the hash code value for this object
		 */
		public override int GetHashCode()
			{
			return formatString.GetHashCode();
			}

		/**
		 * Standard equals method.  This compares the contents of two
		 * format records, and not their indexCodes, which are ignored
		 *
		 * @param o the object to compare
		 * @return TRUE if the two objects are equal, FALSE otherwise
		 */
		public override bool Equals(object o)
			{
			if (o == this)
				return true;

			if (!(o is FormatRecord))
				return false;

			FormatRecord fr = (FormatRecord)o;

			// Initialized format comparison
			if (initialized && fr.initialized)
				{
				// Must be either a number or a date format
				if (date != fr.date ||
					number != fr.number)
					return false;

				return formatString.Equals(fr.formatString);
				}

			// Uninitialized format comparison
			return formatString.Equals(fr.formatString);
			}
		}
	}
