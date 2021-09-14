package rentasad.library.tools.asciigenerationtool.objects;

/**
 * 
 * Gustini GmbH (2015)
 * Creation: 16.12.2015
 * Library
 * rentasad.library.tools.asciigenerationtool.objects
 * 
 * @author Matthias Staud
 *
 *
 *         description:Beschreibt ein Feld eines Datensatzes einer ASCII-Zeile
 *
 */
public class AsciiDescriptionObject
{
    private final int fieldNumber;
    private final int fieldFrom;
    private final int fieldTo;
    /**
     * Feldnummer
     */
    private final String fieldDescription;
    private final AsciiFieldFormatEnum fieldFormatEnum;
    private String description;
    private String valueString;
    private boolean fillZeros = false;

    /**
     * @param fieldNumber
     * @param fieldFrom
     * @param fieldTo
     * @param fieldDescription
     * @param fieldFormatEnum
     */
    public AsciiDescriptionObject(
                                  int fieldNumber,
                                  int fieldFrom,
                                  int fieldTo,
                                  String fieldDescription,
                                  AsciiFieldFormatEnum fieldFormatEnum)
    {
        super();
        this.fieldNumber = fieldNumber;
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.fieldDescription = fieldDescription;
        this.fieldFormatEnum = fieldFormatEnum;
    }

    public AsciiDescriptionObject(
                                  int fieldNumber,
                                  int fieldFrom,
                                  int fieldTo,
                                  String fieldDescription,
                                  AsciiFieldFormatEnum fieldFormatEnum,
                                  final boolean fillZeros)
    {
        super();
        this.fieldNumber = fieldNumber;
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.fieldDescription = fieldDescription;
        this.fieldFormatEnum = fieldFormatEnum;
        this.fillZeros = fillZeros;
    }

    public AsciiDescriptionObject(
                                  int fieldNumber,
                                  int fieldFrom,
                                  int fieldTo,
                                  String fieldDescription,
                                  AsciiFieldFormatEnum fieldFormatEnum,
                                  String value)
    {
        super();
        this.fieldNumber = fieldNumber;
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.fieldDescription = fieldDescription;
        this.fieldFormatEnum = fieldFormatEnum;
        this.valueString = value;
    }

    public AsciiDescriptionObject(
                                  int fieldNumber,
                                  int fieldFrom,
                                  int fieldTo,
                                  String fieldDescription,
                                  AsciiFieldFormatEnum fieldFormatEnum,
                                  String value,
                                  final boolean fillZeros)
    {
        super();
        this.fieldNumber = fieldNumber;
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.fieldDescription = fieldDescription;
        this.fieldFormatEnum = fieldFormatEnum;
        this.valueString = value;
        this.fillZeros = fillZeros;
    }

    /**
     * @return the fieldFormatEnum
     */
    public AsciiFieldFormatEnum getFieldFormatEnum()
    {
        return fieldFormatEnum;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * @return the fieldNumber
     */
    public int getFieldNumber()
    {
        return fieldNumber;
    }

    /**
     * @return the fieldFrom
     */
    public int getFieldFrom()
    {
        return fieldFrom;
    }

    /**
     * @return the fieldTo
     */
    public int getFieldTo()
    {
        return fieldTo;
    }

    /**
     * @return the fieldDescription
     */
    public String getFieldDescription()
    {
        return fieldDescription;
    }

    public int getFieldLength()
    {
        return this.fieldTo - this.fieldFrom + 1;
    }

    /**
     * @return the valueString
     */
    public String getValueString()
    {
        return valueString;
    }

    /**
     * @param valueString
     *            the valueString to set
     */
    public void setValueString(String valueString)
    {
        this.valueString = valueString;
    }

    /**
     * @return the fillZeros
     */
    public boolean isFillZeros()
    {
        return fillZeros;
    }

    
}
