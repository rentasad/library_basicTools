package rentasad.library.basicTools.ftpUtil;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FtpCheckUploadToolTest
{

    boolean startTest;

    @BeforeAll
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

    @AfterAll
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testGetFtpFileStatusCollection() throws Exception
    {
//        if (startTest)
//        {
//            final String configFileName = "config/config.ini";
//
//            final String configSection = "ftpUpload";
//            Map<String, String> configMap = ConfigFileTool.readConfiguration(configFileName, configSection);
//            String konfigSheetNamesString = configMap.get("konfigSheets");
//            String[] konfigSheetNamesArray = konfigSheetNamesString.split(",");
//            String pathToExcelConfigFile = configMap.get("pathToExcelKonfigFile");
////            KonfigurationObject ko = FtpCheckUploadTool.getKonfigurationObjectFromExcelFile(pathToExcelConfigFile);
//
//            for (String sheetName : konfigSheetNamesArray)
//            {
//                KonfigurationSheet konfigurationSheet = ko.getKonfigurationSheet(sheetName);
//
//                Collection<FtpFileStatus> ftpFileStatusCollection = FtpCheckUploadTool.getFtpFileStatusCollection(konfigurationSheet);
//                assertNotNull(ftpFileStatusCollection);
//            }
//        }
    }
}
