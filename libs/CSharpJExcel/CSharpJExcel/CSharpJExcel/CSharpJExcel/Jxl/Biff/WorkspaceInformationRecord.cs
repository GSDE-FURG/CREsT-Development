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
	 * A record detailing whether the sheet is protected
	 */
	public class WorkspaceInformationRecord : WritableRecordData
		{
		// the logger
		//private static Logger logger = Logger.getLogger(WorkspaceInformationRecord.class);

		/**
		 * The options byte
		 */
		private int wsoptions;

		/**
		 * The row outlines
		 */
		private bool rowOutlines;

		/**
		 * The column outlines
		 */
		private bool columnOutlines;

		/**
		 * The fit to pages flag
		 */
		private bool fitToPages;

		// the masks
		private const int FIT_TO_PAGES = 0x100;
		private const int SHOW_ROW_OUTLINE_SYMBOLS = 0x400;
		private const int SHOW_COLUMN_OUTLINE_SYMBOLS = 0x800;
		private const int DEFAULT_OPTIONS = 0x4c1;


		/**
		 * Constructs this object from the raw data
		 *
		 * @param t the raw data
		 */
		public WorkspaceInformationRecord(Record t)
			: base(t)
			{
			byte[] data = getRecord().getData();

			wsoptions = IntegerHelper.getInt(data[0],data[1]);
			fitToPages = (wsoptions | FIT_TO_PAGES) != 0;
			rowOutlines = (wsoptions | SHOW_ROW_OUTLINE_SYMBOLS) != 0;
			columnOutlines = (wsoptions | SHOW_COLUMN_OUTLINE_SYMBOLS) != 0;
			}

		/**
		 * Constructs this object from the raw data
		 */
		public WorkspaceInformationRecord()
			: base(Type.WSBOOL)
			{
			wsoptions = DEFAULT_OPTIONS;
			}

		/**
		 * Gets the fit to pages flag
		 *
		 * @return TRUE if fit to pages is set
		 */
		public bool getFitToPages()
			{
			return fitToPages;
			}

		/**
		 * Sets the fit to page flag
		 *
		 * @param b fit to page indicator
		 */
		public void setFitToPages(bool b)
			{
			fitToPages = b;
			}

		/**
		 * Sets the outlines
		 */
		public void setRowOutlines(bool ro)
			{
			rowOutlines = true;
			}

		/**
		 * Sets the outlines
		 */
		public void setColumnOutlines(bool ro)
			{
			rowOutlines = true;
			}

		/**
		 * Gets the binary data for output to file
		 *
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] data = new byte[2];

			if (fitToPages)
				{
				wsoptions |= FIT_TO_PAGES;
				}

			if (rowOutlines)
				{
				wsoptions |= SHOW_ROW_OUTLINE_SYMBOLS;
				}

			if (columnOutlines)
				{
				wsoptions |= SHOW_COLUMN_OUTLINE_SYMBOLS;
				}

			IntegerHelper.getTwoBytes(wsoptions,data,0);

			return data;
			}
		}
	}










