package rentasad.library.excelGenerator;

import java.io.IOException;
import java.util.zip.DataFormatException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rentasad.library.excelGenerator.exceptions.SheetNotFoundException;
import rentasad.library.excelGenerator.exceptions.UnknownCellStyleException;
import rentasad.library.excelGenerator.objects.ExcelGeneratorValueEntry;
import rentasad.library.excelGenerator.objects.IExcelMatrix;

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
 *         Diese Klasse erzeugt XLSX-Dateien auf Grundlage von IExcelMatrix
 *         implementierenden Datenobjekten.
 */
public class XlsxGenerator
{
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

	Workbook workbook;
	private CellStyle dateCellStyle;
	private CellStyle currencyCellStyle;
	private CellStyle headerCellStyle;
	private CellStyle borderCellStyle;
	@SuppressWarnings("unused")
	private CellStyle noneCellStyle;

	/**
	 * Das Workbook fuer den Generator wird mit uebergeben
	 * 
	 * @param workbook
	 */
	public XlsxGenerator(
							Workbook workbook)
	{
		super();
		this.workbook = workbook;
		initStyles(workbook);
	}

	/**
	 * Das Workbook wird neu erstellt, da keines uebergeben wurde.
	 */
	public XlsxGenerator()
	{
		super();
		this.workbook = new XSSFWorkbook();
		initStyles(workbook);
	}

	/**
	 * Gibt CellStyle zurueck
	 * 
	 * Example:
	 * 
	 * getStyle(XlsxGenerator.CELL_STYLE_BORDER)
	 * 
	 * returns CellStyle borderCellStyle
	 * 
	 * 
	 * @param style
	 * @return Style
	 * @throws UnknownCellStyleException
	 */
	public CellStyle getCellStyleFromInt(int style) throws UnknownCellStyleException
	{
		switch (style)
		{
			case XlsxGenerator.CELL_STYLE_BORDER:
				return borderCellStyle;
			case XlsxGenerator.CELL_STYLE_CURRENCY_EUR:
				return currencyCellStyle;
			case XlsxGenerator.CELL_STYLE_DATE_DDMMYYYY:
				return dateCellStyle;
			case XlsxGenerator.CELL_STYLE_HEADER:
				return headerCellStyle;
			case XlsxGenerator.CELL_STYLE_NONE:
				return null;

			default:
				throw new UnknownCellStyleException();
		}
	}

	private void initStyles(Workbook workbook)
	{
		CreationHelper createHelper = workbook.getCreationHelper();
		
		this.dateCellStyle = workbook.createCellStyle();
		this.dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.mm.yyyy"));
		this.dateCellStyle.setBorderBottom(BorderStyle.THIN);
		this.dateCellStyle.setBorderLeft(BorderStyle.THIN);
		this.dateCellStyle.setBorderRight(BorderStyle.THIN);
		this.dateCellStyle.setBorderTop(BorderStyle.THIN);
		/*
		 * CellStyle fuer Waehrung
		 */
		this.currencyCellStyle = workbook.createCellStyle();
		this.currencyCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("_-* #,##0.00 \u20AC_-;-* #.##0,00 \u20AC_-;_-* \"-\"?? \u20AC_-;_-@_-"));
		this.currencyCellStyle.setBorderBottom(BorderStyle.THIN);
		this.currencyCellStyle.setBorderLeft(BorderStyle.THIN);
		this.currencyCellStyle.setBorderRight(BorderStyle.THIN);
		this.currencyCellStyle.setBorderTop(BorderStyle.THIN);

		/*
		 * CellStyle fuer Beschriftungen
		 */
		this.headerCellStyle = workbook.createCellStyle();
		// currencyCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("_-* #,##0.00 \u20AC_-;-* #.##0,00 \u20AC_-;_-* \"-\"?? \u20AC_-;_-@_-"));
		this.headerCellStyle.setBorderBottom(BorderStyle.THIN);
		this.headerCellStyle.setBorderLeft(BorderStyle.THIN);
		this.headerCellStyle.setBorderRight(BorderStyle.THIN);
		this.headerCellStyle.setBorderTop(BorderStyle.THIN);
		Font font = workbook.createFont();
		font.setBold(true);
		this.headerCellStyle.setFont(font);

