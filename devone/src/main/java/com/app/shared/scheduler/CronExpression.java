package com.app.shared.scheduler;
public class CronExpression {

	// Day Constants
	public final static byte SUN = 1;
	public final static byte MON = 2;
	public final static byte TUE = 3;
	public final static byte WED = 4;
	public final static byte THU = 5;
	public final static byte FRI = 6;
	public final static byte SAT = 7;

	// Month Constants
	public final static byte JAN = 1;
	public final static byte FEB = 2;
	public final static byte MAR = 3;
	public final static byte APR = 4;
	public final static byte MAY = 5;
	public final static byte JUN = 6;
	public final static byte JUL = 7;
	public final static byte AUG = 8;
	public final static byte SEP = 9;
	public final static byte OCT = 10;
	public final static byte NOV = 11;
	public final static byte DEC = 12;

	// Cron Variables : Year is not Mandatory
	private String seconds = "0"; // Mandatory, Allowed values 0-59, Special
									// Chars ,-*/
	private String minutes = "0"; // Mandatory, Allowed values 0-59, Special
									// Chars ,-*/
	private String hours = "0"; // Mandatory, Allowed values 0-23, Special Chars
								// ,-*/
	private String dayOfMonth = "*"; // Mandatory, Allowed values 1-31, Special
										// Chars ,-*?/LW
	private String month = "*"; // Mandatory, Allowed values 1-12 or JAN-DEC,
								// Special Chars ,-*/
	private String dayOfWeek = "*"; // Mandatory, Allowed values 1-7 or SUN-SAT,
									// Special Chars ,-*?/L#
	private String year = "*"; // Optional, Allowed values 2015, 2015-2017,
								// Special Chars ,-*/

	/**
	 * Special characters
	 * 
	 * 
	 * ("all values") - used to select all values within a field. For example,
	 * "" in the minute field means *"every minute".
	 * 
	 * 
	 * ? ("no specific value") - useful when you need to specify something in
	 * one of the two fields in which the character is allowed, but not the
	 * other. For example, if I want my trigger to fire on a particular day of
	 * the month (say, the 10th), but don't care what day of the week that
	 * happens to be, I would put "10" in the day-of-month field, and "?" in the
	 * day-of-week field. See the examples below for clarification.
	 * 
	 * 
	 * - - used to specify ranges. For example, "10-12" in the hour field means
	 * "the hours 10, 11 and 12".
	 * 
	 * 
	 * , - used to specify additional values. For example, "MON,WED,FRI" in the
	 * day-of-week field means "the days Monday, Wednesday, and Friday".
	 * 
	 * 
	 * / - used to specify increments. For example, "0/15" in the seconds field
	 * means "the seconds 0, 15, 30, and 45". And "5/15" in the seconds field
	 * means "the seconds 5, 20, 35, and 50". You can also specify '/' after the
	 * '' character - in this case '' is equivalent to having '0' before the
	 * '/'. '1/3' in the day-of-month field means "fire every 3 days starting on
	 * the first day of the month".
	 * 
	 * 
	 * L ("last") - has different meaning in each of the two fields in which it
	 * is allowed. For example, the value "L" in the day-of-month field means
	 * "the last day of the month" - day 31 for January, day 28 for February on
	 * non-leap years. If used in the day-of-week field by itself, it simply
	 * means "7" or "SAT". But if used in the day-of-week field after another
	 * value, it means "the last xxx day of the month" - for example "6L" means
	 * "the last friday of the month". When using the 'L' option, it is
	 * important not to specify lists, or ranges of values, as you'll get
	 * confusing results.
	 * 
	 * 
	 * W ("weekday") - used to specify the weekday (Monday-Friday) nearest the
	 * given day. As an example, if you were to specify "15W" as the value for
	 * the day-of-month field, the meaning is:
	 * "the nearest weekday to the 15th of the month". So if the 15th is a
	 * Saturday, the trigger will fire on Friday the 14th. If the 15th is a
	 * Sunday, the trigger will fire on Monday the 16th. If the 15th is a
	 * Tuesday, then it will fire on Tuesday the 15th. However if you specify
	 * "1W" as the value for day-of-month, and the 1st is a Saturday, the
	 * trigger will fire on Monday the 3rd, as it will not 'jump' over the
	 * boundary of a month's days. The 'W' character can only be specified when
	 * the day-of-month is a single day, not a range or list of days.
	 * 
	 * 
	 * The 'L' and 'W' characters can also be combined in the day-of-month field
	 * to yield 'LW', which translates to *"last weekday of the month"*.
	 * 
	 * 
	 * # - used to specify "the nth" XXX day of the month. For example, the
	 * value of "6#3" in the day-of-week field means "the third Friday of the
	 * month" (day 6 = Friday and "#3
	 * " = the 3rd one in the month). Other examples: "2#1" = the first Monday
	 * of the month and "4#5" = the fifth Wednesday of the month. Note that if
	 * you specify "#5" and there is not 5 of the given day-of-week in the
	 * month, then no firing will occur that month.
	 * 
	 * 
	 * The legal characters and the names of months and days of the week are not
	 * case sensitive. MON is the same as mon.
	 */

