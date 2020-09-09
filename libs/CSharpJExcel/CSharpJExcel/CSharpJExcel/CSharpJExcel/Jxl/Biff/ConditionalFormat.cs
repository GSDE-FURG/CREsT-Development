/*********************************************************************
*
*      Copyright (C) 2007 Andrew Khan
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
using CSharpJExcel.Jxl.Biff.Formula;
using CSharpJExcel.Jxl.Write.Biff;
using System.Collections;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Class containing the CONDFMT and CF records for conditionally formatting
	 * a cell
	 */
	public class ConditionalFormat
		{
		/**
		 * The range of the format
		 */
		private ConditionalFormatRangeRecord range;

		/**
		 * The format conditions
		 */
		private ArrayList conditions;

		/**
		 * Constructor
		 */
		public ConditionalFormat(ConditionalFormatRangeRecord cfrr)
			{
			range = cfrr;
			conditions = new ArrayList();
			}

		/**
		 * Adds a condition
		 *
		 * @param cond the condition
		 */
		public void addCondition(ConditionalFormatRecord cond)
			{
			conditions.Add(cond);
			}

		/**
		 * Inserts a blank column into this spreadsheet.  If the column is out of 
		 * range of the columns in the sheet, then no action is taken
		 *
		 * @param col the column to insert
		 */
		public void insertColumn(int col)
			{
			range.insertColumn(col);
			}

		/**
		 * Removes a column from this spreadsheet.  If the column is out of range
		 * of the columns in the sheet, then no action is taken
		 *
		 * @param col the column to remove
		 */
		public void removeColumn(int col)
			{
			range.removeColumn(col);
			}

		/**
		 * Removes a row from this spreadsheet.  If the row is out of 
		 * range of the columns in the sheet, then no action is taken
		 *
		 * @param row the row to remove
		 */
		public void removeRow(int row)
			{
			range.removeRow(row);
			}

		/**
		 * Inserts a blank row into this spreadsheet.  If the row is out of range
		 * of the rows in the sheet, then no action is taken
		 *
		 * @param row the row to insert
		 */
		public void insertRow(int row)
			{
			range.insertRow(row);
			}

		/**
		 * Writes out the data validation
		 * 
		 * @exception IOException 
		 * @param outputFile the output file
		 */
		public void write(File outputFile)
			{
			outputFile.write(range);

			foreach (ConditionalFormatRecord cfr in conditions)
				outputFile.write(cfr);
			}
		}
	}
