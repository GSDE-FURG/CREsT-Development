using System;
using System.Collections.Generic;
using System.Text;
using System.Globalization;

namespace CSharpJExcel.Interop
	{
	public class DecimalFormat : NumberFormat
		{
		private string _format;

		public DecimalFormat()
			{
			_format = "##########0";
			}

		public DecimalFormat(string pattern) : this()
			{
			_format = pattern;
			}

		public DecimalFormat(DecimalFormat Other)
			: this(Other._format)
			{
			}

		public override string format(double number)
			{
			return number.ToString(_format);
//			return string.Format(_format, number);
			}

		public override StringBuilder format(double number, StringBuilder toAppendTo)
			{
			string s = format(number);
			toAppendTo.Append(s);
			return toAppendTo;
			}

		public override string format(long number)
			{
			return string.Format(_format, number);
			}

		public override StringBuilder format(long number, StringBuilder toAppendTo)
			{
			string s = format(number);
			toAppendTo.Append(s);
			return toAppendTo;
			}

		public override double parseDouble(string source)
			{
			return Double.Parse(source, CultureInfo.GetCultureInfo("en-us"));
			}

		public override long parseLong(string source)
			{
			return long.Parse(source, CultureInfo.GetCultureInfo("en-us"));
			}

		
		public int parseInt(string source)
			{
			return (int)parseLong(source);
			}
		}
	}
