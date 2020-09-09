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
	 * Record containing the localization information
	 */
	class CountryRecord : WritableRecordData
		{
		/**
		 * The user interface language
		 */
		private int language;

		/**
		 * The regional settings
		 */
		private int regionalSettings;

		/**
		 * Constructor
		 */
		public CountryRecord(CountryCode lang, CountryCode r)
			: base(Type.COUNTRY)
			{
			language = lang.getValue();
			regionalSettings = r.getValue();
			}

		public CountryRecord(CSharpJExcel.Jxl.Read.Biff.CountryRecord cr)
			: base(Type.COUNTRY)
			{
			language = cr.getLanguageCode();
			regionalSettings = cr.getRegionalSettingsCode();
			}

		/**
		 * Retrieves the data to be written to the binary file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] data = new byte[4];

			IntegerHelper.getTwoBytes(language, data, 0);
			IntegerHelper.getTwoBytes(regionalSettings, data, 2);

			return data;
			}
		}
	}