	/**
	 * CronExpression
	 */
	public CronExpression() {

	}

	/**
	 * Builds the Cron Expression Pattern
	 * 
	 * @return String
	 */
	public String build() {
		StringBuilder sb = new StringBuilder();
		sb.append(seconds).append(" ");
		sb.append(minutes).append(" ");
		sb.append(hours).append(" ");
		sb.append(dayOfMonth).append(" ");
		sb.append(month).append(" ");
		sb.append(dayOfWeek).append(" ");
		sb.append(year);
		return sb.toString();
	}

	// ---------------------------------------------------------
	// SECONDS : Allowed Values 0-59, Special Chars ,-*/
	// ---------------------------------------------------------

	public CronExpression setSeconds(int _second) {
		if (_second >= 0 && _second < 60) {
			seconds = "" + _second;
		}
		return this;
	}

	public CronExpression setSecondsRange(String _range) {
		if (_range != null && _range.length() > 0) {
			seconds = _range;
		}
		return this;
	}

	public CronExpression everySecond() {
		seconds = "*";
		return this;
	}

	public CronExpression repeatInSeconds(int repeat) {
		return repeatInSeconds(0, repeat);
	}

	public CronExpression repeatInSeconds(int start, int repeat) {
		if ((start >= 0 && start < 60) && (repeat > 0 && repeat < 60)) {
			seconds = start + "/" + repeat;
		}
		return this;
	}

	// ---------------------------------------------------------
	// MINUTES : Allowed Values 0-59, Special Chars ,-*/
	// ---------------------------------------------------------

	public CronExpression setMinutes(int _minutes) {
		if (_minutes >= 0 && _minutes < 60) {
			minutes = "" + _minutes;
		}
		return this;
	}

	public CronExpression setMinutesRange(String _range) {
		if (_range != null && _range.length() > 0) {
			minutes = _range;
		}
		return this;
	}

	public CronExpression everyMinute() {
		minutes = "*";
		return this;
	}

	public CronExpression repeatInMinutes(int repeat) {
		return repeatInMinutes(0, repeat);
	}

	public CronExpression repeatInMinutes(int start, int repeat) {
		if ((start >= 0 && start < 60) && (repeat > 0 && repeat < 60)) {
			minutes = start + "/" + repeat;
		}
		return this;
	}

	// ---------------------------------------------------------
	// HOURS : Allowed Values 0-23, Special Chars ,-*/
	// ---------------------------------------------------------

	public CronExpression setHour(int hour) {
		if (hour > 0 && hour < 24) {
			hours = "" + hour;
			dayOfWeek = "?";
		}
		return this;
	}

	public CronExpression setHoursRange(String _range) {
		if (_range != null && _range.length() > 0) {
			hours = _range;
		}
		return this;
	}

	public CronExpression everyHour() {
		hours = "*";
		return this;
	}

	public CronExpression repeatInHours(int repeat) {
		return repeatInHours(0, repeat);
	}

	public CronExpression repeatInHours(int start, int repeat) {
		if ((start >= 0 && start < 24) && (repeat > 0 && repeat < 24)) {
			hours = start + "/" + repeat;
		}
		return this;
	}

	// --------------------------------------------------------------
	// DAY Of MONTH : Allowed Values 1-31 : Special Chars ,-*?/LW
	// --------------------------------------------------------------

	public CronExpression setDayOfMonth(int _day) {
		if (_day > 0 && _day < 30) {
			dayOfMonth = _day + "";
		}
		return this;
	}

