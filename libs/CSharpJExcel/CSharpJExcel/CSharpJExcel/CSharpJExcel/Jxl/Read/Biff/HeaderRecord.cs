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


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A workbook page header record
	 */
	public class HeaderRecord : RecordData
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(HeaderRecord.class);

		/**
		 * The footer
		 */
		private string header;

		/**
		 * Dummy indicators for overloading the constructor
		 */
		public sealed class Biff7 
			{ 
			};

		public static readonly Biff7 biff7 = new Biff7();

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the record data
		 * @param ws the workbook settings
		 */
		public HeaderRecord(Record t, WorkbookSettings ws)
			: base(t)
			{
			byte[] data = getRecord().getData();
			if (data.Length == 0)
				return;

			int chars = IntegerHelper.getInt(data[0], data[1]);
			bool unicode = data[2] == 1;
			if (unicode)
				header = StringHelper.getUnicodeString(data, chars, 3);
			else
				header = StringHelper.getString(data, chars, 3, ws);
			}

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the record data
		 * @param ws the workbook settings
		 * @param dummy dummy record to indicate a biff7 document
		 */
		public HeaderRecord(Record t, WorkbookSettings ws, Biff7 dummy)
			: base(t)
			{
			byte[] data = getRecord().getData();
			if (data.Length == 0)
				return;

			int chars = data[0];
			header = StringHelper.getString(data, chars, 1, ws);
			}

		/**
		 * Gets the header string
		 *
		 * @return the header string
		 */
		public string getHeader()
			{
			return header;
			}
		}
	}
