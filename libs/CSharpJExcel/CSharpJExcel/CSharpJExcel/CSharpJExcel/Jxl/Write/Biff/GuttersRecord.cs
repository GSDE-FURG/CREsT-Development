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


using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * Record which contains the size of the row and column gutters.  These are
	 * all set to zero by default
	 */
	class GuttersRecord : WritableRecordData
		{
		/**
		 * The binary data
		 */
		private byte[] data;
		/**
		 * The rowGutter
		 */
		private int rowGutter;
		/**
		 * The column gutter
		 */
		private int colGutter;
		/**
		 * The maximum outline level for the row gutter
		 */
		private int maxRowOutline;
		/**
		 * The maximum row outline level for the column gutter
		 */
		private int maxColumnOutline;

		/**
		 * Constructor
		 */
		public GuttersRecord()
			: base(Type.GUTS)
			{
			}

		/**
		 * Gets the binary data for output
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			data = new byte[8];
			IntegerHelper.getTwoBytes(rowGutter, data, 0);
			IntegerHelper.getTwoBytes(colGutter, data, 2);
			IntegerHelper.getTwoBytes(maxRowOutline, data, 4);
			IntegerHelper.getTwoBytes(maxColumnOutline, data, 6);
			return data;
			}

		/** 
		 * Accessor for the maximum row outline
		 *
		 * @return the maximum row outline
		 */
		public int getMaxRowOutline()
			{
			return maxRowOutline;
			}

		/** 
		 * Sets the maximum row outline
		 *
		 * @param value the maximum row outline
		 */
		public void setMaxRowOutline(int value)
			{
			maxRowOutline = value;
			rowGutter = 1 + 14 * value;
			}

		/** 
		 * Accessor for the maximum column outline
		 *
		 * @return the maximum column outline
		 */
		public int getMaxColumnOutline()
			{
			return maxColumnOutline;
			}

		/** 
		 * Sets the maximum column outline
		 *
		 * @param value the maximum row outline
		 */
		public void setMaxColumnOutline(int value)
			{
			maxColumnOutline = value;
			colGutter = 1 + 14 * value;
			}
		}
	}



