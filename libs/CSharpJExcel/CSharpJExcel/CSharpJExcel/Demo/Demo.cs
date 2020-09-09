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


namespace Demo
	{
	/**
	 * The main demo class which interprets the command line switches in order
	 * to determine how to call the demo programs
	 * The demo program uses stdout as its default output stream
	 */
	public class Demo
		{
		private static readonly int CSVFormat = 13;
		private static readonly int XMLFormat = 14;


		/**
		 * Displays the acceptable command line arguments
		 */
		private static void displayHelp()
			{
			Console.WriteLine("Command format:  Demo [-unicode] [-csv] [-hide] excelfile");
			Console.WriteLine("                 Demo -xml [-format]  excelfile");
			Console.WriteLine("                 Demo -readwrite|-rw excelfile output");
			Console.WriteLine("                 Demo -biffdump | -bd | -wa | -write | -formulas | -features | -escher | -escherdg excelfile");
			Console.WriteLine("                 Demo -ps excelfile [property] [output]");
			Console.WriteLine("                 Demo -version | -h | -help");

			}

		/**
		 * The main method.  Gets the worksheet and then uses the API 
		 * within a simple loop to print out the spreadsheet contents as
		 * comma separated values
		 * 
		 * @param Args the command line arguments
		 */
		[STAThread]
		public static void Main(string[] Args)
			{
			if (Args.Length == 0)
				{
				displayHelp();
				return;
				}

			if (Args[0] == "-help" || Args[0] == "-h")
				{
				displayHelp();
				return;
				}

			if (Args[0] == "-version")
				{
				Console.WriteLine("v" + Workbook.getVersion());
				return;
				}

			bool write = false;
			bool readwrite = false;
			bool formulas = false;
			bool biffdump = false;
			bool jxlversion = false;
			bool propertysets = false;
			bool features = false;
			bool escher = false;
			bool escherdg = false;
			string file = Args[0];
			string outputFile = null;
			string propertySet = null;

			if (Args[0] == "-write")
				{
				write = true;
				file = Args[1];
				}
			else if (Args[0] == "-formulas")
				{
				formulas = true;
				file = Args[1];
				}
			else if (Args[0] == "-features")
				{
				features = true;
				file = Args[1];
				}
			else if (Args[0] == "-escher")
				{
				escher = true;
				file = Args[1];
				}
			else if (Args[0] == "-escherdg")
				{
				escherdg = true;
				file = Args[1];
				}
			else if (Args[0] == "-biffdump" || Args[0] == "-bd")
				{
				biffdump = true;
				file = Args[1];
				}
			else if (Args[0] == "-wa")
				{
				jxlversion = true;
				file = Args[1];
				}
			else if (Args[0] == "-ps")
				{
				propertysets = true;
				file = Args[1];

				if (Args.Length > 2)
					propertySet = Args[2];

				if (Args.Length == 4)
					outputFile = Args[3];
				}
			else if (Args[0] == "-readwrite" || Args[0] == "-rw")
				{
				readwrite = true;
				file = Args[1];
				outputFile = Args[2];
				}
			else
				{
				file = Args[Args.Length - 1];
				}

			string encoding = "UTF8";
			int format = CSVFormat;
			bool formatInfo = false;
			bool hideCells = false;

			if (write == false &&
				readwrite == false &&
				formulas == false &&
				biffdump == false &&
				jxlversion == false &&
				propertysets == false &&
				features == false &&
				escher == false &&
				escherdg == false)
				{
				for (int i = 0; i < Args.Length - 1; i++)
					{
					if (Args[i] == "-unicode")
						encoding = "UnicodeBig";
					else if (Args[i] == "-xml")
						format = XMLFormat;
					else if (Args[i] == "-csv")
						format = CSVFormat;
					else if (Args[i] == "-format")
						formatInfo = true;
					else if (Args[i] == "-hide")
						hideCells = true;
					else
						{
						Console.WriteLine("Command format:  CSV [-unicode] [-xml|-csv] excelfile");
						return;
						}
					}
				}

			try
				{
				if (write)
					{
					Write w = new Write(file);
					w.write();
					}
				else if (readwrite)
					{
					ReadWrite rw = new ReadWrite(file, outputFile);
					rw.readWrite();
					}
				else if (formulas)
					{
					Workbook w = Workbook.getWorkbook(new FileInfo(file));
					Formulas f = new Formulas(w, Console.Out, encoding);
					w.close();
					}
				else if (features)
					{
					Workbook w = Workbook.getWorkbook(new FileInfo(file));
					Features f = new Features(w, Console.Out, encoding);
					w.close();
					}
				else if (escher)
					{
					Workbook w = Workbook.getWorkbook(new FileInfo(file));
					Escher f = new Escher(w, Console.Out, encoding);
					w.close();
					}
				else if (escherdg)
					{
					Workbook w = Workbook.getWorkbook(new FileInfo(file));
					EscherDrawingGroup f = new EscherDrawingGroup(w, Console.Out, encoding);
					w.close();
					}
				else if (biffdump)
					{
					BiffDump bd = new BiffDump(new FileInfo(file), Console.Out);
					}
				else if (jxlversion)
					{
					WriteAccess bd = new WriteAccess(new FileInfo(file), Console.Out);
					}
				else if (propertysets)
					{
					TextWriter os = Console.Out;
					//if (outputFile != null)
					//    os = new TextWriter((outputFile, FileMode.Create));
					PropertySetsReader psr = new PropertySetsReader(new FileInfo(file), propertySet, os);
					}
				else
					{
					Workbook w = Workbook.getWorkbook(new FileInfo(file));

					//        findTest(w);

					if (format == CSVFormat)
						{
						CSV csv = new CSV(w, Console.Out, encoding, hideCells);
						}
					else if (format == XMLFormat)
						{
						XML xml = new XML(w, Console.Out, encoding, formatInfo);
						}

					w.close();
					}
				}
			catch (Exception t)
				{
				Console.WriteLine(t);
				Console.WriteLine(t.StackTrace);
				}
			}

		/**
		 * A private method to test the various find functions
		 */
		private static void findTest(Workbook w)
			{
			Cell c = w.findCellByName("named1");
			if (c != null)
				{
				Console.WriteLine("named1 contents:  " + c.getContents());
				}

			c = w.findCellByName("named2");
			if (c != null)
				{
				Console.WriteLine("named2 contents:  " + c.getContents());
				}

			c = w.findCellByName("namedrange");
			if (c != null)
				{
				Console.WriteLine("named2 contents:  " + c.getContents());
				}

			Range[] range = w.findByName("namedrange");
			if (range != null)
				{
				c = range[0].getTopLeft();
				Console.WriteLine("namedrange top left contents:  " + c.getContents());

				c = range[0].getBottomRight();
				Console.WriteLine("namedrange bottom right contents:  " + c.getContents());
				}

			range = w.findByName("nonadjacentrange");
			if (range != null)
				{
				for (int i = 0; i < range.Length; i++)
					{
					c = range[i].getTopLeft();
					Console.WriteLine("nonadjacent top left contents:  " + c.getContents());

					c = range[i].getBottomRight();
					Console.WriteLine("nonadjacent bottom right contents:  " + c.getContents());
					}
				}

			range = w.findByName("horizontalnonadjacentrange");
			if (range != null)
				{
				for (int i = 0; i < range.Length; i++)
					{
					c = range[i].getTopLeft();
					Console.WriteLine("horizontalnonadjacent top left contents:  " +
									   c.getContents());

					c = range[i].getBottomRight();
					Console.WriteLine("horizontalnonadjacent bottom right contents:  " +
							 c.getContents());
					}
				}

			}
		}
	}
