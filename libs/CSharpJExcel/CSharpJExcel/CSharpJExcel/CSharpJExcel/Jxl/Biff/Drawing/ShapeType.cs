/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan
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

namespace CSharpJExcel.Jxl.Biff.Drawing
	{

	/**
	 * Enumerations for the shape type
	 */
	public sealed class ShapeType
		{
		/**
		 * The value
		 */
		private int value;

		/**
		 * The list of shape types
		 */
		private static ShapeType[] types = new ShapeType[0];

		/**
		 * Constructor
		 *
		 * @param v the value
		 */
		internal ShapeType(int v)
			{
			value = v;

			ShapeType[] old = types;
			types = new ShapeType[types.Length + 1];
			System.Array.Copy(old,0,types,0,old.Length);
			types[old.Length] = this;
			}

		/**
		 * Gets the shape type given the value
		 *
		 * @param v the value
		 * @return the shape type for the value
		 */
		public static ShapeType getType(int v)
			{
			ShapeType st = UNKNOWN;
			bool found = false;
			for (int i = 0; i < types.Length && !found; i++)
				{
				if (types[i].value == v)
					{
					found = true;
					st = types[i];
					}
				}
			return st;
			}

		/**
		 * Accessor for the value
		 *
		 * @return the value
		 */
		public int getValue()
			{
			return value;
			}

		public static readonly ShapeType MIN = new ShapeType(0);
		public static readonly ShapeType PICTURE_FRAME = new ShapeType(75);
		public static readonly ShapeType HOST_CONTROL = new ShapeType(201);
		public static readonly ShapeType TEXT_BOX = new ShapeType(202);
		public static readonly ShapeType UNKNOWN = new ShapeType(-1);
		}
	}
