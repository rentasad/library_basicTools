package rentasad.library.basicTools.ftpUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;

import rentasad.library.basicTools.ftpUtil.Exceptions.FtpLoginException;
import rentasad.library.basicTools.ftpUtil.Exceptions.FtpUtilException;
import rentasad.library.basicTools.ftpUtil.objects.FtpFileStatus;
import rentasad.library.basicTools.ftpUtil.objects.FtpSettings;
import rentasad.library.basicTools.ftpUtil.objects.IFTPKonfigurationSheetParameter;
import rentasad.library.configFileTool.ConfigFileToolException;

/**
 *
 * Gustini GmbH (2015)
 * Creation: 11.05.2015
 * Rentasad Library
 * rentasad.lib.tools.ftputil
 *
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 */
public class FtpCheckUploadTool implements IFTPKonfigurationSheetParameter
{

    /**
     *
     * @throws SheetNotFoundException
     * @throws DataTypeNotDefinedException
     */
    private FtpCheckUploadTool()
    {
    }

    /**
     * Liest aus Konfiguration Einstellungen und checkt das Alter der zu
     * pruefenden FTP-Dateien bzw. ob sie vorraetig sind.
     * 
     * @throws FtpUtilException
     *
     * @throws SocketException
     * @throws IOException
     * @throws KonfigurationPropertyEntryNotExistException
     * @throws FtpLoginException
     */
    /**
     * Liest aus Konfiguration Einstellungen und checkt das Alter der zu
     * pruefenden FTP-Dateien bzw. ob sie vorraetig sind.
     * Description:
     * 
     * @param konfigurationSheet
     * @return
     * @throws FtpUtilException
     *             Creation: 09.03.2017 by mst
     */
    public static Collection<FtpFileStatus> getFtpFileStatusCollection(final Map<String, String> configMap) throws FtpUtilException
    {
        boolean debug = false;
        String ftpServer = configMap.get(PARAMETER_NAME_FTP_HOST);
        String ftpUsername = configMap.get(PARAMETER_NAME_FTP_USERNAME);
        String ftpPassword = configMap.get(PARAMETER_NAME_FTP_PASSWORT);
        String localArchivDir = configMap.get(PARAMETER_NAME_LOCAL_ARCHIV_DIR);
        String ftpStartDir = configMap.get(PARAMETER_NAME_FTP_START_DIR);
        String crcCheckNeededString = configMap.get(PARAMETER_NAME_CRC_CHECK);
        Boolean crcCheckNeeded = Boolean.valueOf(crcCheckNeededString);
        String ftpServerTimeDifferenceString = configMap.get(PARAMETER_NAME_FTP_SERVER_TIME_DIFFERENCE_MINUTES);
        int ftpServerTimeDifference = Integer.valueOf(ftpServerTimeDifferenceString);
        FtpSettings ftpSettings = new FtpSettings(ftpServer, ftpUsername, ftpPassword);
        FTPConnection ftpConnection = new FTPConnection(ftpSettings);
        // FTP Verbindung herstellen
        try
        {
            if (ftpConnection.connect())
            {
                if (ftpStartDir.equals("") == false)
                {// Wenn Startverzeichnis definiert wurde in Tabelle
                    boolean changeDirSuccessfull = ftpConnection.changeDir(ftpStartDir);
                    System.out.println("Verzeichniswechsel in " + ftpStartDir + "erfolgreich? " + changeDirSuccessfull);
                }
                final int fileToCheckCount = Integer.valueOf(configMap.get(PARAMETER_NAME_CHECK_FILES_COUNT));
                String[] fileNamesToCheck = new String[fileToCheckCount];
                int[] fileMaxAgeArray = new int[fileToCheckCount];
                boolean existSemaphore = ftpConnection.existSemaphoreInFtpRootDirectory();
                // fileNamenArray aus Konfiguration mit Dateinamen fuellen
                for (int i = 0; i < fileNamesToCheck.length; i++)
                {
                    final String fileNameWithNr = PARAMETER_NAME_CHECK_FILES_BASED_NAME + (i + 1);
                    final String fileMaxAgeWithNr = PARAMETER_NAME_CHECK_FILES_MAX_AGE_IN_MINUTES + (i + 1);
                    if (configMap.containsKey(fileNameWithNr))
                    {
                        String fileNameEntry = configMap.get(fileNameWithNr);
                        String fileMaxAgeEntry = configMap.get(fileMaxAgeWithNr);
                        fileNamesToCheck[i] = fileNameEntry;
                        fileMaxAgeArray[i] = Integer.valueOf(fileMaxAgeEntry);
                    } else
                    {
                        // Fehler im Programm --> Verbindung wird geschlossen.
                        ftpConnection.disconnect();
                        throw new ConfigFileToolException("Parameter nicht gefunden: " + fileNameWithNr);
                    }

                }
                // Pruefen ob Dateien lokal und auf dem FTP-Server existieren
                Collection<FtpFileStatus> fileStatusCollection = new ArrayList<FtpFileStatus>();
                Hashtable<String, FTPFile> ftpFilesHashTable = ftpConnection.getFtpFileHashTable();

                for (int i = 0; i < fileNamesToCheck.length; i++)
                {

                    String fileName = fileNamesToCheck[i];
                    int fileMaxAgeInMinutes = fileMaxAgeArray[i];
                    File localFile = new File(localArchivDir + System.getProperty("file.separator") + fileName);
                    boolean existLocalFile = localFile.exists();

                    // String fileNameWithPath = "/" + ftpStartDir + "/"+ fileName;
                    String fileNameWithPath = fileName;
                    boolean existFtpFile = ftpFilesHashTable.containsKey(fileNameWithPath);
                    FTPFile ftpFile = null;
                    if (existFtpFile)
                    {
                        ftpFile = ftpFilesHashTable.get(fileNameWithPath);

                    }

                    if (debug)
                        System.out.println(fileNameWithPath);
                    if (debug)
                        System.out.println("Lokal existiert:" + existLocalFile + ", FTP existiert: " + existFtpFile);

                    FtpFileStatus fileStatus = new FtpFileStatus(ftpFile, localFile);
                    fileStatus.setFileName(fileNameWithPath);
                    fileStatus.setMaxAgeInMinutes(fileMaxAgeInMinutes);
                    fileStatus.setExistFTPFile(existFtpFile);
                    fileStatus.setExistLocalFile(existLocalFile);
                    fileStatus.setFtpServerTimeDifference(ftpServerTimeDifference);
                    fileStatus.setSemaphoreExist(existSemaphore);
                    fileStatus.setCrcCheckNeeded(crcCheckNeeded);
                    if (existFtpFile)
                    {
                        fileStatus.setTimestampFtpFile(ftpFile.getTimestamp());
                    }
                    if (existLocalFile)
                    {
                        Calendar localFileTimestamp = Calendar.getInstance();
                        localFileTimestamp.setTimeInMillis(localFile.lastModified());
                        fileStatus.setTimestampLocalFile(localFileTimestamp);
                    }

                    fileStatusCollection.add(fileStatus);

                }

                ftpConnection.disconnect();
                return fileStatusCollection;
            } else
            {// Es konnte keine Connection hergestellt werden
                return null;

            }
        } catch (IOException | FtpLoginException | ConfigFileToolException e)
        {
            throw new FtpUtilException(e);
        }
    }

}
