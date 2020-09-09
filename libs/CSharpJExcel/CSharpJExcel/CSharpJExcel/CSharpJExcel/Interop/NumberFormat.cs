using System;
using System.Collections.Generic;
using System.Text;

namespace CSharpJExcel.Interop
	{
	public abstract class NumberFormat : Format
		{
		public abstract string format(double number);
		public abstract StringBuilder format(double number, StringBuilder toAppendTo);
		public abstract string format(long number);
		public abstract StringBuilder format(long number, StringBuilder toAppendTo);
		public abstract double parseDouble(string source);
		public abstract long parseLong(string source);
		}
	}
