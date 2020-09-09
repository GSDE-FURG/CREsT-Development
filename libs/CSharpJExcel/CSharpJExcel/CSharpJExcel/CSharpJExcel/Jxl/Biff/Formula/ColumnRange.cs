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
	 * A class to hold range information across two entire columns
	 */
	public class ColumnRange : Area
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(ColumnRange.class);

		/**
		 * Constructor
		 */
		public ColumnRange()
			: base()
			{
			}

		/**
		 * Constructor invoked when parsing a string formula
		 *
		 * @param s the string to parse
		 */
		public ColumnRange(string s) 
			: base()
			{
			int seppos = s.IndexOf(":");
			Assert.verify(seppos != -1);
			string startcell = s.Substring(0,seppos);
			string endcell = s.Substring(seppos + 1);

			int columnFirst = CellReferenceHelper.getColumn(startcell);
			int rowFirst = 0;
			int columnLast = CellReferenceHelper.getColumn(endcell);
			int rowLast = 0xffff;

			bool columnFirstRelative =
			  CellReferenceHelper.isColumnRelative(startcell);
			bool rowFirstRelative = false;
			bool columnLastRelative = CellReferenceHelper.isColumnRelative(endcell);
			bool rowLastRelative = false;

			setRangeData(columnFirst,columnLast,
						 rowFirst,rowLast,
						 columnFirstRelative,columnLastRelative,
						 rowFirstRelative,rowLastRelative);
			}

		/**
		 * Gets the string representation of this item
		 *
		 * @param buf the string buffer
		 */
		public override void getString(StringBuilder buf)
			{
			CellReferenceHelper.getColumnReference(getFirstColumn(),buf);
			buf.Append(':');
			CellReferenceHelper.getColumnReference(getLastColumn(),buf);
			}
		}
	}
