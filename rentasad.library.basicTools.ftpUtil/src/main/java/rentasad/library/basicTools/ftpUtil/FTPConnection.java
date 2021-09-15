package rentasad.library.basicTools.ftpUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import rentasad.library.basicTools.NumberTools;
import rentasad.library.basicTools.ftpUtil.Exceptions.FtpLoginException;
import rentasad.library.basicTools.ftpUtil.objects.FtpSettings;

public class FTPConnection
{
    private ArrayList<String> messageLog = new ArrayList<String>();
    private boolean debug = false;
    private FtpSettings ftpSettings;
    private FTPClient ftpClient = new FTPClient();
    private boolean verbose = true;
    private boolean showMessages = true;

    public FTPConnection(
                         FtpSettings ftpSettings)
    {
        super();
        this.ftpSettings = ftpSettings;
        this.ftpClient.setControlKeepAliveTimeout(20);
    }

    public long getFileSize(String filePath) throws Exception
    {
        long fileSize = 0;
        FTPFile[] files = ftpClient.listFiles(filePath);

        if (files.length == 1 && files[0].isFile())
        {
            fileSize = files[0].getSize();
        }
        // Log.i(TAG, "File size = " + fileSize);
        return fileSize;
    }

    public Long getCrcFromRemoteFtpFile(final FTPFile ftpFile, final FTPFile ftpFileCrc) throws IOException, FtpLoginException
    {
        File localTempCrcFile = File.createTempFile("crc_", ".crc");
        boolean downloadSuccess = download(localTempCrcFile.getAbsolutePath(), ftpFileCrc);
        if (downloadSuccess)
        {
            BufferedReader br = new BufferedReader(new FileReader(localTempCrcFile));

            String line;
            if ((line = br.readLine()) != null)
            {

                if (NumberTools.isNumericLong(line))
                {
                    Long crcLong =  Long.valueOf(line);
                    br.close();
                    localTempCrcFile.deleteOnExit();
                    return crcLong;
                }else
                {
                    br.close();
                    return null;
                }

            }else
            {
                br.close();
                return null;
            }
        } else
        {
            return null;
        }
    }

