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


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A record containing the references to the various sheets (internal and
	 * external) referenced by formulas in this workbook
	 */
	public class SupbookRecord : RecordData
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(SupbookRecord.class);

		/**
		 * The type of this supbook record
		 */
		private CSharpJExcel.Jxl.Write.Biff.SupbookRecord.SupbookType type;

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
		 * The type of supbook this refers to
		 */
		public class Type 
			{ 
			};

		public static readonly CSharpJExcel.Jxl.Write.Biff.SupbookRecord.SupbookType INTERNAL = CSharpJExcel.Jxl.Write.Biff.SupbookRecord.INTERNAL;
		public static readonly CSharpJExcel.Jxl.Write.Biff.SupbookRecord.SupbookType EXTERNAL = CSharpJExcel.Jxl.Write.Biff.SupbookRecord.EXTERNAL;
		public static readonly CSharpJExcel.Jxl.Write.Biff.SupbookRecord.SupbookType ADDIN = CSharpJExcel.Jxl.Write.Biff.SupbookRecord.ADDIN;
		public static readonly CSharpJExcel.Jxl.Write.Biff.SupbookRecord.SupbookType LINK = CSharpJExcel.Jxl.Write.Biff.SupbookRecord.LINK;
		public static readonly CSharpJExcel.Jxl.Write.Biff.SupbookRecord.SupbookType UNKNOWN = CSharpJExcel.Jxl.Write.Biff.SupbookRecord.UNKNOWN;

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 * @param ws the workbook settings
		 */
		public SupbookRecord(Record t, WorkbookSettings ws)
			: base(t)
			{
			byte[] data = getRecord().getData();

			// First deduce the type
			if (data.Length == 4)
				{
				if (data[2] == 0x01 && data[3] == 0x04)
					{
					type = INTERNAL;
					}
				else if (data[2] == 0x01 && data[3] == 0x3a)
					{
					type = ADDIN;
					}
				else
					{
					type = UNKNOWN;
					}
				}
			else if (data[0] == 0 && data[1] == 0)
				{
				type = LINK;
				}
			else
				{
				type = EXTERNAL;
				}

			if (type == INTERNAL)
				{
				numSheets = IntegerHelper.getInt(data[0],data[1]);
				}

			if (type == EXTERNAL)
				{
				readExternal(data,ws);
				}
			}

		/**
		 * Reads the external data records
		 *
		 * @param data the data
		 * @param ws the workbook settings
		 */
		private void readExternal(byte[] data,WorkbookSettings ws)
			{
			numSheets = IntegerHelper.getInt(data[0],data[1]);

			// subtract file name encoding from the length
			int ln = IntegerHelper.getInt(data[2],data[3]) - 1;
			int pos = 0;

			if (data[4] == 0)
				{
				// non-unicode string
				int encoding = data[5];
				pos = 6;
				if (encoding == 0)
					{
					fileName = StringHelper.getString(data,ln,pos,ws);
					pos += ln;
					}
				else
					{
					fileName = getEncodedFilename(data,ln,pos);
					pos += ln;
					}
				}
			else
				{
				// unicode string
				int encoding = IntegerHelper.getInt(data[5],data[6]);
				pos = 7;
				if (encoding == 0)
					{
					fileName = StringHelper.getUnicodeString(data,ln,pos);
					pos += ln * 2;
					}
				else
					{
					fileName = getUnicodeEncodedFilename(data,ln,pos);
					pos += ln * 2;
					}
				}

			sheetNames = new string[numSheets];

			for (int i = 0; i < sheetNames.Length; i++)
				{
				ln = IntegerHelper.getInt(data[pos],data[pos + 1]);

				if (data[pos + 2] == 0x0)
					{
					sheetNames[i] = StringHelper.getString(data,ln,pos + 3,ws);
					pos += ln + 3;
					}
				else if (data[pos + 2] == 0x1)
					{
					sheetNames[i] = StringHelper.getUnicodeString(data,ln,pos + 3);
					pos += ln * 2 + 3;
					}
				}
			}

		/**
		 * Gets the type of this supbook record
		 *
		 * @return the type of this supbook
		 */
		public CSharpJExcel.Jxl.Write.Biff.SupbookRecord.SupbookType getType()
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
		 * Gets the name of the external file
		 *
		 * @return the name of the external file
		 */
		public string getFileName()
			{
			return fileName;
			}

		/**
		 * Gets the name of the external sheet
		 *
		 * @param i the index of the external sheet
		 * @return the name of the sheet
		 */
		public string getSheetName(int i)
			{
			return sheetNames[i];
			}

		/**
		 * Gets the data - used when copying a spreadsheet
		 *
		 * @return the raw external sheet data
		 */
		public virtual byte[] getData()
			{
			return getRecord().getData();
			}

		/**
		 * Gets the encoded string from the data array
		 *
		 * @param data the data
		 * @param ln length of the string
		 * @param pos the position in the array
		 * @return the string
		 */
		private string getEncodedFilename(byte[] data,int ln,int pos)
			{
			StringBuilder buf = new StringBuilder();
			int endpos = pos + ln;
			while (pos < endpos)
				{
				char c = (char)data[pos];

				if (c == '\u0001')
					{
					// next character is a volume letter
					pos++;
					c = (char)data[pos];
					buf.Append(c);
					buf.Append(":\\\\");
					}
				else if (c == '\u0002')
					{
					// file is on the same volume
					buf.Append('\\');
					}
				else if (c == '\u0003')
					{
					// down directory
					buf.Append('\\');
					}
				else if (c == '\u0004')
					{
					// up directory
					buf.Append("..\\");
					}
				else
					{
					// just add on the character
					buf.Append(c);
					}

				pos++;
				}

			return buf.ToString();
			}

		/**
		 * Gets the encoded string from the data array
		 *
		 * @param data the data
		 * @param ln length of the string
		 * @param pos the position in the array
		 * @return the string
		 */
		private string getUnicodeEncodedFilename(byte[] data,int ln,int pos)
			{
			StringBuilder buf = new StringBuilder();
			int endpos = pos + ln * 2;
			while (pos < endpos)
				{
				char c = (char)IntegerHelper.getInt(data[pos],data[pos + 1]);

				if (c == '\u0001')
					{
					// next character is a volume letter
					pos += 2;
					c = (char)IntegerHelper.getInt(data[pos],data[pos + 1]);
					buf.Append(c);
					buf.Append(":\\\\");
					}
				else if (c == '\u0002')
					{
					// file is on the same volume
					buf.Append('\\');
					}
				else if (c == '\u0003')
					{
					// down directory
					buf.Append('\\');
					}
				else if (c == '\u0004')
					{
					// up directory
					buf.Append("..\\");
					}
				else
					{
					// just add on the character
					buf.Append(c);
					}

				pos += 2;
				}

			return buf.ToString();
			}
		}
	}
