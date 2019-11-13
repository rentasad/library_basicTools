package org.gustini.library.tools.csvGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.gustini.library.tools.csvGenerator.ICsvGeneratorDataEntry;
import org.gustini.library.tools.exceptions.UnknownFieldIntException;




/**
 * 
 * Gustini GmbH (2018)
 * Creation: 06.11.2015
 * gustini.library.basicTools
 * org.gustini.library.tools.csvGenerator
 * 
 * @author Matthias Staud
 *
 *
 * Description: CSV Generation Tool
 *
 */
public class CSVGenerator
{
	private String trennzeichen;
	private String satzTrenner;
	private boolean writeHeader;

	public CSVGenerator(
						String trennzeichen,
						String satzTrenner,
						boolean writeHeader)
	{
		super();
		this.trennzeichen = trennzeichen;
		this.satzTrenner = satzTrenner;
		this.writeHeader = writeHeader;
	}

	/**
	 * 
	 * Description:  CSV-Datei aus implementierten ICSVGeneratorDataEntry - Interface
	 * 
	 * @param csvFileName
	 * @param valueEntriesList
	 * @return
	 * @throws IOException
	 * @throws UnknownFieldIntException
	 * Creation: 12.11.2018 by mst
	 */
	public boolean generateCSVFromICsvGeneratorDataEntry(final String csvFileName, final List<ICsvGeneratorDataEntry> valueEntriesList) throws IOException, UnknownFieldIntException
	{
	    ICsvGeneratorDataEntry[] valueEntries = valueEntriesList.toArray(new ICsvGeneratorDataEntry[0]);
	    return generateCSVFromICsvGeneratorDataEntry(csvFileName,valueEntries );
	}
	
	
	/**
	 * Schreibt CSV-Datei aus implementierten ICSVGeneratorDataEntry - Interface
	 * 
	 * @param fileName
	 * @param valueEntries
	 * @return
	 * @throws IOException
	 * @throws UnknownFieldIntException 
	 */
	public boolean generateCSVFromICsvGeneratorDataEntry(final String fileName, final ICsvGeneratorDataEntry[] valueEntries) throws IOException, UnknownFieldIntException
	{

		boolean success = false;

		FileWriter fileWriter = new FileWriter(fileName);

		for (int i = 0; i < valueEntries.length; i++)
		{
			ICsvGeneratorDataEntry value = valueEntries[i];
			String[] headerStringArray = value.getCSVHeaderStringArray();

			if (i == 0 && this.writeHeader)
			{
				String headerString = "";
				/*
				 * HEADER der CSV schreiben
				 */
				for (int col = 0; col < headerStringArray.length; col++)
				{
					if (col == 0)
					{
						headerString += this.satzTrenner + headerStringArray[col] + this.satzTrenner;
					} else
					{
						headerString += trennzeichen + this.satzTrenner + headerStringArray[col] + this.satzTrenner;
					}
				}
				fileWriter.write(headerString);
				fileWriter.append('\r');
				fileWriter.append('\n');
			}
			/**
			 * Zeilen schreiben.
			 */
			String zeile = "";
			for (int col = 0; col < headerStringArray.length; col++)
			{
			   
				if (col == 0)
				{
					zeile += this.satzTrenner + value.getValueEntry(col) + this.satzTrenner;
				} else
				{
					zeile += trennzeichen + this.satzTrenner + value.getValueEntry(col) + this.satzTrenner;
				}

			}

			fileWriter.write(zeile);
			fileWriter.append('\r');
			fileWriter.append('\n');
		}
		fileWriter.close();
		File checkFile = new File(fileName);
		success = checkFile.exists();
		return success;
	}

	/**
	 * Schreibt UTF8-konforme CSV-Datei aus implementierten
	 * ICSVGeneratorDataEntry - Interface
	 * 
	 * @param fileName
	 * @param valueEntries
	 * @return boolean ob Erstellung der CSV-Datei geklappt hat.
	 * @throws IOException
	 * @throws UnknownFieldIntException 
	 */
	public boolean generateCSVFromICsvGeneratorDataEntryUTF8(final String fileName, final ICsvGeneratorDataEntry[] valueEntries) throws IOException, UnknownFieldIntException
	{

		boolean success = false;


		OutputStream os = new FileOutputStream(fileName);
		/*
		 * Bytes Prefix faer UTF8-Codierung
		 * 239 EF
		 * 187 BB
		 * 191 BF
		 */
		os.write(239);
		os.write(187);
		os.write(191);

		PrintWriter out = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

		for (int i = 0; i < valueEntries.length; i++)
		{
			ICsvGeneratorDataEntry value = valueEntries[i];
			String[] headerStringArray = value.getCSVHeaderStringArray();

			if (i == 0 && this.writeHeader)
			{
				String headerString = "";
				/*
				 * HEADER der CSV schreiben
				 */
				for (int col = 0; col < headerStringArray.length; col++)
				{
					if (col == 0)
					{
						headerString += this.satzTrenner + headerStringArray[col] + this.satzTrenner;
					} else
					{
						headerString += trennzeichen + this.satzTrenner + headerStringArray[col] + this.satzTrenner;
					}
				}
				out.print(headerString);
				out.append('\n');
			}
			/**
			 * Zeilen schreiben.
			 */
			String zeile = "";
			for (int col = 0; col < headerStringArray.length; col++)
			{
				if (col == 0)
				{
					zeile += this.satzTrenner + value.getValueEntry(col) + this.satzTrenner;
				} else
				{
					zeile += trennzeichen + this.satzTrenner + value.getValueEntry(col) + this.satzTrenner;
				}

			}

			out.print(zeile);
			out.append('\n');
		}
		out.flush();
		out.close();
		File checkFile = new File(fileName);
		success = checkFile.exists();
		return success;
	}
}
