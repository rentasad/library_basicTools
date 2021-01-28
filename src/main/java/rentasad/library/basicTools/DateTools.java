package rentasad.library.basicTools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.Month;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class DateTools
{

    public final static long LONG_TIME_SECOND = 1000;
    public final static long LONG_TIME_MINUTE = LONG_TIME_SECOND * 60;
    public final static long LONG_TIME_HOUR = LONG_TIME_MINUTE * 60;
    public final static long LONG_TIME_DAY = LONG_TIME_HOUR * 24;
    public final static long LONG_TIME_WEEK = LONG_TIME_DAY * 7;

    /**
     * Diese Klasse wird nur im statischen Kontext verwendet.
     */
    private DateTools()
    {
        super();
    }

    public static String getMonthForInt(int num)
    {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11)
        {
            month = months[num];
        }
        return month;
    }

    /**
     *
     * Description: Gibt das heutige Datum als Date zurueck, aber ohne Uhrzeit (00:00:00)
     *
     * @return
     *         Creation: 01.10.2015 by mst
     */
    public static Date getDateWithoutTimeFromToday()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     *
     * Description: Monat ist 1-Basiert
     * Jan = 1
     * Dez = 12
     *
     * @param date
     * @return 1-12
     *         Creation: 23.10.2015 by mst
     */
    public static int getMonthIntFromDate(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     *
     * Description: Monat ist 1-Basiert
     * Jan = 1
     * Dez = 12
     *
     * @param date
     * @return 1-12
     *         Creation: 23.10.2015 by mst
     */
    public static int getYearIntFromDate(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     *
     * Description: Entfernt aus dem uebergebenen Datum die Uhrzeit und setzt sie auf 00:00:00
     *
     * @param dateWithTime
     * @return
     *         Creation: 01.10.2015 by mst
     */
    public static Date getDateWithoutTime(Date dateWithTime)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateWithTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     *
     * Description:
     *
     * @param year
     *            Jahr
     * @param month
     *            1-12
     * @param day
     *            1-31
     * @return java.sql.Date
     *         Creation: 30.09.2015 by mst
     */
    public static java.sql.Date getSqlDateFromArguments(int year, int month, int day)
    {
        Date date = new GregorianCalendar(year, month - 1, day).getTime();
        return new java.sql.Date(date.getTime());
    }

    /**
     *
     * Description:
     *
     * @param year
     *            Jahr
     * @param month
     *            1-12
     * @param day
     *            1-31
     * @return
     *         Creation: 30.09.2015 by mst
     */
    public static Date getDateFromArguments(int year, int month, int day)
    {
        return new GregorianCalendar(year, month - 1, day).getTime();
    }

    /**
     *
     * @param dateString
     *            im Format TT.MM.JJ bzw. DD.MM.YY bzw. DD.MM.YYYY
     * @return The String date value as Date (fixed to 10:10 am),
     *
     *         <pre>
     *         null
     *         </pre>
     *
     *         if String is invalid
     */
    public static Date getDateFromGermanShortDateString(final String dateString)
    {
        Date date = null;
        try
        {
            if (dateString != null && dateString.length() >= 8)
            {
                int year = (dateString.length() >= 10) ?  Integer.valueOf(dateString.substring(6, 10)) :  Integer.valueOf(dateString.substring(6, 8)) + 2000;
                int month = Integer.parseInt(dateString.substring(3, 5));
                int day = Integer.parseInt(dateString.substring(0, 2));
                /*
                 * Month value is 0-based. e.g., 0 for January.
                 */
                date = new GregorianCalendar(year, month - 1, day, 0, 0, 0).getTime();
            }
        } catch (NumberFormatException e)
        {
            System.out.println("Fehler bei Datumskonvertierung: " + dateString);
            // TODO: handle exception
        }

        return date;
    }

    /**
     *
     * @param dateString
     *            im Format YYYYMMDD
     * @return
     */
    public static Date getDateFromDateStringWithoutComparators(String dateString)
    {
        Date date = null;
        try
        {
            if (dateString.length() >= 8)
            {
                // DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
                int year = Integer.parseInt(dateString.substring(0, 4));
                int month = Integer.parseInt(dateString.substring(4, 6));
                int day = Integer.parseInt(dateString.substring(6, 8));
                /*
                 * Aufgrund eines nicht gefundenen BUGs habe ich den Monat um 1 reduziert...
                 */
                date = new GregorianCalendar(year, month - 1, day, 0, 0).getTime();

                // System.out.println("Vorher: " + dateString + " ; Nachher: " + year + "-" + month + "-" + day + " DateFormat: " + format.format(date));

            }
        } catch (NumberFormatException e)
        {
            System.out.println("Fehler bei Datumskonvertierung: " + dateString);
        }
        return date;
    }

    /**
     *
     * @param dateString
     *            im Format TT.MM.JJJJ bzw. DD.MM.YYYY
     * @return
     */
    public static Date getDateFromGermanMediumDateString(String dateString)
    {
        Date date = null;
        try
        {
            if (dateString.length() >= 10)
            {
                // DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
                int year = Integer.parseInt(dateString.substring(6, 10));
                int month = Integer.parseInt(dateString.substring(3, 5));
                int day = Integer.parseInt(dateString.substring(0, 2));
                // Aufgrund eines nicht gefundenen BUGs habe ich den Monat um 1 reduziert...
                date = new GregorianCalendar(year, month - 1, day, 10, 10).getTime();
                // System.out.println("Vorher: " + dateString + " ; Nachher: " + year + "-" + month + "-" + day + " DateFormat: " + format.format(date));

            }
        } catch (NumberFormatException e)
        {
            System.out.println("Fehler bei Datumskonvertierung: " + dateString);
            // TODO: handle exception
        }
        return date;
    }

    /**
     *
     * @param date
     * @return String "TT.MM.YYYY"
     */
    public static String getDateStringFromDate(Date date)
    {
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        return formater.format(date); // z.B.14.07.2005
    }

    /**
     *
     * Description:
     *
     * @param date
     * @return "yyyyMMdd"
     *         Creation: 11.09.2015 by mst
     */
    public static String getPureDateStringFromDate(Date date)
    {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        return formater.format(date); // z.B.20150911
    }

    /**
     *
     * @param sqlDateString
     *            "JJJJ-MM-TT"
     * @return
     */
    public static Date getDateFromSQLDateString(String sqlDateString)
    {
        Date date = null;
        try
        {
            if (sqlDateString.length() >= 8)
            {
                // DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
                int year = Integer.parseInt(sqlDateString.substring(0, 4));
                int month = Integer.parseInt(sqlDateString.substring(5, 7));
                int day = Integer.parseInt(sqlDateString.substring(8, 10));
                // Aufgrund eines nicht gefundenen BUGs habe ich den Monat um 1 reduziert...
                date = new GregorianCalendar(year, month - 1, day, 10, 10).getTime();
                // System.out.println("Vorher: " + dateString + " ; Nachher: " + year + "-" + month + "-" + day + " DateFormat: " + format.format(date));
                // {
                // int i=0;
                // }

            }
        } catch (NumberFormatException e)
        {
            System.out.println("Fehler bei Datumskonvertierung: " + sqlDateString);
            // TODO: handle exception
        }
        return date;
    }

    public static int getDaysBetween2Dates(Date beforeDate, Date afterDate)
    {
        Long timeBefore = beforeDate.getTime();
        Long timeAfter = afterDate.getTime();
        long differenz = timeAfter - timeBefore;
        int tage = Long.valueOf(Math.round((double) differenz / (24. * 60. * 60. * 1000.))).intValue();
        return tage;
    }

    /**
     *
     * @param dateString
     * @return
     */
    public static long getTimeInMillisecondsFromSqlDateString(String dateString)
    {
        // 2009-04-24

        int year = Integer.parseInt(dateString.substring(0, 4)) + 2000;
        int month = Integer.parseInt(dateString.substring(5, 7));
        int day = Integer.parseInt(dateString.substring(8, 10));

        long time = new GregorianCalendar(year, month, day).getTimeInMillis();

        return time;
    }

    /**
     *
     * @param dateString
     * @return
     */
    public static String getTimeInMillisecondsFromSqlDateString(long timeInMilliseconds)
    {
        // 2009-04-24
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timeInMilliseconds);

        String year = Integer.toString(calendar.get(GregorianCalendar.YEAR));
        String month = Integer.toString(calendar.get(GregorianCalendar.MONTH) + 1);
        String day = Integer.toString(calendar.get(GregorianCalendar.DAY_OF_MONTH) + 1);
        return year + "-" + month + "-" + day;
    }

    /**
     *
     * @return "YYYY-MM-DD"
     */
    public static String getDateStringFromNow()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        String dayString = Integer.toString(calendar.get(GregorianCalendar.DAY_OF_MONTH));
        String monthString = Integer.toString(month);
        String yearString = Integer.toString(calendar.get(GregorianCalendar.YEAR));

        return yearString + "-" + monthString + "-" + dayString;
    }

    /**
     *
     * Description:
     *
     * @return YYYY-MM-DD_HH_mm
     *         Creation: 05.10.2015 by mst
     */
    public static String getDateTimeStringFromNow()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        String dayString = getInt2StringWithForwardZero(Integer.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH)));
        String monthString = getInt2StringWithForwardZero(Integer.valueOf(month));
        String yearString = Integer.toString(calendar.get(GregorianCalendar.YEAR)).toString();
        String hourString = getInt2StringWithForwardZero(calendar.get(GregorianCalendar.HOUR_OF_DAY));
        String minutesString = getInt2StringWithForwardZero(calendar.get(GregorianCalendar.MINUTE));

        return (yearString + "-" + monthString + "-" + dayString + "_" + hourString + "-" + minutesString);
    }

    /**
     * Gibt bei einer Zahl kleiner 9 eine fuehrende Null aus. Eine Zahl groesser 9 wird nur als Stringumwandlung zurueckgegeben
     *
     * @param value
     * @return
     */
    private static String getInt2StringWithForwardZero(int value)
    {
        if (value < 10)
        {
            return "0" + Integer.toString(value);
        } else
        {
            return Integer.toString(value);
        }
    }

    /**
     * Diese Funktion gibt die aktuelle Kalenderwoche zurueck.
     *
     * @return
     */
    public static int getActKW()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.get(GregorianCalendar.WEEK_OF_YEAR);
    }

    /**
     * Gibt den Montag einer Kalenderwoche zurueck
     *
     * @param kw
     *            - Kalenderwoche
     * @param year
     *            - Jahr
     * @return
     */
    public static Date getMondayFromKW(int kw, int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * Gibt den Montag einer Kalenderwoche zurueck
     *
     * @param kw
     *            - Kalenderwoche
     * @return
     */
    public static Date getMondayFromKW(int kw)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * Gibt den Montag einer Kalenderwoche zurueck
     *
     * @param kw
     *            - Kalenderwoche
     * @return
     */
    public static java.sql.Date getMondayFromKWSqlDate(int kw)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new java.sql.Date(cal.getTime().getTime());
    }

    /**
     * 
     * Description: Gibt den Freitag einer Woche zurück
     * 
     * @param kw
     * @return
     *         Creation: 03.02.2017 by mst
     */
    public static Date getFridayFromKW(int kw)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return cal.getTime();
    }

    /**
     * Gibt den Sonntag einer KW zurueck
     *
     * @param kw
     *            - Kalenderwoche
     * @param year
     *            - Jahr
     * @return
     */
    public static java.sql.Date getSundayFromKWSqlDate(int kw, int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new java.sql.Date(cal.getTime().getTime());
    }

    /**
     * 
     * Description:
     * 
     * @param kw
     * @param year
     * @return
     *         Creation: 08.05.2017 by mst
     */
    public static Date getSundayFromKW(int kw, int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * Gibt den Sonntag einer KW zurueck
     *
     * @param kw
     *            - Kalenderwoche
     * @param year
     *            - Jahr
     * @return
     */
    public static Date getSundayFromKW(int kw)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * Gibt den Sonntag einer KW zurueck
     *
     * @param kw
     *            - Kalenderwoche
     * @param year
     *            - Jahr
     * @return
     */
    public static java.sql.Date getSundayFromKWSqlDate(int kw)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new java.sql.Date(cal.getTime().getTime());
    }

    /**
     *
     * @param kw
     *            aktuelle Kalenderwochen
     * @param anzahlWochen
     *            Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
     * @return Date - Das Datum des Montags X Wochen vor der Kalenderwoche
     */
    public static Date getMondayXWeeksBeforeKW(int kw, int anzahlWochen)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
        return cal.getTime();
    }

    /**
     *
     * @param kw
     *            aktuelle Kalenderwochen
     * @param anzahlWochen
     *            Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
     * @return Date - Das Datum des Montags X Wochen vor der Kalenderwoche
     */
    public static java.sql.Date getMondayXWeeksBeforeKWSqlDate(int kw, int anzahlWochen)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
        System.out.println("Montag: " + DateTools.getDateStringFromDate(cal.getTime()));
        return new java.sql.Date(cal.getTime().getTime());
    }

    /**
     *
     * @param kw
     *            aktuelle Kalenderwochen
     * @param anzahlWochen
     *            Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
     * @return Date - Das Datum des Montags X Wochen vor der Kalenderwoche
     */
    public static java.sql.Date getMondayXWeeksBeforeKWSqlDate(int year, int kw, int anzahlWochen)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
        return new java.sql.Date(cal.getTime().getTime());
    }

    /**
     *
     * @param kw
     *            aktuelle Kalenderwochen
     * @param anzahlWochen
     *            Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
     * @return Date - Das Datum des Sonntags X Wochen vor der Kalenderwoche
     */
    public static Date getSundayXWeeksBeforeKW(int year, int kw, int anzahlWochen)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
        return cal.getTime();
    }

    /**
     *
     * @param kw
     *            aktuelle Kalenderwochen
     * @param anzahlWochen
     *            Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
     * @return Date - Das Datum des Sonntags X Wochen vor der Kalenderwoche
     */
    public static java.sql.Date getSundayXWeeksBeforeKWSqlDate(int year, int kw, int anzahlWochen)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
        return new java.sql.Date(cal.getTime().getTime());
    }

    /**
     *
     * @param kw
     *            aktuelle Kalenderwochen
     * @param anzahlWochen
     *            Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
     * @return Date - Das Datum des Sonntags X Wochen vor der Kalenderwoche
     */
    public static java.sql.Date getSundayXWeeksBeforeKWSqlDate(int kw, int anzahlWochen)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
        System.out.println("Sonntag: " + DateTools.getDateStringFromDate(cal.getTime()));
        return new java.sql.Date(cal.getTime().getTime());
    }

    /**
     *
     * @param kw
     *            aktuelle Kalenderwochen
     * @param anzahlWochen
     *            Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
     * @return Date - Das Datum des Sonntags X Wochen vor der Kalenderwoche
     */
    public static Date getSundayXWeeksBeforeKW(int kw, int anzahlWochen)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
        return cal.getTime();
    }

    // /**
    // *
    // * @param kw
    // * aktuelle Kalenderwochen
    // * @param anzahlWochen
    // * Anzahl der WOchen vor der aktuellen Kalenderwoche die beruecksichtigt werden soll
    // * @return Date - Das Datum des Sonntags X Wochen vor der Kalenderwoche
    // */
    // public static java.sql.Date getSundayXWeeksBeforeKWSqlDate(int kw, int anzahlWochen)
    // {
    // Calendar cal = Calendar.getInstance();
    // cal.set(Calendar.WEEK_OF_YEAR, kw);
    // cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    // cal.add(Calendar.DAY_OF_WEEK, -1 * 7 * anzahlWochen);
    // return new java.sql.Date(cal.getTime().getTime());
    // }

    /**
     * return date String from date in Format yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getSQLDateStringFromDate(Date date)
    {
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTimeInMillis(date.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        return dateString;

    }

    public static String getMSSQLDateStringFromDate(Date date)
    {
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTimeInMillis(date.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        String dateString = dateFormat.format(date);
        return dateString;

    }

    /**
     *
     * @param date
     * @return timeStamp in Format "yyyy-MM-dd hh:mm:ss"
     */
    public static String getSQLTimeStampFromDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     *
     * @param date
     * @return timeStamp in Format "yyyy-MM-dd hh:mm:ss"
     */
    public static String getFileTimeStampFromDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return dateFormat.format(date);
    }

    /**
     *
     * @param date
     * @return timeStamp in Format "yyyy-MM-dd"
     */
    public static String getFileDateStampFromDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static int getKWFromDate(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Rechnet Millisekunden in Minuten um.
     *
     * @param milliseconds
     * @return
     */
    public static int getMinutesFromMilliseconds(long milliseconds)
    {
        long minutes = milliseconds / LONG_TIME_MINUTE;
        Integer minutesInteger = Long.valueOf(minutes).intValue();
        return minutesInteger;

    }

    /**
     * Bekommt ein Datum uebergeben und gibt das Jahr des Datums zurueck
     *
     * @param date
     * @return
     */
    public static int getYearFromDate(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Erzeugt eine DEMO-Ausgabe um die Einstellungsmoeglichkeiten des GregorianCalendar zu demonstrieren
     * und Programmierungen daraus zu vereinfachen.
     */
    public static void printGregorianCalenderIntFormatDemo()
    {
        // get the supported ids for GMT-08:00 (Pacific Standard Time)
        String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        // if (ids.length == 0)
        // System.exit(0);

        // begin output
        System.out.println("Current Time");

        // create a Pacific Standard Time time zone
        SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);

        // set up rules for Daylight Saving Time
        pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
        pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

        // create a GregorianCalendar with the Pacific Daylight time zone
        // and the current date and time
        Calendar calendar = new GregorianCalendar(pdt);
        Date trialTime = new Date();
        calendar.setTime(trialTime);

        // print out a bunch of interesting things
        System.out.println("ERA: " + calendar.get(Calendar.ERA));
        System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
        System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("DATE: " + calendar.get(Calendar.DATE));
        System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("DAY_OF_WEEK_IN_MONTH: " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
        System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
        System.out.println("ZONE_OFFSET: " + (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)));
        System.out.println("DST_OFFSET: " + (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000)));

        System.out.println("Current Time, with hour reset to 3");
        calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
        calendar.set(Calendar.HOUR, 3);
        System.out.println("ERA: " + calendar.get(Calendar.ERA));
        System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
        System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("DATE: " + calendar.get(Calendar.DATE));
        System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("DAY_OF_WEEK_IN_MONTH: " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
        System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
        System.out.println("ZONE_OFFSET: " + (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000))); // in hours
        System.out.println("DST_OFFSET: " + (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000))); // in hours
    }

    /**
     *
     * Description: return the first day from gived Month
     * it is usefull for sql querys with dates between values...
     *
     * @param year
     * @param month
     * @return
     *         Creation: 18.12.2015 by mst
     */
    public static Date getFirstDayDateFromMonth(int year, Month month)
    {
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(Calendar.YEAR, year);
        firstDate.set(Calendar.MONTH, month.getValue() - 1);
        firstDate.set(Calendar.DAY_OF_MONTH, 1);
        firstDate.set(Calendar.HOUR, 0);
        firstDate.set(Calendar.MINUTE, 0);
        firstDate.set(Calendar.SECOND, 0);
        firstDate.set(Calendar.MILLISECOND, 0);
        return firstDate.getTime();
    }

    /**
     *
     * Description: return the last day from gived Month
     * it is usefull for sql querys with dates between values...
     *
     * @param year
     * @param month
     * @return
     *         Creation: 18.12.2015 by mst
     */
    public static Date getLastDayDateFromMonth(int year, Month month)
    {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.YEAR, year);
        lastDate.set(Calendar.MONTH, month.getValue() - 1);
        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        lastDate.set(Calendar.HOUR, 0);
        lastDate.set(Calendar.MINUTE, 0);
        lastDate.set(Calendar.SECOND, 0);
        lastDate.set(Calendar.MILLISECOND, 0);
        return lastDate.getTime();
    }

    /**
     * 
     * Description: Return array with Dates between Dates
     * 
     * @param dateFrom
     * @param DateTo
     *            Creation: 02.09.2016 by mst
     */
    public static List<Date> getDateListBetweenTwoDates(Date dateFrom, Date dateTo)
    {

        List<Date> dateList = new ArrayList<Date>();

        if (dateFrom.before(dateTo))
        {
            // int daysCount = getDaysBetween2Dates(dateFrom, dateTo);
            long newDateLong = dateFrom.getTime();
            long newDateTarget = dateTo.getTime();
            // for (int i=0;i< daysCount;i++)
            while (newDateLong < newDateTarget)
            {
                dateList.add(new Date(newDateLong));
                newDateLong += LONG_TIME_DAY;
            }
            dateList.add(dateTo);
        }
        return dateList;

    }

    /**
     * 
     * Description: return Monday to Friday Dates as Date[]
     * 
     * @param year
     * @param kw
     * @return
     *         Creation: 03.02.2017 by mst
     */
    public static List<Date> getDatesFromKWMondayToFriday(int year, int kw)
    {
        List<Date> daysOfWeek = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, kw);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        daysOfWeek.add(new Date(cal.getTimeInMillis()));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        daysOfWeek.add(new Date(cal.getTimeInMillis()));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        daysOfWeek.add(new Date(cal.getTimeInMillis()));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        daysOfWeek.add(new Date(cal.getTimeInMillis()));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        daysOfWeek.add(new Date(cal.getTimeInMillis()));
        return daysOfWeek;
    }

    /**
     * 
     * Description:
     * 
     * @param date
     * @return
     *         Creation: 03.02.2017 by mst
     */
    public static String getWeekdayFromDate(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek)
        {
            case Calendar.MONDAY:
                return "MONDAY";
            case Calendar.TUESDAY:
                return "TUESDAY";
            case Calendar.WEDNESDAY:
                return "WEDNESDAY";
            case Calendar.THURSDAY:
                return "THURSDAY";
            case Calendar.FRIDAY:
                return "FRIDAY";
            case Calendar.SATURDAY:
                return "SATURDAY";
            case Calendar.SUNDAY:
                return "SUNDAY";
            default:
                throw new IllegalStateException();
        }

    }

    /**
     * 
     * Description: Return the DayOfWeek-Enum
     * 
     * @param date
     * @return
     *         Creation: 03.09.2016 by mst
     */
    public static DayOfWeek getDayOfWeekFromDate(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(2);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1)
        {
            dayOfWeek = 7;
        } else
        {
            dayOfWeek--;
        }
        return DayOfWeek.of(dayOfWeek);
    }

    /**
     * 
     * Description: convert util.date to sql.date
     * 
     * @param date
     * @return
     *         Creation: 17.11.2016 by mst
     */
    public static java.sql.Date getSQLDateFromDate(Date date)
    {
        return new java.sql.Date(date.getTime());
    }

    public static Date parseDateString(String dateString)
    {
        String[] formatStrings =
        { "d.M.y", "d,M,y", "d_M_y", "y-M-d", "M,y", ",d,M,y", ",d.M.y", "d,,M,y" };

        for (String formatString : formatStrings)
        {
            try
            {
                return new SimpleDateFormat(formatString).parse(dateString);
            } catch (ParseException e)
            {
            }
        }

        return null;
    }

    /**
     * 
     * Description: Addiert oder subtrahiert x Tage vom aktuellen Datum und gibt dieses Datum zurück
     * 
     * @param numberOfDays
     * @return
     *         Creation: 10.10.2017 by mst
     */
    public static java.util.Date addDaysToDateToday(int numberOfDays)
    {
//        Calendar cal = Calendar.getInstance();
        Calendar calReturn = Calendar.getInstance();
        calReturn.add(Calendar.DATE, numberOfDays);
        return calReturn.getTime();
    }

    /**
     * 
     * Description:
     * 
     * @param fromLastDaysCount
     *            + or - Days before today
     * @return
     *         Creation: 23.05.2018 by mst
     */
    public static java.sql.Date getDateXdaysFromToday(int fromLastDaysCount)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar gc = new GregorianCalendar();

        System.out.println(dateFormat.format(gc.getTime()));

        gc.add(Calendar.DAY_OF_MONTH, fromLastDaysCount);

        // System.out.println(dateFormat.format(gc.getTime()));
        return DateTools.getSQLDateFromDate(gc.getTime());
    }
