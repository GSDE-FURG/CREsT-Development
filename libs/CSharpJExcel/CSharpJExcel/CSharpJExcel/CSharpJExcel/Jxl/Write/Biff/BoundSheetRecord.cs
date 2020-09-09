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
	 * Record which stores the sheet name, the sheet type and the stream
	 * position
	 */
	public class BoundsheetRecord : WritableRecordData
		{
		/**
		 * Hidden flag
		 */
		private bool hidden;

		/**
		 * Chart only flag
		 */
		private bool chartOnly;

		/**
		 * The name of the sheet
		 */
		private string name;

		/**
		 * The data to write to the output file
		 */
		private byte[] data;

		/**
		 * Constructor
		 * 
		 * @param n the sheet name
		 */
		public BoundsheetRecord(string n)
			: base(Type.BOUNDSHEET)
			{
			name = n;
			hidden = false;
			chartOnly = false;
			}

		/**
		 * Sets the hidden flag
		 */
		public void setHidden()
			{
			hidden = true;
			}

		/**
		 * Sets the chart only flag
		 */
		public void setChartOnly()
			{
			chartOnly = true;
			}

		/**
		 * Gets the data to write out to the binary file
		 * 
		 * @return the data to write out
		 */
		public override byte[] getData()
			{
			data = new byte[name.Length * 2 + 8];

			if (chartOnly)
				data[5] = 0x02;
			else
				data[5] = 0; // set stream type to worksheet

			if (hidden)
				{
				data[4] = 0x1;
				data[5] = 0x0;
				}

			data[6] = (byte)name.Length;
			data[7] = (byte)0x1;
			StringHelper.getUnicodeBytes(name, data, 8);

			return data;
			}
		}
	}