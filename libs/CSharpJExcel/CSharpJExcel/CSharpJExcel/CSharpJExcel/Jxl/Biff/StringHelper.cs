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
using System.Text;
using System;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Helper function to convert Java string objects to and from the byte
	 * representations
	 */
	public sealed class StringHelper
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(StringHelper.class);

		// Due to a a Sun bug in some versions of JVM 1.4, the UnicodeLittle
		// encoding doesn't always work.  Making this a public static field
		// enables client code access to this (but in an undocumented and
		// unsupported fashion).  Suggested alternative values for this 
		// are  "UTF-16LE" or "UnicodeLittleUnmarked"
		public static string UNICODE_ENCODING = "UnicodeLittle";

		/**
		 * Private default constructor to prevent instantiation
		 */
		private StringHelper()
			{
			}

		/**
		 * Gets the bytes of the specified string.  This will simply return the ASCII
		 * values of the characters in the string
		 *
		 * @param s the string to convert into bytes
		 * @return the ASCII values of the characters in the string
		 * @deprecated
		 */
		public static byte[] getBytes(string s)
			{
			System.Text.ASCIIEncoding enc = new System.Text.ASCIIEncoding();
			return enc.GetBytes(s);
			}

		/**
		 * Gets the bytes of the specified string.  This will simply return the ASCII
		 * values of the characters in the string
		 *
		 * @param s the string to convert into bytes
		 * @return the ASCII values of the characters in the string
		 */
		public static byte[] getBytes(string s,WorkbookSettings ws)
			{
			try
				{
				System.Text.ASCIIEncoding enc = new System.Text.ASCIIEncoding();
				return enc.GetBytes(s);

				// CML - not sure this is right
				//System.Text.Encoding encoding = System.Text.Encoding.GetEncoding(ws.getEncoding());
				//return encoding.GetEncoder().GetBytes(s);
//				return s.getBytes(ws.getEncoding());
				}
			catch (Exception e)
				{
				// fail silently
				return null;
				}
			}

		/**
		 * Converts the string into a little-endian array of Unicode bytes
		 *
		 * @param s the string to convert
		 * @return the unicode values of the characters in the string
		 */
		public static byte[] getUnicodeBytes(string s)
			{
			try
				{
				System.Text.UnicodeEncoding enc = new System.Text.UnicodeEncoding();
				byte[] b = enc.GetBytes(s);
//				byte[] b = s.getBytes(UNICODE_ENCODING);

				// Sometimes this method writes out the unicode
				// identifier
				if (b.Length == (s.Length * 2 + 2))
					{
					byte[] b2 = new byte[b.Length - 2];
					Array.Copy(b,2,b2,0,b2.Length);
					b = b2;
					}

				return b;
				}
			catch (Exception e)
				{
				// Fail silently
				return null;
				}
			}


		/**
		 * Gets the ASCII bytes from the specified string and places them in the
		 * array at the specified position
		 *
		 * @param pos the position at which to place the converted data
		 * @param s the string to convert
		 * @param d the byte array which will contain the converted string data
		 */
		public static void getBytes(string s,byte[] d,int pos)
			{
			byte[] b = getBytes(s);
			System.Array.Copy(b,0,d,pos,b.Length);
			}

		/**
		 * Inserts the unicode byte representation of the specified string into the
		 * array passed in
		 *
		 * @param pos the position at which to insert the converted data
		 * @param s the string to convert
		 * @param d the byte array which will hold the string data
		 */
		public static void getUnicodeBytes(string s,byte[] d,int pos)
			{
			byte[] b = getUnicodeBytes(s);
			System.Array.Copy(b,0,d,pos,b.Length);
			}

		/**
		 * Gets a string from the data array using the character encoding for
		 * this workbook
		 *
		 * @param pos The start position of the string
		 * @param length The number of bytes to transform into a string
		 * @param d The byte data
		 * @param ws the workbook settings
		 * @return the string built up from the raw bytes
		 */
		public static string getString(byte[] d,int length,int pos,WorkbookSettings ws)
			{
			if (length == 0)
				return string.Empty;  // Reduces number of new Strings

			try
				{
				// convert local encoding to UTF-8 then decode them....
				string encoding = ws.getEncoding();
				if (encoding == null)
					encoding = "us-ascii";
				byte [] b = Encoding.Convert(Encoding.GetEncoding(encoding),Encoding.UTF8,d,pos,length);
                return Encoding.UTF8.GetString(b,0,b.Length);

				//      byte[] b = new byte[length];
				//      System.Array.Copy(d, pos, b, 0, length);
				//      return new string(b, ws.getEncoding());
				}
			catch (Exception e)
				{
				//logger.warn(e.ToString());
				return string.Empty;
				}
			}

		/**
		 * Gets a string from the data array
		 *
		 * @param pos The start position of the string
		 * @param length The number of characters to be converted into a string
		 * @param d The byte data
		 * @return the string built up from the unicode characters
		 */
		public static string getUnicodeString(byte[] d,int length,int pos)
			{
			try
				{
				byte[] b = new byte[length * 2];

				for (int count = 0; count < b.Length; count++)
					b[count] = d[pos + count];

				//System.Array.Copy(d,pos,b,0,length * 2);
				
				string s = Encoding.Unicode.GetString(b);
				// CML: Have been receiving strings with null terminators on them -- remove them?
				if (s.IndexOf('\0') >= 0)
					s = s.Substring(0, s.IndexOf('\0'));
				return s;
				}
			catch (Exception e)
				{
				// Fail silently
				return string.Empty;
				}
			}

		/**
		 * Gets a string from the data array
		 *
		 * @param pos The start position of the string
		 * @param length The number of characters to be converted into a string
		 * @param d The byte data
		 * @return the string built up from the unicode characters
		 */
		public static string getUTF8String(byte[] d, int length, int pos)
			{
			try
				{
				byte[] b = new byte[length * 2];
				System.Array.Copy(d, pos, b, 0, length * 2);

				return Encoding.UTF8.GetString(b);
				}
			catch (Exception e)
				{
				// Fail silently
				return string.Empty;
				}
			}

		/**
		 * Replaces all instances of search with replace in the input.  
		 * Even though later versions of java can use string.replace()
		 * this is included Java 1.2 compatibility
		 *
		 * @param input the format string
		 * @param search the Excel character to be replaced
		 * @param replace the java equivalent
		 * @return the input string with the specified substring replaced
		 */
		public static string replace(string input,string search,string replace)
			{
			string fmtstr = input;
			int pos = fmtstr.IndexOf(search);
			while (pos != -1)
				{
				StringBuilder tmp = new StringBuilder(fmtstr.Substring(0,pos));
				tmp.Append(replace);
				tmp.Append(fmtstr.Substring(pos + search.Length));
				fmtstr = tmp.ToString();
				pos = fmtstr.IndexOf(search,pos + replace.Length);
				}
			return fmtstr;
			}
		}
	}



