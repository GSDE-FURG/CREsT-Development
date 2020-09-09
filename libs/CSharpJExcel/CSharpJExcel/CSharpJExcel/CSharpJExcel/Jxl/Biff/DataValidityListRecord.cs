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

using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl.Read.Biff;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Record containing the list of data validation settings for a given sheet
	 */
	public class DataValidityListRecord : WritableRecordData
		{
		//private static Logger logger = Logger.getLogger(DataValidityListRecord.class);

		/**
		 * The number of settings records associated with this list
		 */
		private int numSettings;

		/**
		 * The object id of the associated down arrow
		 */
		private uint objectId;

		/**
		 * The dval parser
		 */
		private DValParser dvalParser;

		/**
		 * The data
		 */
		private byte[] data;

		/**
		 * Constructor
		 */
		public DataValidityListRecord(Record t)
			: base(t)
			{
			data = getRecord().getData();
			objectId = (uint)IntegerHelper.getInt(data[10],data[11],data[12],data[13]);
			numSettings = IntegerHelper.getInt(data[14],data[15],data[16],data[17]);
			}

		/**
		 * Constructor called when generating a data validity list from the API
		 */
		public DataValidityListRecord(DValParser dval)
			: base(Type.DVAL)
			{
			dvalParser = dval;
			}

		/**
		 * Copy constructor
		 *
		 * @param dvlr the record copied from a read only sheet
		 */
		public DataValidityListRecord(DataValidityListRecord dvlr)
			: base(Type.DVAL)
			{
			data = dvlr.getData();
			}

		/**
		 * Accessor for the number of settings records associated with this list
		 */
		public int getNumberOfSettings()
			{
			return numSettings;
			}

		/**
		 * Retrieves the data for output to binary file
		 * 
		 * @return the data to be written
		 */
		public override byte[] getData()
			{
			if (dvalParser == null)
				{
				return data;
				}

			return dvalParser.getData();
			}

		/**
		 * Called when a remove row or column results in one of DV records being 
		 * removed
		 */
		public void dvRemoved()
			{
			if (dvalParser == null)
				{
				dvalParser = new DValParser(data);
				}

			dvalParser.dvRemoved();
			}

		/**
		 * Called when a writable DV record is added to a copied validity list
		 */
		public void dvAdded()
			{
			if (dvalParser == null)
				{
				dvalParser = new DValParser(data);
				}

			dvalParser.dvAdded();
			}

		/**
		 * Accessor for the number of DV records
		 *
		 * @return the number of DV records for this list
		 */
		public bool hasDVRecords()
			{
			if (dvalParser == null)
				{
				return true;
				}

			return dvalParser.getNumberOfDVRecords() > 0;
			}

		/**
		 * Accessor for the object id
		 *
		 * @return the object id
		 */
		public virtual uint getObjectId()
			{
			if (dvalParser == null)
				return objectId;

			return dvalParser.getobjectId();
			}
		}
	}