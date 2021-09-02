package rentasad.library.basicTools.ftpUtil.Listener;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import rentasad.library.basicTools.dateTool.DateTools;
import rentasad.library.basicTools.ftpUtil.FTPConnection;
import rentasad.library.basicTools.ftpUtil.objects.FtpFileStatus;
import rentasad.library.basicTools.ftpUtil.objects.IFTPKonfigurationSheetParameter;


/**
 *
 * Gustini GmbH (2015)
 * Creation: 22.10.2015
 * Updated: 27.11.2018
 * Rentasad Library
 * rentasad.lib.tools.ftputil.Listener
 *
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 *27.11.2018
 *UploadCheck prüft jetzt auch CRC Checksumme, wenn Remote Datei zu alt ist.
 *Das erspart unerwünschte Benachrichtigungen, wenn lokale Datei sich nicht verändert hat.
 *
 *
 *02.09.2021 -> MUSS in Projekt umgezogen werden wo es gebraucht wird --> Hier werden Methoden ausgeklammert
 */
 @SuppressWarnings("unused")
@Deprecated
public class FtpStatusEmailAlertProvider
{
    // Empfaenger der E-Mail
    ArrayList<String> emailRecipients = new ArrayList<String>();
    // SMTP Zugangsdaten
    String smtpServer, smtpPort, smtpUser, smtpPassword;

//    /**
//     * Prueft die FTPFileStatusObjekte gemaess ihrer hinterlegten Regeln und
//     * reagiert entsprechend darauf.
//     *
//     * @param fileStatusCollection
//     * @param mySqlSettingsMap
//     * @throws EmailException
//     * @throws IOException
//     * @throws SQLException
//     */
//    public void checkFileStatusCollection(Collection<FtpFileStatus> fileStatusCollection, Map<String, String> configMap, Map<String, String> mySqlSettingsMap) throws IOException, EmailException, SQLException
//    {
//
//        String mailSendActivatedString = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_SEND_ACTIVATED);
//        String mailAuthentivationActivatedString = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_AUTHENTICATION_ACTIVATED);
//        String ftpFileMustExistString = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_FTP_FILE_MUST_EXIST);
//        boolean mailSendActivated = Boolean.valueOf(mailSendActivatedString);
//        boolean mailAuthentivationActivated = Boolean.valueOf(mailAuthentivationActivatedString);
//        boolean ftpFileMustExist = Boolean.valueOf(ftpFileMustExistString);
//        boolean mailSendNeeded = false;
//        boolean semaphoreSet = false;
//        // Meldungen ueber Artikel werden im Array abgelegt.
//        ArrayList<String> meldungenArrayList = new ArrayList<String>();
//        for (Iterator<FtpFileStatus> iterator = fileStatusCollection.iterator(); iterator.hasNext();)
//        {
//            FtpFileStatus ftpFileStatus = (FtpFileStatus) iterator.next();
//            int maxAgeMin = ftpFileStatus.getMaxAgeInMinutes();
//
//            /*
//             * 1. Prüfen ob Semaphore existiert und eventuell den Import blockiert
//             */
//            semaphoreSet = ftpFileStatus.isSemaphoreExist();
//            if (semaphoreSet)
//            {
//                mailSendNeeded = true;
//                meldungenArrayList.add("\nSEMAPHORE BLOCKIERT UPLOAD");
//                meldungenArrayList.add("Bitte prüfen Sie bei wiederholter Meldung, ob die Semaphore den Artikelipload blockiert!");
//            }
//
//            /*
//             * 2. Pruefen ob Datei existiert - muss auf beiden existieren.
//             */
//            if (ftpFileStatus.isExistFTPFile() || !ftpFileMustExist)
//            {
//
//                if (ftpFileStatus.isExistLocalFile())
//                {
//
//                    int timeZoneDifferenceFtpServerInMinutes = ftpFileStatus.getFtpServerTimeDifference();
//
//                    int ftpFileAgeInMinutes = 0;
//                    if (ftpFileMustExist)
//                        ftpFileAgeInMinutes = DateTools.getMinutesFromMilliseconds(ftpFileStatus.getAgeOfFtpFile()) + timeZoneDifferenceFtpServerInMinutes;
//                    int localFileAgeInMinutes = DateTools.getMinutesFromMilliseconds(ftpFileStatus.getAgeOfLocalFile());
//
//                    if (localFileAgeInMinutes > maxAgeMin)
//                    {// Lokale Datei ist zu alt
//                        mailSendNeeded = true;
//                        meldungenArrayList.add(String.format("\nDateiname: %s", ftpFileStatus.getFileName()));
//                        meldungenArrayList.add("Existiert lokal: JA, FTP: JA");
//                        meldungenArrayList.add(String.format("Alter in Minuten Lokal: %d, FTP: %d", localFileAgeInMinutes, ftpFileAgeInMinutes));
//                        meldungenArrayList.add(String.format("FEHLER: LOKALE DATEI ZU ALT (MAX: %d Minuten, Alter: %d Minuten", maxAgeMin, localFileAgeInMinutes));
//                    }
//                    if (ftpFileAgeInMinutes > maxAgeMin)
//                    {// REMOTE Datei ist zu alt
//                     // Inhaltlich prüfen ob diese anders als die REMOTE-Datei ist
//
//                        /*
//                         * Lokale Datei existiert --> Aktualität mit DB abgleichen
//                         * Wenn Dateien remote und lokal identisch, dann muss keine Benachrichtigung gesendet werden, weil es keine Änderungen an der Datenbasis gibt
//                         */
//                        Long crcChecksumLastUpload = getCrcChecksumFromDatabase(ftpFileStatus.getLocalFile().getName(), mySqlSettingsMap);
//                        Long actualLCrcLocal = FTPConnection.getCRCFromLocalFile(ftpFileStatus.getLocalFile().getAbsolutePath());
//                        System.out.println(String.format("Lokal: %d - Remote: %d", actualLCrcLocal, crcChecksumLastUpload));
//                        if (!crcChecksumLastUpload.equals(actualLCrcLocal))
//                        {
//                            /**
//                             * Dateien lokal und Remote sind unterschiedlich --> sie müssten aktuell sein --> Fehlermeldung senden!
//                             */
//                            mailSendNeeded = true;
//                            meldungenArrayList.add(String.format("*************************************************", ftpFileStatus.getFileName()));
//                            meldungenArrayList.add(String.format("\nDateiname: %s", ftpFileStatus.getFileName()));
//                            meldungenArrayList.add(String.format("*************************************************", ftpFileStatus.getFileName()));
//                            meldungenArrayList.add("\nExistiert lokal: JA, FTP: JA");
//                            meldungenArrayList.add(String.format("\nAlter in Minuten Lokal: %d, FTP: %d", localFileAgeInMinutes, ftpFileAgeInMinutes));
//                            meldungenArrayList.add(String.format("\nFEHLER: FTP-DATEI ZU ALT (MAX: %d Minuten, Alter: %d Minuten", maxAgeMin, ftpFileAgeInMinutes));
//                        }
//                    }
//                } else
//                {
//                    mailSendNeeded = true;
//                    // localArchivDir + System.getProperty("file.separator")
//                    meldungenArrayList.add(String.format("\nLokale Datei existiert nicht: %s", ftpFileStatus.getFileName()));
//                }
//            } else
//            {
//                mailSendNeeded = true;
//                meldungenArrayList.add(String.format("FTP-Datei existiert nicht: %s", ftpFileStatus.getFileName()));
//            }
//
//        }
//        if (mailSendNeeded)
//        {
//            String mailBody = new String();
//            mailBody += "Gustini FTPUploadCheck mit E-Mailbenachrichtigung";
//            mailBody += "\n Diese E-Mail wird Ihnen gesendet, weil der Upload der Dateien nicht mehr aktuell ist";
//            mailBody += "\n oder an anderer Stelle fehlschlaegt";
//            mailBody += "\n";
//            mailBody += "\n Bitte pruefen Sie den Upload und Export der Dateien";
//            mailBody += "\n";
//            String meldung = new String("");
//            for (Iterator<String> iterator = meldungenArrayList.iterator(); iterator.hasNext();)
//            {
//                meldung += (String) iterator.next() + "\n";
//
//            }
//            System.out.println(meldung);
//
//            if (mailSendActivated)
//            {
//                System.out.println("Versende E-Mail mit Statusmeldung");
//                String mailFrom = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_SMTP_FROM);
//                String mailHost = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_SMTP_HOST);
//                String mailTo = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_SMTP_TO);
//                String[] mailToArray = mailTo.split(",");
//                String mailUsername = null;
//                String mailPassword = null;
//                String mailSubject = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_SUBJECT);
//                if (mailAuthentivationActivated)
//                {
//                    mailUsername = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_SMTP_USERNAME);
//                    mailPassword = configMap.get(IFTPKonfigurationSheetParameter.PARAMETER_NAME_MAIL_SMTP_PASSWORT);
//                }
//                String mailText = mailBody + "\n" + meldung;
//                System.out.println(sendeEmail(mailHost, mailUsername, mailPassword, mailFrom, mailToArray, mailSubject, mailText));
//
//            }
//
//        } else
//        {
//            System.out.println("Alle zu pruefenden Dateien sind vorhanden und aktuell.");
//        }
//    }
	/*
	 * Methode wird nicht mehr genutzt, weshalb sie ausgeblendet wird.
	 */
    
