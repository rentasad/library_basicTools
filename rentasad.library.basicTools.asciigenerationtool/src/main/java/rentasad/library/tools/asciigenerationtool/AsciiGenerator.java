package rentasad.library.tools.asciigenerationtool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.TreeMap;

import rentasad.library.basicTools.NumberTools;
import rentasad.library.tools.asciigenerationtool.objects.AsciObjectInterface;
import rentasad.library.tools.asciigenerationtool.objects.AsciiDescriptionObject;
import rentasad.library.tools.asciigenerationtool.objects.AsciiGeneratorException;


/**
 * 
 * Gustini GmbH (2015)
 * Creation: 17.12.2015
 * Library
 * rentasad.library.tools.asciigenerationtool
 * 
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 */
public class AsciiGenerator
{
    /**
     * 
     * Description:
     * 
     * @param fileName
     * @param asciiObjects
     * @throws AsciiGeneratorException
     *             Creation: 18.12.2015 by mst
     */
    public static void generateAsciFile(String fileName, AsciObjectInterface[] asciiObjects) throws AsciiGeneratorException
    {

        try
        {
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            Writer w = new BufferedWriter(new OutputStreamWriter(fos, "CP850"));
            
//            int i = 0;
            for (AsciObjectInterface asciiObject : asciiObjects)
            {

                String line = generateAsciStringFromAsciiObject(asciiObject, true);
                /*
                 * Letzte Zeile erhaelt keinen Zeilenumbruch
                 */
//              i++;
//                if (i < asciiObjects.length)
                    line = line + System.getProperty("line.separator");

                w.write(line);
            }

            w.flush();
            w.close();
        } catch (

        IOException e)

        {
            throw new AsciiGeneratorException(e);
        }

    }

    /**
     * 
     * Description: Gibt zu einem AsciiObject den zugehoerigen Ascii-String zurueck
     * 
     * @param asciiObject
     * @return
     *         Creation: 18.12.2015 by mst
     * @throws AsciiGeneratorException
     */
    public static String generateAsciStringFromAsciiObject(AsciObjectInterface asciiObject, boolean valueValidationNeeded) throws AsciiGeneratorException
    {
        String asciiLine = "";
        ArrayList<AsciiDescriptionObject> aoList = AsciiGenerator.fillAsciiFields(asciiObject.getDescriptionObjectTreeMap());
        for (AsciiDescriptionObject ao : aoList)
        {
            if (valueValidationNeeded)
            {
                boolean isValid = isValueValid(ao.getValueString(), ao);
                if (isValid)
                {
                    asciiLine += ao.getValueString();
                } else
                {
                    isValueValid(ao.getValueString(), ao);
                    throw new AsciiGeneratorException("AsciValue is not Valid: " + ao.getFieldDescription());
                }
            }
        }
        return asciiLine;

    }

    public static ArrayList<AsciiDescriptionObject> fillAsciiFields(TreeMap<Integer, AsciiDescriptionObject> asciiObjectsTreeMap) throws AsciiGeneratorException
    {
        ArrayList<AsciiDescriptionObject> aoList = new ArrayList<AsciiDescriptionObject>();

        for (AsciiDescriptionObject ao : asciiObjectsTreeMap.values())
        {
            boolean isValid = isValueValid(ao.getValueString(), ao);
            boolean fillZeros = ao.isFillZeros();
            if (!isValid)
            {
                switch (ao.getFieldFormatEnum())
                {
                    case INTEGER:
                            ao.setValueString(fillIntegerToLength(ao.getValueString(), ao.getFieldLength(),fillZeros));
                        break;
                    case STRING:
                        ao.setValueString(fillStringToLengthRightAligned(ao.getValueString(), ao.getFieldLength()));
                        break;
                    case DOUBLE:
                        ao.setValueString(fillDoubleToLength(ao.getValueString(), ao.getFieldLength(),fillZeros));
                        break;
                    default:
                        throw new AsciiGeneratorException("Unbekannter Datentyp: " + ao.getFieldFormatEnum().name());
                }
            }
            aoList.add(ao);
        }

        return aoList;
    }

