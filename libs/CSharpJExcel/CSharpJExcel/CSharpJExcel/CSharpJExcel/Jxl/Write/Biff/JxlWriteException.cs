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


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * Exception thrown when reading a biff file
	 */
	public class JxlWriteException : WriteException
		{
		public sealed class WriteMessage
			{
			/**
			 */
			public string message;
			/**
			 * Constructs this exception with the specified message
			 * 
			 * @param m the messageA
			 */
			internal WriteMessage(string m) 
				{ 
				message = m; 
				}
			}

		/**
		 */
		public static readonly WriteMessage formatInitialized = new WriteMessage("Attempt to modify a referenced format");
		public static readonly WriteMessage cellReferenced = new WriteMessage("Cell has already been added to a worksheet");
		public static readonly WriteMessage maxRowsExceeded = new WriteMessage("The maximum number of rows permitted on a worksheet been exceeded");
		public static readonly WriteMessage maxColumnsExceeded = new WriteMessage("The maximum number of columns permitted on a worksheet has been exceeded");
		public static readonly WriteMessage copyPropertySets = new WriteMessage("Error encounted when copying additional property sets");

		/**
		 * Constructs this exception with the specified message
		 * 
		 * @param m the message
		 */
		public JxlWriteException(WriteMessage m)
			: base(m.message)
			{
			}
		}
	}

