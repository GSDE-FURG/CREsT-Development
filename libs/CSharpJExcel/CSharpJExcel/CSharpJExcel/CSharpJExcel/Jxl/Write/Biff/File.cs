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
using System.IO;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A file of excel data to be written out.  All the excel data is held
	 * in memory, and when the close method is called a CompoundFile object
	 * is used to write the Biff oriented excel data in the CompoundFile
	 * format
	 */
	public sealed class File
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(File.class);

		/**
		 * The data from the excel 97 file
		 */
		private ExcelDataOutput data;
		/**
		 * The current position within the file
		 */
		private int pos;
		/**
		 * The output stream
		 */
		private Stream outputStream;
		/**
		 * The initial file size
		 */
		private int initialFileSize;
		/**
		 * The amount to increase the growable array by
		 */
		private int arrayGrowSize;
		/**
		 * The workbook settings
		 */
		private WorkbookSettings workbookSettings;
		/**
		 * The read compound file.  This will only be non-null if there are macros
		 * or other property sets of that ilk which that we should be copying
		 */
		private CSharpJExcel.Jxl.Read.Biff.CompoundFile readCompoundFile;

		/**
		 * Constructor
		 * 
		 * @param os the output stream
		 * @param ws the configuration settings for this workbook
		 * @param rcf the rea compound file
		 * @exception IOException
		 */
		public File(Stream os, WorkbookSettings ws, CSharpJExcel.Jxl.Read.Biff.CompoundFile rcf)
			{
			outputStream = os;
			workbookSettings = ws;
			readCompoundFile = rcf;
			createDataOutput();
			}

		private void createDataOutput()
			{
			if (workbookSettings.getUseTemporaryFileDuringWrite())
				data = new FileDataOutput(workbookSettings.getTemporaryFileDuringWriteDirectory());
			else
				{
				initialFileSize = workbookSettings.getInitialFileSize();
				arrayGrowSize = workbookSettings.getArrayGrowSize();

				data = new MemoryDataOutput(initialFileSize, arrayGrowSize);
				}
			}

		/**
		 * Closes the file.  In fact, this writes out all the excel data
		 * to disk using a CompoundFile object, and then frees up all the memory
		 * allocated to the workbook
		 * 
		 * @exception IOException 
		 * @exception JxlWriteException
		 * @param cs TRUE if this should close the stream, FALSE if the application
		 * closes it
		 */
		public void close(bool cs)
			{
			CompoundFile cf = new CompoundFile(data,
											   data.getPosition(),
											   outputStream,
											   readCompoundFile);
			cf.write();

			outputStream.Flush();
			data.close();

			if (cs)
				outputStream.Close();

			// Cleanup the memory a bit
			data = null;

			//if (!workbookSettings.getGCDisabled())
			//    System.gc();
			}

		/**
		 * Adds the biff record data to the memory allocated for this File
		 * 
		 * @exception IOException 
		 * @param record the record to add to the excel data
		 */
		public void write(ByteData record)
			{
			byte[] bytes = record.getBytes();

			data.write(bytes);
			}

		/**
		 * Gets the current position within the file
		 * 
		 * @return the current position
		 * @exception IOException
		 */
		public int getPos()
			{
			return data.getPosition();
			}

		/**
		 * Used to manually alter the contents of the written out data.  This
		 * is used when cross-referencing cell records
		 * 
		 * @param pos the position to alter
		 * @param newdata the data to modify
		 * @exception IOException
		 */
		public void setData(byte[] newdata, int pos)
			{
			data.setData(newdata, pos);
			}

		/**
		 * Sets a new output file.  This allows the same workbook to be
		 * written to various different output files without having to
		 * read in any templates again
		 *
		 * @param os the output stream
		* @exception IOException
		 */
		public void setOutputFile(Stream os)
			{
			if (data != null)
				{
				//logger.warn("Rewriting a workbook with non-empty data");
				}

			outputStream = os;
			createDataOutput();
			}
		}
	}