    /**
     * 
     * Description: 
     * 
     * @param valueString
     * @param fieldLength
     * @param fillWithZeros
     * @return
     * @throws AsciiGeneratorException
     * Creation: 03.08.2018 by mst
     */
    private static String fillDoubleToLength(String valueString, int fieldLength, boolean fillWithZeros) throws AsciiGeneratorException
    {
        String fillString;
        if (fillWithZeros)
        {
            fillString = "0"; 
        }else
        {
            fillString = " ";
        }
        if (NumberTools.isNumeric(valueString))
        {
            BigDecimal valueBigDecimal = BigDecimal.valueOf(Double.parseDouble(valueString));
            int valueInt = valueBigDecimal.setScale(0, RoundingMode.HALF_UP).intValue();

            String newValueString = Integer.toString(valueInt);
            int difference = fieldLength - valueString.length();
            for (int i = 0; i < difference; i++)
            {
                newValueString = fillString + newValueString;
            }
            return newValueString;
        } else
        {
            throw new AsciiGeneratorException("PARSE ERROR: " + valueString);
        }
    }

    /**
     * 
     * Description:
     * 
     * @param valueString
     * @param fieldLength
     * @return
     *         Creation: 22.12.2015 by mst
     */
    public static String fillStringToLengthRightAligned(final String valueString, final int fieldLength)
    {
        String newValueString = valueString;
        int difference = fieldLength - valueString.length();
        for (int i = 0; i < difference; i++)
        {
            newValueString = " " + newValueString;
        }
        return newValueString;
    }

    /**
     * 
     * Description: 
     * 
     * @param valueString
     * @param length
     * @param fillWithZeros
     * @return
     * @throws AsciiGeneratorException
     * Creation: 22.12.2015 by mst
     */
    public static String fillIntegerToLength(String valueString, final int length, boolean fillWithZeros) throws AsciiGeneratorException
    {
        String fillString;
        if (fillWithZeros)
        {
            fillString = "0"; 
        }else
        {
            fillString = " ";
        }
        if (NumberTools.isNumeric(valueString))
        {
            int valueInt = Integer.parseInt(valueString);
            String newValueString = Integer.toString(valueInt);
            int difference = length - valueString.length();
            for (int i = 0; i < difference; i++)
            {
                newValueString = fillString + newValueString;
            }
            return newValueString;
        } else
        {
            throw new AsciiGeneratorException("PARSE ERROR ON VALUE: " + valueString);
        }
    }

    /**
     * 
     * Description: Prueft ob das Ascii-Feld-Value der Typendefinition entspricht.
     * 
     * @param value
     * @param ao
     * @return
     * @throws AsciiGeneratorException
     *             Creation: 18.12.2015 by mst
     */
    public static boolean isValueValid(String value, final AsciiDescriptionObject ao) throws AsciiGeneratorException
    {
        boolean isValid = false;
        if (ao.getFieldLength() == value.length())
        {
            switch (ao.getFieldFormatEnum())
            {
                case ALN:
                    isValid = true;
                    break;
                case NUM:
                    isValid = NumberTools.isNumeric(value);
                    break;
                case BLANK:
                    isValid = value.equals(getSpaceForBlankFields(ao.getFieldLength()));
                    break;
                case DOUBLE:
                    isValid = NumberTools.isNumeric(value);
                    break;
                case INTEGER:
                    isValid = NumberTools.isNumeric(value);
                    break;
                case LONG:
                    isValid = NumberTools.isNumeric(value);
                    break;
                case STRING:
                    isValid = true;
                    break;
                default:
                    throw new AsciiGeneratorException("Unbekannter Datentyp: " + ao.getFieldFormatEnum().name());
            }
        }

        return isValid;
    }

    /**
     * 
     * Description:Gibt einen String mit der Anzahl an Leerzeichen zurueck, die als int uebergeben wurde.
     * 
     * @return
     *         Creation: 17.12.2015 by mst
     */
    public static String getSpaceForBlankFields(int length)
    {
        String spaceString = "";
        for (int i = 0; i < length; i++)
        {
            spaceString += " ";
        }
        return spaceString;
    }
}
