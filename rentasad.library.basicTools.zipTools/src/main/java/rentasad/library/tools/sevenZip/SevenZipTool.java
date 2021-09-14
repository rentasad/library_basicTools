package rentasad.library.tools.sevenZip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import rentasad.library.tools.sevenZip.CompressArchiveStructure.Item;

/**
 * 
 * @author mst
 *
 */
public class SevenZipTool
{

	/**
	 * 
	 * @param sourceFileToZip
	 * @param targetSevenZipFileName
	 * @throws IOException
	 */
	public static void zipSingleFile(final File sourceFileToZip, File targetSevenZipFileName) throws IOException
	{
		if (sourceFileToZip.exists() && sourceFileToZip.isFile())
		{
			byte[] fileContent = Files.readAllBytes(sourceFileToZip.toPath());

			Item fileToZipItem = new Item(sourceFileToZip.getName(), fileContent);
			Item[] items =
			{ fileToZipItem };
			CompressNonGenericZip zip = new CompressNonGenericZip();
			zip.compress(items ,targetSevenZipFileName.getAbsolutePath());
		} else
		{
			

		}

	}
	

	/**
	 * 
	 * @param sourceFileToZip
	 * @param targetSevenZipFileName
	 * @throws IOException
	 */
	public static void sevenZipSingleFile(final File sourceFileToZip, File targetSevenZipFileName) throws IOException
	{
		if (sourceFileToZip.exists() && sourceFileToZip.isFile())
		{
			byte[] fileContent = Files.readAllBytes(sourceFileToZip.toPath());

			Item fileToZipItem = new Item(sourceFileToZip.getName(), fileContent);
			Item[] items =
			{ fileToZipItem };
			CompressNonGeneric7z zip = new CompressNonGeneric7z();
			zip.compress(items ,targetSevenZipFileName.getAbsolutePath());
		} else
		{
			

		}

	}

	

}
