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


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * A record which merely holds the MSODRAWING data.  Used when copying files
	 * which contain images
	 */
	public class MsoDrawingRecord : WritableRecordData
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(MsoDrawingRecord.class);

		/**
		 * Flag to indicate whether this is the first drawing on the sheet
		 * - needed for copying
		 */
		private bool first;
		/**
		 * The raw drawing data which was read in
		 */
		private byte[] data;

		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 */
		public MsoDrawingRecord(Record t)
			: base(t)
			{
			data = getRecord().getData();
			first = false;
			}

		/**
		 * Constructor
		 *
		 * @param d the drawing data
		 */
		public MsoDrawingRecord(byte[] d)
			: base(Type.MSODRAWING)
			{
			data = d;
			first = false;
			}

		/**
		 * Expose the protected function
		 *
		 * @return the raw record data
		 */
		public override byte[] getData()
			{
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
		 * Sets the flag to indicate that this is the first drawing on the sheet
		 */
		public void setFirst()
			{
			first = true;
			}

		/**
		 * Accessor for the first drawing on the sheet.  This is used when
		 * copying unmodified sheets to indicate that this drawing contains
		 * the first time Escher gubbins
		 *
		 * @return TRUE if this MSORecord is the first drawing on the sheet
		 */
		public bool isFirst()
			{
			return first;
			}
		}
	}


