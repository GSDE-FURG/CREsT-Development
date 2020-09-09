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
	 * A string formula record, manufactured out of the Shared Formula
	 * "optimization"
	 */
	public class SharedStringFormulaRecord : BaseSharedFormulaRecord,LabelCell,FormulaData,StringFormulaCell
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(SharedStringFormulaRecord.class);

		/**
		 * The value of this string formula
		 */
		private string value;

		// Dummy value for overloading the constructor when the string evaluates
		// to null
		public sealed class EmptyString 
			{ 
			};

		public static readonly EmptyString EMPTY_STRING = new EmptyString();

		/**
		 * Constructs this string formula
		 *
		 * @param t the record
		 * @param excelFile the excel file
		 * @param fr the formatting record
		 * @param es the external sheet
		 * @param nt the workbook
		 * @param si the sheet
		 * @param ws the workbook settings
		 */
		public SharedStringFormulaRecord(Record t,
										 File excelFile,
										 FormattingRecords fr,
										 ExternalSheet es,
										 WorkbookMethods nt,
										 SheetImpl si,
										 WorkbookSettings ws)
			: base(t,fr,es,nt,si,excelFile.getPos())
			{
			int pos = excelFile.getPos();

			// Save the position in the excel file
			int filepos = excelFile.getPos();

			// Look for the string record in one of the records after the
			// formula.  Put a cap on it to prevent ednas
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

			int chars = IntegerHelper.getInt(stringData[0],stringData[1]);

			bool unicode = false;
			int startpos = 3;
			if (stringData.Length == chars + 2)
				{
				// string might only consist of a one byte length indicator, instead
				// of the more normal 2
				startpos = 2;
				unicode = false;
				}
			else if (stringData[2] == 0x1)
				{
				// unicode string, two byte length indicator
				startpos = 3;
				unicode = true;
				}
			else
				{
				// ascii string, two byte length indicator
				startpos = 3;
				unicode = false;
				}

			if (!unicode)
				{
				value = StringHelper.getString(stringData,chars,startpos,ws);
				}
			else
				{
				value = StringHelper.getUnicodeString(stringData,chars,startpos);
				}

			// Restore the position in the excel file, to enable the SHRFMLA
			// record to be picked up
			excelFile.setPos(filepos);
			}

		/**
		 * Constructs this string formula
		 *
		 * @param t the record
		 * @param excelFile the excel file
		 * @param fr the formatting record
		 * @param es the external sheet
		 * @param nt the workbook
		 * @param si the sheet
		 * @param dummy the overload indicator
		 */
		public SharedStringFormulaRecord(Record t,
										 File excelFile,
										 FormattingRecords fr,
										 ExternalSheet es,
										 WorkbookMethods nt,
										 SheetImpl si,
										 EmptyString dummy)
			: base(t,fr,es,nt,si,excelFile.getPos())
			{
			value = string.Empty;
			}

		/**
		 * Accessor for the value
		 *
		 * @return the value
		 */
		public string getString()
			{
			return value;
			}

		/**
		 * Accessor for the contents as a string
		 *
		 * @return the value as a string
		 */
		public override string getContents()
			{
			return value;
			}

		/**
		 * Accessor for the cell type
		 *
		 * @return the cell type
		 */
		public override CellType getType()
			{
			return CellType.STRING_FORMULA;
			}

		/**
		 * Gets the raw bytes for the formula.  This will include the
		 * parsed tokens array.  Used when copying spreadsheets
		 *
		 * @return the raw record data
		 * @exception FormulaException
		 */
		public override byte[] getFormulaData()
			{
			if (!getSheet().getWorkbookBof().isBiff8())
				{
				throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
				}

			// Get the tokens, taking into account the mapping from shared
			// formula specific values into normal values
			FormulaParser fp = new FormulaParser
			  (getTokens(),this,
			   getExternalSheet(),getNameTable(),
			   getSheet().getWorkbook().getSettings());
			fp.parse();
			byte[] rpnTokens = fp.getBytes();

			byte[] data = new byte[rpnTokens.Length + 22];

			// Set the standard info for this cell
			IntegerHelper.getTwoBytes(getRow(),data,0);
			IntegerHelper.getTwoBytes(getColumn(),data,2);
			IntegerHelper.getTwoBytes(getXFIndex(),data,4);

			// Set the two most significant bytes of the value to be 0xff in
			// order to identify this as a string
			data[6] = 0;
			data[12] = (byte)0xff;
			data[13] = (byte)0xff;

			// Now copy in the parsed tokens
			System.Array.Copy(rpnTokens,0,data,22,rpnTokens.Length);
			IntegerHelper.getTwoBytes(rpnTokens.Length,data,20);

			// Lop off the standard information
			byte[] d = new byte[data.Length - 6];
			System.Array.Copy(data,6,d,0,data.Length - 6);

			return d;
			}
		}
	}
