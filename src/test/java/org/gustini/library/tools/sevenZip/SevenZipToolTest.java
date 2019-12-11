package org.gustini.library.tools.sevenZip;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SevenZipToolTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testZipSingleFile() throws Exception
	{
		String pathToCompress = "resources\\untiTest\\7zip";
		String fileToCompress = pathToCompress + File.separator + "2019-12-09_Rechnungen_GUSTINI.pdf";
		
		File sourceFile = new File(fileToCompress);
		File targetFile = new File(pathToCompress + "\\test.zip");
		
		SevenZipTool.zipSingleFile(sourceFile, targetFile);
	}

	@Test
	public void testSevenZipSingleFile() throws Exception
	{
		String pathToCompress = "resources\\untiTest\\7zip";
		String fileToCompress = pathToCompress + File.separator + "2019-12-09_Rechnungen_GUSTINI.pdf";
		
		File sourceFile = new File(fileToCompress);
		File targetFile = new File(pathToCompress + "\\test.7z");
		
		SevenZipTool.sevenZipSingleFile(sourceFile, targetFile);
	}

}
