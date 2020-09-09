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

using CSharpJExcel.Jxl.Read.Biff;
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * A record which merely holds the OBJ data.  Used when copying files which
	 * contain images
	 */
	public class ObjRecord : WritableRecordData
		{
		/**
		 * The logger
		 */
		//private const Logger logger = Logger.getLogger(ObjRecord.class);

		/**
		 * The object type
		 */
		private ObjType type;

		/**
		 * Indicates whether this record was read in
		 */
		private bool read;

		/**
		 * The object id
		 */
		private uint objectId;

		/**
		 * object type enumeration
		 */
		public sealed class ObjType
			{
			public uint value;
			public string desc;

			private static ObjType[] types = new ObjType[0];

			internal ObjType(uint v,string d)
				{
				value = v;
				desc = d;

				ObjType[] oldtypes = types;
				types = new ObjType[types.Length + 1];
				System.Array.Copy(oldtypes,0,types,0,oldtypes.Length);
				types[oldtypes.Length] = this;
				}

			public override string ToString()
				{
				return desc;
				}

			public static ObjType getType(int val)
				{
				ObjType retval = UNKNOWN;
				for (int i = 0; i < types.Length && retval == UNKNOWN; i++)
					{
					if (types[i].value == val)
						{
						retval = types[i];
						}
					}
				return retval;
				}
			}

		// The object types
		public static readonly ObjType GROUP = new ObjType(0x0,"Group");
		public static readonly ObjType LINE = new ObjType(0x01,"Line");
		public static readonly ObjType RECTANGLE = new ObjType(0x02,"Rectangle");
		public static readonly ObjType OVAL = new ObjType(0x03,"Oval");
		public static readonly ObjType ARC = new ObjType(0x04,"Arc");
		public static readonly ObjType CHART = new ObjType(0x05,"Chart");
		public static readonly ObjType TEXT = new ObjType(0x06,"Text");
		public static readonly ObjType BUTTON = new ObjType(0x07,"Button");
		public static readonly ObjType PICTURE = new ObjType(0x08,"Picture");
		public static readonly ObjType POLYGON = new ObjType(0x09,"Polygon");
		public static readonly ObjType CHECKBOX = new ObjType(0x0b,"Checkbox");
		public static readonly ObjType OPTION = new ObjType(0x0c,"Option");
		public static readonly ObjType EDITBOX = new ObjType(0x0d,"Edit Box");
		public static readonly ObjType LABEL = new ObjType(0x0e,"Label");
		public static readonly ObjType DIALOGUEBOX = new ObjType(0x0f,"Dialogue Box");
		public static readonly ObjType SPINBOX = new ObjType(0x10,"Spin Box");
		public static readonly ObjType SCROLLBAR = new ObjType(0x11,"Scrollbar");
		public static readonly ObjType LISTBOX = new ObjType(0x12,"List Box");
		public static readonly ObjType GROUPBOX = new ObjType(0x13,"Group Box");
		public static readonly ObjType COMBOBOX = new ObjType(0x14,"Combo Box");
		public static readonly ObjType MSOFFICEDRAWING = new ObjType(0x1e,"MS Office Drawing");
		public static readonly ObjType FORMCONTROL = new ObjType(0x14,"Form Combo Box");
		public static readonly ObjType EXCELNOTE = new ObjType(0x19,"Excel Note");

		public static readonly ObjType UNKNOWN = new ObjType(0xff,"Unknown");

		// Field sub records
		private const int COMMON_DATA_LENGTH = 22;
		private const int CLIPBOARD_FORMAT_LENGTH = 6;
		private const int PICTURE_OPTION_LENGTH = 6;
		private const int NOTE_STRUCTURE_LENGTH = 26;
		private const int COMBOBOX_STRUCTURE_LENGTH = 44;
		private const int END_LENGTH = 4;

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 */
		public ObjRecord(Record t)
			: base(t)
			{
			byte[] data = t.getData();
			int objtype = IntegerHelper.getInt(data[4],data[5]);
			read = true;
			type = ObjType.getType(objtype);

			if (type == UNKNOWN)
				{
				//logger.warn("unknown object type code " + objtype);
				}

			objectId = (uint)IntegerHelper.getInt(data[6],data[7]);
			}

		/**
		 * Constructor
		 *
		 * @param objId the object id
		 * @param t the object type
		 */
		public ObjRecord(uint objId,ObjType t)
			: base(Type.OBJ)
			{
			objectId = objId;
			type = t;
			}

		/**
		 * Expose the protected function to the SheetImpl in this package
		 *
		 * @return the raw record data
		 */
		public override byte[] getData()
			{
			if (read)
				{
				return getRecord().getData();
				}

			if (type == PICTURE || type == CHART)
				{
				return getPictureData();
				}
			else if (type == EXCELNOTE)
				{
				return getNoteData();
				}
			else if (type == COMBOBOX)
				{
				return getComboBoxData();
				}
			else
				{
				Assert.verify(false);
				}
			return null;
			}

		/**
		 * Gets the ObjRecord subrecords for a picture
		 *
		 * @return the binary data for the picture
		 */
		private byte[] getPictureData()
			{
			int dataLength = COMMON_DATA_LENGTH +
				  CLIPBOARD_FORMAT_LENGTH +
				  PICTURE_OPTION_LENGTH +
				  END_LENGTH;
			int pos = 0;
			byte[] data = new byte[dataLength];

			// The jxl.common.data
			// record id
			IntegerHelper.getTwoBytes(0x15,data,pos);

			// record length
			IntegerHelper.getTwoBytes(COMMON_DATA_LENGTH - 4,data,pos + 2);

			// object type
			IntegerHelper.getTwoBytes(type.value,data,pos + 4);

			// object id
			IntegerHelper.getTwoBytes(objectId,data,pos + 6);

			// the options
			IntegerHelper.getTwoBytes(0x6011,data,pos + 8);
			pos += COMMON_DATA_LENGTH;

			// The clipboard format
			// record id
			IntegerHelper.getTwoBytes(0x7,data,pos);

			// record length
			IntegerHelper.getTwoBytes(CLIPBOARD_FORMAT_LENGTH - 4,data,pos + 2);

			// the data
			IntegerHelper.getTwoBytes(0xffff,data,pos + 4);
			pos += CLIPBOARD_FORMAT_LENGTH;

			// Picture option flags
			// record id
			IntegerHelper.getTwoBytes(0x8,data,pos);

			// record length
			IntegerHelper.getTwoBytes(PICTURE_OPTION_LENGTH - 4,data,pos + 2);

			// the data
			IntegerHelper.getTwoBytes(0x1,data,pos + 4);
			pos += CLIPBOARD_FORMAT_LENGTH;

			// End  record id
			IntegerHelper.getTwoBytes(0x0,data,pos);

			// record length
			IntegerHelper.getTwoBytes(END_LENGTH - 4,data,pos + 2);

			// the data
			pos += END_LENGTH;

			return data;
			}

		/**
		 * Gets the ObjRecord subrecords for a note
		 *
		 * @return  the note data
		 */
		private byte[] getNoteData()
			{
			int dataLength = COMMON_DATA_LENGTH +
				  NOTE_STRUCTURE_LENGTH +
				  END_LENGTH;
			int pos = 0;
			byte[] data = new byte[dataLength];

			// The jxl.common.data
			// record id
			IntegerHelper.getTwoBytes(0x15,data,pos);

			// record length
			IntegerHelper.getTwoBytes(COMMON_DATA_LENGTH - 4,data,pos + 2);

			// object type
			IntegerHelper.getTwoBytes(type.value,data,pos + 4);

			// object id
			IntegerHelper.getTwoBytes(objectId,data,pos + 6);

			// the options
			IntegerHelper.getTwoBytes(0x4011,data,pos + 8);
			pos += COMMON_DATA_LENGTH;

			// The note structure
			// record id
			IntegerHelper.getTwoBytes(0xd,data,pos);

			// record length
			IntegerHelper.getTwoBytes(NOTE_STRUCTURE_LENGTH - 4,data,pos + 2);

			// the data
			pos += NOTE_STRUCTURE_LENGTH;

			// End
			// record id
			IntegerHelper.getTwoBytes(0x0,data,pos);

			// record length
			IntegerHelper.getTwoBytes(END_LENGTH - 4,data,pos + 2);

			// the data
			pos += END_LENGTH;

			return data;
			}

		/**
		 * Gets the ObjRecord subrecords for a combo box
		 *
		 * @return returns the binary data for a combo box
		 */
		private byte[] getComboBoxData()
			{
			int dataLength = COMMON_DATA_LENGTH +
					  COMBOBOX_STRUCTURE_LENGTH +
					  END_LENGTH;
			int pos = 0;
			byte[] data = new byte[dataLength];

			// The jxl.common.data
			// record id
			IntegerHelper.getTwoBytes(0x15,data,pos);

			// record length
			IntegerHelper.getTwoBytes(COMMON_DATA_LENGTH - 4,data,pos + 2);

			// object type
			IntegerHelper.getTwoBytes(type.value,data,pos + 4);

			// object id
			IntegerHelper.getTwoBytes(objectId,data,pos + 6);

			// the options
			IntegerHelper.getTwoBytes(0x0,data,pos + 8);
			pos += COMMON_DATA_LENGTH;

			// The combo box structure
			// record id
			IntegerHelper.getTwoBytes(0xc,data,pos);

			// record length
			IntegerHelper.getTwoBytes(0x14,data,pos + 2);

			// the data
			data[pos + 14] = 0x01;
			data[pos + 16] = 0x04;
			data[pos + 20] = 0x10;
			data[pos + 24] = 0x13;
			data[pos + 26] = (byte)0xee;
			data[pos + 27] = 0x1f;
			data[pos + 30] = 0x04;
			data[pos + 34] = 0x01;
			data[pos + 35] = 0x06;
			data[pos + 38] = 0x02;
			data[pos + 40] = 0x08;
			data[pos + 42] = 0x40;

			pos += COMBOBOX_STRUCTURE_LENGTH;

			// End
			// record id
			IntegerHelper.getTwoBytes(0x0,data,pos);

			// record length
			IntegerHelper.getTwoBytes(END_LENGTH - 4,data,pos + 2);

			// the data
			pos += END_LENGTH;

			return data;
			}


		/**
		 * Expose the protected function to the SheetImpl in this package
		 *
		 * @return the raw record data
		 */
		public override Record getRecord()
			{
			return base.getRecord();
			}

		/**
		 * Accessor for the object type
		 *
		 * @return the object type
		 */
		public ObjType getType()
			{
			return type;
			}

		/**
		 * Accessor for the object id
		 *
		 * @return accessor for the object id
		 */
		public virtual uint getObjectId()
			{
			return objectId;
			}
		}
	}




