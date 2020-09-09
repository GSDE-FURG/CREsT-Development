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

using System.Globalization;
using System.Collections.Generic;
using CSharpJExcel.Configs;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * A class which contains the function names for the current workbook. The
	 * function names can potentially vary from workbook to workbook depending
	 * on the locale
	 */
	public class FunctionNames
		{
		/**
		 * The logger class
		 */
		//  private static Logger logger = Logger.getLogger(FunctionNames.class);

		/**
		 * A hash mapping keyed on the function and returning its locale specific
		 * name
		 */
		private Dictionary<Function,string> names;

		/**
		 * A hash mapping keyed on the locale specific name and returning the
		 * function
		 */
		private Dictionary<string,Function> functions;

		/**
		 * Constructor
		 *
		 * @param l the locale
		 */
		public FunctionNames(CultureInfo locale)
			{
//			ResourceBundle rb = ResourceBundle.getBundle("functions",locale);
			Function[] allfunctions = Function.getFunctions();
			names = new Dictionary<Function,string>(allfunctions.Length);
			functions = new Dictionary<string,Function>(allfunctions.Length);

			string languageCode = locale.IetfLanguageTag;
			if (languageCode.IndexOf('-') > 0)
				languageCode = languageCode.Substring(0,languageCode.IndexOf('-'));		// tear off country from IETF tag

			// Iterate through all the functions, adding them to the hash maps
			for (int i = 0; i < allfunctions.Length; i++)
				{
				Function f = allfunctions[i];
				string propname = f.getPropertyName();

				string localName = FunctionNameLookup.LookupFunctionName(languageCode, propname);
				if (localName != null)
					{
					string s = localName.ToUpper();		// keys are always uppercase
					names.Add(f, s);
					functions.Add(s,f);
					}
				else		// CML - should the function still not be IN the list of functions even if it not in the language?
					{
					string s = propname.ToUpper();		// keys are always uppercase
					if (!functions.ContainsKey(s))
						{
						names.Add(f, s);
						functions.Add(s, f);
						}
					}
				}
			}

		/**
		 * Gets the function for the specified name
		 *
		 * @param s the string
		 * @return  the function
		 */
		public Function getFunction(string s)
			{
			s = s.ToUpper();		// keys are uppercase always
			if (!functions.ContainsKey(s))
				return null;
			return functions[s];
			}

		/**
		 * Gets the name for the function
		 *
		 * @param f the function
		 * @return  the string
		 */
		public string getName(Function f)
			{
			if (!names.ContainsKey(f))
				return null;
			return names[f];
			}
		}
	}
