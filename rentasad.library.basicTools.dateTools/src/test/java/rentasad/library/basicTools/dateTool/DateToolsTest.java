package rentasad.library.basicTools.dateTool;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

public class DateToolsTest
{

	@Test
	public void testGetDateFromGermanShortDateString()
	{
		Date date = DateTools.getDateFromGermanShortDateString("13.10.2014");
		assertNotNull(date);
		assertEquals(new GregorianCalendar(2014, 9, 13,0,0).getTime(),date);
	}

	@Test
	public void testGetDateFromGermanShortDateStringShort()
	{
		Date date = DateTools.getDateFromGermanShortDateString("13.10.14");
		assertNotNull(date);
		assertEquals(new GregorianCalendar(2014, 9, 13,0,0).getTime(),date);
	}

	/*@Test
	public void testGetDateFromGermanShortDateStringInvalidMonth()
	{
		Date date = DateTools.getDateFromGermanShortDateString("13.13.14");
		assertNull(date);
	}*/

	@Test
	public void testGetDateFromGermanShortDateStringNoNumbers()
	{
		Date date = DateTools.getDateFromGermanShortDateString("abscdefg");
		assertNull(date);
	}

	@Test
	public void testGetDateFromGermanShortDateStringTooShort()
	{
		Date date = DateTools.getDateFromGermanShortDateString("10.10");
		assertNull(date);
	}

	@Test
	public void testGetDateFromGermanShortDateStringEmptyString()
	{
		Date date = DateTools.getDateFromGermanShortDateString("");
		assertNull(date);
	}

	@Test
	public void testGetDateFromGermanShortDateStringNull()
	{
		Date date = DateTools.getDateFromGermanShortDateString(null);
		assertNull(date);
	}

	@Test
	public void testGetDateFromGermanMediumDateString() throws Exception
	{
		Date date = DateTools.getDateFromGermanMediumDateString("13.10.2014");
		assertNotNull(date);
		assertEquals(new GregorianCalendar(2014, 9, 13,10,10).getTime(),date);
	}

	@Test
	public void testGetDateFromGermanMediumDateStringShort() throws Exception
	{
		Date date = DateTools.getDateFromGermanMediumDateString("13.10.14");
		assertNull(date);
	}

	@Test
	public void testGetMinutesFromMilliseconds() throws Exception
	{
		// 15 Minuten sind 900.000 ms


		assertEquals(15, DateTools.getMinutesFromMilliseconds(900000));
	}

