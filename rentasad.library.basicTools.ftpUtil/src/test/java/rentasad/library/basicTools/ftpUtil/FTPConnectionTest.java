package rentasad.library.basicTools.ftpUtil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.jupiter.api.Test;

import rentasad.library.basicTools.ftpUtil.Exceptions.FtpLoginException;
import rentasad.library.basicTools.ftpUtil.objects.FtpSettings;

public class FTPConnectionTest
{
	/**
	 * Here you should configure the TEST FTP-SERVER for unti testing
	 */
	private final String FTP_HOSTNAME = "";
	private final String FTP_USERNAME = "";
	private final String FTP_PASSWORD = "";
	private final Integer FTP_PORT = 21;
	
	
    FTPConnection ftpConnection = null;;
    boolean startTest;

    // @BeforeAll
    /**
     * Only test if test system is windows based
     * @throws Exception
     */
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

    // @AfterAll
    public void tearDown() throws Exception
    {
        if (this.ftpConnection != null)
        {
            this.ftpConnection.disconnect();
        }
    }

    // @Test
    public void testGetFTPFileList() throws Exception
    {
        if (startTest)
        {
            /* Richtige FTP Zugangsdaten */
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);

            assertNotNull(ftpConnection.getFTPFileList(), "Objekt wird Null zuraeckgegeben");

            // throw new RuntimeException("not yet implemented");
        }
    }

    // @Test(expected = FtpLoginException.class)
    public void testGetFTPFileListWrongUsername() throws Exception
    {
        if (startTest)
        {
            /* Falscher Benutzername */
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);

            assertNotNull(ftpConnection.getFTPFileList(), "Objekt wird Null zuraeckgegeben");

            // throw new RuntimeException("not yet implemented");
        } else
        {
            throw new FtpLoginException("TEST DEAKTIVIERT - Exception fuer erwartetes Ergebnis geworfen");
        }
    }

    // @Test(expected = FtpLoginException.class)
    public void testGetFTPFileListWrongPassword() throws Exception
    {
        if (startTest)
        {
            /* Falscher Benutzername */
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);

            assertNotNull(ftpConnection.getFTPFileList());

            // throw new RuntimeException("not yet implemented");
        } else
        {
            throw new FtpLoginException("TEST DEAKTIVIERT - Exception fuer erwartetes Ergebnis geworfen");
        }
    }

    // @Test(expected = UnknownHostException.class)
    public void testGetFTPFileListWrongHostname() throws Exception
    {
        if (startTest)
        {
            /* Falscher Benutzername */
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);
            assertNotNull(ftpConnection.getFTPFileList());

            // throw new RuntimeException("not yet implemented");
        } else
        {
            throw new UnknownHostException();
        }
    }

    // @Test
    public void testConnect() throws SocketException, IOException, FtpLoginException
    {
        if (startTest)
        {
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);
            assertTrue(ftpConnection.connect(), "Verbindung fehlgeschlagen mit " + settings.getFtpHost());
            ftpConnection.disconnect();
        }
    }

    // @Test
    public void testDownloadCorrectParameters() throws Exception
    {
        if (startTest)
        {
            /*
             * Richtige FTP Zugangsdaten
             * Test Download einer Datei
             */
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);

            List<FTPFile> ftpFiles = ftpConnection.getFTPFileList();
            FTPFile file = ftpFiles.get(0);
            assertTrue(this.ftpConnection.download("g:/transfer/" + file.getName(), file), "Download fehlgeschlagen");
        }
    }

    // @Test
    public void testDownloadNotExistentFile() throws Exception
    {
        if (startTest)
        {
            /*
             * Richtige FTP Zugangsdaten
             * Test Download einer Datei
             */
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);

            List<FTPFile> ftpFiles = ftpConnection.getFTPFileList();
            FTPFile file = ftpFiles.get(0);
            file.setName("WRONG.TXT");
            assertFalse(this.ftpConnection.download("ressources/" + file.getName(), file), "Falscher Raeckgabewert");
        }
    }

    // @Test
    public void testUpload() throws Exception
    {
//        if (startTest)
//        {
//            // throw new RuntimeException("not yet implemented");
//            FtpSettings settings = new FtpSettings();
//        settings.setFtpPassword(FTP_PASSWORD);
//        settings.setFtpPort(FTP_PORT);
//        settings.setFtpHost(FTP_HOSTNAME);
//        settings.setFtpUsername(FTP_USERNAME);
//            this.ftpConnection = new FTPConnection(settings);
//            System.out.println("aebertrag auftrag.xml");
//            assertTrue(this.ftpConnection.upload("g:/Abteilungen/IT/05 Development/Java Workspace Gustini/Rentasad Library/test/Daten/FTP/auftrag/auftrag.xml", "auftrag.xml"));
//
//        }
    }

    @Test
    public void testExistFile() throws Exception
    {
        if (startTest)
        {
//            FtpSettings settings = new FtpSettings();
//            settings.setFtpPassword(FTP_PASSWORD);
//            settings.setFtpPort(FTP_PORT);
//            settings.setFtpHost(FTP_HOSTNAME);
//            settings.setFtpUsername(FTP_USERNAME);
//            this.ftpConnection = new FTPConnection(settings);
//            ftpConnection.connect();
//            String filename = "export_article_ean.csv";
//            System.out.println(filename);
//            try
//            {
//                assertTrue(this.ftpConnection.existFile(filename));
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            ftpConnection.disconnect();
        }
    }

    // @Test
    /**
     * Nicht existierendes File
     * 
     * @throws Exception
     */
    public void testExistFileNotExistent() throws Exception
    {
        if (startTest)
        {
            FtpSettings settings = new FtpSettings();
            settings.setFtpPassword(FTP_PASSWORD);
            settings.setFtpPort(FTP_PORT);
            settings.setFtpHost(FTP_HOSTNAME);
            settings.setFtpUsername(FTP_USERNAME);
            this.ftpConnection = new FTPConnection(settings);
            System.out.println("aebertrag auftrag.xml");
            try
            {
                assertFalse(this.ftpConnection.existFile("auftrag2.xml"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    // @Test(expected = FileNotFoundException.class)
    /**
     * (expected = IOException.class)
     * 
     * @throws Exception
     */
    public void testGetCRCFromInputStreamFileNotExist() throws Exception
    {
        if (startTest)
        {
            String fileName = "g:/lala.gfx";
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            Long crcLong = Long.valueOf(FTPConnection.getCRCFromInputStream(fileInputStream));
            assertNotNull(crcLong);
        } else
        {
            throw new FileNotFoundException("TEST DEAKTIVIERT - Exception fuer erwartetes Ergebnis geworfen");
        }
    }

    // g:/Abteilungen/IT/05 Development/Java Workspace Gustini/Rentasad Library/test/Daten/FTP/auftrag/Adressen.xml
    // @Test
    /**
     * @throws Exception
     */
    public void testGetCRCFromInputStreamFileExist() throws Exception
    {
        if (startTest)
        {
            String fileName = "g:/Abteilungen/IT/05 Development/Java Workspace Gustini/Rentasad Library/test/Daten/FTP/auftrag/Adressen.xml";
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            Long crcLong = null;
            crcLong = Long.valueOf(FTPConnection.getCRCFromInputStream(fileInputStream));
            System.out.println(crcLong);
            // assertNotNull(crcLong);
        }
    }

    // @Test
    /**
     * 
     * Description: Ermittelt SHA1 CRC von FTP-File wenn FTP-Server das unterstützt
     * 
     * @throws Exception
     *             Creation: 27.11.2018 by mst
     */
    public void testGetShaHashsumCrcFromFtpFile() throws Exception
    {
        FtpSettings settings = new FtpSettings();
        settings.setFtpPassword(FTP_PASSWORD);
        settings.setFtpPort(FTP_PORT);
        settings.setFtpHost(FTP_HOSTNAME);
        settings.setFtpUsername(FTP_USERNAME);
        this.ftpConnection = new FTPConnection(settings);
        ftpConnection.connect();
        String filename = "export_article_ean.csv";
        String[] shaStringArray = this.ftpConnection.getShaHashsumFromFtpFile(filename);
        for (String sha : shaStringArray)
        {
            System.out.println(String.format("%s: %s", filename, sha));
        }
        ftpConnection.disconnect();

    }

    @Test
    public void testUploadWithCrc() throws Exception
    {
        FtpSettings settings = new FtpSettings();
        settings.setFtpPassword(FTP_PASSWORD);
        settings.setFtpPort(FTP_PORT);
        settings.setFtpHost(FTP_HOSTNAME);
        settings.setFtpUsername(FTP_USERNAME);
        this.ftpConnection = new FTPConnection(settings);
        ftpConnection.connect();
        String filename = "resources/unittest/crcValueFileTest.csv";
        File file = new File(filename);
        System.out.println(file.getAbsolutePath());
        String remoteResultFileName = "crcValueFileTest.csv";

        boolean result = this.ftpConnection.uploadWithCrc(filename, remoteResultFileName);
        assertTrue(result);

        ftpConnection.disconnect();
    }

    @Test
    public void testGetCrcFromRemoteFtpFile() throws Exception
    {
        FtpSettings settings = new FtpSettings();
        settings.setFtpPassword(FTP_PASSWORD);
        settings.setFtpPort(FTP_PORT);
        settings.setFtpHost(FTP_HOSTNAME);
        settings.setFtpUsername(FTP_USERNAME);
        this.ftpConnection = new FTPConnection(settings);
        ftpConnection.connect();

        String remoteResultFileName = "crcValueFileTest.csv";
        String remoteResultCrcFileName = "crcValueFileTest.csv.crc";
        FTPFile ftpFile = this.ftpConnection.getFtpFileHashTable().get(remoteResultFileName);
        FTPFile ftpFileCrc = this.ftpConnection.getFtpFileHashTable().get(remoteResultCrcFileName);
        Long result = this.ftpConnection.getCrcFromRemoteFtpFile(ftpFile, ftpFileCrc);
        System.out.println(result);
        assertTrue(result == 952757702);
        ftpConnection.deleteFileFromFtp(remoteResultFileName);
        ftpConnection.deleteFileFromFtp(remoteResultCrcFileName);
        ftpConnection.disconnect();
    }
}