	public CronExpression setDayOfMonthRange(String _range) {
		if (_range != null && _range.length() > 0) {
			dayOfMonth = _range;
		}
		return this;
	}

	public CronExpression repeatEveryDayOfMonth(int repeat) {
		return repeatEveryDayOfMonth(1, repeat);
	}

	public CronExpression repeatEveryDayOfMonth(int start, int repeat) {
		dayOfMonth = start + "/" + repeat;
		return this;
	}

	public CronExpression anyDayOfMonth() {
		dayOfMonth = "?";
		return this;
	}

	public CronExpression lastDayOfMonth() {
		dayOfMonth = "L";
		return this;
	}

	public CronExpression lastThisDayOfMonth(int _day) {
		if (_day >= MON && _day <= SAT) {
			dayOfMonth = _day + "L";
		}
		return this;

	}

	public CronExpression onlyWeekDays() {
		dayOfMonth = "W";
		return this;
	}

	public CronExpression lastWeekDayOfMonth() {
		dayOfMonth = "LW";
		return this;
	}

	// -------------------------------------------------------------
	// MONTH : Allowed Values 1-12 or JAN-DEC, Special Chars ,-*/
	// -------------------------------------------------------------
	public CronExpression setMonth(int _month) {
		if (_month >= JAN && _month <= DEC) {
			month = "" + _month;
		}
		return this;
	}

	public CronExpression setMonthRange(String _range) {
		if (_range != null && _range.length() > 0) {
			month = _range;
		}
		return this;
	}

	public CronExpression repeatInMonth(int repeat) {
		return repeatInMonth(1, repeat);
	}

	public CronExpression repeatInMonth(int start, int repeat) {
		if ((start > 0 && start < 13) && (repeat > 0 && repeat < 13)) {
			month = start + "/" + repeat;
		}
		return this;
	}

	// --------------------------------------------------------------------
	// DAY OF WEEK : Allowed Values 1-7 or SUN-SAT, Special Chars ,-*?/L#
	// --------------------------------------------------------------------

	public CronExpression setDayOfWeek(int _day) {
		if (_day >= SUN && _day <= SAT) {
			dayOfWeek = "" + _day;
		}
		return this;
	}

	public CronExpression setDayOfWeek(String _range) {
		if (_range != null && _range.length() > 0) {
			dayOfWeek = _range;
		}
		return this;
	}

	public CronExpression everyDayOfWeek() {
		dayOfWeek = "*";
		return this;
	}

	public CronExpression anyDayOfWeek() {
		dayOfWeek = "?";
		return this;
	}

	public CronExpression lastDayOfWeek() {
		dayOfWeek = "L";
		return this;
	}

	// -----------------------------------------------------------------
	// YEAR : Allowed Values Empty, 2015, 2015-2017, Special Chars ,-*/
	// -----------------------------------------------------------------

	public CronExpression setYear(int _year) {
		if (_year > 2014) {
			year = "" + _year;
		}
		return this;
	}

	public CronExpression setYearRange(String _range) {
		if (_range != null && _range.length() > 0) {
			year = _range;
		}
		return this;
	}

	public CronExpression repeatInYear(int repeat) {
		return repeatInYear(2015, repeat);
	}

	public CronExpression repeatInYear(int start, int repeat) {
		if (start >= 2015 && repeat > 0) {
			year = start + "/" + repeat;
		}
		return this;
	}

	/**
	 * Example Code for Testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		CronExpression cronExpression = new CronExpression();

		String expr = cronExpression.build();

		System.out.println("Cron Default Expression ==>  " + expr);

		expr = new CronExpression().lastWeekDayOfMonth().build();
		System.out.println("Fire Every Last Week Day of Month ==>  " + expr);

		expr = new CronExpression().lastThisDayOfMonth(CronExpression.SAT).build();
		System.out.println("Fire Every Last SAT Day of Month ==>  " + expr);

		expr = new CronExpression().setMinutes(30).setHour(23).build();
		System.out.println("Fire Every day @ 11:30 pm  ==>  " + expr);

		expr = new CronExpression().setMinutes(30).setHour(23).setYear(2015).build();
		System.out.println("Fire Every day @ 11:30 pm in 2015  ==>  " + expr);

		expr = new CronExpression().setMinutes(30).setHour(23).setYearRange("2015-2017").build();
		System.out.println("Fire Every day @ 11:30 pm from 2015 to 2017  ==>  " + expr);

	}
}
