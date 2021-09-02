package rentasad.library.tools.sevenZip;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class SevenZipToolTest
{
	private final String pathToCompress = "resources\\untiTest\\7zip";
	private final String fileToCompress = pathToCompress + File.separator + "loremIpsum.txt";
	
	@AfterEach
	public void deleteZipFiles()
	{
		File targetFileZip = new File(pathToCompress + "\\test.zip");
		File targetFile7z = new File(pathToCompress + "\\test.7z");
		if (targetFileZip.exists()) targetFileZip.delete();
		if (targetFile7z.exists()) targetFile7z.delete();
	}

	@Test
	public void testZipSingleFile() throws Exception
	{

		
		File sourceFile = new File(fileToCompress);
		File targetFile = new File(pathToCompress + "\\test.zip");
		
		SevenZipTool.zipSingleFile(sourceFile, targetFile);
		assertTrue(targetFile.exists());
	}

	@Test
	public void testSevenZipSingleFile() throws Exception
	{
		
		File sourceFile = new File(fileToCompress);
		File targetFile = new File(pathToCompress + "\\test.7z");
		
		SevenZipTool.sevenZipSingleFile(sourceFile, targetFile);
		assertTrue(targetFile.exists());
	}

}
