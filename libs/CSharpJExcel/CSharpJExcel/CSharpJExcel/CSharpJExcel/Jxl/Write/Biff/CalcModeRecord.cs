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
	 * The calculation mode for the workbook, as set from the Options
	 * dialog box
	 */
	public class CalcModeRecord : WritableRecordData
		{
		/**
		 * The calculation mode (manual, automatic)
		 */
		private CalcMode calculationMode;

		public sealed class CalcMode
			{
			/**
			 * The indicator as written to the output file
			 */
			public int value;

			/**
			 * Constructor
			 * 
			 * @param m 
			 */
			public CalcMode(int m)
				{
				value = m;
				}
			}

		/**
		 * Manual calculation
		 */
		public static CalcMode manual = new CalcMode(0);
		/**
		 * Automatic calculation
		 */
		public static CalcMode automatic = new CalcMode(1);
		/**
		 * Automatic calculation, except tables
		 */
		public static CalcMode automaticNoTables = new CalcMode(-1);

		/**
		 * Constructor
		 * 
		 * @param cm the calculation mode
		 */
		public CalcModeRecord(CalcMode cm)
			: base(Type.CALCMODE)
			{
			calculationMode = cm;
			}


		/**
		 * Gets the binary to data to write to the output file
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] data = new byte[2];

			IntegerHelper.getTwoBytes(calculationMode.value, data, 0);

			return data;
			}
		}
	}