    /**
     * 
     * Description:
     * 
     * @param fileNameString
     * @param mySqlConfigMap
     * @return
     *         Creation: 27.11.2018 by mst
     * @throws SQLException
     * @Deprecated
     */
//    public Long getCrcChecksumFromDatabase(final String fileNameString, final Map<String, String> mySqlConfigMap) throws SQLException
//    {
//    	/*
//    	 * Methode wird nicht mehr genutzt, weshalb sie ausgeblendet wird.
//    	 */
//        Connection con = MYSQLConnection.dbConnect(mySqlConfigMap);
//        Long lastHashFromDatabase = null;
//        String countQuery = "SELECT count(*) as anzahl from ftp_uploadLog where fileName like ?";
//        String dataQuery = "SELECT fileHash from ftp_uploadLog where fileName like ?";
//
//        java.sql.PreparedStatement countPrepStmt = con.prepareStatement(countQuery);
//        countPrepStmt.setString(1, fileNameString);
//        boolean existUpladFileOnDatabase = false;
//        ResultSet rs = countPrepStmt.executeQuery();
//        if (rs.next())
//        {
//            // Wenn Datei einmal existiert, dann true
//            existUpladFileOnDatabase = rs.getInt("anzahl") == 1;
//        }
//        rs.close();
//        countPrepStmt.close();
//        if (existUpladFileOnDatabase)
//        {
//
//            java.sql.PreparedStatement dataPrepStmt = con.prepareStatement(dataQuery);
//            dataPrepStmt.setString(1, fileNameString);
//            rs = dataPrepStmt.executeQuery();
//            if (rs.next())
//            {
//                lastHashFromDatabase = rs.getLong("fileHash");
//            }
//            rs.close();
//            dataPrepStmt.close();
//        }
//        con.close();
//        return lastHashFromDatabase;
//    }

    /**
     * E-Mail mit Anhang aus Stream mit Apache Commons Email versenden
     * (http://commons.apache.org/proper/commons-email/apidocs/index.html)
     */
    public static String sendeEmail(String mailserver, String username, String password, String absender, String[] empfaengerArray, String betreff, String text) throws IOException, EmailException
    {
        MultiPartEmail email = new MultiPartEmail();
        if (username != null && password != null)
        {
            DefaultAuthenticator defaultAuthenticator = new DefaultAuthenticator(username, password);
            email.setAuthenticator(defaultAuthenticator);
            email.setSSLOnConnect(false);
        }
        email.setHostName(mailserver);
        email.setFrom(absender);
        for (String toItem : empfaengerArray)
        {
            email.addTo(toItem);
        }

        email.setCharset("UTF-8");
        email.setSubject(betreff);
        email.setMsg(text);
        // email.attach(new ByteArrayDataSource(anhangInputStream, anhangContentType), anhangDateiName, anhangBeschreibung, EmailAttachment.ATTACHMENT);
        return email.send();
    }
}
