/*********************************************************************
*
*      Copyright (C) 2005 Andrew Khan
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

using System.Text;
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * A nested class to hold range information
	 */
	public class ColumnRange3d : Area3d
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(ColumnRange3d.class);

		/**
		 * A handle to the workbook
		 */
		private ExternalSheet workbook;

		/**
		 * The sheet number
		 */
		private int sheet;

		/**
		 * Constructor
		 *
		 * @param es the external sheet
		 */
		public ColumnRange3d(ExternalSheet es)
			: base(es)
			{
			workbook = es;
			}

		/**
		 * Constructor invoked when parsing a string formula
		 *
		 * @param s the string to parse
		 * @param es the external sheet
		 * @exception FormulaException
		 */
		public ColumnRange3d(string s,ExternalSheet es)
			: base(es)
			{
			workbook = es;
			int seppos = s.LastIndexOf(":");
			Assert.verify(seppos != -1);
			string startcell = s.Substring(0,seppos);
			string endcell = s.Substring(seppos + 1);

			// Get the the start cell details
			int sep = s.IndexOf('!');
			string cellString = s.Substring(sep + 1,seppos);
			int columnFirst = CellReferenceHelper.getColumn(cellString);
			int rowFirst = 0;

			// Get the sheet index
			string sheetName = s.Substring(0,sep);
			int sheetNamePos = sheetName.LastIndexOf(']');

			// Remove single quotes, if they exist
			if (sheetName[0] == '\'' &&
				sheetName[sheetName.Length - 1] == '\'')
				{
				sheetName = sheetName.Substring(1,sheetName.Length - 1);
				}

			sheet = es.getExternalSheetIndex(sheetName);

			if (sheet < 0)
				{
				throw new FormulaException(FormulaException.SHEET_REF_NOT_FOUND,sheetName);
				}

			// Get the last cell index
			int columnLast = CellReferenceHelper.getColumn(endcell);
			int rowLast = 0xffff;

			bool columnFirstRelative = true;
			bool rowFirstRelative = true;
			bool columnLastRelative = true;
			bool rowLastRelative = true;

			setRangeData(sheet,columnFirst,columnLast,rowFirst,rowLast,
						 columnFirstRelative,rowFirstRelative,
						 columnLastRelative,rowLastRelative);
			}

		/**
		 * Gets the string representation of this column range
		 *
		 * @param buf the string buffer to append to
		 */
		public override void getString(StringBuilder buf)
			{
			buf.Append('\'');
			buf.Append(workbook.getExternalSheetName(sheet));
			buf.Append('\'');
			buf.Append('!');

			CellReferenceHelper.getColumnReference(getFirstColumn(),buf);
			buf.Append(':');
			CellReferenceHelper.getColumnReference(getLastColumn(),buf);
			}
		}
	}
