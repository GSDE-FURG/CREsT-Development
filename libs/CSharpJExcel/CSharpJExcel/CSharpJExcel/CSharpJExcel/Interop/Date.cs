using System;
using System.Collections.Generic;
using System.Text;

namespace CSharpJExcel.Interop
	{
	public class Date
		{
		private long _ticks;
		private static readonly DateTime _utcStart = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc);

		public Date()
			{
			_ticks = DateTime.Now.Ticks;
			}

		public Date(long TimeMSec)
			{
			this.SetTime(TimeMSec);
			}

		public Date(DateTime OtherDate)
			{
			_ticks = OtherDate.ToUniversalTime().Ticks;
			}

		public Date(Date OtherDate)
			{
			_ticks = OtherDate._ticks;
			}

		public DateTime DateTime
			{
			get
				{
				return new DateTime(_ticks);
				}
			}

		public void SetTime(long TimeMSec)
			{
			_ticks = (TimeMSec * 10000L) + _utcStart.Ticks;
			}

		public long GetTime()
			{
			return (_ticks - _utcStart.Ticks) / 10000L;		// 100 nanosecond ticks
			}
		}
	}
