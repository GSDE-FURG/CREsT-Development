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


using CSharpJExcel.Jxl.Write.Biff;
using CSharpJExcel.Jxl.Format;


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * A cell, created by user applications, which contains a numerical value
	 */
	public class Formula : FormulaRecord,WritableCell
		{
		/**
		 * Constructs the formula
		 *
		 * @param c the column
		 * @param r the row
		 * @param form the  formula
		 */
		public Formula(int c,int r,string form)
			: base(c,r,form)
			{
			}

		/**
		 * Constructs a formula
		 *
		 * @param c the column
		 * @param r the row
		 * @param form the formula
		 * @param st the cell style
		 */
		public Formula(int c,int r,string form,CellFormat st)
			: base(c,r,form,st)
			{
			}

		/**
		 * Copy constructor
		 *
		 * @param c the column
		 * @param r the row
		 * @param f the record to  copy
		 */
		protected Formula(int c,int r,Formula f)
			: base(c,r,f)
			{
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
			return new Formula(col,row,this);
			}
		}
	}
