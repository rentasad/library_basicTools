package rentasad.library.basicTools.ftpUtil.objects;
/**
 *
 * Gustini GmbH (2015)
 * Creation: 04.08.2015
 * Rentasad Library
 * rentasad.lib.tools.ftputil
 *
 * @author Matthias Staud
 *
 *
 * Description:FTP Upload/Check ENUM fuer Parameter Namen der Excel-Konfiguration
 *
 */
public interface IFTPKonfigurationSheetParameter
{

	public final static String PARAMETER_NAME_FTP_START_DIR = "ftpStartdir";
	public final static String PARAMETER_NAME_FTP_PASSWORT = "ftpPasswort";
	public final static String PARAMETER_NAME_FTP_HOST = "ftpHost";
	public final static String PARAMETER_NAME_FTP_USERNAME = "ftpUsername";
	public final static String PARAMETER_NAME_FTP_PORT = "ftpPort";
	public final static String PARAMETER_NAME_LOCAL_TEMP_DIR = "localTempDir";
	public final static String PARAMETER_NAME_LOCAL_ARCHIV_DIR = "archivTempDir";
	public final static String PARAMETER_NAME_CHECK_FILES_COUNT = "checkFilesCount";
	public final static String PARAMETER_NAME_CHECK_FILES_BASED_NAME = "FileNameNr";
	public final static String PARAMETER_NAME_CHECK_FILES_MAX_AGE_IN_MINUTES = "FileAgeNr";
	public final static String PARAMETER_NAME_FTP_SERVER_TIME_DIFFERENCE_MINUTES = "ftpServerTimeDifferenceMinutes";
	public final static String PARAMETER_NAME_FTP_FILE_MUST_EXIST = "ftpFileMustExist";
	public final static String PARAMETER_NAME_CRC_CHECK = "CRC_CHECK";
	
	/*
	 * Parameter fuer E-Mail-Versand
	 *
	 */
	public final static String PARAMETER_NAME_MAIL_SEND_ACTIVATED = "sendMailActivated";
	public final static String PARAMETER_NAME_MAIL_SMTP_HOST = "smtpHost";
	public final static String PARAMETER_NAME_MAIL_SMTP_USERNAME = "smtpUsername";
	public final static String PARAMETER_NAME_MAIL_SMTP_PASSWORT = "smtpPasswort";
	public final static String PARAMETER_NAME_MAIL_SMTP_TO = "smtpTo";
	public final static String PARAMETER_NAME_MAIL_SMTP_FROM = "smtpFrom";
	public final static String PARAMETER_NAME_MAIL_AUTHENTICATION_ACTIVATED = "smtpAuthentication";
	public final static String PARAMETER_NAME_KONFIGURATION_SHEET_TYPE = "konfigurationSheetType";
	public final static String PARAMETER_NAME_MAIL_SUBJECT = "smtpSubject";


}
