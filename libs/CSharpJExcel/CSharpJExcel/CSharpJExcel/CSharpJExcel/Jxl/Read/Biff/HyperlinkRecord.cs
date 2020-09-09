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
using System.IO;
using System.Text;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A number record.  This is stored as 8 bytes, as opposed to the
	 * 4 byte RK record
	 */
	public class HyperlinkRecord : RecordData,Hyperlink
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(HyperlinkRecord.class);

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
		private FileInfo file;

		/**
		 * The location in this workbook referred to by this hyperlink
		 */
		private string location;

		/**
		 * The range of cells which activate this hyperlink
		 */
		private SheetRangeImpl range;

		/**
		 * The type of this hyperlink
		 */
		private LinkType linkType;

		/**
		 * The excel type of hyperlink
		 */
		public sealed class LinkType 
			{ 
			};

		private readonly LinkType urlLink = new LinkType();
		private readonly LinkType fileLink = new LinkType();
		private readonly LinkType workbookLink = new LinkType();
		private readonly LinkType unknown = new LinkType();

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 * @param s the sheet
		 * @param ws the workbook settings
		 */
		public HyperlinkRecord(Record t,Sheet s,WorkbookSettings ws)
			: base(t)
			{
			linkType = unknown;

			byte[] data = getRecord().getData();

			// Build up the range of cells occupied by this hyperlink
			firstRow = IntegerHelper.getInt(data[0],data[1]);
			lastRow = IntegerHelper.getInt(data[2],data[3]);
			firstColumn = IntegerHelper.getInt(data[4],data[5]);
			lastColumn = IntegerHelper.getInt(data[6],data[7]);
			range = new SheetRangeImpl(s,
											 firstColumn,firstRow,
											 lastColumn,lastRow);

			int options = IntegerHelper.getInt(data[28],data[29],data[30],data[31]);

			bool description = (options & 0x14) != 0;
			int startpos = 32;
			int descbytes = 0;
			if (description)
				{
				int descchars = IntegerHelper.getInt(data[startpos],data[startpos + 1],data[startpos + 2],data[startpos + 3]);
				descbytes = descchars * 2 + 4;
				}

			startpos += descbytes;

			bool targetFrame = (options & 0x80) != 0;
			int targetbytes = 0;
			if (targetFrame)
				{
				int targetchars = IntegerHelper.getInt(data[startpos],data[startpos + 1],data[startpos + 2],data[startpos + 3]);
				targetbytes = targetchars * 2 + 4;
				}

			startpos += targetbytes;

			// Try and determine the type
			if ((options & 0x3) == 0x03)
				{
				linkType = urlLink;

				// check the guid monicker
				if (data[startpos] == 0x03)
					linkType = fileLink;
				}
			else if ((options & 0x01) != 0)
				{
				linkType = fileLink;
				// check the guid monicker
				if (data[startpos] == (byte)0xe0)
					{
					linkType = urlLink;
					}
				}
			else if ((options & 0x08) != 0)
				{
				linkType = workbookLink;
				}

			// Try and determine the type
			if (linkType == urlLink)
				{
				string urlString = null;
				try
					{
					startpos += 16;

					// Get the url, ignoring the 0 char at the end
					int bytes = IntegerHelper.getInt(data[startpos],
													 data[startpos + 1],
													 data[startpos + 2],
													 data[startpos + 3]);

					urlString = StringHelper.getUnicodeString(data,bytes / 2 - 1,
															  startpos + 4);
					url = new Uri(urlString);
					}
				catch (UriFormatException e)
					{
					//logger.warn("URL " + urlString + " is malformed.  Trying a file");
					try
						{
						linkType = fileLink;
						file = new FileInfo(urlString);
						}
					catch (Exception e3)
						{
						//logger.warn("Cannot set to file.  Setting a default URL");

						// Set a default URL
						try
							{
							linkType = urlLink;
							url = new Uri("http://www.andykhan.com/jexcelapi/index.html");
							}
						catch (UriFormatException e2)
							{
							// fail silently
							}
						}
					}
				catch (Exception e)
					{
					//StringBuilder sb1 = new StringBuilder();
					//StringBuilder sb2 = new StringBuilder();
					//CellReferenceHelper.getCellReference(firstColumn,firstRow,sb1);
					//CellReferenceHelper.getCellReference(lastColumn,lastRow,sb2);
					//sb1.Insert(0,"Exception when parsing URL ");
					//sb1.Append('\"').Append(sb2.ToString()).Append("\".  Using default.");
					//logger.warn(sb1,e);

					// Set a default URL
					try
						{
						url = new Uri("http://www.andykhan.com/jexcelapi/index.html");
						}
					catch (UriFormatException e2)
						{
						// fail silently
						}
					}
				}
			else if (linkType == fileLink)
				{
				try
					{
					startpos += 16;

					// Get the name of the local file, ignoring the zero character at the
					// end
					int upLevelCount = IntegerHelper.getInt(data[startpos],
															data[startpos + 1]);
					int chars = IntegerHelper.getInt(data[startpos + 2],
													 data[startpos + 3],
													 data[startpos + 4],
													 data[startpos + 5]);
					string fileName = StringHelper.getString(data,chars - 1,
															 startpos + 6,ws);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < upLevelCount; i++)
						{
						sb.Append("..\\");
						}

					sb.Append(fileName);

					file = new FileInfo(sb.ToString());
					}
				catch (Exception e)
					{
					//logger.warn("Exception when parsing file " + e.getClass().getName() + ".");
					file = new FileInfo(".");
					}
				}
			else if (linkType == workbookLink)
				{
				int chars = IntegerHelper.getInt(data[32],data[33],data[34],data[35]);
				location = StringHelper.getUnicodeString(data,chars - 1,36);
				}
			else
				{
				// give up
				//logger.warn("Cannot determine link type");
				return;
				}
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
		public virtual FileInfo getFile()
			{
			return file;
			}

		/**
		 * Exposes the base class method.  This is used when copying hyperlinks
		 *
		 * @return the Record data
		 */
		public override Record getRecord()
			{
			return base.getRecord();
			}

		/**
		 * Gets the range of cells which activate this hyperlink
		 * The get sheet index methods will all return -1, because the
		 * cells will all be present on the same sheet
		 *
		 * @return the range of cells which activate the hyperlink
		 */
		public Range getRange()
			{
			return range;
			}

		/**
		 * Gets the location referenced by this hyperlink
		 *
		 * @return the location
		 */
		public string getLocation()
			{
			return location;
			}
		}
	}







