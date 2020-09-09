/*********************************************************************
*
*      Copyright (C) 2001 Andrew Khan
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
	 * A row  record
	 */
	public class ExternalNameRecord : RecordData
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(ExternalNameRecord.class);

		/**
		 * The name
		 */
		private string name;

		/**
		 * Add in function flag
		 */
		private bool addInFunction;

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 * @param ws the workbook settings
		 */
		public ExternalNameRecord(Record t, WorkbookSettings ws)
			: base(t)
			{
			byte[] data = getRecord().getData();
			int options = IntegerHelper.getInt(data[0],data[1]);

			if (options == 0)
				addInFunction = true;

			if (!addInFunction)
				return;

			int length = data[6];

			bool unicode = (data[7] != 0);

			if (unicode)
				name = StringHelper.getUnicodeString(data,length,8);
			else
				name = StringHelper.getString(data,length,8,ws);
			}

		/**
		 * Queries whether this name record refers to an external record
		 *
		 * @return TRUE if this name record is an add in function, FALSE otherwise
		 */
		public bool isAddInFunction()
			{
			return addInFunction;
			}

		/**
		 * Gets the name
		 *
		 * @return the name
		 */
		public string getName()
			{
			return name;
			}
		}
	}


