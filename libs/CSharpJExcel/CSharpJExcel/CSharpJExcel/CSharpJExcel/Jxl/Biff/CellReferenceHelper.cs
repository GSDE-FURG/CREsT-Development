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
using CSharpJExcel.Jxl.Biff.Formula;
using System.Text;
using System;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * A helper to transform between excel cell references and
	 * sheet:column:row notation
	 * Because this function will be called when generating a string
	 * representation of a formula, the cell reference will merely
	 * be appened to the string buffer instead of returning a full
	 * blooded string, for performance reasons
	 */
	public sealed class CellReferenceHelper
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(CellReferenceHelper.class);

		/**
		 * The character which indicates whether a reference is fixed
		 */
		private const char fixedInd = '$';

		/**
		 * The character which indicates the sheet name terminator
		 */
		private const char sheetInd = '!';

		/**
		 * Constructor to prevent instantiation
		 */
		private CellReferenceHelper()
			{
			}

		/**
		 * Gets the cell reference 
		 *
		 * @param column
		 * @param row
		 * @param buf
		 */
		public static void getCellReference(int column,int row,StringBuilder buf)
			{
			// Put the column letter into the buffer
			getColumnReference(column,buf);

			// Add the row into the buffer
			buf.Append((row + 1).ToString());
			}

		/**
		 * Overloaded method which prepends $ for absolute reference
		 *
		 * @param column
		 * @param colabs TRUE if the column reference is absolute
		 * @param row
		 * @param rowabs TRUE if the row reference is absolute
		 * @param buf
		 */
		public static void getCellReference(int column,bool colabs,
											int row,bool rowabs,
											StringBuilder buf)
			{
			if (colabs)
				buf.Append(fixedInd);

			// Put the column letter into the buffer
			getColumnReference(column,buf);

			if (rowabs)
				buf.Append(fixedInd);

			// Add the row into the buffer
			buf.Append((row + 1).ToString());
			}

		/**
		 * Gets the column letter corresponding to the 0-based column number
		 * 
		 * @param column the column number
		 * @return the letter for that column number
		 */
		public static string getColumnReference(int column)
			{
			StringBuilder buf = new StringBuilder();
			getColumnReference(column,buf);
			return buf.ToString();
			}

		/**
		 * Gets the column letter corresponding to the 0-based column number
		 * 
		 * @param column the column number
		 * @param buf the string buffer in which to write the column letter
		 */
		public static void getColumnReference(int column,StringBuilder buf)
			{
			int v = column / 26;
			int r = column % 26;

			StringBuilder tmp = new StringBuilder();
			while (v != 0)
				{
				char col = (char)('A' + r);

				tmp.Append(col);

				r = v % 26 - 1; // subtract one because only rows >26 preceded by A
				v = v / 26;
				}

			char newCol = (char)('A' + r);
			tmp.Append(newCol);

			// Insert into the proper string buffer in reverse order
			for (int i = tmp.Length - 1; i >= 0; i--)
				buf.Append(tmp[i]);
			}

		/**
		 * Gets the fully qualified cell reference given the column, row
		 * external sheet reference etc
		 *
		 * @param sheet
		 * @param column
		 * @param row
		 * @param workbook
		 * @param buf
		 */
		public static void getCellReference
		  (int sheet,int column,int row,
		   ExternalSheet workbook,StringBuilder buf)
			{
			// Quotes are added by the WorkbookParser
			string name = workbook.getExternalSheetName(sheet);
			buf.Append(StringHelper.replace(name, "\'", "\'\'"));
			buf.Append(sheetInd);
			getCellReference(column,row,buf);
			}

		/**
		 * Gets the fully qualified cell reference given the column, row
		 * external sheet reference etc
		 *
		 * @param sheet
		 * @param column
		 * @param colabs TRUE if the column is an absolute reference
		 * @param row
		 * @param rowabs TRUE if the row is an absolute reference
		 * @param workbook
		 * @param buf
		 */
		public static void getCellReference
		  (int sheet,int column,bool colabs,
		   int row,bool rowabs,
		   ExternalSheet workbook,StringBuilder buf)
			{
			// WorkbookParser now appends quotes and escapes apostrophes
			string name = workbook.getExternalSheetName(sheet);
			buf.Append(name);
			buf.Append(sheetInd);
			getCellReference(column,colabs,row,rowabs,buf);
			}

		/**
		 * Gets the fully qualified cell reference given the column, row
		 * external sheet reference etc
		 *
		 * @param sheet
		 * @param column
		 * @param row
		 * @param workbook
		 * @return the cell reference in the form 'Sheet 1'!A1
		 */
		public static string getCellReference
		  (int sheet,int column,int row,
		   ExternalSheet workbook)
			{
			StringBuilder sb = new StringBuilder();
			getCellReference(sheet,column,row,workbook,sb);
			return sb.ToString();
			}


		/**
		 * Gets the cell reference for the specified column and row
		 *
		 * @param column
		 * @param row
		 * @return
		 */
		public static string getCellReference(int column,int row)
			{
			StringBuilder buf = new StringBuilder();
			getCellReference(column,row,buf);
			return buf.ToString();
			}

		/**
		 * Gets the columnn number of the string cell reference
		 *
		 * @param s the string to parse
		 * @return the column portion of the cell reference
		 */
		public static int getColumn(string s)
			{
			int colnum = 0;
			int numindex = getNumberIndex(s);

			string s2 = s.ToUpper();

			int startPos = s.LastIndexOf(sheetInd) + 1;
			if (s[startPos] == fixedInd)
				startPos++;

			int endPos = numindex;
			if (s[numindex - 1] == fixedInd)
				endPos--;

			for (int i = startPos; i < endPos; i++)
				{

				if (i != startPos)
					{
					colnum = (colnum + 1) * 26;
					}
				colnum += (int)s2[i] - (int)'A';
				}

			return colnum;
			}

		/**
		 * Gets the row number of the cell reference
		 */
		public static int getRow(string s)
			{
			try
				{
				return (System.Int32.Parse(s.Substring(getNumberIndex(s))) - 1);
				}
			catch (FormatException e)
				{
				//logger.warn(e,e);
				return 0xffff;
				}
			}

		/**
		 * Finds the position where the first number occurs in the string
		 */
		private static int getNumberIndex(string s)
			{
			// Find the position of the first number
			bool numberFound = false;
			int pos = s.LastIndexOf(sheetInd) + 1;
			char c = '\0';

			while (!numberFound && pos < s.Length)
				{
				c = s[pos];

				if (c >= '0' && c <= '9')
					numberFound = true;
				else
					pos++;
				}

			return pos;
			}

		/**
		 * Sees if the column component is relative or not
		 *
		 * @param s
		 * @return TRUE if the column is relative, FALSE otherwise
		 */
		public static bool isColumnRelative(string s)
			{
			return s[0] != fixedInd;
			}

		/**
		 * Sees if the row component is relative or not
		 *
		 * @param s
		 * @return TRUE if the row is relative, FALSE otherwise
		 */
		public static bool isRowRelative(string s)
			{
			return s[getNumberIndex(s) - 1] != fixedInd;
			}

		/**
		 * Gets the sheet name from the cell reference string
		 *
		 * @param reference
		 * @return the sheet reference
		 */
		public static string getSheet(string reference)
			{
			int sheetPos = reference.LastIndexOf(sheetInd);
			if (sheetPos == -1)
				return string.Empty;

			return reference.Substring(0,sheetPos);
			}
		}
	}
