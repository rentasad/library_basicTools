package rentasad.library.tools.fileOperator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileOperatorTest
{

	@Test void getUrlFromResourceFile()
	{
		final String filePath = "testGetUrlFromResourceFile.txt";
		URL url = FileOperator.getUrlFromResourceFile(filePath);
		assertNotNull(url);

	}

	@Test void getFileValueFromResources() throws IOException
	{
		final String filePath = "testGetUrlFromResourceFile.txt";
		String value = FileOperator.getFileValueFromResources(filePath).trim();
		assertEquals("testGetUrlFromResourceFile.txt", value);

	}
}