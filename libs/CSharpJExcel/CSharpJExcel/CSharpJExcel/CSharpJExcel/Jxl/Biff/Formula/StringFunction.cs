/*********************************************************************
*
*      Copyright (C) 2003 Andrew Khan
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


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * Class used to hold a function when reading it in from a string.  At this
	 * stage it is unknown whether it is a BuiltInFunction or a VariableArgFunction
	 */
	class StringFunction : StringParseItem
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(StringFunction.class);

		/**
		 * The function
		 */
		private Function function;

		/**
		 * The function string
		 */
		private string functionString;

		/**
		 * Constructor
		 *
		 * @param s the lexically parsed stirng
		 */
		public StringFunction(string s)
			{
			functionString = s.Substring(0,s.Length - 1);
			}

		/**
		 * Accessor for the function
		 *
		 * @param ws the workbook settings
		 * @return the function
		 */
		public Function getFunction(WorkbookSettings ws)
			{
			if (function == null)
				{
				function = Function.getFunction(functionString,ws);
				}
			return function;
			}
		}
	}