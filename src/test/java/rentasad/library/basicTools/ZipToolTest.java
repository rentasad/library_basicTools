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
			String pathTo7ZipExe = "ressources/Gustini/Library/tools/FileTools/7z/7z";
			String zipFileName = "ressources/Gustini/Library/tools/FileTools/enrypted.csv.zip";
			String destinationPath = "ressources/Gustini/Library/tools/FileTools";
			String password = "grAEfin-11";
			ZipTool tool = new ZipTool(pathTo7ZipExe);
			tool.extractEncryptedZip(zipFileName, destinationPath, password);
			boolean exist = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv").exists();
			File extractedFile = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv");
			if (extractedFile.exists())
			{
				extractedFile.delete();
			}
			assertTrue(exist);
		}

	}

	@Test
	public void testExtractEncryptedAES256ZipFile() throws Exception
	{
		/*
		 * Passwort fuer ZIP-Datei 123456
		 */
		if (startTest)
		{
			String zipFileName = "ressources/Gustini/Library/tools/FileTools/encryptedAES-256.zip";
			String destinationPath = "ressources/Gustini/Library/tools/FileTools";
			String password = "123456";
			ZipTool.extractZipFile(zipFileName, destinationPath, password);
			boolean exist = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv").exists();
			File extractedFile = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv");
			if (extractedFile.exists())
			{
				extractedFile.delete();
			}
			assertTrue(exist);
		}
	}

	@Test
	public void testExtractEncryptedZipCryptoZipFile() throws Exception
	{
		if (startTest)
		{
			/*
			 * Passwort fuer ZIP-Datei 123456
			 */

			String zipFileName = "ressources/Gustini/Library/tools/FileTools/encryptedZipCrypto.zip";
			String destinationPath = "ressources/Gustini/Library/tools/FileTools/";
			String password = "123456";
			ZipTool.extractZipFile(zipFileName, destinationPath, password);
			boolean exist = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv").exists();
			File extractedFile = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv");
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
			String zipFileName = "ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.zip";
			String destinationPath = "ressources/Gustini/Library/tools/FileTools";
			ZipTool.extractZipFile(zipFileName, destinationPath);
			boolean exist = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv").exists();
			File extractedFile = new File("ressources/Gustini/Library/tools/FileTools/O_DVU1201_IZ_1_2201041504_220115_114780.csv");
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
			String zipFileName = "ressources/Gustini/Library/tools/FileTools/enrypted.csv.zip";
			FileHeader[] headers = ZipTool.getFileHeadersFromZipFile(zipFileName);
			FileHeader fileheader = headers[0];
			String fileName = fileheader.getFileName();
			if (fileName.equalsIgnoreCase("O_DVU1201_IZ_1_2201041504_220115_114780.csv"))
			{
				success = true;
			}
			assertTrue(success);
		}
	}

}