		/*
		 * CellStyle fuer Beschriftungen
		 */
		this.borderCellStyle = workbook.createCellStyle();
		// currencyCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("_-* #,##0.00 \u20AC_-;-* #.##0,00 \u20AC_-;_-* \"-\"?? \u20AC_-;_-@_-"));
		this.borderCellStyle.setBorderBottom(BorderStyle.THIN);
		this.borderCellStyle.setBorderLeft(BorderStyle.THIN);
		this.borderCellStyle.setBorderRight(BorderStyle.THIN);
		this.borderCellStyle.setBorderTop(BorderStyle.THIN);

		this.noneCellStyle = workbook.createCellStyle();
	}

	/*
	 * public void readExcelFile(String filePath)
	 * // {
	 * // try
	 * // {
	 * // FileInputStream file = new FileInputStream(new File(filePath));
	 * //
	 * // // Create Workbook instance holding reference to .xlsx file
	 * // XSSFWorkbook workbook = new XSSFWorkbook(file);
	 * //
	 * // // Get first/desired sheet from the workbook
	 * // XSSFSheet sheet = workbook.getSheetAt(0);
	 * //
	 * // // Iterate through each rows one by one
	 * // Iterator<Row> rowIterator = sheet.iterator();
	 * // while (rowIterator.hasNext())
	 * // {
	 * // Row row = rowIterator.next();
	 * // // For each row, iterate through all the columns
	 * // Iterator<Cell> cellIterator = row.cellIterator();
	 * //
	 * // while (cellIterator.hasNext())
	 * // {
	 * // Cell cell = cellIterator.next();
	 * // System.out.println("\nKoordinaten:" + cell.getRowIndex() + "," +
	 * cell.getColumnIndex() + ": ");
	 * // // Check the cell type and format accordingly
	 * // switch (cell.getCellType())
	 * // {
	 * // case Cell.CELL_TYPE_NUMERIC:
	 * // System.out.print(cell.getNumericCellValue());
	 * // break;
	 * // case Cell.CELL_TYPE_STRING:
	 * // System.out.print(cell.getStringCellValue());
	 * // break;
	 * // }
	 * // }
	 * // }
	 * // file.close();
	 * // } catch (Exception e)
	 * // {
	 * // e.printStackTrace();
	 * // }
	 * //
	 * }
	 */
	/**
	 * 
	 * @param workbook
	 * @param sheetName
	 * @param iExcelMatrixs
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws UnknownCellStyleException
	 */
	public void addExcelSheetFromExcelMatrixClass(XSSFWorkbook workbook, String sheetName, IExcelMatrix[] iExcelMatrixs) throws IOException, DataFormatException, UnknownCellStyleException
	{
		int cellStyleInt = CELL_STYLE_NONE;
		addExcelSheetFromExcelMatrixClass(workbook, sheetName, iExcelMatrixs, cellStyleInt);
	}

	/**
	 * Fuegt einem bestehendem XSSF-Workbook ein Sheet hinzu mit dem inhalt eines
	 * iExcelMatrixArrays
	 * 
	 * @param workbook
	 * @param sheetName
	 * @param iExcelMatrixs
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws UnknownCellStyleException
	 */
	public void addExcelSheetFromExcelMatrixClass(String sheetName, IExcelMatrix[] iExcelMatrixs) throws IOException, DataFormatException, UnknownCellStyleException
	{
		int cellStyleInt = CELL_STYLE_NONE;
		addExcelSheetFromExcelMatrixClass(this.workbook, sheetName, iExcelMatrixs, cellStyleInt);
		// addExcelSheetFromExcelMatrixClass(this.workbook, sheetName,
		// iExcelMatrixs, cellStyleInt);
	}

	/**
	 * Fuegt einem bestehendem XSSF-Workbook ein Sheet hinzu mit dem inhalt eines
	 * iExcelMatrixArrays
	 * 
	 * @param sheetName
	 * @param iExcelMatrixs
	 * @param cellStyleInt
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws UnknownCellStyleException
	 */
	public void addExcelSheetFromExcelMatrixClass(String sheetName, IExcelMatrix[] iExcelMatrixs, int cellStyleInt) throws IOException, DataFormatException, UnknownCellStyleException
	{
		addExcelSheetFromExcelMatrixClass(this.workbook, sheetName, iExcelMatrixs, cellStyleInt);
	}

	/**
	 * Fuegt einem bestehendem XSSF-Workbook ein Sheet hinzu mit dem inhalt eines
	 * iExcelMatrixArrays
	 * 
	 * @param workbook
	 * @param sheetName
	 * @param iExcelMatrixs
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws UnknownCellStyleException
	 */
	public void addExcelSheetFromExcelMatrixClass(Workbook workbook, String sheetName, IExcelMatrix[] iExcelMatrixs, int cellStyleInt) throws IOException, DataFormatException, UnknownCellStyleException
	{

		Sheet sheet = workbook.createSheet(sheetName);

		int zeile = 0;
		for (int i = 0; i < iExcelMatrixs.length; i++)
		{
			// Excel-Zeile anlegen
			Row row = sheet.createRow(zeile);

			// Der erste Eintrag wird verwendet, um den Tabellenkopf zu
			// erstellen

			IExcelMatrix dataObject = iExcelMatrixs[i];
			ExcelGeneratorValueEntry[] entries = dataObject.getExcelGeneratorValueEntries();
			if (zeile == 0)
			{

				for (int spalte = 0; spalte < entries.length; spalte++)
				{
					ExcelGeneratorValueEntry entry = entries[spalte];
					row.createCell(spalte).setCellValue(entry.getColumnName());
				}

				// Header komplett geschrieben, Zeile rutschen:
				zeile++;
			}
			row = sheet.createRow(zeile);
			for (int spalte = 0; spalte < entries.length; spalte++)
			{

				ExcelGeneratorValueEntry entry = entries[spalte];
				Cell cell = row.createCell(spalte);
				if (cellStyleInt != CELL_STYLE_NONE)
				{
					cell.setCellStyle(getCellStyleFromInt(cellStyleInt));
				}

				/*
				 * Je nach Datentyp wird eine entsprechende Zelle dem
				 * "Worksheet" hinzugefuegt.
				 */
				switch (entry.getDataType())
				{
					case ExcelGeneratorValueEntry.CELL_TYPE_DATE:
						cell.setCellValue(entry.getDateValue());
						cell.setCellStyle(getCellStyleFromInt(CELL_STYLE_DATE_DDMMYYYY));
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_LABEL:
						cell.setCellValue(entry.getStringValue());
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_DOUBLE:
						if (entry.isReturnNullWhenNumberValueZero())
						{
							if (entry.getDoubleValue() != 0)
								cell.setCellValue(entry.getDoubleValue());
						}else
						{
							cell.setCellValue(entry.getDoubleValue());	
						}
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_FLOAT:
						if (entry.isReturnNullWhenNumberValueZero())
						{
							if (entry.getFloatValue() != 0)
								cell.setCellValue(entry.getFloatValue());
						}else
						{
							cell.setCellValue(entry.getFloatValue());
						}
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_INT:
						if (entry.isReturnNullWhenNumberValueZero())
						{
							if (entry.getIntValue() != 0)
								cell.setCellValue(entry.getIntValue());
						}else
						{
							cell.setCellValue(entry.getIntValue());
						}
						break;
						
					case ExcelGeneratorValueEntry.CELL_TYPE_BOOLEAN:
						if (entry.isReturnNullWhenNumberValueZero())
						{
							if (entry.isBooleanValue())
							{
								cell.setCellValue(entry.isBooleanValue());
							}
						}else
						{
							cell.setCellValue(entry.isBooleanValue());
						}
							
						break;
						
						
					default:
						throw new DataFormatException();
				}
			}
			zeile++;
		}
	}

	/**
	 * Ermittelt, ob ein Sheet existiert oder nicht, indem eines angefordert
	 * wird (get). Wenn die Rueckgabe NULL ist, wird false zurueckgegeben.
	 * 
	 * @param sheetName
	 * @return
	 */
	public boolean existSheetInWorkbook(String sheetName)
	{
		Sheet sheet = this.workbook.getSheet(sheetName);
		boolean existSheet = (sheet != null);
		return existSheet;
	}

	/**
	 * Ermittelt, ob ein Sheet existiert oder nicht, indem eines angefordert
	 * wird (get). Wenn die Rueckgabe NULL ist, wird false zurueckgegeben.
	 * 
	 * @param sheetName
	 * @return
	 */
	public boolean existSheetInWorkbook(int sheetIndexNumber)
	{
		Sheet sheet = this.workbook.getSheetAt(sheetIndexNumber);
		boolean existSheet = (sheet == null);
		return existSheet;
	}

	/**
	 * Fuegt einen einzelnen Datensatz ExcelGeneratorValueEntry einem Sheet
	 * hinzu.
	 * 
	 * @param sheetName
	 * @param entries
	 * @throws SheetNotFoundException
	 * @throws DataFormatException
	 * @throws UnknownCellStyleException 
	 */
	public void addEntryToSheet(String sheetName, IExcelMatrix iExcelMatrix) throws SheetNotFoundException, DataFormatException, UnknownCellStyleException
	{
		if (existSheetInWorkbook(sheetName))
		{
			ExcelGeneratorValueEntry[] entries = iExcelMatrix.getExcelGeneratorValueEntries();
			Sheet sheet = this.workbook.getSheet(sheetName);
			int lastRowNum;
			lastRowNum = sheet.getLastRowNum();

			Row row;
			/*
			 * Erste Zeile schreibt Header
			 */
			if (lastRowNum == 0)
			{
				row = sheet.createRow(lastRowNum);
				for (int spalte = 0; spalte < entries.length; spalte++)
				{
					ExcelGeneratorValueEntry entry = entries[spalte];
					row.createCell(spalte).setCellValue(entry.getColumnName());
				}

				// Header komplett geschrieben, Zeile rutschen:
			}
			int nextRowNum = lastRowNum + 1;
			/*
			 * Datensatz schreiben
			 */
			row = sheet.createRow(nextRowNum);
			for (int spalte = 0; spalte < entries.length; spalte++)
			{

				ExcelGeneratorValueEntry entry = entries[spalte];
				Cell cell = row.createCell(spalte);
				if (entry.getCellStyleInt() != ExcelGeneratorValueEntry.CELL_STYLE_NONE)
				{
					cell.setCellStyle(getCellStyleFromInt(entry.getCellStyleInt()));
				}

				/*
				 * Je nach Datentyp wird eine entsprechende Zelle dem
				 * "Worksheet" hinzugefuegt.
				 */
				switch (entry.getDataType())
				{
					case ExcelGeneratorValueEntry.CELL_TYPE_DATE:
						cell.setCellStyle(dateCellStyle);
						cell.setCellValue(entry.getDateValue());
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_LABEL:
						cell.setCellValue(entry.getStringValue());
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_DOUBLE:
						cell.setCellValue(entry.getDoubleValue());
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_FLOAT:
						cell.setCellValue(entry.getFloatValue());
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_INT:
						cell.setCellValue(entry.getIntValue());
						break;
					default:
						throw new DataFormatException();
				}
			}

		} else
		{
			throw new SheetNotFoundException(sheetName);
		}

	}

	/**
	 * Optimiert die Breite der ersten 25 Spalten
	 * 
	 * @param sheetName
	 * @throws SheetNotFoundException
	 */
	public void autoFitColumnsOfSheet(String sheetName) throws SheetNotFoundException
	{
		if (existSheetInWorkbook(sheetName))
		{
			Sheet sheet = this.workbook.getSheet(sheetName);
			for (int i = 0; i < 25; i++)
			{
				sheet.autoSizeColumn(i);
			}

		} else
		{
			throw new SheetNotFoundException(sheetName);
		}
	}

	/**
	 * @return the workbook
	 */
	public Workbook getWorkbook()
	{
		return workbook;
	}

	/**
	 * @return the dateCellStyle
	 */
	public CellStyle getDateCellStyle()
	{
		return dateCellStyle;
	}

	/**
	 * @return the currencyCellStyle
	 */
	public CellStyle getCurrencyCellStyle()
	{
		return currencyCellStyle;
	}

	/**
	 * @return the headerCellStyle
	 */
	public CellStyle getHeaderCellStyle()
	{
		return headerCellStyle;
	}

	/**
	 * @return the borderCellStyle
	 */
	public CellStyle getBorderCellStyle()
	{
		return borderCellStyle;
	}

}
