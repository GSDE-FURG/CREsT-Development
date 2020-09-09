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


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * An external sheet record, used to maintain integrity when formulas
	 * are copied from read databases
	 */
	class ExternalSheetRecord : WritableRecordData
		{
		/**
		 * The underlying external sheet data
		 */
		private byte[] data;

		/**
		 * The list of XTI structures
		 */
		private ArrayList xtis;

		/**
		 * An XTI structure
		 */
		public sealed class XTI
			{
			public int supbookIndex;
			public int firstTab;
			public int lastTab;

			public XTI(int s, int f, int l)
				{
				supbookIndex = s;
				firstTab = f;
				lastTab = l;
				}

			public void sheetInserted(int index)
				{
				if (firstTab >= index)
					firstTab++;

				if (lastTab >= index)
					lastTab++;
				}

			public void sheetRemoved(int index)
				{
				if (firstTab == index)
					firstTab = 0;

				if (lastTab == index)
					lastTab = 0;

				if (firstTab > index)
					firstTab--;

				if (lastTab > index)
					lastTab--;
				}
			}

		/**
		 * Constructor
		 * 
		 * @param esf the external sheet record to copy
		 */
		public ExternalSheetRecord(CSharpJExcel.Jxl.Read.Biff.ExternalSheetRecord esf)
			: base(Type.EXTERNSHEET)
			{

			xtis = new ArrayList(esf.getNumRecords());
			XTI xti = null;
			for (int i = 0; i < esf.getNumRecords(); i++)
				{
				xti = new XTI(esf.getSupbookIndex(i),
							  esf.getFirstTabIndex(i),
							  esf.getLastTabIndex(i));
				xtis.Add(xti);
				}
			}

		/**
		 * Constructor used for writable workbooks
		 */
		public ExternalSheetRecord()
			: base(Type.EXTERNSHEET)
			{
			xtis = new ArrayList();
			}

		/**
		 * Gets the extern sheet index for the specified parameters, creating
		 * a new xti record if necessary
		 * @param supbookind the internal supbook reference
		 * @param sheetind the sheet index
		 */
		public int getIndex(int supbookind, int sheetind)
			{
			int pos = 0;
			foreach (XTI xti in xtis)
				{
				if (xti.supbookIndex == supbookind && xti.firstTab == sheetind)
					return pos;
				else
					pos++;
				}

			XTI newXti = new XTI(supbookind, sheetind, sheetind);
			xtis.Add(newXti);
			pos = xtis.Count - 1;

			return pos;
			}

		/**
		 * Gets the binary data for output to file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] data = new byte[2 + xtis.Count * 6];

			int pos = 0;
			IntegerHelper.getTwoBytes(xtis.Count, data, 0);
			pos += 2;

			foreach (XTI xti in xtis)
				{
				IntegerHelper.getTwoBytes(xti.supbookIndex, data, pos);
				IntegerHelper.getTwoBytes(xti.firstTab, data, pos + 2);
				IntegerHelper.getTwoBytes(xti.lastTab, data, pos + 4);
				pos += 6;
				}

			return data;
			}

		/**
		 * Gets the supbook index for the specified external sheet
		 * 
		 * @param the index of the supbook record
		 * @return the supbook index 
		 */
		public int getSupbookIndex(int index)
			{
			return ((XTI)xtis[index]).supbookIndex;
			}

		/**
		 * Gets the first tab index for the specified external sheet
		 * 
		 * @param the index of the supbook record
		 * @return the first tab index
		 */
		public int getFirstTabIndex(int index)
			{
			return ((XTI)xtis[index]).firstTab;
			}

		/**
		 * Gets the last tab index for the specified external sheet
		 * 
		 * @param the index of the supbook record
		 * @return the last tab index
		 */
		public int getLastTabIndex(int index)
			{
			return ((XTI)xtis[index]).lastTab;
			}

		/**
		 * Called when a sheet has been inserted via the API
		 * @param the position of the insertion
		 */
		public void sheetInserted(int index)
			{
			foreach (XTI xti in xtis)
				xti.sheetInserted(index);
			}

		/**
		 * Called when a sheet has been removed via the API
		 * @param the position of the insertion
		 */
		public void sheetRemoved(int index)
			{
			foreach (XTI xti in xtis)
				xti.sheetRemoved(index);
			}

		}
	}

