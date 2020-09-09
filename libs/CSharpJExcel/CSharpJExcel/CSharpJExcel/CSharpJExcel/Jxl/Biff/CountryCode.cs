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


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Enumeration type for the excel country codes
	 */
	public class CountryCode
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(CountryCode.class);

		/**
		 * The country code
		 */
		private int value;

		/**
		 * The ISO 3166 two letter country mnemonic (as used by the Locale class)
		 */
		private string code;

		/**
		 * The long description
		 */
		private string description;

		/**
		 * The array of country codes
		 */
		private static CountryCode[] codes = new CountryCode[0];

		/**
		 * Constructor
		 */
		private CountryCode(int v,string c,string d)
			{
			value = v;
			code = c;
			description = d;

			CountryCode[] newcodes = new CountryCode[codes.Length + 1];
			System.Array.Copy(codes,0,newcodes,0,codes.Length);
			newcodes[codes.Length] = this;
			codes = newcodes;
			}

		/**
		 * Constructor used to create an arbitrary code with a specified value.  
		 * Doesn't add the latest value to the static array
		 */
		private CountryCode(int v)
			{
			value = v;
			description = "Arbitrary";
			code = "??";
			}

		/**
		 * Accessor for the excel value
		 *
		 * @return the excel value
		 */
		public int getValue()
			{
			return value;
			}

		/**
		 * Accessor for the string
		 * 
		 * @return the two character iso 3166 string
		 */
		public string getCode()
			{
			return code;
			}

		/**
		 * Gets the country code for the given two character mnemonic string
		 */
		public static CountryCode getCountryCode(string s)
			{
			if (s == null || s.Length != 2)
				{
				//logger.warn("Please specify two character ISO 3166 country code");
				return USA;
				}

			CountryCode code = UNKNOWN;
			for (int i = 0; i < codes.Length && code == UNKNOWN; i++)
				{
				if (codes[i].code.Equals(s))
					{
					code = codes[i];
					}
				}

			return code;
			}

		/**
		 * Creates an arbitrary country code with the specified value.  Used
		 * when copying sheets, and the country code isn't initialized as part
		 * of the static data below
		 */
		public static CountryCode createArbitraryCode(int i)
			{
			return new CountryCode(i);
			}

		// The country codes
		public static readonly CountryCode USA = new CountryCode(0x1,"US","USA");
		public static readonly CountryCode CANADA = new CountryCode(0x2,"CA","Canada");
		public static readonly CountryCode GREECE = new CountryCode(0x1e,"GR","Greece");
		public static readonly CountryCode NETHERLANDS = new CountryCode(0x1f,"NE","Netherlands");
		public static readonly CountryCode BELGIUM = new CountryCode(0x20,"BE","Belgium");
		public static readonly CountryCode FRANCE = new CountryCode(0x21,"FR","France");
		public static readonly CountryCode SPAIN = new CountryCode(0x22,"ES","Spain");
		public static readonly CountryCode ITALY = new CountryCode(0x27,"IT","Italy");
		public static readonly CountryCode SWITZERLAND = new CountryCode(0x29,"CH","Switzerland");
		public static readonly CountryCode UK = new CountryCode(0x2c,"UK","United Kingdowm");
		public static readonly CountryCode DENMARK = new CountryCode(0x2d,"DK","Denmark");
		public static readonly CountryCode SWEDEN = new CountryCode(0x2e,"SE","Sweden");
		public static readonly CountryCode NORWAY = new CountryCode(0x2f,"NO","Norway");
		public static readonly CountryCode GERMANY = new CountryCode(0x31,"DE","Germany");
		public static readonly CountryCode PHILIPPINES = new CountryCode(0x3f,"PH","Philippines");
		public static readonly CountryCode CHINA = new CountryCode(0x56,"CN","China");
		public static readonly CountryCode INDIA = new CountryCode(0x5b,"IN","India");
		public static readonly CountryCode UNKNOWN = new CountryCode(0xffff,"??","Unknown");
		}
	}