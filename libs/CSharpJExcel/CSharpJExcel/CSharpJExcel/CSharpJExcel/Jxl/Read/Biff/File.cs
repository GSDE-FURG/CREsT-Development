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

using System.IO;
using CSharpJExcel.Jxl.Biff;
using System.Threading;
using System;


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * File containing the data from the binary stream
	 */
	public class File
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(File.class);

		/**
		 * The data from the excel 97 file
		 */
		private byte[] data;
		/**
		 * The current position within the file
		 */
		private int filePos;
		/**
		 * The saved pos
		 */
		private int oldPos;
		/**
		 * The initial file size
		 */
		private int initialFileSize;
		/**
		 * The amount to increase the growable array by
		 */
		private int arrayGrowSize;
		/**
		 * A handle to the compound file. This is only preserved when the
		 * copying of PropertySets is enabled
		 */
		private CompoundFile compoundFile;
		/**
		 * The workbook settings
		 */
		private WorkbookSettings workbookSettings;

		/**
		 * Constructs a file from the input stream
		 *
		 * @param is the input stream
		 * @param ws the workbook settings
		 * @exception IOException
		 * @exception BiffException
		 */
		public File(Stream input, WorkbookSettings ws)
			{
			// Initialize the file sizing parameters from the settings
			workbookSettings = ws;
			initialFileSize = workbookSettings.getInitialFileSize();
			arrayGrowSize = workbookSettings.getArrayGrowSize();

			byte[] d = new byte[initialFileSize];
			int bytesRead = input.Read(d,0,d.Length);
			int pos = bytesRead;

			// Handle thread interruptions, in case the user keeps pressing
			// the Submit button from a browser.  Thanks to Mike Smith for this
// TODO: CML - don't know what to do with this in context of asp.net
			//if (Thread.currentThread().isInterrupted())
			//    {
			//    throw new InterruptedIOException();
			//    }

			while (bytesRead > 0)
				{
				if (pos >= d.Length)
					{
					// Grow the array
					byte[] newArray = new byte[d.Length + arrayGrowSize];
					System.Array.Copy(d,0,newArray,0,d.Length);
					d = newArray;
					}
				bytesRead = input.Read(d,pos,d.Length - pos);
				pos += bytesRead;

// TODO: CML - don't know what to do with this in context of asp.net
				//if (Thread.currentThread().isInterrupted())
				//    {
				//    throw new InterruptedIOException();
				//    }
				}

			bytesRead = pos + 1;

			// Perform file reading checks and throw exceptions as necessary
			if (bytesRead == 0)
				{
				throw new BiffException(BiffException.excelFileNotFound);
				}

			CompoundFile cf = new CompoundFile(d,ws);
			try
				{
				data = cf.getStream("workbook");
				}
			catch (BiffException e)
				{
				// this might be in excel 95 format - try again
				data = cf.getStream("book");
				}

			if (!workbookSettings.getPropertySetsDisabled() &&
				(cf.getNumberOfPropertySets() >
				 BaseCompoundFile.STANDARD_PROPERTY_SETS.Length))
				{
				compoundFile = cf;
				}

			cf = null;

			//if (!workbookSettings.getGCDisabled())
			//    {
			//    System.gc();
			//    }

			// Uncomment the following lines to send the pure workbook stream
			// (ie. a defragged ole stream) to an output file

			//      FileOutputStream fos = new FileOutputStream("defraggedxls");
			//      fos.write(data);
			//      fos.close();

			}

		/**
		 * Constructs a file from already defragged binary data.  Useful for
		 * displaying subportions of excel streams.  This is only used during
		 * special runs of the "BiffDump" demo program and should not be invoked
		 * as part of standard JExcelApi parsing
		 *
		 * @param d the already parsed data
		 */
		public File(byte[] d)
			{
			data = d;
			}

		/**
		 * Returns the next data record and increments the pointer
		 *
		 * @return the next data record
		 */
		public Record next()
			{
			Record r = new Record(data,filePos,this);
			return r;
			}

		/**
		 * Peek ahead to the next record, without incrementing the file position
		 *
		 * @return the next record
		 */
		public Record peek()
			{
			int tempPos = filePos;
			Record r = new Record(data,filePos,this);
			filePos = tempPos;
			return r;
			}

		/**
		 * Skips forward the specified number of bytes
		 *
		 * @param bytes the number of bytes to skip forward
		 */
		public void skip(int bytes)
			{
			filePos += bytes;
			}

		/**
		 * Copies the bytes into a new array and returns it.
		 *
		 * @param pos the position to read from
		 * @param length the number of bytes to read
		 * @return The bytes read
		 */
		public byte[] read(int pos,int length)
			{
			byte[] ret = new byte[length];
			try
				{
				System.Array.Copy(data,pos,ret,0,length);
				}
			catch (IndexOutOfRangeException e)
				{
				//logger.error("Array index out of bounds at position " + pos +
				//             " record length " + length);
				throw;
				}
			return ret;
			}

		/**
		 * Gets the position in the stream
		 *
		 * @return the position in the stream
		 */
		public int getPos()
			{
			return filePos;
			}

		/**
		 * Saves the current position and temporarily sets the position to be the
		 * new one.  The original position may be restored usind the restorePos()
		 * method. This is used when reading in the cell values of the sheet - an
		 * addition in 1.6 for memory allocation reasons.
		 *
		 * These methods are used by the SheetImpl.readSheet() when it is reading
		 * in all the cell values
		 *
		 * @param p the temporary position
		 */
		public void setPos(int p)
			{
			oldPos = filePos;
			filePos = p;
			}

		/**
		 * Restores the original position
		 *
		 * These methods are used by the SheetImpl.readSheet() when it is reading
		 * in all the cell values
		 */
		public void restorePos()
			{
			filePos = oldPos;
			}

		/**
		 * Moves to the first bof in the file
		 */
		private void moveToFirstBof()
			{
			bool bofFound = false;
			while (!bofFound)
				{
				int code = IntegerHelper.getInt(data[filePos],data[filePos + 1]);
				if (code == CSharpJExcel.Jxl.Biff.Type.BOF.value)
					{
					bofFound = true;
					}
				else
					{
					skip(128);
					}
				}
			}

		/**
		 * "Closes" the biff file
		 *
		 * @deprecated As of version 1.6 use workbook.close() instead
		 */
		public void close()
			{
			}

		/**
		 * Clears the contents of the file
		 */
		public void clear()
			{
			data = null;
			}

		/**
		 * Determines if the current position exceeds the end of the file
		 *
		 * @return TRUE if there is more data left in the array, FALSE otherwise
		 */
		public bool hasNext()
			{
			// Allow four bytes for the record code and its length
			return filePos < data.Length - 4;
			}

		/**
		 * Accessor for the compound file.  The returned value will only be non-null
		 * if the property sets feature is enabled and the workbook contains
		 * additional property sets
		 *
		 * @return the compound file
		 */
		public CompoundFile getCompoundFile()
			{
			return compoundFile;
			}
		}
	}
