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

using System;
using CSharpJExcel.Interop;

namespace CSharpJExcel.Jxl
	{
	/**
	 * A date cell
	 */
	public interface DateCell : Cell
		{
		/**
		 * Gets the date contained in this cell
		 *
		 * @return the cell contents
		 */
		DateTime getDate();

		/**
		 * Indicates whether the date value contained in this cell refers to a date,
		 * or merely a time
		 *
		 * @return TRUE if the value refers to a time
		 */
		bool isTime();

		/**
		 * Gets the DateFormat used to format the cell.  This will normally be
		 * the format specified in the excel spreadsheet, but in the event of any
		 * difficulty parsing this, it will revert to the default date/time format.
		 *
		 * @return the DateFormat object used to format the date in the original
		 * excel cell
		 */
		DateFormat getDateFormat();
		}
	}