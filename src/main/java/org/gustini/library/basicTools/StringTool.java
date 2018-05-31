package org.gustini.library.basicTools;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTool
{
    /**
     * For String to HEX Conversions
     */
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    
    /**
     *
     * Suchen und Ersetzen es wird nach dem Key gesucht und durch das Value des
     * HASHMAPs ersetzt
     *
     * ACHTUNG: Diese Funktion ist speziell angepasst: Er ändert den Suchbegriff
     * "key" durch #KEY{@link #clone()}, bevor er danach sucht.
     *
     * @param hashMap
     * @param toReplaceString
     *            -
     *            Der String, in dem die Ersetzunen durchgeführt werden sollen.
     * @return
     */
    public static String stringReplacer(HashMap<String, String> hashMap, String toReplaceString)
    {
        Set<String> keySet = hashMap.keySet();
        String[] contentSiteKeyStringArray = keySet.toArray(new String[0]);
        for (int i = 0; i < contentSiteKeyStringArray.length; i++)
        {
            String key = contentSiteKeyStringArray[i];
            String searchString = "#" + key.toUpperCase() + "#";
            String replaceValue = hashMap.get(key);
            if (replaceValue != null)
            {
                toReplaceString = toReplaceString.replaceAll(searchString, replaceValue);
            } else
            {
                toReplaceString = toReplaceString.replaceAll(searchString, "");

            }

        }
        return toReplaceString;
    }

    /**
     *
     * Description:
     *
     * @param valueString
     * @return
     * Creation: 21.06.2016 by mst
     */
    public static String escapeStringToHtml(final String valueString)
    {
        String returnString = "";
        if (valueString != null)
        {
            returnString = valueString.replace("Ä", "&Auml;");
            returnString = returnString.replace("ä", "&auml;");
            returnString = returnString.replace("Ë", "&Euml;");
            returnString = returnString.replace("ë", "&euml;");
            returnString = returnString.replace("Ï", "&Iuml;");
            returnString = returnString.replace("ï", "&iuml;");
            returnString = returnString.replace("Ö", "&Ouml;");
            returnString = returnString.replace("ö", "&ouml;");
            returnString = returnString.replace("Ü", "&Uuml;");
            returnString = returnString.replace("ü", "&uuml;");
            returnString = returnString.replace("Ÿ", "&Yuml;");
            returnString = returnString.replace("ÿ", "&yuml;");
            returnString = returnString.replace("À", "&Agrave;");
            returnString = returnString.replace("Á", "&Aacute;");
            returnString = returnString.replace("Â", "&Acirc;");
            returnString = returnString.replace("Ã", "&Atilde;");
           // returnString = returnString.replace("�...", "&Aring;");
            returnString = returnString.replace("Æ", "&AElig;");
            returnString = returnString.replace("Ç", "&Ccedil;");
            returnString = returnString.replace("È", "&Egrave;");
            returnString = returnString.replace("É", "&Eacute;");
            returnString = returnString.replace("Ê", "&Ecirc;");
            returnString = returnString.replace("Ì", "&Igrave;");
            returnString = returnString.replace("Í", "&Iacute;");
            returnString = returnString.replace("Î", "&Icirc;");
            returnString = returnString.replace("Ð", "&ETH;");
            returnString = returnString.replace("Ñ", "&Ntilde;");
            returnString = returnString.replace("Ò", "&Ograve;");
            returnString = returnString.replace("Ó", "&Oacute;");
            returnString = returnString.replace("Ô", "&Ocirc;");
            returnString = returnString.replace("Õ", "&Otilde;");
            returnString = returnString.replace("Ø", "&Oslash;");
            returnString = returnString.replace("Œ", "&OElig;");
            returnString = returnString.replace("Ù", "&Ugrave;");
            returnString = returnString.replace("Ú", "&Uacute;");
            returnString = returnString.replace("Û", "&Ucirc;");
            returnString = returnString.replace("Ý", "&Yacute;");
            returnString = returnString.replace("Þ", "&THORN;");
            returnString = returnString.replace("à", "&agrave;");
            returnString = returnString.replace("á", "&aacute;");
            returnString = returnString.replace("â", "&acirc;");
            returnString = returnString.replace("ã", "&atilde;");
            returnString = returnString.replace("å", "&aring;");
            returnString = returnString.replace("æ", "&aelig;");
            returnString = returnString.replace("ç", "&ccedil;");
            returnString = returnString.replace("è", "&egrave;");
            returnString = returnString.replace("é", "&eacute;");
            returnString = returnString.replace("ê", "&ecirc;");
            returnString = returnString.replace("ì", "&igrave;");
            returnString = returnString.replace("í", "&iacute;");
            returnString = returnString.replace("î", "&icirc;");
            returnString = returnString.replace("ð", "&eth;");
            returnString = returnString.replace("ñ", "&ntilde;");
            returnString = returnString.replace("ò", "&ograve;");
            returnString = returnString.replace("ó", "&oacute;");
            returnString = returnString.replace("ô", "&ocirc;");
            returnString = returnString.replace("õ", "&otilde;");
            returnString = returnString.replace("ø", "&oslash;");
            returnString = returnString.replace("�", "&oelig;");
            returnString = returnString.replace("ù", "&ugrave;");
            returnString = returnString.replace("ú", "&uacute;");
            returnString = returnString.replace("û", "&ucirc;");
            returnString = returnString.replace("ý", "&yacute;");
            returnString = returnString.replace("þ", "&thorn;");
            returnString = returnString.replace("Š", "&Scaron;");
            returnString = returnString.replace("š", "&scaron;");
            //returnString = returnString.replace("ae�", "&Ccaron;");
           // returnString = returnString.replace("ae�", "&ccaron;");
            returnString = returnString.replace("ß", "&szlig;");
            /*
             * returnString = returnString.replace("Α", "&Alpha;");
             * returnString = returnString.replace("Β", "&Beta;");
             * returnString = returnString.replace("�"", "&Gamma;");
             * returnString = returnString.replace("�"", "&Delta;");
             * returnString = returnString.replace("Ε", "&Epsilon;");
             * returnString = returnString.replace("Ζ", "&Zeta;");
             * returnString = returnString.replace("Η", "&Eta;");
             * returnString = returnString.replace("Θ", "&Theta;");
             * returnString = returnString.replace("Ι", "&Iota;");
             * returnString = returnString.replace("Κ", "&Kappa;");
             * returnString = returnString.replace("Λ", "&Lambda;");
             * returnString = returnString.replace("Μ", "&Mu;");
             * returnString = returnString.replace("Ν", "&Nu;");
             * returnString = returnString.replace("Ξ", "&Xi;");
             * returnString = returnString.replace("Ο", "&Omicron;");
             * returnString = returnString.replace("Π", "&Pi;");
             * returnString = returnString.replace("Ρ", "&Rho;");
             * returnString = returnString.replace("Σ", "&Sigma;");
             * returnString = returnString.replace("Τ", "&Tau;");
             * returnString = returnString.replace("Υ", "&Upsilon;");
             * returnString = returnString.replace("Φ", "&Phi;");
             * returnString = returnString.replace("Χ", "&Chi;");
             * returnString = returnString.replace("Ψ", "&Psi;");
             * returnString = returnString.replace("Ω", "&Omega;");
             * returnString = returnString.replace("α", "&alpha;");
             * returnString = returnString.replace("β", "&beta;");
             * returnString = returnString.replace("γ", "&gamma;");
             * returnString = returnString.replace("δ", "&delta;");
             * returnString = returnString.replace("ε", "&epsilon;");
             * returnString = returnString.replace("ζ", "&zeta;");
             * returnString = returnString.replace("η", "&eta;");
             * returnString = returnString.replace("θ", "&theta;");
             * returnString = returnString.replace("ι", "&iota;");
             * returnString = returnString.replace("κ", "&kappa;");
             * returnString = returnString.replace("λ", "&lambda;");
             * returnString = returnString.replace("μ", "&mu;");
             * returnString = returnString.replace("ν", "&nu;");
             * returnString = returnString.replace("ξ", "&xi;");
             * returnString = returnString.replace("ο", "&omicron;");
             * returnString = returnString.replace("π", "&pi;");
             * returnString = returnString.replace("ρ", "&rho;");
             * returnString = returnString.replace("ς", "&sigmaf;");
             * returnString = returnString.replace("σ", "&sigma;");
             * returnString = returnString.replace("τ", "&tau;");
             * returnString = returnString.replace("�...", "&upsilon;");
             * returnString = returnString.replace("φ", "&phi;");
             * returnString = returnString.replace("χ", "&chi;");
             * returnString = returnString.replace("ψ", "&psi;");
             * returnString = returnString.replace("ω", "&omega;");
             */
        }
        return returnString;

    }

    /**
     *
     * Description: Entfernt ungültige Zeichen aus einem String und erzeugt daraus einen
     * gültigen Verwendungszweck für Zahlungen
     *
     * @param string
     * @return
     *         Creation: 25.08.2015 by mst
     */
    public static String getValidVerwendungszweckString(String string)
    {
        // Dateiname in Kleinbuchstaben abbilden.
        string = string.toLowerCase();
        string = string.trim();

        // string = string.replaceAll("\\*\\*\\*", "");
        string = string.replaceAll("ä", "ae");
        string = string.replaceAll("ü", "ue");
        string = string.replaceAll("ö", "oe");
        string = string.replace('\'', '#');
        string = string.replace('\"', '#');
        string = string.replace('´', '-');
        string = string.replace('`', ' ');
        string = string.replace('á', 'a');
        string = string.replace('à', 'a');
        string = string.replace('é', 'e');
        string = string.replace('è', 'e');
        string = string.replace('ó', 'o');
        string = string.replace('ò', 'o');
        string = string.replace('ú', 'u');
        string = string.replace('ù', 'u');
        string = string.replaceAll("ß", "ss");
        string = string.replace('%', ' ');
        string = string.replace('/', ' ');
        string = string.replace('-', ' ');
        string = string.replace('?', ' ');
        string = string.replace('<', ' ');
        string = string.replace('>', ' ');

        string = string.replaceAll("###", "");
        string = string.replaceAll("#", "");
        string = string.replaceAll("<br>", "");
        string = string.replaceAll("\\.\\.", "");
        string = string.replaceAll("\\.\\.\\.", "");
        string = string.replaceAll("&#063;", "");
        string = string.replaceAll("„", "");
        string = string.replaceAll("“", "");
        string = string.replaceAll("\\.", "");

        return string;
    }

    /**
     * Entfernt ungültige Zeichen aus einem String und erzeugt daraus einen
     * UNIX-tauglichen Dateinamen.
     *
     * @param string
     *            Dateiname (OHNE ENDUNG wie .html!!
     * @return
     */
    public static String getValidFileNameFromString(String string)
    {
        // Dateiname in Kleinbuchstaben abbilden.
        string = string.toLowerCase();
        string = string.trim();

        // string = string.replaceAll("\\*\\*\\*", "");
        string = string.replaceAll("ä", "ae");
        string = string.replaceAll("ü", "ue");
        string = string.replaceAll("ö", "oe");
        string = string.replace('\'', '#');
        string = string.replace('\"', '#');
        string = string.replace('´', '-');
        string = string.replace('`', ' ');
        string = string.replace('á', 'a');
        string = string.replace('à', 'a');
        string = string.replace('é', 'e');
        string = string.replace('è', 'e');
        string = string.replace('ó', 'o');
        string = string.replace('ò', 'o');
        string = string.replace('ú', 'u');
        string = string.replace('ù', 'u');
        string = string.replaceAll("ß", "ss");
        string = string.replace('%', '#');
        string = string.replace('/', '#');
        string = string.replace(' ', '-');
        string = string.replace('?', '#');
        string = string.replace('<', '#');
        string = string.replace('>', '#');

        string = string.replace(' ', '-');
        string = string.replaceAll("###", "");
        string = string.replaceAll("#", "");
        string = string.replaceAll("<br>", "");
        string = string.replaceAll("\\.\\.", "");
        string = string.replaceAll("\\.\\.\\.", "");
        string = string.replaceAll("&#063;", "");
        string = string.replaceAll("„", "");
        string = string.replaceAll("“", "");
        string = string.replaceAll("\\.", "");

        return string;
    }

    /**
     *
     * Description: convert from UTF-8 -> internal Java String format
     *
     * @param s
     * @return
     *         Creation: 11.12.2015 by mst
     * @throws UnsupportedEncodingException
     */
    public static String convertFromUTF8(String s) throws UnsupportedEncodingException
    {
        return new String(s.getBytes("ISO-8859-1"), "UTF-8");
    }

    /**
     *
     * Description: convert from internal Java String format -> UTF-8
     *
     * @param s
     * @return
     *         Creation: 11.12.2015 by mst
     * @throws UnsupportedEncodingException
     */
    public static String convertToUTF8(String s) throws UnsupportedEncodingException
    {
        return new String(s.getBytes("UTF-8"), "ISO-8859-1");
    }

    /**
     *
     * Description:
     *
     * @param vector
     * @return
     *         Creation: 11.12.2015 by mst
     */
    public static String verketteStringVector2String(final Vector<String> vector)
    {
        String string = "";

        String[] stringArray = vector.toArray(new String[0]);
        for (int i = 0; i < stringArray.length; i++)
        {
            string = string + stringArray[i];
        }
        return string;

    }

    /**
     * Verkettet eine Arraylist mit Strings zu einem zusammenhängenden String
     *
     * @param arrayList
     * @return
     */
    public static String verketteStringArraylist2String(List<String> arrayList)
    {
        String string = "";

        String[] stringArray = arrayList.toArray(new String[0]);
        for (int i = 0; i < stringArray.length; i++)
        {
            string = string + stringArray[i];
        }
        return string;

    }

    /**
     * Verkettet ein StringArray zu einem zusammenhängenden String
     *
     * @param arrayList
     * @return
     */
    public static String verketteStringArraylist2String(String[] stringArray)
    {
        String string = "";

        for (int i = 0; i < stringArray.length; i++)
        {
            string = string + stringArray[i];
        }
        return string;

    }

    /**
     * Verkettet ein StringArray zu einem zusammenhängenden String
     * <br>
     * Die einzelnen Elemente werden mit einem \n Zeilenumbruch getrent.
     * Die erste Zeile erhält keinen New Line
     *
     * @param arrayList
     * @return
     */
    public static String verketteStringArray2StringWithNewLine(String[] stringArray)
    {
        String string = "";

        for (int i = 0; i < stringArray.length; i++)
        {
            if (i == 0)
            {
                string = string + stringArray[i];
            }else
            {
                string = string + "\n" + stringArray[i];
            }
                
        }
        return string;

    }

    /**
     * Konvertiert Zahlen in ein Format, durch Welches sie in Zahlentypen
     * konvertiert werden können.
     *
     * @param numberString
     *            "z.B. -13.497,25"
     * @return "-13497.25"
     *
     *         Es werden erst Punkte entfernt und anschließend Kommata durch
     *         Punkte ersetzt.
     */
    public static String convertNumberStringToValidNumberString(String numberString)
    {
        numberString = numberString.replaceAll("[.]", "");
        numberString = numberString.replace(',', '.');
        return numberString;
    }

    /**
     * Gibt Wert zwischen Anfangszeichen und Endzeichen zurück. ACHTUNG - Anfang und Ende sind Bestandteile von regulären Ausdrücken.
     *
     * Anwendungsbeispiel:
     *
     * Wert in Klammern ermitteln bei Text "varchar(8)".
     * Aufruf:
     * getStringZwischen("varchar(8)", "\\(", "\\))
     *
     * @param text
     * @param anfang
     * @param ende
     *
     * @return StringArray mit Ergebnissen.
     */
    public static String[] getStringZwischen(String text, String anfang, String ende)
    {
        // Bei Ausdrücken (text) mit mehreren Klammern muss noch eine
        // Einschränkung bei .* getroffen werden
        Pattern p = Pattern.compile("(?<=" + anfang + ").*(?=" + ende + ")");
        Matcher m = p.matcher(text);
        ArrayList<String> ergebnisStringArray = new ArrayList<String>();
        while (m.find())
        {
            ergebnisStringArray.add(m.group());
        }
        return ergebnisStringArray.toArray(new String[0]);
    }

    /**
     * Wandelt einen boolschen Wert in einen String um
     *
     * @param booleanValue
     * @return String : True oder False
     */
    public static String getBooleanString(boolean booleanValue)
    {
        if (booleanValue)
        {
            return "true";
        } else
        {
            return "false";
        }
    }

    /*
     * Splittet String mit Separator
     */
    public static String[] splittStringWithCommas(String splittString, String separator)
    {
        return splittString.split(separator);
    }
    
    /**
     * 
     * Description: 
     * 
     * @param input
     * @return
     * Creation: 15.12.2016 by mst
     */
    public static boolean isValidUTF8( byte[] input ) {

        
        CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();

        try {
            cs.decode(ByteBuffer.wrap(input));
            return true;
        }
        catch(CharacterCodingException e){
            return false;
        }       
    }
    /**
     * 
     * Description: 
     * 
     * @param input
     * @param encoding
     * @return
     * Creation: 15.12.2016 by mst
     */
    public static boolean isUTF8MisInterpreted( String input, String encoding) {

        SortedMap<String, Charset> csMap = Charset.availableCharsets();
        Set keySet = csMap.keySet();
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
        ByteBuffer tmp;
        try {
            tmp = encoder.encode(CharBuffer.wrap(input));
        }

        catch(CharacterCodingException e) {
            return false;
        }

        try {
            decoder.decode(tmp);
            return true;
        }
        catch(CharacterCodingException e){
            return false;
        }       
    }
    /**
     * 
     * Description: 
     * 
     * @param stringValue
     * @return
     * Creation: 17.01.2017 by mst
     */
    public static String removeUniCodeGlyphs(String stringValue)
    {
        
        stringValue = stringValue.replaceAll("\u00ef", "");
        stringValue = stringValue.replaceAll("\u00bf", "");
        stringValue = stringValue.replaceAll("\u00bb", "");
        stringValue = stringValue.replaceAll("\uFFFD", "");
        stringValue = stringValue.replaceAll("\u00DE", "");
        stringValue = stringValue.replaceAll("\u00FF", "");
        stringValue = stringValue.replaceAll("\ufffd", "");
        stringValue = stringValue.replaceAll("\"", "");
        return stringValue;
    }

    /**
     * 
     * Description: 
     * 
     * @param plaintext
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * Creation: 06.11.2017 by mst
     */
    public static String getMd5HashFromString(String plaintext) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }
}
