using System;
using System.Collections.Generic;
using System.Text;

namespace CSharpJExcel.Interop
	{
	/// <summary>
	/// Provides a Java-like DateFormat class.
	/// </summary>
	public abstract class DateFormat : Format
		{
		public static readonly string SHORT = "hh:mm tt";
		public static readonly string MEDIUM = "hh:mm:ss tt";
		public static readonly string DEFAULT = MEDIUM;

		public static DateFormat getDateInstance()
			{
			return new SimpleDateFormat();
			}

		public abstract string format(DateTime date);
		public abstract TimeZone getTimeZone();
		public abstract void setTimeZone(TimeZone Zone);
		public abstract DateTime parse(String source);
		}
	}
