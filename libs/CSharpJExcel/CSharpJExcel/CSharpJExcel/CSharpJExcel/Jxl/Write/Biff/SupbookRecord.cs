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
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * Stores the supporting workbook information.  For files written by
	 * JExcelApi this will only reference internal sheets
	 */
	public class SupbookRecord : WritableRecordData
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(SupbookRecord.class);

		/**
		 * The type of this supbook record
		 */
		private SupbookType type;

		/**
		 * The data to be written to the binary file
		 */
		private byte[] data;

		/**
		 * The number of sheets - internal & external supbooks only
		 */
		private int numSheets;

		/**
		 * The name of the external file
		 */
		private string fileName;

		/**
		 * The names of the external sheets
		 */
		private string[] sheetNames;

		/**
		 * The workbook settings
		 */
		private WorkbookSettings workbookSettings;

		/**
		 * The type of supbook this refers to
		 */
		public sealed class SupbookType 
			{
			string _name;

			public SupbookType(string Name)
				{
				_name = Name;
				}
			};

		public static readonly SupbookType INTERNAL = new SupbookType("internal");
		public static readonly SupbookType EXTERNAL = new SupbookType("external");
		public static readonly SupbookType ADDIN = new SupbookType("addin");
		public static readonly SupbookType LINK = new SupbookType("link");
		public static readonly SupbookType UNKNOWN = new SupbookType("unknown");

		/**
		 * Constructor for add in function names
		 */
		public SupbookRecord()
			: base(Type.SUPBOOK)
			{
			type = ADDIN;
			}

		/**
		 * Constructor for internal sheets
		 */
		public SupbookRecord(int sheets, WorkbookSettings ws)
			: base(Type.SUPBOOK)
			{
			numSheets = sheets;
			type = INTERNAL;
			workbookSettings = ws;
			}

		/**
		 * Constructor for external sheets
		 *
		 * @param fn the filename of the external supbook
		 * @param ws the workbook settings
		 */
		public SupbookRecord(string fn, WorkbookSettings ws)
			: base(Type.SUPBOOK)
			{
			fileName = fn;
			numSheets = 1;
			sheetNames = new string[0];
			workbookSettings = ws;

			type = EXTERNAL;
			}

		/**
		 * Constructor used when copying from an external workbook
		 */
		public SupbookRecord(CSharpJExcel.Jxl.Read.Biff.SupbookRecord sr, WorkbookSettings ws)
			: base(Type.SUPBOOK)
			{
			workbookSettings = ws;
			if (sr.getType() == SupbookRecord.INTERNAL)
				{
				type = INTERNAL;
				numSheets = sr.getNumberOfSheets();
				}
			else if (sr.getType() == SupbookRecord.EXTERNAL)
				{
				type = EXTERNAL;
				numSheets = sr.getNumberOfSheets();
				fileName = sr.getFileName();
				sheetNames = new string[numSheets];

				for (int i = 0; i < numSheets; i++)
					sheetNames[i] = sr.getSheetName(i);
				}

			if (sr.getType() == SupbookRecord.ADDIN)
				{
				//logger.warn("Supbook type is addin");
				}
			}

		/**
		 * Initializes an internal supbook record
		 * 
		 * @param sr the read supbook record to copy from
		 */
		private void initInternal(CSharpJExcel.Jxl.Read.Biff.SupbookRecord sr)
			{
			numSheets = sr.getNumberOfSheets();
			initInternal();
			}

		/**
		 * Initializes an internal supbook record
		 */
		private void initInternal()
			{
			data = new byte[4];

			IntegerHelper.getTwoBytes(numSheets, data, 0);
			data[2] = 0x1;
			data[3] = 0x4;
			type = INTERNAL;
			}

		/**
		 * Adjust the number of internal sheets.  Called by WritableSheet when
		 * a sheet is added or or removed to the workbook
		 *
		 * @param sheets the new number of sheets
		 */
		public void adjustInternal(int sheets)
			{
			Assert.verify(type == INTERNAL);
			numSheets = sheets;
			initInternal();
			}

		/**
		 * Initializes an external supbook record
		 */
		private void initExternal()
			{
			int totalSheetNameLength = 0;
			for (int i = 0; i < numSheets; i++)
				{
				totalSheetNameLength += sheetNames[i].Length;
				}

			byte[] fileNameData = EncodedURLHelper.getEncodedURL(fileName,
																 workbookSettings);
			int dataLength = 2 + // numsheets
					4 + fileNameData.Length +
					numSheets * 3 + totalSheetNameLength * 2;

			data = new byte[dataLength];

			IntegerHelper.getTwoBytes(numSheets, data, 0);

			// Add in the file name.  Precede with a byte denoting that it is a 
			// file name
			int pos = 2;
			IntegerHelper.getTwoBytes(fileNameData.Length + 1, data, pos);
			data[pos + 2] = 0; // ascii indicator
			data[pos + 3] = 1; // file name indicator
			System.Array.Copy(fileNameData, 0, data, pos + 4, fileNameData.Length);

			pos += 4 + fileNameData.Length;

			// Get the sheet names
			for (int i = 0; i < sheetNames.Length; i++)
				{
				IntegerHelper.getTwoBytes(sheetNames[i].Length, data, pos);
				data[pos + 2] = 1; // unicode indicator
				StringHelper.getUnicodeBytes(sheetNames[i], data, pos + 3);
				pos += 3 + sheetNames[i].Length * 2;
				}
			}

		/**
		 * Initializes the supbook record for add in functions
		 */
		private void initAddin()
			{
			data = new byte[] { 0x1, 0x0, 0x1, 0x3a };
			}

		/**
		 * The binary data to be written out
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			if (type == INTERNAL)
				{
				initInternal();
				}
			else if (type == EXTERNAL)
				{
				initExternal();
				}
			else if (type == ADDIN)
				{
				initAddin();
				}
			else
				{
				//logger.warn("unsupported supbook type - defaulting to internal");
				initInternal();
				}

			return data;
			}

		/**
		 * Gets the type of this supbook record
		 * 
		 * @return the type of this supbook
		 */
		public SupbookType getType()
			{
			return type;
			}

		/**
		 * Gets the number of sheets.  This will only be non-zero for internal
		 * and external supbooks
		 *
		 * @return the number of sheets
		 */
		public int getNumberOfSheets()
			{
			return numSheets;
			}

		/**
		 * Accessor for the file name
		 *
		 * @return the file name
		 */
		public string getFileName()
			{
			return fileName;
			}

		/**
		 * Adds the worksheet name to this supbook
		 *
		 * @param name the worksheet name
		 * @return the index of this sheet in the supbook record
		 */
		public int getSheetIndex(string s)
			{
			bool found = false;
			int sheetIndex = 0;
			for (int i = 0; i < sheetNames.Length && !found; i++)
				{
				if (sheetNames[i].Equals(s))
					{
					found = true;
					sheetIndex = 0;
					}
				}

			if (found)
				{
				return sheetIndex;
				}

			// Grow the array
			string[] names = new string[sheetNames.Length + 1];
			System.Array.Copy(sheetNames, 0, names, 0, sheetNames.Length);
			names[sheetNames.Length] = s;
			sheetNames = names;
			return sheetNames.Length - 1;
			}

		/**
		 * Accessor for the sheet name
		 * 
		 * @param s the sheet index
		 */
		public string getSheetName(int s)
			{
			return sheetNames[s];
			}
		}
	}
