package rentasad.library.excelGenerator.objects;

import java.util.Date;

/**
 * 
 * Gustini GmbH (2015)
 * Creation: 19.01.2015
 * Rentasad Library
 * rentasad.lib.tools.excelGenerator
 * 
 * @author Matthias Staud
 * 
 * 
 *         Description:
 *         Diese Klasse dient der Definition eines Datenobjektes. Mittels dieser
 *         Definition lassen sich Excel-Templates erstellen,
 *         welche die Generierung von Excel-Tabellen mit einer Bibliotheksklasse
 *         zur Excelgenerierung vereinfachen sollen.
 */
public class ExcelGeneratorValueEntry
{
	
	/**
	 * 
	 * Steuerungszeichen
	 * Wenn RETURN_NULL_WHEN_NUMBER_VALUE_ZERO = True, wird, wenn der Numberwert = 0 ist, statt der Zahl "Null" einfach "NULL" zurueck gegeben.
	 */
	public boolean returnNullWhenNumberValueZero = false;
	
	
	public final static int CELL_TYPE_LABEL = 1;
	public final static int CELL_TYPE_DATE = 2;
	public final static int CELL_TYPE_NUMBER_INT = 4;
	public final static int CELL_TYPE_NUMBER_FLOAT = 8;
	public final static int CELL_TYPE_NUMBER_DOUBLE = 16;
	public final static int CELL_TYPE_BOOLEAN = 32;

	/**
	 * Border rundum schmal schwarz
	 * Fettgedruckte Schrift
	 * TEXT
	 */
	public static final int CELL_STYLE_HEADER = 0;
	/**
	 * Border rundum schmal schwarz
	 * TEXT
	 */
	public static final int CELL_STYLE_BORDER = 1;
	/**
	 * Border rundum schmal schwarz
	 * Datum DD.MM.YYYY
	 */
	public static final int CELL_STYLE_DATE_DDMMYYYY = 2;
	/**
	 * Border rundum schmal schwarz
	 * Waehrung in EUR
	 */
	public static final int CELL_STYLE_CURRENCY_EUR = 3;
	/**
	 * Kein Style - Text bleibt Plain
	 * 
	 */
	public static final int CELL_STYLE_NONE = 99;

	/**
	 * Nummer der Spalte, 0 = erste Spalte, 1= zweite Spalte etc...
	 */
	private int ColumnNumber;
	/**
	 * Name der Spalte, zur Beschriftung des Tabellenkopfes
	 */
	private String ColumnName;
	/**
	 * Datentyp
	 */
	private int dataType;

	private String stringValue;
	private Float floatValue;
	private Double doubleValue;
	private Integer intValue;
	private Date dateValue;
	private boolean booleanValue;

	private Integer cellStyleInt = CELL_STYLE_NONE;

	/**
	 * 
	 * @param columnNumber
	 * @param columnName
	 * @param intValue
	 */
	public ExcelGeneratorValueEntry(
									int columnNumber,
									String columnName,
									Integer intValue)
	{
		super();
		ColumnNumber = columnNumber;
		ColumnName = columnName;
		this.intValue = intValue;
		this.dataType = CELL_TYPE_NUMBER_INT;
	}

	/**
	 * @param columnNumber
	 * @param columnName
	 * @param booleanValue
	 */
	public ExcelGeneratorValueEntry(
									int columnNumber,
									String columnName,
									boolean booleanValue)
	{
		super();
		ColumnNumber = columnNumber;
		ColumnName = columnName;
		this.booleanValue = booleanValue;
		this.dataType = CELL_TYPE_BOOLEAN;
	}

	/**
	 * 
	 * @param columnNumber
	 *            (Beginnend mit 0)
	 * @param columnName
	 * @param stringValue
	 */
	public ExcelGeneratorValueEntry(
									int columnNumber,
									String columnName,
									String stringValue)
	{
		super();
		ColumnNumber = columnNumber;
		ColumnName = columnName;
		this.stringValue = stringValue;
		this.dataType = CELL_TYPE_LABEL;
	}

	/**
	 * 
	 * @param columnNumber
	 * @param columnName
	 * @param dateValue
	 */
	public ExcelGeneratorValueEntry(
									int columnNumber,
									String columnName,
									Date dateValue)
	{
		super();
		ColumnNumber = columnNumber;
		ColumnName = columnName;
		this.dateValue = dateValue;
		this.dataType = CELL_TYPE_DATE;
	}

	/**
	 * 
	 * @param columnNumber
	 * @param columnName
	 * @param floatValue
	 */
	public ExcelGeneratorValueEntry(
									int columnNumber,
									String columnName,
									float floatValue)
	{
		super();
		ColumnNumber = columnNumber;
		ColumnName = columnName;
		this.floatValue = floatValue;
		this.dataType = CELL_TYPE_NUMBER_FLOAT;
	}

	/**
	 * 
	 * @param columnNumber
	 * @param columnName
	 * @param doubleValue
	 */
	public ExcelGeneratorValueEntry(
									int columnNumber,
									String columnName,
									double doubleValue)
	{
		super();
		ColumnNumber = columnNumber;
		ColumnName = columnName;
		this.doubleValue = doubleValue;
		this.dataType = CELL_TYPE_NUMBER_DOUBLE;
	}

	public int getColumnNumber()
	{
		return ColumnNumber;
	}

	public String getColumnName()
	{
		return ColumnName;
	}

	public int getDataType()
	{
		return dataType;
	}

	public String getStringValue()
	{
		return stringValue;
	}

	public Float getFloatValue()
	{
			return floatValue;
	}

	public Double getDoubleValue()
	{
			return doubleValue;
	}

	public Integer getIntValue()
	{
			return intValue;
	}

	public Date getDateValue()
	{
		return dateValue;
	}

	/**
	 * @param columnNumber
	 *            the columnNumber to set
	 */
	public void setColumnNumber(int columnNumber)
	{
		ColumnNumber = columnNumber;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName)
	{
		ColumnName = columnName;
	}

	/**
	 * @return the cellStyleInt
	 */
	public int getCellStyleInt()
	{
		return cellStyleInt;
	}

	/**
	 * @param cellStyleInt
	 *            the cellStyleInt to set
	 */
	public void setCellStyleInt(int cellStyleInt)
	{
		this.cellStyleInt = cellStyleInt;
	}

	/**
	 * @return the booleanValue
	 */
	public boolean isBooleanValue()
	{
		return booleanValue;
	}

	/**
	 * @param booleanValue the booleanValue to set
	 */
	public void setBooleanValue(boolean booleanValue)
	{
		this.booleanValue = booleanValue;
	}

	/**
	 * @return the setReturnNullWhenNumberValueZero
	 */
	public boolean isReturnNullWhenNumberValueZero()
	{
		return returnNullWhenNumberValueZero;
	}

	/**
	 * @param setReturnNullWhenNumberValueZero the setReturnNullWhenNumberValueZero to set
	 */
	public void setReturnNullWhenNumberValueZero(boolean setReturnNullWhenNumberValueZero)
	{
		this.returnNullWhenNumberValueZero = setReturnNullWhenNumberValueZero;
	}

	
	
}
