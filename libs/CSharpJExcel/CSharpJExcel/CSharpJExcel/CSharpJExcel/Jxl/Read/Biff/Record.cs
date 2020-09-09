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
using System.Collections;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A container for the raw record data within a biff file
	 */
	public sealed class Record
		{
		/**
		 * The logger
		 */
		// private static final Logger logger = Logger.getLogger(Record.class);

		/**
		 * The excel biff code
		 */
		private int code;
		/**
		 * The data type
		 */
		private Type type;
		/**
		 * The length of this record
		 */
		private int length;
		/**
		 * A pointer to the beginning of the actual data
		 */
		private int dataPos;
		/**
		 * A handle to the excel 97 file
		 */
		private File file;
		/**
		 * The raw data within this record
		 */
		private byte[] data;

		/**
		 * Any continue records
		 */
		private ArrayList continueRecords;

		/**
		 * Constructor
		 *
		 * @param offset the offset in the raw file
		 * @param f the excel 97 biff file
		 * @param d the data record
		 */
		public Record(byte[] d, int offset, File f)
			{
			code = IntegerHelper.getInt(d[offset],d[offset + 1]);
			length = IntegerHelper.getInt(d[offset + 2],d[offset + 3]);
			file = f;
			file.skip(4);
			dataPos = f.getPos();
			file.skip(length);
			type = Type.getType(code);
			}

		/**
		 * Gets the biff type
		 *
		 * @return the biff type
		 */
		public Type getType()
			{
			return type;
			}

		/**
		 * Gets the length of the record
		 *
		 * @return the length of the record
		 */
		public int getLength()
			{
			return length;
			}

		/**
		 * Gets the data portion of the record
		 *
		 * @return the data portion of the record
		 */
		public byte[] getData()
			{
			if (data == null)
				{
				data = file.read(dataPos,length);
				}

			// copy in any data from the continue records
			if (continueRecords != null)
				{
				int size = 0;
				byte[][] contData = new byte[continueRecords.Count][];
				for (int i = 0; i < continueRecords.Count; i++)
					{
					Record r = (Record)continueRecords[i];
					contData[i] = r.getData();
					byte[] d2 = contData[i];
					size += d2.Length;
					}

				byte[] d3 = new byte[data.Length + size];
				System.Array.Copy(data,0,d3,0,data.Length);
				int pos = data.Length;
				for (int i = 0; i < contData.Length; i++)
					{
					byte[] d2 = contData[i];
					System.Array.Copy(d2,0,d3,pos,d2.Length);
					pos += d2.Length;
					}

				data = d3;
				}

			return data;
			}

		/**
		 * The excel 97 code
		 *
		 * @return the excel code
		 */
		public int getCode()
			{
			return code;
			}

		/**
		 * In the case of dodgy records, this method may be called to forcibly
		 * set the type in order to continue processing
		 *
		 * @param t the forcibly overridden type
		 */
		public void setType(Type t)
			{
			type = t;
			}

		/**
		 * Adds a continue record to this data
		 *
		 * @param d the continue record
		 */
		public void addContinueRecord(Record d)
			{
			if (continueRecords == null)
				{
				continueRecords = new ArrayList();
				}

			continueRecords.Add(d);
			}
		}
	}
