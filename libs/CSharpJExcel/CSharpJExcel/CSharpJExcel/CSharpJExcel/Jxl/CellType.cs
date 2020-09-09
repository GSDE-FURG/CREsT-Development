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


namespace CSharpJExcel.Jxl
	{
	/**
	 * An enumeration type listing the available content types for a cell
	 */
	public sealed class CellType
		{

		/**
		 * The text description of this cell type
		 */
		private string description;

		/**
		 * Private constructor
		 * @param desc the description of this type
		 */
		private CellType(string desc)
			{
			description = desc;
			}

		/**
		 * Returns a string description of this cell
		 *
		 * @return the string description for this type
		 */
		public override string ToString()
			{
			return description;
			}

		/**
		 * An empty cell can still contain formatting information and comments
		 */
		public static readonly CellType EMPTY = new CellType("Empty");
		/**
		 */
		public static readonly CellType LABEL = new CellType("Label");
		/**
		 */
		public static readonly CellType NUMBER = new CellType("Number");
		/**
		 */
		public static readonly CellType BOOLEAN = new CellType("Boolean");
		/**
		 */
		public static readonly CellType ERROR = new CellType("Error");
		/**
		 */
		public static readonly CellType NUMBER_FORMULA = new CellType("Numerical Formula");
		/**
		 */
		public static readonly CellType DATE_FORMULA = new CellType("Date Formula");
		/**
		 */
		public static readonly CellType STRING_FORMULA = new CellType("string Formula");
		/**
		 */
		public static readonly CellType BOOLEAN_FORMULA = new CellType("Boolean Formula");
		/**
		 */
		public static readonly CellType FORMULA_ERROR = new CellType("Formula Error");
		/**
		 */
		public static readonly CellType DATE = new CellType("Date");

		}
	}


		
