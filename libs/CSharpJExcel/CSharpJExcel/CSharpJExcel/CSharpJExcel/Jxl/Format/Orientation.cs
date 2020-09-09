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
	 * Enumeration type which describes the orientation of data within a cell
	 */
	public sealed class Orientation
		{
		/**
		 * The internal binary value which gets written to the generated Excel file
		 */
		private int value;

		/**
		 * The textual description
		 */
		private string description;

		/**
		 * The list of alignments
		 */
		private static Orientation[] orientations = new Orientation[0];

		/**
		 * Constructor
		 * 
		 * @param val 
		 */
		internal Orientation(int val,string s)
			{
			value = val;
			description = s;

			Orientation[] oldorients = orientations;
			orientations = new Orientation[oldorients.Length + 1];
			System.Array.Copy(oldorients,0,orientations,0,oldorients.Length);
			orientations[oldorients.Length] = this;
			}

		/**
		 * Accessor for the binary value
		 * 
		 * @return the internal binary value
		 */
		public int getValue()
			{
			return value;
			}

		/**
		 * Gets the textual description
		 */
		public string getDescription()
			{
			return description;
			}

		/**
		 * Gets the alignment from the value
		 *
		 * @param val 
		 * @return the alignment with that value
		 */
		public static Orientation getOrientation(int val)
			{
			for (int i = 0; i < orientations.Length; i++)
				{
				if (orientations[i].getValue() == val)
					{
					return orientations[i];
					}
				}

			return HORIZONTAL;
			}


		/**
		 * Cells with this specified orientation will be horizontal
		 */
		public static readonly Orientation HORIZONTAL = new Orientation(0,"horizontal");
		/**
		 * Cells with this specified orientation have their data
		 * presented vertically
		 */
		public static readonly Orientation VERTICAL = new Orientation(0xff,"vertical");
		/**
		 * Cells with this specified orientation will have their data
		 * presented with a rotation of 90 degrees upwards
		 */
		public static readonly Orientation PLUS_90 = new Orientation(90,"up 90");
		/**
		 * Cells with this specified orientation will have their data
		 * presented with a rotation of 90 degrees downwards
		 */
		public static readonly Orientation MINUS_90 = new Orientation(180,"down 90");
		/**
		 * Cells with this specified orientation will have their data
		 * presented with a rotation 45 degrees upwards
		 */
		public static readonly Orientation PLUS_45 = new Orientation(45,"up 45");
		/**
		 * Cells with this specified orientation will have their data
		 * presented with a rotation 45 degrees downwards
		 */
		public static readonly Orientation MINUS_45 = new Orientation(135,"down 45");
		/**
		 * Cells with this specified orientation will have their text stacked
		 * downwards, but not rotated
		 */
		public static readonly Orientation STACKED = new Orientation(255,"stacked");

		}
	}
