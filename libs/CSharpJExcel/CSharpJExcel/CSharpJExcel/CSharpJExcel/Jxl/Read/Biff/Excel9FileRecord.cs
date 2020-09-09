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
	 * A excel9file record
	 */
	class Excel9FileRecord : RecordData
		{
		/**
		 * The template
		 */
		private bool excel9file;

		/**
		 * Constructor
		 *
		 * @param t the record
		 */
		public Excel9FileRecord(Record t)
			: base(t)
			{
			excel9file = true;
			}

		/**
		 * Accessor for the template mode
		 *
		 * @return the template mode
		 */
		public bool getExcel9File()
			{
			return excel9file;
			}
		}
	}