	@Test
	public void testGetSQLTimeStampFromNow() throws Exception
	{
		Calendar calendar = Calendar.getInstance();
		// Achtung - Monate beginnen mit 0 und nicht mit 1!!
		calendar.set(2014, 8, 21, 9, 50, 30);
		Date date = calendar.getTime();
		String timeStampString = DateTools.getSQLTimeStampFromDate(date);
		System.out.println("Erstellt: " + timeStampString + "\n SOLLwert: " + "2014-08-21 09:50:30");
		assertEquals("2014-09-21 09:50:30", timeStampString);

	}
/**
 *
 * @throws Exception
 */
	@Test
	public void testGetYearFromDate() throws Exception
	{
		Calendar calendar = Calendar.getInstance();
		// Achtung - Monate beginnen mit 0 und nicht mit 1!!
		calendar.set(2014, 8, 21, 9, 50, 30);
		Date date = calendar.getTime();

		assertEquals(2014, DateTools.getYearFromDate(date));

	}

@Test
public void testPrintGregorianCalenderIntFormatDemo() throws Exception
{
	DateTools.printGregorianCalenderIntFormatDemo();
}

@Test
public void testGetPureDateStringFromDate() throws Exception
{
	Calendar calendar = Calendar.getInstance();
	// Achtung - Monate beginnen mit 0 und nicht mit 1!!
	calendar.set(2014, Calendar.AUGUST, 21);
	Date date = calendar.getTime();
	System.out.println(DateTools.getPureDateStringFromDate(date));
	assertEquals("20140821",DateTools.getPureDateStringFromDate(date) );
}

@Test
public void testGetDateFromDateStringWithoutComparators() throws Exception
{
	Calendar calendar = Calendar.getInstance();
	// Achtung - Monate beginnen mit 0 und nicht mit 1!!
	calendar.set(2015, Calendar.SEPTEMBER, 29, 0,0,0);
	calendar.set(Calendar.MILLISECOND, 0);

	String dateString = "20150929";
	Date date = DateTools.getDateFromDateStringWithoutComparators(dateString);
	System.out.println(date.getTime());
	System.out.println(calendar.getTime().getTime());
	assertEquals(calendar.getTime(),date );
}

@Test
public void testGetFirstDayDateFromMonth() throws Exception
{
    Date firstDate = DateTools.getFirstDayDateFromMonth(2015, Month.NOVEMBER);
    Date lastDate = DateTools.getLastDayDateFromMonth(2015, Month.NOVEMBER);

    System.out.println(firstDate);
    System.out.println(lastDate);
}

@Test
public void testGetMonthForInt() throws Exception
{
    String monat = DateTools.getMonthForInt(3);
    assertTrue(monat.equals("April"));
}

@Test
public void testGetDayOfWeekFromDate() throws Exception
{
    assertTrue(DateTools.getDayOfWeekFromDate(DateTools.getDateFromArguments(2016, 9, 19)) == DayOfWeek.MONDAY);
    assertTrue(DateTools.getDayOfWeekFromDate(DateTools.getDateFromArguments(2016, 9, 20)) == DayOfWeek.TUESDAY);
    assertTrue(DateTools.getDayOfWeekFromDate(DateTools.getDateFromArguments(2016, 9, 21)) == DayOfWeek.WEDNESDAY);
    assertTrue(DateTools.getDayOfWeekFromDate(DateTools.getDateFromArguments(2016, 9, 22)) == DayOfWeek.THURSDAY);
    assertTrue(DateTools.getDayOfWeekFromDate(DateTools.getDateFromArguments(2016, 9, 23)) == DayOfWeek.FRIDAY);
    assertTrue(DateTools.getDayOfWeekFromDate(DateTools.getDateFromArguments(2016, 9, 24)) == DayOfWeek.SATURDAY);
    assertTrue(DateTools.getDayOfWeekFromDate(DateTools.getDateFromArguments(2016, 9, 25)) == DayOfWeek.SUNDAY);
}

@Test
public void testParseDateString() throws Exception
{
    System.out.println(DateTools.parseDateString("01.01.15").toString());
    System.out.println(DateTools.parseDateString("01,01,15").toString());
    System.out.println(DateTools.parseDateString("15-05-14").toString());
    System.out.println(DateTools.parseDateString("2015-05-14").toString());
    System.out.println(DateTools.parseDateString("01.01.15").toString());
    System.out.println(DateTools.parseDateString("31.01.2018").toString());
    System.out.println(DateTools.parseDateString("06,04,18").toString());
}

@Test
public void testAddDaysToDateToday() throws Exception
{
    System.out.println("Heute vor 10 Tagen war der : "+ DateTools.addDaysToDateToday(-10));
}

@Test
public void testGetDateXdaysFromToday() throws Exception
{
    System.out.println("10 Days before today" + (DateTools.getDateXdaysFromToday(-10)));
}

@Test
public void testGetDateFromSqlTimeStampString() throws Exception
{
   String sqlTimeStampString = "2018-07-26 10:36:15";
   Date date = DateTools.getDateFromSqlTimeStampString(sqlTimeStampString);
   assertEquals(DateTools.getSQLTimeStampFromDate(date), "2018-07-26 10:36:15");
}

@Test
public void testGetSQLTimeStampFromDate() throws Exception
{
    String sqlTimeStampString = "2018-07-26 10:36:15";
    Date date = DateTools.getDateFromSqlTimeStampString(sqlTimeStampString);
    String timeString = DateTools.getSQLTimeStampFromDate(date).substring(11, 19);
    System.out.println(timeString);
    assertEquals("10:36:15", timeString);
}

@Test
public void testGetMondayXWeeksBeforeKWSqlDateIntIntInt() throws Exception
{
    System.out.println(DateTools.getMondayXWeeksBeforeKW(1, 0));
}

@Test
public void testGetTimeStampFromTimeStampString() throws Exception
{
    String testTimestampString = "2019-07-11 14:50:34";
    Timestamp ts = DateTools.getTimeStampFromTimeStampString(testTimestampString);
    long time = ts.getTime();
    assertEquals(1562849434000l, time);
}

@Test
public void testGetTimeStampFromISO8601TimeStampString() throws Exception
{
    
    String testTimestampString = "2019-07-10T20:23:56+02:00";
    Timestamp ts = DateTools.getTimeStampFromISO8601TimeStampString(testTimestampString);
    long time = ts.getTime();
    System.out.println(time);
    assertEquals(1562783036000l, time);
}


}
