/*********************************************************************
*
*      Copyright (C) 2003 Andrew Khan
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

using System.Collections;


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * An options record in the escher stream
	 */
	class Opt : EscherAtom
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(Opt.class);

		/**
		 * The binary data
		 */
		private byte[] data;

		/**
		 * The number of properties
		 */
		private int numProperties;

		/**
		 * The list of properties
		 */
		private ArrayList properties;

		/**
		 * Properties enumeration inner class
		 */
		public class Property
			{
			public int id;
			public bool blipId;
			public bool complex;
			public int value;
			public string StringValue;

			/**
			 * Constructor
			 *
			 * @param i the property id
			 * @param bl the blip id
			 * @param co complex flag
			 * @param v the value
			 */
			public Property(int i,bool bl,bool co,int v)
				{
				id = i;
				blipId = bl;
				complex = co;
				value = v;
				}

			/**
			 * Constructor
			 *
			 * @param i the property id
			 * @param bl the blip id
			 * @param co complex flag
			 * @param v the value
			 * @param s the property string
			 */
			public Property(int i,bool bl,bool co,int v,string s)
				{
				id = i;
				blipId = bl;
				complex = co;
				value = v;
				// CML: Have been receiving strings with null terminators on them -- remove them?
				//if (s.IndexOf('\0') >= 0)
				//    s = s.Substring(0, s.IndexOf('\0'));
				StringValue = s;
				}
			}

		/**
		 * Constructor
		 *
		 * @param erd the escher record data
		 */
		public Opt(EscherRecordData erd)
			: base(erd)
			{
			numProperties = getInstance();
			readProperties();
			}

		/**
		 * Reads the properties
		 */
		private void readProperties()
			{
			properties = new ArrayList();
			int pos = 0;
			byte[] bytes = getBytes();

			for (int i = 0; i < numProperties; i++)
				{
				int val = IntegerHelper.getInt(bytes[pos],bytes[pos + 1]);
				int id = val & 0x3fff;
				int value = IntegerHelper.getInt(bytes[pos + 2],bytes[pos + 3],bytes[pos + 4],bytes[pos + 5]);
				Property p = new Property(id,(val & 0x4000) != 0,(val & 0x8000) != 0,value);
				pos += 6;
				properties.Add(p);
				}

			foreach (Property p in properties)
				{
				if (p.complex)
					{
					p.StringValue = StringHelper.getUnicodeString(bytes,p.value / 2,pos);
					pos += p.value;
					}
				}
			}

		/**
		 * Constructor
		 */
		public Opt()
			: base(EscherRecordType.OPT)
			{
			properties = new ArrayList();
			setVersion(3);
			}

		/**
		 * Accessor for the binary data
		 *
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			numProperties = properties.Count;
			setInstance(numProperties);

			data = new byte[numProperties * 6];
			int pos = 0;

			// Add in the root data
			foreach (Property p in properties)
				{
				int val = p.id & 0x3fff;

				if (p.blipId)
					val |= 0x4000;

				if (p.complex)
					{
					val |= 0x8000;
					if (p.value != p.StringValue.Length * 2)		// CML -- had an off by one in some vals
						p.value = p.StringValue.Length * 2;
					}

				IntegerHelper.getTwoBytes(val,data,pos);
				IntegerHelper.getFourBytes(p.value,data,pos + 2);
				pos += 6;
				}

			// Add in any complex data
			foreach (Property p in properties)
				{
				if (p.complex && p.StringValue != null)
					{
					byte[] newData = new byte[data.Length + p.StringValue.Length * 2];
					System.Array.Copy(data,0,newData,0,data.Length);
					StringHelper.getUnicodeBytes(p.StringValue,newData,data.Length);
					data = newData;
					}
				}

			return setHeaderData(data);
			}

		/**
		 * Adds a property into the options
		 *
		 * @param id the property id
		 * @param blip the blip id
		 * @param complex whether it's a complex property
		 * @param val the value
		 */
		public void addProperty(int id, bool blip, bool complex, int val)
			{
			Property p = new Property(id,blip,complex,val);
			properties.Add(p);
			}

		/**
		 * Adds a property into the options
		 *
		 * @param id the property id
		 * @param blip the blip id
		 * @param complex whether it's a complex property
		 * @param val the value
		 * @param s the value string
		 */
		public void addProperty(int id, bool blip, bool complex, int val, string s)
			{
			Property p = new Property(id,blip,complex,val,s);
			properties.Add(p);
			}

		/**
		 * Accessor for the property
		 *
		 * @param id the property id
		 * @return the property
		 */
		public Property getProperty(int id)
			{
			foreach (Property p in properties)
				{
				if (p.id == id)
					return p;
				}
			return null;
			}
		}
	}
