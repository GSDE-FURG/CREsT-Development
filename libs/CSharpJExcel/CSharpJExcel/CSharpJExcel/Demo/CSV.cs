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
	 * Simple demo class which uses the api to present the contents
	 * of an excel 97 spreadsheet as comma separated values, using a workbook
	 * and output stream of your choice
	 */
	public class CSV
		{
		/**
		 * Constructor
		 *
		 * @param w The workbook to interrogate
		 * @param out The output stream to which the CSV values are written
		 * @param encoding The encoding used by the output stream.  Null or 
		 * unrecognized values cause the encoding to default to UTF8
		 * @param hide Suppresses hidden cells
		 * @exception java.io.IOException
		 */
		public CSV(Workbook w, TextWriter os, string encoding, bool hide)
			{
			if (encoding == null || encoding != "UnicodeBig")
				{
				encoding = "UTF8";
				}

			try
				{
				//OutputStreamWriter osw = os;
				//BufferedWriter os = new BufferedWriter(osw);

				for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++)
					{
					Sheet s = w.getSheet(sheet);

					if (!(hide && s.getSettings().isHidden()))
						{
						os.Write("*** " + s.getName() + " ****");
						os.WriteLine();

						Cell[] row = null;

						for (int i = 0; i < s.getRows(); i++)
							{
							row = s.getRow(i);

							if (row.Length > 0)
								{
								if (!(hide && row[0].isHidden()))
									{
									os.Write(row[0].getContents());
									// Java 1.4 code to handle embedded commas
									// os.Write("\"" + row[0].getContents().replaceAll("\"","\"\"") + "\"");
									}

								for (int j = 1; j < row.Length; j++)
									{
									os.Write(',');
									if (!(hide && row[j].isHidden()))
										{
										os.Write(row[j].getContents());
										// Java 1.4 code to handle embedded quotes
										//  os.Write("\"" + row[j].getContents().replaceAll("\"","\"\"") + "\"");
										}
									}
								}
							os.WriteLine();
							}
						}
					}
				os.Flush();
				}
			catch (Exception e)
				{
				Console.WriteLine(e.ToString());
				}
			}
		}
	}




