/*********************************************************************
*
*      Copyright (C) 2002 Andrew Khan
*
* This library inStream free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library inStream distributed input the hope that it will be useful,
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

using System.Collections.Generic;
using System.Text;
using CSharpJExcel.Jxl;
using System.IO;
using CSharpJExcel.Jxl.Read.Biff;
using System;

namespace Demo
	{
	public class BiffDump
		{
		private CSharpJExcel.Jxl.Read.Biff.BiffRecordReader reader;

		private Dictionary<CSharpJExcel.Jxl.Biff.Type, string> recordNames;

		private int xfIndex;
		private int fontIndex;
		private int bofs;

		private static readonly int bytesPerLine = 16;

		/**
		 * Constructor
		 *
		 * @param file the file
		 * @param os the output stream
		 * @exception IOException 
		 * @exception BiffException
		 */
		public BiffDump(FileInfo file, TextWriter os)
			{
			FileStream fis = new FileStream(file.FullName,FileMode.Open);
			CSharpJExcel.Jxl.Read.Biff.File f = new CSharpJExcel.Jxl.Read.Biff.File(fis, new WorkbookSettings());
			reader = new BiffRecordReader(f);

			buildNameHash();
			dump(os);

			os.Flush();
//			os.close();
			fis.Close();
			}

		/**
		 * Builds the hashmap of record names
		 */
		private void buildNameHash()
			{
			recordNames = new Dictionary<CSharpJExcel.Jxl.Biff.Type,string>();

			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BOF, "BOF");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.EOF, "EOF");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FONT, "FONT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SST, "SST");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.LABELSST, "LABELSST");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.WRITEACCESS, "WRITEACCESS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FORMULA, "FORMULA");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FORMULA2, "FORMULA");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.XF, "XF");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.MULRK, "MULRK");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.NUMBER, "NUMBER");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BOUNDSHEET, "BOUNDSHEET");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.CONTINUE, "CONTINUE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FORMAT, "FORMAT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.EXTERNSHEET, "EXTERNSHEET");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.INDEX, "INDEX");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DIMENSION, "DIMENSION");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.ROW, "ROW");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DBCELL, "DBCELL");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BLANK, "BLANK");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.MULBLANK, "MULBLANK");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.RK, "RK");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.RK2, "RK");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.COLINFO, "COLINFO");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.LABEL, "LABEL");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SHAREDFORMULA, "SHAREDFORMULA");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.CODEPAGE, "CODEPAGE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.WINDOW1, "WINDOW1");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.WINDOW2, "WINDOW2");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.MERGEDCELLS, "MERGEDCELLS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.HLINK, "HLINK");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.HEADER, "HEADER");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FOOTER, "FOOTER");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.INTERFACEHDR, "INTERFACEHDR");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.MMS, "MMS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.INTERFACEEND, "INTERFACEEND");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DSF, "DSF");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FNGROUPCOUNT, "FNGROUPCOUNT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.COUNTRY, "COUNTRY");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.TABID, "TABID");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PROTECT, "PROTECT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SCENPROTECT, "SCENPROTECT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.OBJPROTECT, "OBJPROTECT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.WINDOWPROTECT, "WINDOWPROTECT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PASSWORD, "PASSWORD");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PROT4REV, "PROT4REV");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PROT4REVPASS, "PROT4REVPASS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BACKUP, "BACKUP");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.HIDEOBJ, "HIDEOBJ");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.NINETEENFOUR, "1904");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PRECISION, "PRECISION");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BOOKBOOL, "BOOKBOOL");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.STYLE, "STYLE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.EXTSST, "EXTSST");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.REFRESHALL, "REFRESHALL");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.CALCMODE, "CALCMODE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.CALCCOUNT, "CALCCOUNT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.NAME, "NAME");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.MSODRAWINGGROUP, "MSODRAWINGGROUP");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.MSODRAWING, "MSODRAWING");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.OBJ, "OBJ");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.USESELFS, "USESELFS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SUPBOOK, "SUPBOOK");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.LEFTMARGIN, "LEFTMARGIN");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.RIGHTMARGIN, "RIGHTMARGIN");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.TOPMARGIN, "TOPMARGIN");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BOTTOMMARGIN, "BOTTOMMARGIN");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.HCENTER, "HCENTER");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.VCENTER, "VCENTER");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.ITERATION, "ITERATION");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DELTA, "DELTA");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SAVERECALC, "SAVERECALC");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PRINTHEADERS, "PRINTHEADERS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PRINTGRIDLINES, "PRINTGRIDLINES");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SETUP, "SETUP");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SELECTION, "SELECTION");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.STRING, "STRING");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FONTX, "FONTX");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.IFMT, "IFMT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.WSBOOL, "WSBOOL");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.GRIDSET, "GRIDSET");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.REFMODE, "REFMODE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.GUTS, "GUTS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.EXTERNNAME, "EXTERNNAME");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FBI, "FBI");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.CRN, "CRN");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.HORIZONTALPAGEBREAKS, "HORIZONTALPAGEBREAKS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.VERTICALPAGEBREAKS, "VERTICALPAGEBREAKS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DEFAULTROWHEIGHT, "DEFAULTROWHEIGHT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.TEMPLATE, "TEMPLATE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PANE, "PANE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SCL, "SCL");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PALETTE, "PALETTE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.PLS, "PLS");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.OBJPROJ, "OBJPROJ");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DEFCOLWIDTH, "DEFCOLWIDTH");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.ARRAY, "ARRAY");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.WEIRD1, "WEIRD1");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BOOLERR, "BOOLERR");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SORT, "SORT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.BUTTONPROPERTYSET, "BUTTONPROPERTYSET");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.NOTE, "NOTE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.TXO, "TXO");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DV, "DV");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.DVAL, "DVAL");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SERIES, "SERIES");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SERIESLIST, "SERIESLIST");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.SBASEREF, "SBASEREF");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.CONDFMT, "CONDFMT");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.CF, "CF");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.FILTERMODE, "FILTERMODE");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.AUTOFILTER, "AUTOFILTER");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.AUTOFILTERINFO, "AUTOFILTERINFO");
			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.XCT, "XCT");

			recordNames.Add(CSharpJExcel.Jxl.Biff.Type.UNKNOWN, "???");
			}
		/**
		 * Dumps out the contents of the excel file
		 */
		private void dump(TextWriter os)
			{
			Record r = null;
			bool cont = true;
			while (reader.hasNext() && cont)
				{
				r = reader.next();
				cont = writeRecord(r,os);
				}
			}

		/**
		 * Writes out the biff record
		 * @param r
		 * @exception IOException if an error occurs
		 */
		private bool writeRecord(CSharpJExcel.Jxl.Read.Biff.Record r, TextWriter os)
			{
			bool cont = true;
			int pos = reader.getPos();
			int code = r.getCode();

			if (bofs == 0)
				{
				cont = (r.getType() == CSharpJExcel.Jxl.Biff.Type.BOF);
				}

			if (!cont)
				{
				return cont;
				}

			if (r.getType() == CSharpJExcel.Jxl.Biff.Type.BOF)
				{
				bofs++;
				}

			if (r.getType() == CSharpJExcel.Jxl.Biff.Type.EOF)
				{
				bofs--;
				}

			StringBuilder buf = new StringBuilder();

			// Write out the record header
			writeSixDigitValue(pos, buf);
			buf.Append(" [");
			buf.Append(recordNames[r.getType()]);
			buf.Append("]");
			buf.Append("  (0x");
			buf.Append(code.ToString("x"));
			buf.Append(")");

			if (code == CSharpJExcel.Jxl.Biff.Type.XF.value)
				{
				buf.Append(" (0x");
				buf.Append(xfIndex.ToString("x"));
				buf.Append(")");
				xfIndex++;
				}

			if (code == CSharpJExcel.Jxl.Biff.Type.FONT.value)
				{
				if (fontIndex == 4)
					{
					fontIndex++;
					}

				buf.Append(" (0x");
				buf.Append(fontIndex.ToString("x"));
				buf.Append(")");
				fontIndex++;
				}

			os.Write(buf.ToString());
			os.WriteLine();

			byte[] standardData = new byte[4];
			standardData[0] = (byte)(code & 0xff);
			standardData[1] = (byte)((code & 0xff00) >> 8);
			standardData[2] = (byte)(r.getLength() & 0xff);
			standardData[3] = (byte)((r.getLength() & 0xff00) >> 8);
			byte[] recordData = r.getData();
			byte[] data = new byte[standardData.Length + recordData.Length];
			Array.Copy(standardData, 0, data, 0, standardData.Length);
			Array.Copy(recordData, 0, data, standardData.Length, recordData.Length);

			int byteCount = 0;
			int lineBytes = 0;

			while (byteCount < data.Length)
				{
				buf = new StringBuilder();
				writeSixDigitValue(pos + byteCount, buf);
				buf.Append("   ");

				lineBytes = Math.Min(bytesPerLine, data.Length - byteCount);

				for (int i = 0; i < lineBytes; i++)
					{
					writeByte(data[i + byteCount], buf);
					buf.Append(' ');
					}

				// Perform any padding
				if (lineBytes < bytesPerLine)
					{
					for (int i = 0; i < bytesPerLine - lineBytes; i++)
						{
						buf.Append("   ");
						}
					}

				buf.Append("  ");

				for (int i = 0; i < lineBytes; i++)
					{
					char c = (char)data[i + byteCount];
					if (c < ' ' || c > 'z')
						c = '.';
					buf.Append(c);
					}

				byteCount += lineBytes;

				os.Write(buf.ToString());
				os.WriteLine();
				}

			return cont;
			}

		/**
		 * Writes the string passed in as a minimum of four digits
		 */
		private void writeSixDigitValue(int pos, StringBuilder buf)
			{
			string val = pos.ToString("x");

			for (int i = 6; i > val.Length; i--)
				buf.Append('0');
			buf.Append(val);
			}

		/**
		 * Writes the string passed in as a minimum of four digits
		 */
		private void writeByte(byte val, StringBuilder buf)
			{
			string sv = (val & 0xff).ToString("x");

			if (sv.Length == 1)
				buf.Append('0');
			buf.Append(sv);
			}
		}
	}
