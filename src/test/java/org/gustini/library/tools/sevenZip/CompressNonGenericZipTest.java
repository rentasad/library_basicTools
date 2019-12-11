package org.gustini.library.tools.sevenZip;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompressNonGenericZipTest
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
	public void testCompress() throws Exception
	{
		String pathToCompress = "resources\\untiTest\\7zip";
		String fileToCompress = pathToCompress + File.pathSeparator + "2019-12-09_Rechnungen_GUSTINI.pdf";
		
	
		CompressNonGenericZip zip = new CompressNonGenericZip();
//		zip.compress(fileToCompress);
	}

}