    /**
     * 
     * Description:
     * 
     * @param ftpFilename
     * @return
     * @throws IOException
     *             Creation: 27.11.2018 by mst
     */
    public String[] getShaHashsumFromFtpFile(String ftpFilename) throws IOException
    {
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("XSHA1", ftpFilename)))
        {
            String[] reply = ftpClient.getReplyStrings();
            return reply;
        } else
            return null;
    }

    public boolean createSemaphoreInFtpRootDirectory() throws IOException, FtpLoginException
    {
        String tempDir = System.getProperty("java.io.tmpdir");
        String semaphoreFileName = tempDir + File.separator + "LOCK";
        PrintWriter writer = new PrintWriter(semaphoreFileName, "UTF-8");
        writer.println("This is a semphore File");
        writer.println("Until this File is visibility the files of this folder will be updated");
        writer.close();
        File semaphoreFile = new File(semaphoreFileName);
        if (semaphoreFile.exists())
        {
            System.out.println("UPLOAD SEMAPHORE");
            return upload(semaphoreFileName, semaphoreFile.getName());
        } else
        {
            System.err.println("Fehler beim Erzeugen der Semaphore");
            return false;
        }
    }

    public boolean removeSemaphoreInFtpRootDirectory() throws IOException, FtpLoginException
    {
        if (existFile("LOCK"))
        {
            System.out.println("REMOVE SEMAPHORE");
            return deleteFileFromFtp("LOCK");
        } else
        {
            System.err.println("Semaphore existiert nicht auf FTP-Server");
            return false;
        }
    }

    public boolean existSemaphoreInFtpRootDirectory() throws IOException, FtpLoginException
    {
        return existFile("LOCK");
    }

    /**
     * Prueft ob die FTP-Verbindung bereits besteht und baut sie gegebenenfalls
     * auf
     *
     * @return
     * @throws SocketException
     * @throws IOException
     * @throws rentasad.lib.tools.ftputil.Exceptions.FtpLoginException
     */
    public boolean connect() throws SocketException, IOException, FtpLoginException
    {

        // int replyCode = -9999;

        if (this.ftpClient.isConnected() == false)
        {

            this.ftpClient.connect(this.ftpSettings.getFtpHost(), this.ftpSettings.getFtpPort());
            if (this.showMessages)
            {
            	ftpClient.enterLocalPassiveMode();
                System.out.println(ftpClient.getReplyString());
                this.messageLog.add(ftpClient.getReplyString());
            }
            @SuppressWarnings("unused")
            boolean resultOk = true;
            resultOk &= ftpClient.login(this.ftpSettings.getFtpUsername(), ftpSettings.getFtpPassword());
            if (ftpClient.getReplyCode() == FTPReply.NOT_LOGGED_IN)
            {
                this.messageLog.add(ftpClient.getReplyString());
                throw new FtpLoginException(ftpClient.getReplyString());

            }
            if (this.showMessages)
            {
                System.out.println(ftpClient.getReplyString());
                this.messageLog.add(ftpClient.getReplyString());
            }

            // replyCode = this.ftpClient.getReplyCode();
        }
        return this.ftpClient.isConnected();
    }

    public void disconnect() throws IOException
    {

        if (this.ftpClient.isConnected() == true)
        {
            this.ftpClient.disconnect();
        }
    }

    /**
     *
     * Description: Loescht Datei vom FTP-Server
     *
     * @param fileName
     * @return
     *         Creation: 12.08.2015 by mst
     * @throws IOException
     * @throws FtpLoginException
     */
    public boolean deleteFileFromFtp(String fileName) throws IOException, FtpLoginException
    {
        // if (ftpClient.isConnected() == false)
        return this.ftpClient.deleteFile(fileName);
    }

    /**
     *
     * @param ftpSettings
     * @return
     * @throws SocketException
     * @throws IOException
     * @throws FtpLoginException
     */
    public List<FTPFile> getFTPFileList() throws SocketException, IOException, FtpLoginException
    {

        final List<FTPFile> ftpFileList = new ArrayList<FTPFile>();

        // String username = this.ftpSettings.getFtpUsername();
        // String password = this.ftpSettings.getFtpPassword();

        // if (this.ftpClient.isConnected() == false)
        connect();
        if (this.ftpClient.isConnected())
        {
            FTPFile[] ftpFileArray = ftpClient.listFiles();
            for (int i = 0; i < ftpFileArray.length; i++)
            {
                ftpFileList.add(ftpFileArray[i]);
            }

        } else
        {
            System.err.println("Es kam keine FTP-Verbindung zustande");
        }
        return ftpFileList;
    }

    /**
     * Gibt HashTable mit FileNames und FTPFile zurueck
     *
     * @return Hashtable<String, FTPFile>
     * @throws SocketException
     * @throws IOException
     * @throws FtpLoginException
     */
    public Hashtable<String, FTPFile> getFtpFileHashTable() throws SocketException, IOException, FtpLoginException
    {
        Hashtable<String, FTPFile> ftpFileHashTable = new Hashtable<String, FTPFile>();
        List<FTPFile> ftpFileList = getFTPFileList();
        for (@SuppressWarnings("rawtypes")
        Iterator iterator = ftpFileList.iterator(); iterator.hasNext();)
        {
            FTPFile ftpFile = (FTPFile) iterator.next();
            if (debug)
                System.out.println("Name Key: " + ftpFile.getName());
            ftpFileHashTable.put(ftpFile.getName(), ftpFile);
        }
        return ftpFileHashTable;
    }

    public boolean download(final String localResultFile, final FTPFile ftpFile) throws IOException, FtpLoginException
    {
        return download(localResultFile, ftpFile, "");
    }

    public boolean download(final String localResultFilePath, final FTPFile ftpFile, String remoteFtpPath) throws IOException, FtpLoginException
    {
        ftpClient.setBufferSize(1048576);
        FileOutputStream fileOutputStream = null;
        boolean resultOk = true;

        // try
        // {
        this.connect();
        if (!(remoteFtpPath.equals("")) && (remoteFtpPath != null))
        {
            this.changeDir(remoteFtpPath);
        }
        fileOutputStream = new FileOutputStream(localResultFilePath);
        long ftpSize = ftpFile.getSize();
        resultOk &= ftpClient.retrieveFile(ftpFile.getName(), fileOutputStream);
        if (resultOk)
        {
            long localSize = new File(localResultFilePath).length();
            resultOk = (localSize == ftpSize);
        }
        if (showMessages)
        {
            System.out.println(ftpClient.getReplyString());
            this.messageLog.add(ftpClient.getReplyString());
        }
        // resultOk &= ftpClient.logout();
        // if (showMessages)
        // {
        // System.out.println(ftpClient.getReplyString());
        // }
        // }
        // finally
        // {
        // try
        // {
        // if (fileOutputStream != null)
        // {
        // fileOutputStream.close();
        // }
        // } catch (IOException e)
        // {/* nothing to do */
        // }
        // ftpClient.disconnect();
        // }

        return resultOk;

    }

    /**
     * 
     * Description: Generate CRC32 from File to upload, upload CRC Info File with upload file
     * 
     * @param localSourceFileName
     * @param remoteResultFileName
     * @return
     *         Creation: 27.11.2018 by mst
     * @throws IOException
     * @throws FtpLoginException
     */
    public boolean uploadWithCrc(String localSourceFileName, String remoteResultFileName) throws IOException, FtpLoginException
    {
        Long crc = getCRCFromLocalFile(localSourceFileName);
        String crcFilename = localSourceFileName + ".crc";
        PrintWriter writer = new PrintWriter(crcFilename, "UTF-8");
        writer.println(crc.toString());
        writer.close();
        upload(crcFilename, remoteResultFileName + ".crc");
        return upload(localSourceFileName, remoteResultFileName);
    }

    /**
     * FTP-Client-Upload.
     *
     * @return true falls ok
     * @throws FtpLoginException
     */
    public boolean upload(String localSourceFileName, String remoteResultFileName) throws IOException, FtpLoginException
    {
        FileInputStream fis = null;
        boolean resultOk = true;

        try
        {
            connect();
            // ftpClient.setBufferSize(0);
            ftpClient.setBufferSize(1048576);// Beschleunigt FTP-Transfer um hohen Faktor
            fis = new FileInputStream(localSourceFileName);
            if (this.verbose)
                System.out.println("uebertrage Datei " + localSourceFileName + "...");

            resultOk &= ftpClient.storeFile(remoteResultFileName, fis);
            showMessages = false;
            if (showMessages)
            {
                System.out.println(ftpClient.getReplyString());
                this.messageLog.add(ftpClient.getReplyString());
            }

            // resultOk &= ftpClient.logout();
            // if (showMessages)
            // {
            // System.out.println(ftpClient.getReplyString());
            // }
        } finally
        {
            try
            {
                if (fis != null)
                {
                    fis.close();
                    // System.out.println(ftpClient.getReplyString());
                    this.messageLog.add(ftpClient.getReplyString());
                }
            } catch (IOException e)
            {/* nothing to do */
            }
        }

        return resultOk;
    }

    /**
     * Prueft das aktuelle FTP-Verzeichnis darauf ob eine Datei darin existiert.
     * Dies wird durchgefuehrt, indem eine Liste der vorhandenen Dateien erstellt
     * wird.
     * Diese wird anschliessend in einer Schleife durchsucht.
     *
     * @param remotefile
     * @return
     * @throws IOException
     * @throws FtpLoginException
     */
    public boolean existFile(String remotefile) throws IOException, FtpLoginException
    {
        this.connect();
        boolean existFile = false;
        FTPFile[] ftpFiles = this.ftpClient.listFiles();
        for (int i = 0; i < ftpFiles.length; i++)
        {
            System.out.println(ftpFiles[i].getName());
            String name = ftpFiles[i].getName();
            if (name.equals(remotefile))
            {
                existFile = true;
            }
        }

        return existFile;
    }

    /**
     * Gibt eine Checksumme von einem
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static long getCRCFromLocalFile(String fileName) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        return getCRCFromInputStream(fileInputStream);
    }

    public static Date getTimeStampFromLocalFile(String fileName)
    {
        File file = new File(fileName);
        return new Date(file.lastModified());
    }

    /**
     *
     * @param inputStream
     * @return
     * @throws IOException
     */

    public static long getCRCFromInputStream(InputStream inputStream) throws IOException
    {

        CRC32 crc32 = new CRC32();
        int counter;
        while ((counter = inputStream.read()) != -1)
        {
            crc32.update(counter);
        }
        inputStream.close();
        return crc32.getValue();
    }

    public boolean changeDir(String dirString) throws SocketException, IOException, FtpLoginException
    {
        if (connect())
        {
            if (ftpClient.printWorkingDirectory().equalsIgnoreCase(dirString))
            {
                return true;
            } else
            {
                return this.ftpClient.changeWorkingDirectory(dirString);
            }
        } else
        {
            return false;
        }

    }

    /**
     * @return the verbose
     */
    public boolean isVerbose()
    {
        return verbose;
    }

    /**
     * @param verbose
     *            the verbose to set
     */
    public void setVerbose(boolean verbose)
    {
        this.verbose = verbose;
    }

}
