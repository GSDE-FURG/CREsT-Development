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
using CSharpJExcel.Jxl.Biff.Formula;
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A string formula's last calculated value
	 */
	class StringFormulaRecord : CellValue,LabelCell,FormulaData,StringFormulaCell
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(StringFormulaRecord.class);

		/**
		 * The last calculated value of the formula
		 */
		private string value;

		/**
		 * A handle to the class needed to access external sheets
		 */
		private ExternalSheet externalSheet;

		/**
		 * A handle to the name table
		 */
		private WorkbookMethods nameTable;

		/**
		 * The formula as an excel string
		 */
		private string formulaString;

		/**
		 * The raw data
		 */
		private byte[] data;

		/**
		 * Constructs this object from the raw data.  We need to use the excelFile
		 * to retrieve the string record which follows this formula record
		 *
		 * @param t the raw data
		 * @param excelFile the excel file
		 * @param fr the formatting records
		 * @param es the external sheet records
		 * @param nt the workbook
		 * @param si the sheet impl
		 * @param ws the workbook settings
		 */
		public StringFormulaRecord(Record t,File excelFile,
								   FormattingRecords fr,
								   ExternalSheet es,
								   WorkbookMethods nt,
								   SheetImpl si,
								   WorkbookSettings ws)
			: base(t,fr,si)
			{
			externalSheet = es;
			nameTable = nt;

			data = getRecord().getData();

			int pos = excelFile.getPos();

			// Look for the string record in one of the records after the
			// formula.  Put a cap on it to prevent looping

			Record nextRecord = excelFile.next();
			int count = 0;
			while (nextRecord.getType() != Type.STRING && count < 4)
				{
				nextRecord = excelFile.next();
				count++;
				}
			Assert.verify(count < 4," @ " + pos);
			byte[] stringData = nextRecord.getData();

			// Read in any continuation records
			nextRecord = excelFile.peek();
			while (nextRecord.getType() == Type.CONTINUE)
				{
				nextRecord = excelFile.next(); // move the pointer within the data
				byte[] d = new byte[stringData.Length + nextRecord.getLength() - 1];
				System.Array.Copy(stringData,0,d,0,stringData.Length);
				System.Array.Copy(nextRecord.getData(),1,d,
												 stringData.Length,nextRecord.getLength() - 1);
				stringData = d;
				nextRecord = excelFile.peek();
				}
			readString(stringData,ws);
			}

		/**
		 * Constructs this object from the raw data.  Used when reading in formula
		 * strings which evaluate to null (in the case of some IF statements)
		 *
		 * @param t the raw data
		 * @param fr the formatting records
		 * @param es the external sheet records
		 * @param nt the workbook
		 * @param si the sheet impl
		 * @param ws the workbook settings
		 */
		public StringFormulaRecord(Record t,
								   FormattingRecords fr,
								   ExternalSheet es,
								   WorkbookMethods nt,
								   SheetImpl si)
			: base(t,fr,si)
			{
			externalSheet = es;
			nameTable = nt;

			data = getRecord().getData();
			value = string.Empty;
			}


		/**
		 * Reads in the string
		 *
		 * @param d the data
		 * @param ws the workbook settings
		 */
		private void readString(byte[] d,WorkbookSettings ws)
			{
			int pos = 0;
			int chars = IntegerHelper.getInt(d[0],d[1]);

			if (chars == 0)
				{
				value = string.Empty;
				return;
				}
			pos += 2;
			int optionFlags = d[pos];
			pos++;

			if ((optionFlags & 0xf) != optionFlags)
				{
				// Uh oh - looks like a plain old string, not unicode
				// Recalculate all the positions
				pos = 0;
				chars = IntegerHelper.getInt(d[0],(byte)0);
				optionFlags = d[1];
				pos = 2;
				}

			// See if it is an extended string
			bool extendedString = ((optionFlags & 0x04) != 0);

			// See if string contains formatting information
			bool richString = ((optionFlags & 0x08) != 0);

			if (richString)
				{
				pos += 2;
				}

			if (extendedString)
				{
				pos += 4;
				}

			// See if string is ASCII (compressed) or unicode
			bool asciiEncoding = ((optionFlags & 0x01) == 0);

			if (asciiEncoding)
				{
				value = StringHelper.getString(d,chars,pos,ws);
				}
			else
				{
				value = StringHelper.getUnicodeString(d,chars,pos);
				}
			}

		/**
		 * Interface method which returns the value
		 *
		 * @return the last calculated value of the formula
		 */
		public override string getContents()
			{
			return value;
			}

		/**
		 * Interface method which returns the value
		 *
		 * @return the last calculated value of the formula
		 */
		public string getString()
			{
			return value;
			}

		/**
		 * Returns the cell type
		 *
		 * @return The cell type
		 */
		public override CellType getType()
			{
			return CellType.STRING_FORMULA;
			}

		/**
		 * Gets the raw bytes for the formula.  This will include the
		 * parsed tokens array
		 *
		 * @return the raw record data
		 */
		public byte[] getFormulaData()
			{
			if (!getSheet().getWorkbook().getWorkbookBof().isBiff8())
				{
				throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
				}

			// Lop off the standard information
			byte[] d = new byte[data.Length - 6];
			System.Array.Copy(data,6,d,0,data.Length - 6);

			return d;
			}

		/**
		 * Gets the formula as an excel string
		 *
		 * @return the formula as an excel string
		 * @exception FormulaException
		 */
		public string getFormula()
			{
			if (formulaString == null)
				{
				byte[] tokens = new byte[data.Length - 22];
				System.Array.Copy(data,22,tokens,0,tokens.Length);
				FormulaParser fp = new FormulaParser
				  (tokens,this,externalSheet,nameTable,
				   getSheet().getWorkbook().getSettings());
				fp.parse();
				formulaString = fp.getFormula();
				}

			return formulaString;
			}
		}
	}
