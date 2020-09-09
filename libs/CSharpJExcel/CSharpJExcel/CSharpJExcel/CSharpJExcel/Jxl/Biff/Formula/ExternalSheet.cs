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


using CSharpJExcel.Jxl.Read.Biff;
namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * Interface which exposes the methods needed by formulas
	 * to access external sheet records
	 */
	public interface ExternalSheet
		{
		/**
		 * Gets the name of the external sheet specified by the index
		 *
		 * @param index the external sheet index
		 * @return the name of the external sheet
		 */
		string getExternalSheetName(int index);

		/**
		 * Gets the index of the first external sheet for the name
		 *
		 * @param sheetName the name of the external sheet
		 * @return  the index of the external sheet with the specified name
		 */
		int getExternalSheetIndex(string sheetName);

		/**
		 * Gets the index of the first external sheet for the name
		 *
		 * @param index the external sheet index
		 * @return the sheet index of the external sheet index
		 */
		int getExternalSheetIndex(int index);

		/**
		 * Gets the index of the last external sheet for the name
		 *
		 * @param sheetName the name of the external sheet
		 * @return  the index of the external sheet with the specified name
		 */
		int getLastExternalSheetIndex(string sheetName);

		/**
		 * Gets the index of the first external sheet for the name
		 *
		 * @param index the external sheet index
		 * @return the sheet index of the external sheet index
		 */
		int getLastExternalSheetIndex(int index);

		/**
		 * Parsing of formulas is only supported for a subset of the available
		 * biff version, so we need to test to see if this version is acceptable
		 *
		 * @return the BOF record
		 */
		BOFRecord getWorkbookBof();
		}
	}
