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


namespace CSharpJExcel.Jxl.Format
	{
	/**
	 * The location of a border
	 */
	public class Border
		{
		/**
		 * The string description
		 */
		private string description;

		/**
		 * Constructor
		 */
		protected Border(string s)
			{
			description = s;
			}

		/**
		 * Gets the description
		 */
		public string getDescription()
			{
			return description;
			}

		public static readonly Border NONE = new Border("none");
		public static readonly Border ALL = new Border("all");
		public static readonly Border TOP = new Border("top");
		public static readonly Border BOTTOM = new Border("bottom");
		public static readonly Border LEFT = new Border("left");
		public static readonly Border RIGHT = new Border("right");
		}
	}


