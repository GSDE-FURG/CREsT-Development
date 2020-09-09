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
using System.IO;
using CSharpJExcel.Jxl;
using CSharpJExcel.Jxl.Read.Biff;
using CSharpJExcel.Jxl.Biff;

namespace Demo
	{
	/**
	 * Displays whatever generated the excel file (ie. the WriteAccess record)
	 */
	class WriteAccess
		{
		private BiffRecordReader reader;

		public WriteAccess(FileInfo file, TextWriter os)
			{
			WorkbookSettings ws = new WorkbookSettings();
			Stream fis = new FileStream(file.FullName,FileMode.Open,FileAccess.Read);
			CSharpJExcel.Jxl.Read.Biff.File f = new CSharpJExcel.Jxl.Read.Biff.File(fis, ws);
			reader = new BiffRecordReader(f);

			display(ws,os);
			fis.Close();
			}

		/**
		 * Dumps out the contents of the excel file
		 */
		private void display(WorkbookSettings ws,TextWriter os)
			{
			Record r = null;
			bool found = false;
			while (reader.hasNext() && !found)
				{
				r = reader.next();
				if (r.getType() == CSharpJExcel.Jxl.Biff.Type.WRITEACCESS)
					{
					found = true;
					}
				}

			if (!found)
				{
				Console.WriteLine("Warning:  could not find write access record");
				return;
				}

			byte[] data = r.getData();

			string s = null;

			s = StringHelper.getString(data, data.Length, 0, ws);

			os.WriteLine(s);
			}
		}
	}
