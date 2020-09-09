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
using System.Text;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A class which contains a number format
	 */
	public class NumberFormatRecord : FormatRecord
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(NumberFormatRecord.class);

		// Dummy class to specify non validation
		public sealed class NonValidatingFormat 
			{ 
			public NonValidatingFormat() 
				{ 
				} 
			};


		/**
		 * Constructor.  Replaces some of the characters in the java number
		 * format string with the appropriate excel format characters
		 * 
		 * @param fmt the number format
		 */
		protected NumberFormatRecord(string fmt)
			: base()
			{
			// Do the replacements in the format string
			string fs = fmt;

			fs = replace(fs, "E0", "E+0");

			fs = trimInvalidChars(fs);

			setFormatString(fs);
			}

		/**
		 * Constructor.  Replaces some of the characters in the java number
		 * format string with the appropriate excel format characters
		 * 
		 * @param fmt the number format
		 */
		protected NumberFormatRecord(string fmt, NonValidatingFormat dummy)
			: base()
			{
			// Do the replacements in the format string
			string fs = fmt;

			fs = replace(fs, "E0", "E+0");

			setFormatString(fs);
			}

		/**
		 * Remove all but the first characters preceding the # or the 0.
		 * Remove all characters after the # or the 0, unless it is a )
		 * 
		 * @param fs the candidate number format
		 * @return the string with spurious characters removed
		 */
		private string trimInvalidChars(string fs)
			{
			int firstHash = fs.IndexOf('#');
			int firstZero = fs.IndexOf('0');
			int firstValidChar = 0;

			if (firstHash == -1 && firstZero == -1)
				{
				// The string is complete nonsense.  Return a default string
				return "#.###";
				}

			if (firstHash != 0 && firstZero != 0 &&
				firstHash != 1 && firstZero != 1)
				{
				// The string is dodgy.  Find the first valid char
				firstHash = firstHash == -1 ? firstHash = System.Int32.MaxValue : firstHash;
				firstZero = firstZero == -1 ? firstZero = System.Int32.MaxValue : firstZero;
				firstValidChar = System.Math.Min(firstHash, firstZero);

				StringBuilder tmp = new StringBuilder();
				tmp.Append(fs[0]);
				tmp.Append(fs.Substring(firstValidChar));
				fs = tmp.ToString();
				}

			// Now strip of everything at the end that isn't a # or 0
			int lastHash = fs.LastIndexOf('#');
			int lastZero = fs.LastIndexOf('0');

			if (lastHash == fs.Length || lastZero == fs.Length)
				return fs;

			// Find the last valid character
			int lastValidChar = System.Math.Max(lastHash, lastZero);

			// Check for the existence of a ) or %
			while ((fs.Length > lastValidChar + 1) &&
				   (fs[lastValidChar + 1] == ')' ||
					(fs[lastValidChar + 1] == '%')))
				{
				lastValidChar++;
				}

			return fs.Substring(0, lastValidChar + 1);
			}
		}
	}