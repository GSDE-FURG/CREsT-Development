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
using CSharpJExcel.Jxl.Read.Biff;
using CSharpJExcel.Jxl.Biff.Drawing;

namespace Demo
	{
	/**
	 * Displays the escher data
	 */
	public class EscherDrawingGroup
		{
		/**
		 * Constructor
		 *
		 * @param w The workbook to interrogate
		 * @param out The output stream to which the CSV values are written
		 * @param encoding The encoding used by the output stream.  Null or 
		 * unrecognized values cause the encoding to default to UTF8
		 * @exception java.io.IOException
		 */
		public EscherDrawingGroup(Workbook w, TextWriter os, string encoding)
			{
			if (encoding == null || encoding != "UnicodeBig")
				{
				encoding = "UTF8";
				}

			try
				{
				WorkbookParser wp = (WorkbookParser)w;

				DrawingGroup dg = wp.getDrawingGroup();

				if (dg != null)
					{
					EscherDisplay ed = new EscherDisplay(dg, os);
					ed.display();
					}

				os.WriteLine();
				os.WriteLine();
				os.Flush();
				}
			catch (Exception e)
				{
				Console.WriteLine(e);
				}
			}
		}
	}

