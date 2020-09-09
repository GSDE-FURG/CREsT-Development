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
	 * Interface to abstract away an in-memory output or a temporary file
	 * output.  Used by the File object
	 */
	public interface ExcelDataOutput
		{
		/**
		 * Appends the bytes to the end of the output
		 *
		 * @param d the data to write to the end of the array
		 * @exception IOException
		 */
		 void write(byte[] bytes);

		/**
		 * Gets the current position within the file
		 *
		 * @return the position within the file
		 * @exception IOException
		 */
		int getPosition();

		/**
		 * Sets the data at the specified position to the contents of the array
		 * 
		 * @param pos the position to alter
		 * @param newdata the data to modify
		 * @exception IOException
		 */
		void setData(byte[] newdata, int pos);

		/** 
		 * Writes the data to the output stream
		 * @exception IOException
		 */
		void writeData(Stream outStream);

		/**
		 * Called when the final compound file has been written
		 * @exception IOException
		 */
		void close();
		}
	}	
	


