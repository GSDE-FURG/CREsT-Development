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

using System.Text;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * A constant numerical value in a formula
	 */
	public abstract class NumberValue : Operand,ParsedThing
		{
		protected NumberValue()
			{
			}

		public abstract double getValue();

		public override void getString(StringBuilder buf)
			{
			buf.Append(getValue().ToString());
			}

		/** 
		 * Reads the ptg data from the array starting at the specified position
		 *
		 * @param data the RPN array
		 * @param pos the current position in the array, excluding the ptg identifier
		 * @return the number of bytes read
		 */
		public abstract int read(byte[] data, int pos);
		}
	}

