package org.gustini.library.basicTools;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberTools
{
    public static boolean isNumeric(final String number)
    {
        try
        {
            final double d = Double.parseDouble(number);
        } catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    
    public static boolean isNumericInteger(final String number)
    {
        try
        {
            @SuppressWarnings("unused")
            final int intValue = Integer.parseInt(number);
        } catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    /**
     * 
     * Description: 
     * 
     * @param number
     * @return
     * Creation: 19.01.2017 by mst
     */
    public static boolean isNumericLong(final String number)
    {
        try
        {
            @SuppressWarnings("unused")
            final long intValue = Long.parseLong(number);
        } catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    /**
     *
     * Description: Gibt eine einstellige Integerzahl mit fuehrender Null zurueck
     *
     * @param number
     * @return
     *         Creation: 11.04.2016 by mst
     */
    public static String getLeadingZero(int number)
    {
        return String.format("%02d", number);
    }

    /**
     *
     * Description: Formatiert Nummer nach Pattern und gibt String zurueck
     *
     *
     * <tbody>
     * <tr>
     * <th id="h101">Value</th>
     * <th id="h102">Pattern</th>
     * <th id="h103">Output</th>
     * <th id="h104">Explanation</th>
     * </tr>
     * <tr>
     * <td headers="h101">123456.789</td>
     * <td headers="h102">###,###.###</td>
     * <td headers="h103">123,456.789</td>
     * <td headers="h104">The pound sign (#) denotes a digit, the comma is a placeholder for the grouping separator, and the period is a placeholder for the decimal separator.</td>
     * </tr>
     * <tr>
     * <td headers="h101">123456.789</td>
     * <td headers="h102">###.##</td>
     * <td headers="h103">123456.79</td>
     * <td headers="h104">The <code>value</code> has three digits to the right of the decimal point, but the <code>pattern</code> has only two. The <code>format</code> method handles this by rounding
     * up.</td>
     * </tr>
     * <tr>
     * <td headers="h101">123.78</td>
     * <td headers="h102">000000.000</td>
     * <td headers="h103">000123.780</td>
     * <td headers="h104">The <code>pattern</code> specifies leading and trailing zeros, because the 0 character is used instead of the pound sign (#).</td>
     * </tr>
     * <tr>
     * <td headers="h101">12345.67</td>
     * <td headers="h102">$###,###.###</td>
     * <td headers="h103">$12,345.67</td>
     * <td headers="h104">The first character in the <code>pattern</code> is the dollar sign ($). Note that it immediately precedes the leftmost digit in the formatted <code>output</code>.</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param pattern
     * @param value
     *            Creation: 25.04.2016 by mst
     */
    static public String customFormat(String pattern, double value)
    {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        // System.out.println(value + " " + pattern + " " + output);
        return output;
    }

    /**
     *
     * Description:Gibt count Zahlen zwischen min und max gewuerfelt aus
     * Achtet darauf, dass keine Zahlen doppelt vorkommen.
     *
     * Setzt voraus, dass count nicht groesser als Differenz zwischen min und max ist.
     *
     * @param min
     * @param max
     * @param count
     * @return
     *         Creation: 25.02.2016 by mst
     */
    public static List<Integer> randomGenerator(int min, int max, int count)
    {
        final List<Integer> numberAmountList = new ArrayList<Integer>();

        Random random = new Random();
        int difference = (max - min) + 1;
        while (numberAmountList.size() < count)
        {
            List<Integer> laufListe = new ArrayList<Integer>();
            while (laufListe.size() < difference)
            {
                Integer randomNum = random.nextInt((max - min) + 1) + min;
                if ((!laufListe.contains(randomNum)) && randomNum > 0)
                    laufListe.add(randomNum);
            }
            numberAmountList.addAll(laufListe);
        }

        return numberAmountList;
    }

    /**
     * 
     * Description: Convert a boolean value to integer 
     * 
     * 
     * @param booleanValue
     * @return true = 1, false = 0
     * Creation: 09.02.2018 by mst
     */
    public static int getIntFromBoolean(boolean booleanValue)
    {
        if (booleanValue)
        {
            return 1;
        }else
        {
            return 0;
        }
    }
    
    /**
     * Rundet den übergebenen Wert auf die Anzahl der übergebenen Nachkommastellen
     *
     * @param value ist der zu rundende Wert.
     * @param decimalPoints ist die Anzahl der Nachkommastellen, auf die gerundet werden soll.
     */
     public static double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
     }

}
