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
	 * An external sheet record, used to maintain integrity when formulas
	 * are copied from read databases
	 */
	class ExternalNameRecord : WritableRecordData
		{
		/**
		 * The logger
		 */
		//  Logger logger = Logger.getLogger(ExternalNameRecord.class);

		/**
		 * The name of the addin
		 */
		private string name;

		/**
		 * Constructor used for writable workbooks
		 */
		public ExternalNameRecord(string n)
			: base(Type.EXTERNNAME)
			{
			name = n;
			}

		/**
		 * Gets the binary data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] data = new byte[name.Length * 2 + 12];

			data[6] = (byte)name.Length;
			data[7] = (byte)0x1;
			StringHelper.getUnicodeBytes(name, data, 8);

			int pos = 8 + name.Length * 2;
			data[pos] = 0x2;
			data[pos + 1] = 0x0;
			data[pos + 2] = 0x1c;
			data[pos + 3] = 0x17;

			return data;
			}
		}
	}