/**
 * 
 * Description:  PARSE DATE WITH THE FOLLOWING FORMAT
 * 15.05.2018 08:22 or "dd.MM.yyyy hh:mm"
 * to util.Date
 * 
 * 
 * @param timeStampString
 * @return
 * @throws ParseException
 * Creation: 25.05.2018 by mst
 */
    public static Date getDateFromGermanTimeStamp(String timeStampString) throws ParseException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return dateFormat.parse(timeStampString);
        
        
    }

public static Date getDateFromSqlDate(java.sql.Date date)
{
    return new Date(date.getTime());
}

/**
 * 
 * Description: 
 * 
 * @param updatedDateString
 * @return
 * Creation: 17.08.2018 by mst
 */
public static Date getDateFromSqlTimeStampString(String updatedDateString)
{
    Timestamp ts = Timestamp.valueOf(updatedDateString);
    return new Date(ts.getTime());
}

/**
 * 
 * Description: Return true if date is between fromDate and toDate 
 * 
 * @param dateFrom
 * @param dateTo
 * @param valueDate
 * @return
 * Creation: 26.07.2018 by mst
 */
public static boolean isDateBetweenTwoDates(Date dateFrom, Date dateTo, Date valueDate)
{
    Date dateFromMinusOneDay = DateTools.addDaysToDate(dateFrom, -1);
    Date dateToPlusOneDay = DateTools.addDaysToDate(dateTo, 1);
    return valueDate.before(dateToPlusOneDay) && valueDate.after(dateFromMinusOneDay);
}

