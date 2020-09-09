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

using System;
using System.Collections.Generic;
using System.Text;
using CSharpJExcel.Jxl;
using System.IO;
using CSharpJExcel.Jxl.Format;

namespace Demo
	{
	/**
	 * Simple demo class which uses the api to present the contents
	 * of an excel 97 spreadsheet as an XML document, using a workbook
	 * and output stream of your choice
	 */
	public class XML
		{
		/**
		 * The output stream to write to
		 */
		/** 
		 * The encoding to write
		 */
		private string encoding;

		/**
		 * The workbook we are reading from
		 */
		private Workbook workbook;

		/**
		 * Constructor
		 *
		 * @param w The workbook to interrogate
		 * @param os The output stream to which the XML values are written
		 * @param enc The encoding used by the output stream.  Null or 
		 * unrecognized values cause the encoding to default to UTF8
		 * @param f Indicates whether the generated XML document should contain
		 * the cell format information
		 * @exception java.io.IOException
		 */
		public XML(Workbook w, TextWriter os, string enc, bool f)
			{
			encoding = enc;
			workbook = w;

			if (encoding == null || encoding != "UnicodeBig")
				{
				encoding = "UTF8";
				}

			if (f)
				writeFormattedXML(os);
			else
				writeXML(os);
			}

		/**
	   * Writes os the workbook data as XML, without formatting information
	   */
		private void writeXML(TextWriter os)
			{
			try
				{
				//OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				//BufferedWriter bw = new BufferedWriter(osw);

				os.Write("<?xml version=\"1.0\" ?>");
				os.WriteLine();
				os.Write("<!DOCTYPE workbook SYSTEM \"workbook.dtd\">");
				os.WriteLine();
				os.WriteLine();
				os.Write("<workbook>");
				os.WriteLine();
				for (int sheet = 0; sheet < workbook.getNumberOfSheets(); sheet++)
					{
					Sheet s = workbook.getSheet(sheet);

					os.Write("  <sheet>");
					os.WriteLine();
					os.Write("    <name><![CDATA[" + s.getName() + "]]></name>");
					os.WriteLine();

					Cell[] row = null;

					for (int i = 0; i < s.getRows(); i++)
						{
						os.Write("    <row number=\"" + i + "\">");
						os.WriteLine();
						row = s.getRow(i);

						for (int j = 0; j < row.Length; j++)
							{
							if (row[j].getType() != CellType.EMPTY)
								{
								os.Write("      <col number=\"" + j + "\">");
								os.Write("<![CDATA[" + row[j].getContents() + "]]>");
								os.Write("</col>");
								os.WriteLine();
								}
							}
						os.Write("    </row>");
						os.WriteLine();
						}
					os.Write("  </sheet>");
					os.WriteLine();
					}

				os.Write("</workbook>");
				os.WriteLine();

				os.Flush();
				//bw.close();
				}
			catch (Exception e)
				{
				Console.WriteLine(e);
				}
			}

		/**
		 * Writes os the workbook data as XML, with formatting information
		 */
		private void writeFormattedXML(TextWriter os)
			{
			try
				{
				//OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				//BufferedWriter bw = new BufferedWriter(osw);

				os.Write("<?xml version=\"1.0\" ?>");
				os.WriteLine();
				os.Write("<!DOCTYPE workbook SYSTEM \"formatworkbook.dtd\">");
				os.WriteLine();
				os.WriteLine();
				os.Write("<workbook>");
				os.WriteLine();
				for (int sheet = 0; sheet < workbook.getNumberOfSheets(); sheet++)
					{
					Sheet s = workbook.getSheet(sheet);

					os.Write("  <sheet>");
					os.WriteLine();
					os.Write("    <name><![CDATA[" + s.getName() + "]]></name>");
					os.WriteLine();

					Cell[] row = null;
					CellFormat format = null;
					Font font = null;

					for (int i = 0; i < s.getRows(); i++)
						{
						os.Write("    <row number=\"" + i + "\">");
						os.WriteLine();
						row = s.getRow(i);

						for (int j = 0; j < row.Length; j++)
							{
							// Remember that empty cells can contain format information
							if ((row[j].getType() != CellType.EMPTY) ||
								(row[j].getCellFormat() != null))
								{
								format = row[j].getCellFormat();
								os.Write("      <col number=\"" + j + "\">");
								os.WriteLine();
								os.Write("        <data>");
								os.Write("<![CDATA[" + row[j].getContents() + "]]>");
								os.Write("</data>");
								os.WriteLine();

								if (row[j].getCellFormat() != null)
									{
									os.Write("        <format wrap=\"" + format.getWrap() + "\"");
									os.WriteLine();
									os.Write("                align=\"" +
											 format.getAlignment().getDescription() + "\"");
									os.WriteLine();
									os.Write("                valign=\"" +
											 format.getVerticalAlignment().getDescription() + "\"");
									os.WriteLine();
									os.Write("                orientation=\"" +
											 format.getOrientation().getDescription() + "\"");
									os.Write(">");
									os.WriteLine();

									// The font information
									font = format.getFont();
									os.Write("          <font name=\"" + font.getName() + "\"");
									os.WriteLine();
									os.Write("                point_size=\"" +
											 font.getPointSize() + "\"");
									os.WriteLine();
									os.Write("                bold_weight=\"" +
											 font.getBoldWeight() + "\"");
									os.WriteLine();
									os.Write("                italic=\"" + font.isItalic() + "\"");
									os.WriteLine();
									os.Write("                underline=\"" +
											 font.getUnderlineStyle().getDescription() + "\"");
									os.WriteLine();
									os.Write("                colour=\"" +
											 font.getColour().getDescription() + "\"");
									os.WriteLine();
									os.Write("                script=\"" +
											 font.getScriptStyle().getDescription() + "\"");
									os.Write(" />");
									os.WriteLine();


									// The cell background information
									if (format.getBackgroundColour() != Colour.DEFAULT_BACKGROUND ||
										format.getPattern() != Pattern.NONE)
										{
										os.Write("          <background colour=\"" +
												 format.getBackgroundColour().getDescription() + "\"");
										os.WriteLine();
										os.Write("                      pattern=\"" +
												 format.getPattern().getDescription() + "\"");
										os.Write(" />");
										os.WriteLine();
										}


									// The cell border, if it has one
									if (format.getBorder(Border.TOP) != BorderLineStyle.NONE ||
										format.getBorder(Border.BOTTOM) != BorderLineStyle.NONE ||
										format.getBorder(Border.LEFT) != BorderLineStyle.NONE ||
										format.getBorder(Border.RIGHT) != BorderLineStyle.NONE)
										{

										os.Write("          <border top=\"" +
												 format.getBorder(Border.TOP).getDescription() + "\"");
										os.WriteLine();
										os.Write("                  bottom=\"" +
												 format.getBorder(Border.BOTTOM).getDescription() +
												 "\"");
										os.WriteLine();
										os.Write("                  left=\"" +
												 format.getBorder(Border.LEFT).getDescription() + "\"");
										os.WriteLine();
										os.Write("                  right=\"" +
												 format.getBorder(Border.RIGHT).getDescription() + "\"");
										os.Write(" />");
										os.WriteLine();
										}

									// The cell number/date format
									if (format.getFormat().getFormatString().Length != 0)
										{
										os.Write("          <format_string string=\"");
										os.Write(format.getFormat().getFormatString());
										os.Write("\" />");
										os.WriteLine();
										}

									os.Write("        </format>");
									os.WriteLine();
									}

								os.Write("      </col>");
								os.WriteLine();
								}
							}
						os.Write("    </row>");
						os.WriteLine();
						}
					os.Write("  </sheet>");
					os.WriteLine();
					}

				os.Write("</workbook>");
				os.WriteLine();

				os.Flush();
				//bw.close();
				}
			catch (Exception e)
				{
				Console.WriteLine(e);
				}
			}
		}
	}







