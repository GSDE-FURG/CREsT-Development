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


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * The name used when Excel was installed.  
	 * When writing worksheets, it uses the value from the WorkbookSettings object,
	 * if this is not set (null) this is hard coded as
	 * Java Excel API + Version number
	 */
	class WriteAccessRecord : WritableRecordData
		{
		/**
		 * The data to output to file
		 */
		private byte[] data;

		// string of length 112 characters
		/**
		 * The author of this workbook (ie. the Java Excel API)
		 */
		private const string authorString = "Java Excel API: CSharp Port";
		private string userName;

		/**
		 * Constructor
		 */
		public WriteAccessRecord(string userName)
			: base(Type.WRITEACCESS)
			{
			data = new byte[112];
			string astring = userName != null ?
				userName :
				authorString + " v" + Workbook.getVersion();

			StringHelper.getBytes(astring, data, 0);

			// Pad out the record with space characters
			for (int i = astring.Length; i < data.Length; i++)
				{
				data[i] = 0x20;
				}
			}

		/**
		 * Gets the data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			return data;
			}
		}
	}

