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


using CSharpJExcel.Jxl;
using CSharpJExcel.Jxl.Format;
using CSharpJExcel.Jxl.Write.Biff;


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * A cell, created by user applications, which contains a bool (or
	 * in some cases an error) value
	 */
	public class Boolean : BooleanRecord,WritableCell,BooleanCell
		{
		/**
		 * Constructs a bool value, which, when added to a spreadsheet, will
		 * display the specified value at the column/row position indicated.
		 *
		 * @param c the column
		 * @param r the row
		 * @param val the value
		 */
		public Boolean(int c,int r,bool val)
			: base(c,r,val)
			{
			}

		/**
		 * Constructs a bool, which, when added to a spreadsheet, will display the
		 * specified value at the column/row position with the specified CellFormat.
		 * The CellFormat may specify font information
		 *
		 * @param c the column
		 * @param r the row
		 * @param val the value
		 * @param st the cell format
		 */
		public Boolean(int c,int r,bool val,CellFormat st)
			: base(c,r,val,st)
			{
			}

		/**
		 * Constructor used internally by the application when making a writable
		 * copy of a spreadsheet that has been read in
		 *
		 * @param nc the cell to copy
		 */
		public Boolean(BooleanCell nc)
			: base(nc)
			{
			}

		/**
		 * Copy constructor used for deep copying
		 *
		 * @param col the column
		 * @param row the row
		 * @param b the cell to copy
		 */
		protected Boolean(int col,int row,Boolean b)
			: base(col,row,b)
			{
			}
		/**
		 * Sets the bool value for this cell
		 *
		 * @param val the value
		 */
		public override void setValue(bool val)
			{
			base.setValue(val);
			}

		/**
		 * Implementation of the deep copy function
		 *
		 * @param col the column which the new cell will occupy
		 * @param row the row which the new cell will occupy
		 * @return  a copy of this cell, which can then be added to the sheet
		 */
		public override WritableCell copyTo(int col,int row)
			{
			return new Boolean(col,row,this);
			}
		}
	}
