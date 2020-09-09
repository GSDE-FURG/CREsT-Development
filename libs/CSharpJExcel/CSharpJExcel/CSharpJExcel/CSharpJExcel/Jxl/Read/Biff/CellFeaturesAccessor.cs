/*********************************************************************
*
*      Copyright (C) 2005 Andrew Khan
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


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * Allows the setting of the cell features in this package only
	 */
	interface CellFeaturesAccessor
		{
		/**
		 * Sets the cell features
		 *
		 * @param cf the cell features
		 */
		void setCellFeatures(CellFeatures cf);

		/**
		 * Convenience function (due to casting) to get the cell features
		 *
		 * @return the cell features
		 */
		CellFeatures getCellFeatures();
		}
	}
