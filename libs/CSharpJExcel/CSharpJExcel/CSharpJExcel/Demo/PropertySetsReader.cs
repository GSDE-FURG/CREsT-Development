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
using CSharpJExcel.Jxl.Read.Biff;
using CSharpJExcel.Jxl;
using CSharpJExcel.Jxl.Biff;

namespace Demo
	{
	/**
	 * Generates a biff dump of the specified excel file
	 */
	class PropertySetsReader
		{
		//private BufferedWriter writer;
		private CompoundFile compoundFile;

		/**
		 * Constructor
		 *
		 * @param file the file
		 * @param propertySet the property set to read
		 * @param os the output stream
		 * @exception IOException 
		 * @exception BiffException
		 */
		public PropertySetsReader(FileInfo file, string propertySet, TextWriter os)
			{
			//writer = new BufferedWriter(new OutputStreamWriter(os));
			Stream fis = new FileStream(file.Name,FileMode.Open,FileAccess.Read);

			int initialFileSize = 1024 * 1024; // 1mb
			int arrayGrowSize = 1024 * 1024;// 1mb

			byte[] d = new byte[initialFileSize];
			int bytesRead = fis.Read(d,0,d.Length);
			int pos = bytesRead;

			while (bytesRead != -1)
				{
				if (pos >= d.Length)
					{
					// Grow the array
					byte[] newArray = new byte[d.Length + arrayGrowSize];
					Array.Copy(d, 0, newArray, 0, d.Length);
					d = newArray;
					}
				bytesRead = fis.Read(d, pos, d.Length - pos);
				pos += bytesRead;
				}

			bytesRead = pos + 1;

			compoundFile = new CompoundFile(d, new WorkbookSettings());
			fis.Close();

			if (propertySet == null)
				displaySets(os);
			else
				displayPropertySet(propertySet, os);
			}

		/**
		 * Displays the properties to the output stream
		 */
		void displaySets(TextWriter writer)
			{
			int numSets = compoundFile.getNumberOfPropertySets();

			for (int i = 0; i < numSets; i++)
				{
				BaseCompoundFile.PropertyStorage ps = compoundFile.getPropertySet(i);
				writer.Write(i.ToString());
				writer.Write(") ");
				writer.Write(ps.name);
				writer.Write("(type ");
				writer.Write(ps.type.ToString());
				writer.Write(" size ");
				writer.Write(ps.size.ToString());
				writer.Write(" prev ");
				writer.Write(ps.previous.ToString());
				writer.Write(" next ");
				writer.Write(ps.next.ToString());
				writer.Write(" child ");
				writer.Write(ps.child.ToString());
				writer.Write(" start block ");
				writer.Write(ps.startBlock.ToString());
				writer.Write(")");
				writer.WriteLine();
				}

			writer.Flush();
			//    writer.close();
			}

		/**
		 * Write the property stream to the output stream
		 */
		void displayPropertySet(string ps, TextWriter os)
			{
			if (string.Compare(ps,"SummaryInformation",true) == 0)
				ps = BaseCompoundFile.SUMMARY_INFORMATION_NAME;
			else if (string.Compare(ps,"DocumentSummaryInformation",true) == 0)
				ps = BaseCompoundFile.DOCUMENT_SUMMARY_INFORMATION_NAME;
			else if (string.Compare(ps,"CompObj") == 0)
				ps = BaseCompoundFile.COMP_OBJ_NAME;

			byte[] stream = compoundFile.getStream(ps);
			os.Write(stream);
			}
		}
	}

