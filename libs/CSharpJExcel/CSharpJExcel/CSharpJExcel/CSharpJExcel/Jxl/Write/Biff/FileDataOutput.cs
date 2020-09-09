/*********************************************************************
*
*      Copyright (C) 2007 Andrew Khan
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


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * Used to generate the excel biff data using a temporary file.  This
	 * class wraps a RandomAccessFile
	 */
	class FileDataOutput : ExcelDataOutput
		{
		// The logger
		//  private static Logger logger = Logger.getLogger(FileDataOutput.class);

		/** 
		 * The temporary file
		 */
		private FileInfo temporaryFile;

		/**
		 * The excel data
		 */
		private Stream data;

		/**
		 * Constructor
		 *
		 * @param tmpdir the temporary directory used to write files.  If this is
		 *               NULL then the sytem temporary directory will be used
		 * @exception IOException
		 */
		public FileDataOutput(FileInfo tmpdir)
			{
			System.Random random = new System.Random();

			temporaryFile = new FileInfo(tmpdir.DirectoryName + "\\" + "jxl" + random.Next().ToString("0000000000") + ".tmp");
// TODO: CML -- how to handle this delete on exit support?
//temporaryFile.deleteOnExit();
			data = new FileStream(temporaryFile.FullName, FileMode.Create,FileAccess.ReadWrite);
			}

		/**
		 * Writes the bytes to the end of the array, growing the array
		 * as needs dictate
		 *
		 * @param d the data to write to the end of the array
		 * @exception IOException
		 */
		public void write(byte[] bytes)
			{
			data.Write(bytes,0,bytes.Length);
			}

		/**
		 * Gets the current position within the file
		 *
		 * @return the position within the file
		 * @exception IOException
		 */
		public int getPosition()
			{
			// As all excel data structures are four bytes anyway, it's ok to 
			// truncate the long to an int
			return (int)data.Position;
			}

		/**
		 * Sets the data at the specified position to the contents of the array
		 * 
		 * @param pos the position to alter
		 * @param newdata the data to modify
		 * @exception IOException
		 */
		public void setData(byte[] newdata, int pos)
			{
			long curpos = data.Position;
			data.Seek(pos,SeekOrigin.Begin);
			data.Write(newdata,0,newdata.Length);
			data.Seek(curpos,SeekOrigin.Begin);
			}

		/** 
		 * Writes the data to the output stream
		 * @exception IOException
		 */
		public void writeData(Stream outStream)
			{
			byte[] buffer = new byte[1024];
			int length = 0;
			data.Seek(0,SeekOrigin.Begin);
			while ((length = data.Read(buffer,0,buffer.Length)) != -1)
				outStream.Write(buffer, 0, length);
			}

		/**
		 * Called when the final compound file has been written
		 * @exception IOException
		 */
		public void close()
			{
			data.Close();

			// Explicitly delete the temporary file, since sometimes it is the case
			// that a single process may be generating multiple different excel files
			temporaryFile.Delete();
			}
		}
	}


