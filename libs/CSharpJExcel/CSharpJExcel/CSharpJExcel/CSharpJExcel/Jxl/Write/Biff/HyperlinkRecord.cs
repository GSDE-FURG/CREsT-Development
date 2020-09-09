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
using System;
using System.Text;
using CSharpJExcel.Jxl.Common;
using System.Collections;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A hyperlink
	 */
	public class HyperlinkRecord : WritableRecordData
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(HyperlinkRecord.class);

		/**
		 * The first row
		 */
		private int firstRow;
		/**
		 * The last row
		 */
		private int lastRow;
		/**
		 * The first column
		 */
		private int firstColumn;
		/**
		 * The last column
		 */
		private int lastColumn;

		/**
		 * The URL referred to by this hyperlink
		 */
		private Uri url;

		/**
		 * The local file referred to by this hyperlink
		 */
		private System.IO.FileInfo file;

		/**
		 * The location in this workbook referred to by this hyperlink
		 */
		private string location;

		/**
		 * The cell contents of the cell which activate this hyperlink
		 */
		private string contents;

		/**
		 * The type of this hyperlink
		 */
		private LinkType linkType;

		/**
		 * The data for this hyperlink
		 */
		private byte[] data;

		/**
		 * The range of this hyperlink.  When creating a hyperlink, this will
		 * be null until the hyperlink is added to the sheet
		 */
		private Range range;

		/**
		 * The sheet containing this hyperlink
		 */
		private WritableSheet sheet;

		/**
		 * Indicates whether this record has been modified since it was copied
		 */
		private bool modified;

		/**
		 * The excel type of hyperlink
		 */
		public sealed class LinkType 
			{
			private string _type;

			public LinkType(string Type)
				{
				_type = Type;
				}
			};

		private readonly LinkType urlLink = new LinkType("url");
		private readonly LinkType fileLink = new LinkType("file");
		private readonly LinkType uncLink = new LinkType("unc");
		private readonly LinkType workbookLink = new LinkType("workbook");
		private readonly LinkType unknown = new LinkType("unknown");

		/**
		 * Constructs this object from the readable spreadsheet
		 *
		 * @param hl the hyperlink from the read spreadsheet
		 */
		protected HyperlinkRecord(Hyperlink h, WritableSheet s)
			: base(CSharpJExcel.Jxl.Biff.Type.HLINK)
			{
			if (h is CSharpJExcel.Jxl.Read.Biff.HyperlinkRecord)
				copyReadHyperlink(h, s);
			else
				copyWritableHyperlink(h, s);
			}

		/**
		 * Copies a hyperlink read in from a read only sheet
		 */
		private void copyReadHyperlink(Hyperlink h, WritableSheet s)
			{
			CSharpJExcel.Jxl.Read.Biff.HyperlinkRecord hl = (CSharpJExcel.Jxl.Read.Biff.HyperlinkRecord)h;

			data = hl.getRecord().getData();
			sheet = s;

			// Populate this hyperlink with the copied data
			firstRow = hl.getRow();
			firstColumn = hl.getColumn();
			lastRow = hl.getLastRow();
			lastColumn = hl.getLastColumn();
			range = new SheetRangeImpl(s,
											 firstColumn, firstRow,
											 lastColumn, lastRow);

			linkType = unknown;

			if (hl.isFile())
				{
				linkType = fileLink;
				file = hl.getFile();
				}
			else if (hl.isURL())
				{
				linkType = urlLink;
				url = hl.getURL();
				}
			else if (hl.isLocation())
				{
				linkType = workbookLink;
				location = hl.getLocation();
				}

			modified = false;
			}

		/**
		 * Copies a hyperlink read in from a writable sheet.
		 * Used when copying writable sheets
		 *
		 * @param hl the hyperlink from the read spreadsheet
		 */
		private void copyWritableHyperlink(Hyperlink hl, WritableSheet s)
			{
			HyperlinkRecord h = (HyperlinkRecord)hl;

			firstRow = h.firstRow;
			lastRow = h.lastRow;
			firstColumn = h.firstColumn;
			lastColumn = h.lastColumn;

			if (h.url != null)
				{
				try
					{
					url = new Uri(h.url.ToString());
					}
				catch (UriFormatException e)
					{
					// should never get a malformed url as a result url.ToString()
					Assert.verify(false);
					}
				}

			if (h.file != null)
				file = new System.IO.FileInfo(h.file.FullName);

			location = h.location;
			contents = h.contents;
			linkType = h.linkType;
			modified = true;

			sheet = s;
			range = new SheetRangeImpl(s,firstColumn, firstRow,lastColumn, lastRow);
			}

		/**
		 * Constructs a URL hyperlink to a range of cells
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param lastcol the last column which activates this hyperlink
		 * @param lastrow the last row which activates this hyperlink
		 * @param url the hyperlink
		 * @param desc the description
		 */
		protected HyperlinkRecord(int col, int row,
								  int lastcol, int lastrow,
								  Uri url,
								  string desc)
			: base(CSharpJExcel.Jxl.Biff.Type.HLINK)
			{
			firstColumn = col;
			firstRow = row;

			lastColumn = System.Math.Max(firstColumn, lastcol);
			lastRow = System.Math.Max(firstRow, lastrow);

			this.url = url;
			contents = desc;

			linkType = urlLink;

			modified = true;
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
		protected HyperlinkRecord(int col, int row, int lastcol, int lastrow,
								  System.IO.FileInfo file, string desc)
			: base(CSharpJExcel.Jxl.Biff.Type.HLINK)
			{
			firstColumn = col;
			firstRow = row;

			lastColumn = System.Math.Max(firstColumn, lastcol);
			lastRow = System.Math.Max(firstRow, lastrow);
			contents = desc;

			this.file = file;

			string fileName = file.FullName.Replace('/', '\\');

			if (fileName.StartsWith("\\\\"))
				linkType = uncLink;
			else
				linkType = fileLink;

			modified = true;
			}

		/**
		 * Constructs a hyperlink to some cells within this workbook
		 *
		 * @param col the column containing this hyperlink
		 * @param row the row containing this hyperlink
		 * @param lastcol the last column which activates this hyperlink
		 * @param lastrow the last row which activates this hyperlink
		 * @param desc the contents of the cell which describe this hyperlink
		 * @param sheet the sheet containing the cells to be linked to
		 * @param destcol the column number of the first destination linked cell
		 * @param destrow the row number of the first destination linked cell
		 * @param lastdestcol the column number of the last destination linked cell
		 * @param lastdestrow the row number of the last destination linked cell
		 */
		protected HyperlinkRecord(int col, int row,
								  int lastcol, int lastrow,
								  string desc,
								  WritableSheet s,
								  int destcol, int destrow,
								  int lastdestcol, int lastdestrow)
			: base(CSharpJExcel.Jxl.Biff.Type.HLINK)
			{
			firstColumn = col;
			firstRow = row;

			lastColumn = System.Math.Max(firstColumn, lastcol);
			lastRow = System.Math.Max(firstRow, lastrow);

			setLocation(s, destcol, destrow, lastdestcol, lastdestrow);
			contents = desc;

			linkType = workbookLink;

			modified = true;
			}

		/**
		 * Determines whether this is a hyperlink to a file
		 * 
		 * @return TRUE if this is a hyperlink to a file, FALSE otherwise
		 */
		public bool isFile()
			{
			return linkType == fileLink;
			}

		/**
		 * Determines whether this is a hyperlink to a UNC
		 * 
		 * @return TRUE if this is a hyperlink to a UNC, FALSE otherwise
		 */
		public bool isUNC()
			{
			return linkType == uncLink;
			}

		/**
		 * Determines whether this is a hyperlink to a web resource
		 *
		 * @return TRUE if this is a URL
		 */
		public bool isURL()
			{
			return linkType == urlLink;
			}

		/**
		 * Determines whether this is a hyperlink to a location in this workbook
		 *
		 * @return TRUE if this is a link to an internal location
		 */
		public bool isLocation()
			{
			return linkType == workbookLink;
			}

		/**
		 * Returns the row number of the top left cell
		 * 
		 * @return the row number of this cell
		 */
		public int getRow()
			{
			return firstRow;
			}

		/**
		 * Returns the column number of the top left cell
		 * 
		 * @return the column number of this cell
		 */
		public int getColumn()
			{
			return firstColumn;
			}

		/**
		 * Returns the row number of the bottom right cell
		 * 
		 * @return the row number of this cell
		 */
		public int getLastRow()
			{
			return lastRow;
			}

		/**
		 * Returns the column number of the bottom right cell
		 * 
		 * @return the column number of this cell
		 */
		public int getLastColumn()
			{
			return lastColumn;
			}

		/**
		 * Gets the URL referenced by this Hyperlink
		 *
		 * @return the URL, or NULL if this hyperlink is not a URL
		 */
		public Uri getURL()
			{
			return url;
			}

		/**
		 * Returns the local file eferenced by this Hyperlink
		 *
		 * @return the file, or NULL if this hyperlink is not a file
		 */
		public virtual System.IO.FileInfo getFile()
			{
			return file;
			}

		/**
		 * Gets the binary data to be written to the output file
		 * 
		 * @return the data to write to file
		 */
		public override byte[] getData()
			{
			if (!modified)
				{
				return data;
				}

			// Build up the jxl.common.data
			byte[] commonData = new byte[32];

			// Set the range of cells this hyperlink applies to
			IntegerHelper.getTwoBytes(firstRow, commonData, 0);
			IntegerHelper.getTwoBytes(lastRow, commonData, 2);
			IntegerHelper.getTwoBytes(firstColumn, commonData, 4);
			IntegerHelper.getTwoBytes(lastColumn, commonData, 6);

			// Some inexplicable byte sequence
			commonData[8] = (byte)0xd0;
			commonData[9] = (byte)0xc9;
			commonData[10] = (byte)0xea;
			commonData[11] = (byte)0x79;
			commonData[12] = (byte)0xf9;
			commonData[13] = (byte)0xba;
			commonData[14] = (byte)0xce;
			commonData[15] = (byte)0x11;
			commonData[16] = (byte)0x8c;
			commonData[17] = (byte)0x82;
			commonData[18] = (byte)0x0;
			commonData[19] = (byte)0xaa;
			commonData[20] = (byte)0x0;
			commonData[21] = (byte)0x4b;
			commonData[22] = (byte)0xa9;
			commonData[23] = (byte)0x0b;
			commonData[24] = (byte)0x2;
			commonData[25] = (byte)0x0;
			commonData[26] = (byte)0x0;
			commonData[27] = (byte)0x0;

			// Set up the option flags to indicate the type of this URL.  There
			// is no description
			int optionFlags = 0;
			if (isURL())
				{
				optionFlags = 3;

				if (contents != null)
					optionFlags |= 0x14;
				}
			else if (isFile())
				{
				optionFlags = 1;

				if (contents != null)
					optionFlags |= 0x14;
				}
			else if (isLocation())
				optionFlags = 8;
			else if (isUNC())
				optionFlags = 259;

			IntegerHelper.getFourBytes(optionFlags, commonData, 28);

			if (isURL())
				data = getURLData(commonData);
			else if (isFile())
				data = getFileData(commonData);
			else if (isLocation())
				data = getLocationData(commonData);
			else if (isUNC())
				data = getUNCData(commonData);

			return data;
			}

		/**
		 * A standard toString method
		 * 
		 * @return the contents of this object as a string
		 */
		public override string ToString()
			{
			if (isFile())
				return file.FullName.Replace('/','\\');
			else if (isURL())
				return url.AbsoluteUri;
			else if (isUNC())
				return file.FullName.Replace('/','\\');
			return string.Empty;
			}

		/**
		 * Gets the range of cells which activate this hyperlink
		 * The get sheet index methods will all return -1, because the
		 * cells will all be present on the same sheet
		 *
		 * @return the range of cells which activate the hyperlink or NULL
		 * if this hyperlink has not been added to the sheet
		 */
		public virtual Range getRange()
			{
			return range;
			}

		/**
		 * Sets the URL of this hyperlink
		 *
		 * @param url the url
		 */
		public virtual void setURL(Uri url)
			{
			Uri prevurl = this.url;
			linkType = urlLink;
			file = null;
			location = null;
			contents = null;
			this.url = url;
			modified = true;

			if (sheet == null)
				{
				// hyperlink has not been added to the sheet yet, so simply return
				return;
				}

			// Change the label on the sheet if it was a string representation of the 
			// URL
			WritableCell wc = sheet.getWritableCell(firstColumn, firstRow);

			if (wc.getType() == CellType.LABEL 
				&& prevurl != null)		// CML - found this condition....
				{
				Label l = (Label)wc;
				string prevurlString = prevurl.ToString();
				string prevurlString2 = string.Empty;
				if (prevurlString[prevurlString.Length - 1] == '/' ||
					prevurlString[prevurlString.Length - 1] == '\\')
					prevurlString2 = prevurlString.Substring(0,prevurlString.Length - 1);

				if (l.getString().Equals(prevurlString) || l.getString().Equals(prevurlString2))
					l.setString(url.ToString());
				}
			}

		/**
		 * Sets the file activated by this hyperlink
		 * 
		 * @param file the file
		 */
		public virtual void setFile(System.IO.FileInfo file)
			{
			linkType = fileLink;
			url = null;
			location = null;
			contents = null;
			this.file = file;
			modified = true;

			if (sheet == null)
				{
				// hyperlink has not been added to the sheet yet, so simply return
				return;
				}

			// Change the label on the sheet
			WritableCell wc = sheet.getWritableCell(firstColumn, firstRow);

			Assert.verify(wc.getType() == CellType.LABEL);

			Label l = (Label)wc;
			l.setString(file.ToString());
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
		public virtual void setLocation(string desc,WritableSheet sheet, int destcol, int destrow, int lastdestcol, int lastdestrow)
			{
			linkType = workbookLink;
			url = null;
			file = null;
			modified = true;
			contents = desc;

			setLocation(sheet, destcol, destrow, lastdestcol, lastdestrow);

			if (sheet == null)
				{
				// hyperlink has not been added to the sheet yet, so simply return
				return;
				}

			// Change the label on the sheet
			WritableCell wc = sheet.getWritableCell(firstColumn, firstRow);

			Assert.verify(wc.getType() == CellType.LABEL);

			Label l = (Label)wc;
			l.setString(desc);
			}

		/**
		  * Initializes the location from the data passed in
		 *
		 * @param sheet the sheet containing the cells to be linked to
		 * @param destcol the column number of the first destination linked cell
		 * @param destrow the row number of the first destination linked cell
		 * @param lastdestcol the column number of the last destination linked cell
		 * @param lastdestrow the row number of the last destination linked cell
		 */
		private void setLocation(WritableSheet sheet,
								 int destcol, int destrow,
								 int lastdestcol, int lastdestrow)
			{
			StringBuilder sb = new StringBuilder();
			sb.Append('\'');

			if (sheet.getName().IndexOf('\'') == -1)
				{
				sb.Append(sheet.getName());
				}
			else
				{
				// sb.append(sheet.getName().replaceAll("'", "''"));

				// Can't use replaceAll as it is only 1.4 compatible, so have to
				// do this the tedious way
				string sheetName = sheet.getName();
				int pos = 0;
				int nextPos = sheetName.IndexOf('\'', pos);

				while (nextPos != -1 && pos < sheetName.Length)
					{
					sb.Append(sheetName.Substring(pos, nextPos));
					sb.Append("''");
					pos = nextPos + 1;
					nextPos = sheetName.IndexOf('\'', pos);
					}
				sb.Append(sheetName.Substring(pos));
				}

			sb.Append('\'');
			sb.Append('!');

			lastdestcol = System.Math.Max(destcol, lastdestcol);
			lastdestrow = System.Math.Max(destrow, lastdestrow);

			CellReferenceHelper.getCellReference(destcol, destrow, sb);
			sb.Append(':');
			CellReferenceHelper.getCellReference(lastdestcol, lastdestrow, sb);

			location = sb.ToString();
			}

		/** 
		 * A row has been inserted, so adjust the range objects accordingly
		 *
		 * @param r the row which has been inserted
		 */
		public void insertRow(int r)
			{
			// This will not be called unless the hyperlink has been added to the
			// sheet
			Assert.verify(sheet != null && range != null);

			if (r > lastRow)
				{
				return;
				}

			if (r <= firstRow)
				{
				firstRow++;
				modified = true;
				}

			if (r <= lastRow)
				{
				lastRow++;
				modified = true;
				}

			if (modified)
				{
				range = new SheetRangeImpl(sheet,
											firstColumn, firstRow,
											lastColumn, lastRow);
				}
			}

		/** 
		 * A column has been inserted, so adjust the range objects accordingly
		 *
		 * @param c the column which has been inserted
		 */
		public void insertColumn(int c)
			{
			// This will not be called unless the hyperlink has been added to the
			// sheet
			Assert.verify(sheet != null && range != null);

			if (c > lastColumn)
				{
				return;
				}

			if (c <= firstColumn)
				{
				firstColumn++;
				modified = true;
				}

			if (c <= lastColumn)
				{
				lastColumn++;
				modified = true;
				}

			if (modified)
				{
				range = new SheetRangeImpl(sheet,
											firstColumn, firstRow,
											lastColumn, lastRow);
				}
			}

		/** 
		 * A row has been removed, so adjust the range objects accordingly
		 *
		 * @param r the row which has been inserted
		 */
		public void removeRow(int r)
			{
			// This will not be called unless the hyperlink has been added to the
			// sheet
			Assert.verify(sheet != null && range != null);

			if (r > lastRow)
				{
				return;
				}

			if (r < firstRow)
				{
				firstRow--;
				modified = true;
				}

			if (r < lastRow)
				{
				lastRow--;
				modified = true;
				}

			if (modified)
				{
				Assert.verify(range != null);
				range = new SheetRangeImpl(sheet,
											firstColumn, firstRow,
											lastColumn, lastRow);
				}
			}

		/** 
		 * A column has been removed, so adjust the range objects accordingly
		 *
		 * @param c the column which has been removed
		 */
		public void removeColumn(int c)
			{
			// This will not be called unless the hyperlink has been added to the
			// sheet
			Assert.verify(sheet != null && range != null);

			if (c > lastColumn)
				{
				return;
				}

			if (c < firstColumn)
				{
				firstColumn--;
				modified = true;
				}

			if (c < lastColumn)
				{
				lastColumn--;
				modified = true;
				}

			if (modified)
				{
				Assert.verify(range != null);
				range = new SheetRangeImpl(sheet,
											firstColumn, firstRow,
											lastColumn, lastRow);
				}
			}

		/**
		 * Gets the hyperlink stream specific to a URL link
		 *
		 * @param cd the data jxl.common.for all types of hyperlink
		 * @return the raw data for a URL hyperlink
		 */
		private byte[] getURLData(byte[] cd)
			{
			string urlString = url.ToString();

			int dataLength = cd.Length + 20 + (urlString.Length + 1) * 2;

			if (contents != null)
				dataLength += 4 + (contents.Length + 1) * 2;

			byte[] d = new byte[dataLength];

			System.Array.Copy(cd, 0, d, 0, cd.Length);

			int urlPos = cd.Length;

			if (contents != null)
				{
				IntegerHelper.getFourBytes(contents.Length + 1, d, urlPos);
				StringHelper.getUnicodeBytes(contents, d, urlPos + 4);
				urlPos += (contents.Length + 1) * 2 + 4;
				}

			// Inexplicable byte sequence
			d[urlPos] = (byte)0xe0;
			d[urlPos + 1] = (byte)0xc9;
			d[urlPos + 2] = (byte)0xea;
			d[urlPos + 3] = (byte)0x79;
			d[urlPos + 4] = (byte)0xf9;
			d[urlPos + 5] = (byte)0xba;
			d[urlPos + 6] = (byte)0xce;
			d[urlPos + 7] = (byte)0x11;
			d[urlPos + 8] = (byte)0x8c;
			d[urlPos + 9] = (byte)0x82;
			d[urlPos + 10] = (byte)0x0;
			d[urlPos + 11] = (byte)0xaa;
			d[urlPos + 12] = (byte)0x0;
			d[urlPos + 13] = (byte)0x4b;
			d[urlPos + 14] = (byte)0xa9;
			d[urlPos + 15] = (byte)0x0b;

			// Number of characters in the url, including a zero trailing character
			IntegerHelper.getFourBytes((urlString.Length + 1) * 2, d, urlPos + 16);

			// Put the url into the data string
			StringHelper.getUnicodeBytes(urlString, d, urlPos + 20);

			return d;
			}

		/**
		 * Gets the hyperlink stream specific to a URL link
		 *
		 * @param cd the data jxl.common.for all types of hyperlink
		 * @return the raw data for a URL hyperlink
		 */
		private byte[] getUNCData(byte[] cd)
			{
			string uncString = file.FullName;

			byte[] d = new byte[cd.Length + uncString.Length * 2 + 2 + 4];
			System.Array.Copy(cd, 0, d, 0, cd.Length);

			int urlPos = cd.Length;

			// The length of the unc string, including zero terminator
			int length = uncString.Length + 1;
			IntegerHelper.getFourBytes(length, d, urlPos);

			// Place the string into the stream
			StringHelper.getUnicodeBytes(uncString, d, urlPos + 4);

			return d;
			}

		/**
		 * Gets the hyperlink stream specific to a local file link
		 *
		 * @param cd the data jxl.common.for all types of hyperlink
		 * @return the raw data for a URL hyperlink
		 */
		private byte[] getFileData(byte[] cd)
			{
			// Build up the directory hierarchy in reverse order
			ArrayList path = new ArrayList();
			ArrayList shortFileName = new ArrayList();
			path.Add(file.FullName);
			shortFileName.Add(getShortName(file.Name));

			System.IO.DirectoryInfo parent = file.Directory;
			while (parent != null)
				{
				path.Add(parent.Name);
				shortFileName.Add(getShortName(parent.Name));
				System.IO.FileInfo f = new System.IO.FileInfo(parent.FullName);
				parent = f.Directory;			// get the parent's directory name
				}

			// Deduce the up directory level count and remove the directory from
			// the path
			int upLevelCount = 0;
			int pos = path.Count - 1;
			bool upDir = true;

			while (upDir)
				{
				string s = (string)path[pos];
				if (s.Equals(".."))
					{
					upLevelCount++;
					path.Remove(pos);
					shortFileName.Remove(pos);
					}
				else
					upDir = false;

				pos--;
				}

			StringBuilder filePathSB = new StringBuilder();
			StringBuilder shortFilePathSB = new StringBuilder();

			if (file.FullName.Length > 1 && file.FullName[1] == ':')
				{
				char driveLetter = file.FullName[0];
				if (driveLetter != 'C' && driveLetter != 'c')
					{
					filePathSB.Append(driveLetter);
					filePathSB.Append(':');
					shortFilePathSB.Append(driveLetter);
					shortFilePathSB.Append(':');
					}
				}

			for (int i = path.Count - 1; i >= 0; i--)
				{
				filePathSB.Append((string)path[i]);
				shortFilePathSB.Append((string)shortFileName[i]);

				if (i != 0)
					{
					filePathSB.Append("\\");
					shortFilePathSB.Append("\\");
					}
				}


			string filePath = filePathSB.ToString();
			string shortFilePath = shortFilePathSB.ToString();

			int dataLength = cd.Length +
							 4 + (shortFilePath.Length + 1) + // short file name
							 16 + // inexplicable byte sequence
							 2 + // up directory level count
							 8 + (filePath.Length + 1) * 2 + // long file name
							 24; // inexplicable byte sequence


			if (contents != null)
				dataLength += 4 + (contents.Length + 1) * 2;

			// Copy across the jxl.common.data into the new array
			byte[] d = new byte[dataLength];

			System.Array.Copy(cd, 0, d, 0, cd.Length);

			int filePos = cd.Length;

			// Add in the description text
			if (contents != null)
				{
				IntegerHelper.getFourBytes(contents.Length + 1, d, filePos);
				StringHelper.getUnicodeBytes(contents, d, filePos + 4);
				filePos += (contents.Length + 1) * 2 + 4;
				}

			int curPos = filePos;

			// Inexplicable byte sequence
			d[curPos] = (byte)0x03;
			d[curPos + 1] = (byte)0x03;
			d[curPos + 2] = (byte)0x0;
			d[curPos + 3] = (byte)0x0;
			d[curPos + 4] = (byte)0x0;
			d[curPos + 5] = (byte)0x0;
			d[curPos + 6] = (byte)0x0;
			d[curPos + 7] = (byte)0x0;
			d[curPos + 8] = (byte)0xc0;
			d[curPos + 9] = (byte)0x0;
			d[curPos + 10] = (byte)0x0;
			d[curPos + 11] = (byte)0x0;
			d[curPos + 12] = (byte)0x0;
			d[curPos + 13] = (byte)0x0;
			d[curPos + 14] = (byte)0x0;
			d[curPos + 15] = (byte)0x46;

			curPos += 16;

			// The directory up level count
			IntegerHelper.getTwoBytes(upLevelCount, d, curPos);
			curPos += 2;

			// The number of bytes in the short file name, including zero terminator
			IntegerHelper.getFourBytes((shortFilePath.Length + 1), d, curPos);

			// The short file name
			StringHelper.getBytes(shortFilePath, d, curPos + 4);

			curPos += 4 + (shortFilePath.Length + 1);

			// Inexplicable byte sequence
			d[curPos] = (byte)0xff;
			d[curPos + 1] = (byte)0xff;
			d[curPos + 2] = (byte)0xad;
			d[curPos + 3] = (byte)0xde;
			d[curPos + 4] = (byte)0x0;
			d[curPos + 5] = (byte)0x0;
			d[curPos + 6] = (byte)0x0;
			d[curPos + 7] = (byte)0x0;
			d[curPos + 8] = (byte)0x0;
			d[curPos + 9] = (byte)0x0;
			d[curPos + 10] = (byte)0x0;
			d[curPos + 11] = (byte)0x0;
			d[curPos + 12] = (byte)0x0;
			d[curPos + 13] = (byte)0x0;
			d[curPos + 14] = (byte)0x0;
			d[curPos + 15] = (byte)0x0;
			d[curPos + 16] = (byte)0x0;
			d[curPos + 17] = (byte)0x0;
			d[curPos + 18] = (byte)0x0;
			d[curPos + 19] = (byte)0x0;
			d[curPos + 20] = (byte)0x0;
			d[curPos + 21] = (byte)0x0;
			d[curPos + 22] = (byte)0x0;
			d[curPos + 23] = (byte)0x0;

			curPos += 24;

			// Size of the long file name data in bytes, including inexplicable data 
			// fields
			int size = 6 + filePath.Length * 2;
			IntegerHelper.getFourBytes(size, d, curPos);
			curPos += 4;

			// The number of bytes in the long file name
			// NOT including zero terminator
			IntegerHelper.getFourBytes((filePath.Length) * 2, d, curPos);
			curPos += 4;

			// Inexplicable bytes
			d[curPos] = (byte)0x3;
			d[curPos + 1] = (byte)0x0;

			curPos += 2;

			// The long file name
			StringHelper.getUnicodeBytes(filePath, d, curPos);
			curPos += (filePath.Length + 1) * 2;


			/*
			curPos += 24;
			public int nameLength = filePath.Length * 2;

			// Size of the file link 
			IntegerHelper.getFourBytes(nameLength+6, d, curPos);

			// Number of characters
			IntegerHelper.getFourBytes(nameLength, d, curPos+4);

			// Inexplicable byte sequence
			d[curPos+8] = 0x03;
    
			// The long file name
			StringHelper.getUnicodeBytes(filePath, d, curPos+10);
			*/

			return d;
			}

		/**
		 * Gets the DOS short file name in 8.3 format of the name passed in
		 * 
		 * @param s the name
		 * @return the dos short name
		 */
		private string getShortName(string s)
			{
			int sep = s.IndexOf('.');

			string prefix = null;
			string suffix = null;

			if (sep == -1)
				{
				prefix = s;
				suffix = string.Empty;
				}
			else
				{
				prefix = s.Substring(0, sep);
				suffix = s.Substring(sep + 1);
				}

			if (prefix.Length > 8)
				{
				prefix = prefix.Substring(0, 6) + "~" + (prefix.Length - 8);
				prefix = prefix.Substring(0, 8);
				}

			suffix = suffix.Substring(0, System.Math.Min(3, suffix.Length));

			if (suffix.Length > 0)
				{
				return prefix + '.' + suffix;
				}
			else
				{
				return prefix;
				}
			}

		/**
		 * Gets the hyperlink stream specific to a location link
		 *
		 * @param cd the data jxl.common.for all types of hyperlink
		 * @return the raw data for a URL hyperlink
		 */
		private byte[] getLocationData(byte[] cd)
			{
			byte[] d = new byte[cd.Length + 4 + (location.Length + 1) * 2];
			System.Array.Copy(cd, 0, d, 0, cd.Length);

			int locPos = cd.Length;

			// The number of chars in the location string, plus a 0 terminator
			IntegerHelper.getFourBytes(location.Length + 1, d, locPos);

			// Get the location
			StringHelper.getUnicodeBytes(location, d, locPos + 4);

			return d;
			}


		/**
		 * Initializes the range when this hyperlink is added to the sheet
		 *
		 * @param s the sheet containing this hyperlink
		 */
		public void initialize(WritableSheet s)
			{
			sheet = s;
			range = new SheetRangeImpl(s,
									   firstColumn, firstRow,
									   lastColumn, lastRow);
			}

		/**
		 * Called by the worksheet.  Gets the string contents to put into the cell
		 * containing this hyperlink
		 *
		 * @return the string contents for the hyperlink cell
		 */
		public virtual string getContents()
			{
			return contents;
			}

		/**
		 * Sets the description
		 *
		 * @param desc the description
		 */
		protected void setContents(string desc)
			{
			contents = desc;
			modified = true;
			}
		}
	}








