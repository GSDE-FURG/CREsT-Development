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
	 * Enumeration class which contains the various alignments for data within a 
	 * cell
	 */
	public class Alignment
		{
		/**
		 * The internal numerical repreentation of the alignment
		 */
		private int value;

		/**
		 * The string description of this alignment
		 */
		private string description;

		/**
		 * The list of alignments
		 */
		private static Alignment[] alignments = new Alignment[0];

		/**
		 * Private constructor
		 * 
		 * @param val 
		 * @param string
		 */
		protected Alignment(int val,string s)
			{
			value = val;
			description = s;

			Alignment[] oldaligns = alignments;
			alignments = new Alignment[oldaligns.Length + 1];
			System.Array.Copy(oldaligns,0,alignments,0,oldaligns.Length);
			alignments[oldaligns.Length] = this;
			}

		/**
		 * Gets the value of this alignment.  This is the value that is written to 
		 * the generated Excel file
		 * 
		 * @return the binary value
		 */
		public int getValue()
			{
			return value;
			}

		/**
		 * Gets the string description of this alignment
		 *
		 * @return the string description
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
		public static Alignment getAlignment(int val)
			{
			for (int i = 0; i < alignments.Length; i++)
				{
				if (alignments[i].getValue() == val)
					{
					return alignments[i];
					}
				}

			return GENERAL;
			}

		/**
		 * The standard alignment
		 */
		public static readonly Alignment GENERAL = new Alignment(0,"general");
		/**
		 * Data cells with this alignment will appear at the left hand edge of the 
		 * cell
		 */
		public static readonly Alignment LEFT = new Alignment(1,"left");
		/**
		 * Data in cells with this alignment will be centred
		 */
		public static readonly Alignment CENTRE = new Alignment(2,"centre");
		/**
		 * Data in cells with this alignment will be right aligned
		 */
		public static readonly Alignment RIGHT = new Alignment(3,"right");
		/**
		 * Data in cells with this alignment will fill the cell
		 */
		public static readonly Alignment FILL = new Alignment(4,"fill");
		/**
		 * Data in cells with this alignment will be justified
		 */
		public static readonly Alignment JUSTIFY = new Alignment(5,"justify");
		}
	}

