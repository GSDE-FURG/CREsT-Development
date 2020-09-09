using System;
using System.Collections.Generic;
using System.Text;
using System.Globalization;

namespace CSharpJExcel.Interop
	{
	public class SimpleDateFormat : DateFormat
		{
		private CultureInfo _locale;
		private DateTimeFormatInfo _format;

		public SimpleDateFormat()
			{
			_locale = CultureInfo.CurrentCulture;
			_format = (DateTimeFormatInfo)_locale.DateTimeFormat.Clone();
			}

		public SimpleDateFormat(string Pattern)
			{
			_locale = CultureInfo.CurrentCulture;
			_format = (DateTimeFormatInfo)_locale.DateTimeFormat.Clone();
			_format.LongDatePattern = Pattern;
			}


		public SimpleDateFormat(string Pattern, CultureInfo Locale)
			{
			_locale = Locale;
			_format = (DateTimeFormatInfo)_locale.DateTimeFormat.Clone();
			_format.LongDatePattern = Pattern;
			}

		#region DateFormat Members

		public override string format(DateTime date)
			{
			return date.ToString("D", _format);
			}

		public override void setTimeZone(TimeZone Zone)
			{
			throw new Exception("The method or operation is not implemented.");
			}

		public override TimeZone getTimeZone()
			{
			throw new Exception("The method or operation is not implemented.");
			}

		public override DateTime parse(string source)
			{
			return DateTime.Parse(source, _format);
			}

		#endregion

		}
	}
