/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan
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


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * A Note (TXO) record which contains the information for comments
	 */
	public class NoteRecord : WritableRecordData
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(NoteRecord.class);

		/**
		 * The raw drawing data which was read in
		 */
		private byte[] data;

		/**
		 * The row
		 */
		private int row;

		/**
		 * The column
		 */
		private int column;

		/**
		 * The object id
		 */
		private uint objectId;

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 */
		public NoteRecord(Record t)
			: base(t)
			{
			data = getRecord().getData();
			row = IntegerHelper.getInt(data[0],data[1]);
			column = IntegerHelper.getInt(data[2],data[3]);
			objectId = (uint)IntegerHelper.getInt(data[6],data[7]);
			}

		/**
		 * Constructor
		 *
		 * @param d the drawing data
		 */
		public NoteRecord(byte[] d)
			: base(Type.NOTE)
			{
			data = d;
			}

		/**
		 * Constructor used when writing a Note
		 *
		 * @param c the column
		 * @param r the row
		 * @param id the object id
		 */
		public NoteRecord(int c,int r,uint id)
			: base(Type.NOTE)
			{
			row = r;
			column = c;
			objectId = id;
			}

		/**
		 * Expose the protected function to the SheetImpl in this package
		 *
		 * @return the raw record data
		 */
		public override byte[] getData()
			{
			if (data != null)
				{
				return data;
				}

			string author = string.Empty;
			data = new byte[8 + author.Length + 4];

			// the row
			IntegerHelper.getTwoBytes(row,data,0);

			// the column
			IntegerHelper.getTwoBytes(column,data,2);

			// the object id
			IntegerHelper.getTwoBytes(objectId,data,6);

			// the length of the string
			IntegerHelper.getTwoBytes(author.Length,data,8);

			// the string
			//        StringHelper.getBytes(author, data, 11);

			//  data[data.Length-1]=(byte)0x24;

			return data;
			}

		/**
		 * Accessor for the row
		 *
		 * @return  the row
		 */
		public int getRow()
			{
			return row;
			}

		/**
		 * Accessor for the column
		 *
		 * @return the column
		 */
		public int getColumn()
			{
			return column;
			}

		/**
		 * Accessor for the object id
		 *
		 * @return  the object id
		 */
		public virtual uint getObjectId()
			{
			return objectId;
			}
		}
	}
