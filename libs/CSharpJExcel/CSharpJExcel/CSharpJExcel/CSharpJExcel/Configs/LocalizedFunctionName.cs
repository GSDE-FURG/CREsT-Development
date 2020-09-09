using System;
using System.Collections.Generic;
using System.Text;

namespace CSharpJExcel.Configs
	{
	public class LocalizedFunctionName
		{
		private string _language;
		private string _name;
		private string _functionName;

		public LocalizedFunctionName(string Language,string Name, string FunctionName)
			{
			_language = Language;
			_name = Name;
			_functionName = FunctionName;
			}

		public string Language
			{
			get
				{
				return _language;
				}
			}

		public string Name
			{
			get
				{
				return _name;
				}
			}

		public string FunctionName
			{
			get
				{
				return _functionName;
				}
			}
		}
	}
