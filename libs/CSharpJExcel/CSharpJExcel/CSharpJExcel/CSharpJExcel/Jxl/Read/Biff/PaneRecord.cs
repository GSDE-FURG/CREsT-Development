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


using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * Contains the cell dimensions of this worksheet
	 */
	class PaneRecord : RecordData
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(PaneRecord.class);

		/**
		 * The number of rows visible in the top left pane
		 */
		private int rowsVisible;
		/**
		 * The number of columns visible in the top left pane
		 */
		private int columnsVisible;

		/**
		 * Constructs the dimensions from the raw data
		 *
		 * @param t the raw data
		 */
		public PaneRecord(Record t)
			: base(t)
			{
			byte[] data = t.getData();

			columnsVisible = IntegerHelper.getInt(data[0],data[1]);
			rowsVisible = IntegerHelper.getInt(data[2],data[3]);
			}

		/**
		 * Accessor for the number of rows in the top left pane
		 *
		 * @return the number of rows visible in the top left pane
		 */
		public int getRowsVisible()
			{
			return rowsVisible;
			}

		/**
		 * Accessor for the numbe rof columns visible in the top left pane
		 *
		 * @return the number of columns visible in the top left pane
		 */
		public int getColumnsVisible()
			{
			return columnsVisible;
			}
		}
	}