/**
 * 
 * Description: 
 * 
 * @param date
 * @param numberOfDays
 * @return
 * Creation: 26.07.2018 by mst
 */
public static Date addDaysToDate(final Date date, final int numberOfDays)
{
    Calendar calReturn = Calendar.getInstance();
    calReturn.setTime(date);
    calReturn.add(Calendar.DATE, numberOfDays);
    return calReturn.getTime();
}

/**
 * 
 * Description: return SQL.Timestamp as String 
 * 
 * @param fileNameTimestampAfterProvide
 * @return
 * Creation: 23.08.2018 by mst
 */
public static Object getSqlTimeStampStringFromTimeStamp(Timestamp timestamp)
{
    Date date = new Date(timestamp.getTime());
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return dateFormat.format(date);
}

/**
 * 
 * Return {@link Timestamp} from TimestampString in Format   yyyy-MM-dd hh:mm:ss
 * 
 * @param timeStampString "yyyy-MM-dd hh:mm:ss.SSS"
 * @return
 * @throws ParseException
 * Creation: 11.07.2019 by mst
 */
public static Timestamp getTimeStampFromTimeStampString(final String timeStampString) throws ParseException
{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date parsedDate = dateFormat.parse(timeStampString);
    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
    return timestamp;
}

/**
 * 
 * Return {@link Timestamp} from TimestampString in Format   yyyy-MM-dd hh:mm:ss
 * 
 * @param timeStampString "yyyy-MM-dd hh:mm:ss.SSS"
 * @return
 * @throws ParseException
 * Creation: 11.07.2019 by mst
 */
public static Timestamp getTimeStampFromISO8601TimeStampString(final String timeStampString) throws ParseException
{
    OffsetDateTime odt = OffsetDateTime.parse( timeStampString );
    Instant instant = odt.toInstant();  // Instant is always in UTC.
    java.util.Date date = Date.from( instant);
    Timestamp timestamp = new java.sql.Timestamp(date.getTime());
    return timestamp;
}

}
