/*********************************************************************
*
*      Copyright (C) 2005 Andrew Khan
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
	 * Contains the cell dimensions of this worksheet
	 */
	public class CountryRecord : RecordData
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(CountryRecord.class);

		/**
		 * The user interface language
		 */
		private int language;

		/**
		 * The regional settings
		 */
		private int regionalSettings;

		/**
		 * Constructs the dimensions from the raw data
		 *
		 * @param t the raw data
		 */
		public CountryRecord(Record t)
			: base(t)
			{
			byte[] data = t.getData();

			language = IntegerHelper.getInt(data[0],data[1]);
			regionalSettings = IntegerHelper.getInt(data[2],data[3]);
			}

		/**
		 * Accessor for the language code
		 *
		 * @return  the language code
		 */
		public int getLanguageCode()
			{
			return language;
			}

		/**
		 * Accessor for the regional settings code
		 *
		 * @return the regional settings code
		 */
		public int getRegionalSettingsCode()
			{
			return regionalSettings;
			}
		}
	}






