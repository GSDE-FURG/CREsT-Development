/*********************************************************************
*
*      Copyright (C) 2007 Andrew Khan
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

using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl.Read.Biff;

namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * The conditional format conditions
	 */
	public class ConditionalFormatRecord : WritableRecordData
		{
		// the logger
		//private static Logger logger = Logger.getLogger(ConditionalFormatRecord.class);

		/**
		 * The data
		 */
		private byte[] data;

		/**
		 * Constructor
		 */
		public ConditionalFormatRecord(Record t)
			: base(t)
			{
			data = getRecord().getData();
			}

		/**
		 * Retrieves the data for output to binary file
		 * 
		 * @return the data to be written
		 */
		public override byte[] getData()
			{
			return data;
			}
		}
	}