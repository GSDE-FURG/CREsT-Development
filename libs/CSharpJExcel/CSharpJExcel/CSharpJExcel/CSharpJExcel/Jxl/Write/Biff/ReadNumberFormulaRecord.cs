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

using CSharpJExcel.Jxl.Biff;
using CSharpJExcel.Jxl.Biff.Formula;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * Class for read number formula records
	 */
	class ReadNumberFormulaRecord : ReadFormulaRecord, NumberFormulaCell
		{
		// The logger
		//  private static Logger logger = Logger.getLogger(ReadNumberFormulaRecord.class);

		/**
		 * Constructor
		 *
		 * @param f
		 */
		public ReadNumberFormulaRecord(FormulaData f)
			: base(f)
			{
			}

		/**
		 * Gets the double contents for this cell.
		 *
		 * @return the cell contents
		 */
		public double getValue()
			{
			return ((NumberFormulaCell)getReadFormula()).getValue();
			}

		/**
		 * Gets the NumberFormat used to format this cell.  This is the java
		 * equivalent of the Excel format
		 *
		 * @return the NumberFormat used to format the cell
		 */
		public CSharpJExcel.Interop.NumberFormat getNumberFormat()
			{
			return ((NumberFormulaCell)getReadFormula()).getNumberFormat();
			}

		/**
		 * Error formula specific exception handling.  Can't really create
		 * a formula (as it will look for a cell of that name, so just
		 * create a STRING record containing the contents
		 *
		 * @return the bodged data
		 */
		public override byte[] handleFormulaException()
			{
			byte[] expressiondata = null;
			byte[] celldata = base.getCellData();

			// Generate an appropriate dummy formula
			WritableWorkbookImpl w = getSheet().getWorkbook();
			FormulaParser parser = new FormulaParser(getValue().ToString(), w, w, w.getSettings());

			// Get the bytes for the dummy formula
			try
				{
				parser.parse();
				}
			catch (FormulaException e2)
				{
				//logger.warn(e2.Message);
				}
			byte[] formulaBytes = parser.getBytes();
			expressiondata = new byte[formulaBytes.Length + 16];
			IntegerHelper.getTwoBytes(formulaBytes.Length, expressiondata, 14);
			System.Array.Copy(formulaBytes, 0, expressiondata, 16,
							 formulaBytes.Length);

			// Set the recalculate on load bit
			expressiondata[8] |= 0x02;

			byte[] data = new byte[celldata.Length +
								   expressiondata.Length];
			System.Array.Copy(celldata, 0, data, 0, celldata.Length);
			System.Array.Copy(expressiondata, 0, data,
							 celldata.Length, expressiondata.Length);

			// Store the value in the formula
			DoubleHelper.getIEEEBytes(getValue(), data, 6);

			return data;
			}
		}
	}

