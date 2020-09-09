/*********************************************************************
*
*      Copyright (C) 2001 Andrew Khan
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

using CSharpJExcel.Jxl.Format;
using System;
using CSharpJExcel.Jxl.Biff;
using System.Globalization;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A date stored in the database
	 */
	public abstract class DateRecord : CellValue
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(DateRecord.class);

		/**
		 * The excel value of the date
		 */
		private double value;
		/**
		 * The .NET representation of the date
		 */
		private CSharpJExcel.Interop.Date date;

		/**
		 * Indicates whether this is a full date, or just a time only
		 */
		private bool time;

		// The number of days between 01 Jan 1900 and 01 Jan 1970 - this gives
		// the UTC offset
		/**
		 */
		private const int utcOffsetDays = 25569;

		// The number of milliseconds in  a day
		/**
		 */
		private const long msInADay = 24 * 60 * 60 * 1000;

		/**
		 * This is package protected so that the worksheet might detect
		 * whether or not to override it with the column cell format
		 */
		public static readonly WritableCellFormat defaultDateFormat = new WritableCellFormat(DateFormats.DEFAULT);

		// The number of days between 1 Jan 1900 and 1 March 1900. Excel thinks
		// the day before this was 29th Feb 1900, but it was 28th Feb 19000.
		// I guess the programmers thought nobody would notice that they
		// couldn't be bothered to program this dating anomaly properly
		/**
		 */
		private const int nonLeapDay = 61;

		/**
		 * Class definition for a dummy variable
		 */
		public sealed class GMTDate
			{
			public GMTDate() 
				{ 
				}
			};

		/**
		 * Constructor invoked by the user API
		 * 
		 * @param c the column
		 * @param r the row
		 * @param d the date
		 */
		protected DateRecord(int c, int r, DateTime d)
			: this(c, r, d, defaultDateFormat, false)
			{
			}

		/**
		 * Constructor invoked by the user API
		 * 
		 * @param c the column
		 * @param r the row
		 * @param d the date
		 * @param a adjust timezone
		 */
		protected DateRecord(int c, int r, DateTime d, GMTDate a)
			: this(c, r, d, defaultDateFormat, false)
			{
			}

		/**
		 * Constructor invoked from the user API
		 * 
		 * @param c the column
		 * @param r the row
		 * @param st the format for the date
		 * @param d the date
		 */
		protected DateRecord(int c, int r, DateTime d, CellFormat st)
			: base(CSharpJExcel.Jxl.Biff.Type.NUMBER, c, r, st)
			{
			date = new CSharpJExcel.Interop.Date(d);
			calculateValue(true);
			}

		/**
		 * Constructor invoked from the user API
		 * 
		 * @param c the column
		 * @param r the row
		 * @param st the format for the date
		 * @param d the date
		 * @param a adjust for the timezone
		 */
		protected DateRecord(int c, int r, DateTime d, CellFormat st, GMTDate a)
			: base(CSharpJExcel.Jxl.Biff.Type.NUMBER, c, r, st)
			{
			date = new CSharpJExcel.Interop.Date(d);
			calculateValue(false);
			}

		/**
		 * Constructor invoked from the API
		 * 
		 * @param c the column
		 * @param r the row
		 * @param st the date format
		 * @param tim time indicator
		 * @param d the date
		 */
		protected DateRecord(int c, int r, DateTime d, CellFormat st, bool tim)
			: base(CSharpJExcel.Jxl.Biff.Type.NUMBER, c, r, st)
			{
			date = new CSharpJExcel.Interop.Date(d);
			time = tim;
			calculateValue(false);
			}

		/**
		 * Constructor invoked when copying a readable spreadsheet
		 * 
		 * @param dc the date to copy
		 */
		protected DateRecord(DateCell dc)
			: base(CSharpJExcel.Jxl.Biff.Type.NUMBER, dc)
			{
			date = new CSharpJExcel.Interop.Date(dc.getDate());
			time = dc.isTime();
			calculateValue(false);
			}

		/**
		 * Copy constructor
		 * 
		 * @param c the column
		 * @param r the row
		 * @param dr the record to copy
		 */
		protected DateRecord(int c, int r, DateRecord dr)
			: base(CSharpJExcel.Jxl.Biff.Type.NUMBER, c, r, dr)
			{
			value = dr.value;
			time = dr.time;
			date = dr.date;
			}

		/**
		 * Calculates the 1900 based numerical value based upon the utc value held
		 * in the date object
		 *
		 * @param adjust TRUE if we want to incorporate timezone information
		 * into the raw UTC date eg. when copying from a spreadsheet
		 */
		private void calculateValue(bool adjust)
			{
			// Offsets for current time zone
			long zoneOffset = 0;
			long dstOffset = 0;

			// Get the timezone and dst offsets if we want to take these into
			// account
			if (adjust)
				{
				// Get the current calender, replete with timezone information
				//Calendar cal = Calendar.CurrentEra();
				//cal.setTime(date);

				//zoneOffset = cal[Calendar.ZONE_OFFSET];
				//dstOffset = cal[Calendar.DST_OFFSET];

// TODO: fix this data handling....
				DateTime now = DateTime.Now;
				TimeZone zone = TimeZone.CurrentTimeZone;
				zoneOffset = (long)zone.GetUtcOffset(now).TotalMilliseconds;

				DaylightTime daylight = zone.GetDaylightChanges(now.Year);
				if (daylight != null)
					dstOffset = (long)daylight.Delta.TotalMilliseconds;
				else
					dstOffset = 0;

				if (now.IsDaylightSavingTime())
					zoneOffset += dstOffset;


				//zoneOffset = cal[Calendar.ZONE_OFFSET];
				//dstOffset = cal[Calendar.DST_OFFSET];
				}

			long utcValue = date.GetTime() + zoneOffset + dstOffset;

			// Convert this to the number of days, plus fractions of a day since
			// 01 Jan 1970
			double utcDays = (double)utcValue / (double)msInADay;

			// Add in the offset to get the number of days since 01 Jan 1900
			value = utcDays + utcOffsetDays;

			// Work round a bug in excel.  Excel seems to think there is a date 
			// called the 29th Feb, 1900 - but this was not a leap year.  
			// Therefore for values less than 61, we must subtract 1.  Only do
			// this for full dates, not times
			if (!time && value < nonLeapDay)
				value -= 1;

			// If this refers to a time, then get rid of the integer part
			if (time)
				value = value - (int)value;
			}

		/**
		 * Returns the content type of this cell
		 * 
		 * @return the content type for this cell
		 */
		public override CellType getType()
			{
			return CellType.DATE;
			}

		/**
		 * Gets the binary data for writing
		 * 
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			byte[] celldata = base.getData();
			byte[] data = new byte[celldata.Length + 8];
			System.Array.Copy(celldata, 0, data, 0, celldata.Length);
			DoubleHelper.getIEEEBytes(value, data, celldata.Length);

			return data;
			}

		/**
		 * Quick and dirty function to return the contents of this cell as a string.
		 * For more complex manipulation of the contents, it is necessary to cast
		 * this interface to correct subinterface
		 * 
		 * @return the contents of this cell as a string
		 */
		public override string getContents()
			{
			return date.ToString();
			}

		/**
		 * Sets the date in this cell
		 * 
		 * @param d the date
		 */
		public virtual void setDate(DateTime d)
			{
			date = new CSharpJExcel.Interop.Date(d);
			calculateValue(true);
			}

		/**
		 * Sets the date in this cell, taking the timezone into account
		 * 
		 * @param d the date
		 * @param a adjust for timezone
		 */
		public virtual void setDate(DateTime d, GMTDate a)
			{
			date = new CSharpJExcel.Interop.Date(d);
			calculateValue(false);
			}


		/**
		 * Gets the date contained in this cell
		 * 
		 * @return the cell contents
		 */
		public DateTime getDate()
			{
			return date.DateTime;
			}

		/**
		 * Indicates whether the date value contained in this cell refers to a date,
		 * or merely a time.  When writing a cell, all dates are fully defined,
		 * even if they refer to a time
		 * 
		 * @return FALSE if this is full date, TRUE if a time
		 */
		public bool isTime()
			{
			return time;
			}

		/**
		 * Gets the DateFormat used to format the cell.  This will normally be
		 * the format specified in the excel spreadsheet, but in the event of any
		 * difficulty parsing this, it will revert to the default date/time format.
		 * 
		 * @return the DateFormat object used to format the date in the original 
		 *     excel cell
		 */
		public virtual CSharpJExcel.Interop.DateFormat getDateFormat()
			{
			return null;
			}
		}
	}