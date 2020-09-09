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
using System;
using System.IO;


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * A writable hyperlink.  Provides API to modify the contents of the hyperlink
	 */
	public class WritableHyperlink : HyperlinkRecord, Hyperlink
		{
		/**
		 * Constructor used internally by the worksheet when making a copy
		 * of worksheet
		 *
		 * @param h the hyperlink being read in
		 * @param ws the writable sheet containing the hyperlink
		 */
		public WritableHyperlink(Hyperlink h, WritableSheet ws)
			: base(h, ws)
			{
			}

		/**
		 * Constructs a URL hyperlink in a single cell
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param url the hyperlink
		 */
		public WritableHyperlink(int col, int row, Uri url)
			: this(col, row, col, row, url)
			{
			}

		/**
		 * Constructs a url hyperlink to a range of cells
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param lastcol the last column which activates this hyperlink
		 * @param lastrow the last row which activates this hyperlink
		 * @param url the hyperlink
		 */
		public WritableHyperlink(int col, int row, int lastcol, int lastrow, Uri url)
			: this(col, row, lastcol, lastrow, url, null)
			{
			}

		/**
		 * Constructs a url hyperlink to a range of cells
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param lastcol the last column which activates this hyperlink
		 * @param lastrow the last row which activates this hyperlink
		 * @param url the hyperlink
		 * @param desc the description text to place in the cell
		 */
		public WritableHyperlink(int col,
								 int row,
								 int lastcol,
								 int lastrow,
								 Uri url,
								 string desc)
			: base(col, row, lastcol, lastrow, url, desc)
			{
			}

		/**
		 * Constructs a file hyperlink in a single cell
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param file the hyperlink
		 */
		public WritableHyperlink(int col, int row, FileInfo file)
			: this(col, row, col, row, file, null)
			{
			}

		/**
		 * Constructs a file hyperlink in a single cell
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param file the hyperlink
		 * @param desc the hyperlink description
		 */
		public WritableHyperlink(int col, int row, FileInfo file, string desc)
			: this(col, row, col, row, file, desc)
			{
			}

		/**
		 * Constructs a File hyperlink to a range of cells
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param lastcol the last column which activates this hyperlink
		 * @param lastrow the last row which activates this hyperlink
		 * @param file the hyperlink
		 */
		public WritableHyperlink(int col, int row, int lastcol, int lastrow,
								 FileInfo file)
			: base(col, row, lastcol, lastrow, file, null)
			{
			}

		/**
		 * Constructs a File hyperlink to a range of cells
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param lastcol the last column which activates this hyperlink
		 * @param lastrow the last row which activates this hyperlink
		 * @param file the hyperlink
		 * @param desc the description
		 */
		public WritableHyperlink(int col,
								 int row,
								 int lastcol,
								 int lastrow,
								 FileInfo file,
								 string desc)
			: base(col, row, lastcol, lastrow, file, desc)
			{
			}

		/**
		 * Constructs a hyperlink to some cells within this workbook
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param desc the cell contents for this hyperlink
		 * @param sheet the sheet containing the cells to be linked to
		 * @param destcol the column number of the first destination linked cell
		 * @param destrow the row number of the first destination linked cell
		 */
		public WritableHyperlink(int col, int row,
								 string desc,
								 WritableSheet sheet,
								 int destcol, int destrow)
			: this(col, row, col, row, desc, sheet, destcol, destrow, destcol, destrow)
			{
			}

		/**
		 * Constructs a hyperlink to some cells within this workbook
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param lastcol the last column which activates this hyperlink
		 * @param lastrow the last row which activates this hyperlink
		 * @param desc the cell contents for this hyperlink
		 * @param sheet the sheet containing the cells to be linked to
		 * @param destcol the column number of the first destination linked cell
		 * @param destrow the row number of the first destination linked cell
		 * @param lastdestcol the column number of the last destination linked cell
		 * @param lastdestrow the row number of the last destination linked cell
		 */
		public WritableHyperlink(int col, int row,
								 int lastcol, int lastrow,
								 string desc,
								 WritableSheet sheet,
								 int destcol, int destrow,
								 int lastdestcol, int lastdestrow)
			: base(col, row, lastcol, lastrow, desc,sheet, destcol, destrow, lastdestcol, lastdestrow)
			{
			}

		/**
		 * Sets the URL of this hyperlink
		 *
		 * @param url the url
		 */
		public override void setURL(Uri url)
			{
			base.setURL(url);
			}

		/**
		 * Sets the file activated by this hyperlink
		 *
		 * @param file the file
		 */
		public override void setFile(FileInfo file)
			{
			base.setFile(file);
			}

		/**
		 * Sets the description to appear in the hyperlink cell
		 *
		 * @param desc the description
		 */
		public void setDescription(string desc)
			{
			base.setContents(desc);
			}

		/**
		 * Sets the location of the cells to be linked to within this workbook
		 *
		 * @param desc the label describing the link
		 * @param sheet the sheet containing the cells to be linked to
		 * @param destcol the column number of the first destination linked cell
		 * @param destrow the row number of the first destination linked cell
		 * @param lastdestcol the column number of the last destination linked cell
		 * @param lastdestrow the row number of the last destination linked cell
		 */
		public override void setLocation(string desc, WritableSheet sheet, int destcol, int destrow, int lastdestcol, int lastdestrow)
			{
			base.setLocation(desc, sheet, destcol, destrow, lastdestcol, lastdestrow);
			}
		}
	}

