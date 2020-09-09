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


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * A cell reference in a formula
	 */
	public class IntegerValue : NumberValue,ParsedThing
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(IntegerValue.class);

		/**
		 * The value of this integer
		 */
		private double value;

		/**
		 * Flag which indicates whether or not this integer is out range
		 */
		private bool outOfRange;

		/**
		 * Constructor
		 */
		public IntegerValue()
			{
			outOfRange = false;
			}

		/**
		 * Constructor for an integer value being read from a string
		 */
		public IntegerValue(string s)
			{
			try
				{
				value = System.Int32.Parse(s);
				}
			catch (FormatException e)
				{
				//logger.warn(e,e);
				value = 0;
				}

			short v = (short)value;
			outOfRange = (value != v);
			}

		/** 
		 * Reads the ptg data from the array starting at the specified position
		 *
		 * @param data the RPN array
		 * @param pos the current position in the array, excluding the ptg identifier
		 * @return the number of bytes read
		 */
		public override int read(byte[] data,int pos)
			{
			value = IntegerHelper.getInt(data[pos],data[pos + 1]);

			return 2;
			}

		/**
		 * Gets the token representation of this item in RPN
		 *
		 * @return the bytes applicable to this formula
		 */
		public override byte[] getBytes()
			{
			byte[] data = new byte[3];
			data[0] = Token.INTEGER.getCode();

			IntegerHelper.getTwoBytes((int)value,data,1);

			return data;
			}

		/**
		 * Accessor for the value
		 *
		 * @return the value
		 */
		public override double getValue()
			{
			return value;
			}

		/**
		 * Accessor for the out of range flag
		 *
		 * @return TRUE if the value is out of range for an excel integer
		 */
		public bool isOutOfRange()
			{
			return outOfRange;
			}

		/**
		  * If this formula was on an imported sheet, check that
		  * cell references to another sheet are warned appropriately
		  * Does nothing
		  */
		public override void handleImportedCellReferences()
			{
			}
		}
	}