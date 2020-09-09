/*********************************************************************
*
*      Copyright (C) 2009 Andrew Khan
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


namespace CSharpJExcel.Jxl.Read.Biff
	{
	/**
	 * A windowProtected mode record
	 */
	class WindowProtectedRecord : RecordData
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(WindowProtectedRecord.class);

		/**
		 * The windowProtected mode
		 */
		private bool windowProtected;

		/**
		 * Constructor
		 *
		 * @param t the record
		 */
		public WindowProtectedRecord(Record t)
			: base(t)
			{
			byte[] data = t.getData();
			int mode = IntegerHelper.getInt(data[0],data[1]);
			windowProtected = (mode == 1);
			}

		/**
		 * Accessor for the windowProtected
		 *
		 * @return the windowProtected mode
		 */
		public bool getWindowProtected()
			{
			return windowProtected;
			}
		}
	}
