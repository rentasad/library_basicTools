package org.gustini.library.tools.sevenZip;

import java.io.File;

public class CompressNonGenericZipTest
{


	@org.junit.jupiter.api.Test
	public void testCompress() throws Exception
	{
		String pathToCompress = "resources\\untiTest\\7zip";
		String fileToCompress = pathToCompress + File.pathSeparator + "2019-12-09_Rechnungen_GUSTINI.pdf";
		
	
		CompressNonGenericZip zip = new CompressNonGenericZip();
//		zip.compress(fileToCompress);
	}

}
