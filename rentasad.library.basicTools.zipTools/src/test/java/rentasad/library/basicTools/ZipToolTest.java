package rentasad.library.basicTools;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.lingala.zip4j.model.FileHeader;

public class ZipToolTest
{
	boolean startTest;
	final String PATH_TO_7ZIP_EXE = "resources/7z/7z.exe";
	final String ZIP_FILENAME_ENCRYPTED = "resources/tests/zip/zipfile/loremIpsum_encrypted.zip";
	final String ZIP_FILENAME = "resources/tests/zip/zipfile/loremIpsum.zip";
	final String DESTINATION_PATH = "resources/tests/zip/extracted";
	final String password = "raf3g7kivo";
	final String DESTINATION_FILE = DESTINATION_PATH + "/loremIpsum.txt";
	ZipTool tool = new ZipTool(PATH_TO_7ZIP_EXE);
	@BeforeEach
	public void setUp() throws Exception
	{
		String osString = System.getProperty("os.name");
		if (osString.length() < 7)
		{
			startTest = false;
		} else
		{
			String winString = osString.substring(0, 7);
			if (winString.equalsIgnoreCase("Windows"))
			{
				this.startTest = true;
			} else
			{
				this.startTest = false;
			}
		}

	}

	@AfterEach
	/**
	 * 
	 * Description: 
	 * 
	 * @throws Exception
	 * Creation: 09.11.2015 by mst
	 */
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testExtractEncryptedZip() throws Exception
	{
		if (startTest)
		{
			/*
			 * Passwort fuer ZIP-Datei grAEfin-11
			 */
			// String os = System.getProperty("os.name");
			System.out.println();
			
			
			
			ZipTool tool = new ZipTool(PATH_TO_7ZIP_EXE);
			tool.extractEncryptedZip(ZIP_FILENAME_ENCRYPTED, DESTINATION_PATH, password);
			boolean exist = new File(DESTINATION_PATH + "/loremIpsum.txt").exists();
			File extractedFile = new File(DESTINATION_PATH + "/loremIpsum.txt");
			if (extractedFile.exists())
			{
				extractedFile.delete();
			}
			assertTrue(exist);
		}

	}

//	@Test
//	public void testExtractEncryptedAES256ZipFile() throws Exception
//	{
//		/*
//		 * Passwort fuer ZIP-Datei 123456
//		 */
//		if (startTest)
//		{
//			String zipFileName = "ressources/Gustini/Library/tools/FileTools/encryptedAES-256.zip";
//			String destinationPath = "ressources/Gustini/Library/tools/FileTools";
//			String password = "123456";
//			ZipTool.extractZipFile(zipFileName, destinationPath, password);
//			boolean exist = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv").exists();
//			File extractedFile = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv");
//			if (extractedFile.exists())
//			{
//				extractedFile.delete();
//			}
//			assertTrue(exist);
//		}
//	}

	@Test
	public void testExtractEncryptedZipCryptoZipFile() throws Exception
	{
		if (startTest)
		{
			/*
			 * Passwort fuer ZIP-Datei 123456
			 */

			ZipTool.extractZipFile(ZIP_FILENAME, DESTINATION_PATH, password);
			boolean exist = new File(DESTINATION_FILE).exists();
			File extractedFile = new File(DESTINATION_FILE);
			if (extractedFile.exists())
			{
				extractedFile.delete();
			}
			assertTrue(exist);
		}
	}

	@Test
	public void testExtractNotEncryptedZipFile() throws Exception
	{
		if (startTest)
		{
			String zipFileName = "resources/tests/zip/zipfile/loremIpsum.zip";
			String destinationPath = "resources/tests/zip/extracted";
			
			ZipTool.extractZipFile(zipFileName, destinationPath);
			String extractedFileName = "resources/tests/zip/extracted/loremIpsum.txt";
			boolean exist = new File(extractedFileName).exists();
			File extractedFile = new File(extractedFileName);
			if (extractedFile.exists())
			{
				extractedFile.delete();
			}
			assertTrue(exist);
		}
	}

	@Test
	public void testGetFileHeadersFromZipFile() throws Exception
	{
		if (startTest)
		{
			boolean success = false;
			
			FileHeader[] headers = ZipTool.getFileHeadersFromZipFile(ZIP_FILENAME_ENCRYPTED);
			FileHeader fileheader = headers[0];
			String fileName = fileheader.getFileName();
			if (fileName.equalsIgnoreCase("loremIpsum.txt"))
			{
				success = true;
			}
			assertTrue(success);
		}
	}

}
