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

using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl.Read.Biff;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Extension of the standard RecordData which is used to support those
	 * records which, once read, may also be written
	 */
	public abstract class WritableRecordData : RecordData,ByteData
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(WritableRecordData.class);
		/**
		 * The maximum length allowed by Excel for any record length
		 */
		protected const int maxRecordLength = 8228;

		/**
		 * Constructor used by the writable records
		 *
		 * @param t the biff type of this record
		 */
		protected WritableRecordData(Type t)
			: base(t)
			{

			}

		/**
		 * Constructor used when reading a record
		 *
		 * @param t the raw data read from the biff file
		 */
		protected WritableRecordData(Record t)
			: base(t)
			{

			}

		/**
		 * Used when writing out records.  This portion of the method handles the
		 * biff code and the length of the record and appends on the data retrieved
		 * from the subclasses
		 *
		 * @return the full record data to be written out to the compound file
		 */
		public virtual byte[] getBytes()
			{
			byte[] data = getData();

			int dataLength = data.Length;

			// Don't the call the automatic continuation code for now
			//    Assert.verify(dataLength <= maxRecordLength - 4);
			// If the bytes length is greater than the max record length
			// then split out the data set into continue records
			if (data.Length > maxRecordLength - 4)
				{
				dataLength = maxRecordLength - 4;
				data = handleContinueRecords(data);
				}

			byte[] bytes = new byte[data.Length + 4];

			System.Array.Copy(data,0,bytes,4,data.Length);

			IntegerHelper.getTwoBytes(getCode(),bytes,0);
			IntegerHelper.getTwoBytes(dataLength,bytes,2);

			return bytes;
			}

		/**
		 * The number of bytes for this record exceeds the maximum record
		 * length, so a continue is required
		 * @param data the raw data
		 * @return  the continued data
		 */
		private byte[] handleContinueRecords(byte[] data)
			{
			// Deduce the number of continue records
			int continuedData = data.Length - (maxRecordLength - 4);
			int numContinueRecords = continuedData / (maxRecordLength - 4) + 1;

			// Create the new byte array, allowing for the continue records
			// code and length
			byte[] newdata = new byte[data.Length + numContinueRecords * 4];

			// Copy the bona fide record data into the beginning of the super
			// record
			System.Array.Copy(data,0,newdata,0,maxRecordLength - 4);
			int oldarraypos = maxRecordLength - 4;
			int newarraypos = maxRecordLength - 4;

			// Now handle all the continue records
			for (int i = 0; i < numContinueRecords; i++)
				{
				// The number of bytes to add into the new array
				int length = System.Math.Min(data.Length - oldarraypos,maxRecordLength - 4);

				// Add in the continue record code
				IntegerHelper.getTwoBytes(Type.CONTINUE.value,newdata,newarraypos);
				IntegerHelper.getTwoBytes(length,newdata,newarraypos + 2);

				// Copy in as much of the new data as possible
				System.Array.Copy(data,oldarraypos,newdata,newarraypos + 4,length);

				// Update the position counters
				oldarraypos += length;
				newarraypos += length + 4;
				}

			return newdata;
			}

		/**
		 * Abstract method called by the getBytes method.  Subclasses implement
		 * this method to incorporate their specific binary data - excluding the
		 * biff code and record length, which is handled by this class
		 *
		 * @return subclass specific biff data
		 */
		public abstract byte[] getData();
		}
	}
