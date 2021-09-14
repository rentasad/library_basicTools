package rentasad.library.basicTools.emailSendTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import rentasad.library.basicTools.StringTool;
import rentasad.library.basicTools.emailSendTool.objects.EmailConfigObject;

/**
 *
 * Gustini GmbH (2015) Creation: 22.10.2015 Rentasad Library
 * rentasad.lib.tools.emailTool
 *
 * @author Matthias Staud
 *
 *
 *         Description: Mail send Tool based on org.apache.commons.mail
 *
 */
public class EmailSendTool
{
	private int port = 25;
	public static int PORT = 25;

	/**
	 *
	 * Description: Ermittelt aus der Anrede einen passenden Anredetext
	 *
	 * @param anrede
	 * @param vorname
	 * @param nachname
	 * @return Creation: 23.10.2015 by mst
	 */
	public static String getAnredeString(final String anrede, final String nachname)
	{
		String anredeString;
		if (anrede.equalsIgnoreCase("Herr"))
		{
			anredeString = String.format("Sehr geehrter Herr %s", nachname);
		} else if (anrede.equalsIgnoreCase("Frau"))
		{
			anredeString = String.format("Sehr geehrte Frau %s", nachname);
		} else
		{
			anredeString = "Sehr geehrte Damen und Herren,";
		}
		return anredeString;
	}

	private EmailConfigObject emailConfigObject;

	public EmailSendTool(EmailConfigObject emailConfigObject)
	{
		super();
		this.emailConfigObject = emailConfigObject;
		if (this.emailConfigObject.getPort() != null)
		{
			this.port = this.emailConfigObject.getPort();
		}
	}

	/**
	 *
	 * Description:Sendet Email mit Anhang
	 *
	 * @param to
	 * @param from
	 * @param subject
	 * @param messageText
	 * @param attachmentFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws EmailException        Creation: 22.10.2015 by mst
	 */
	public String sendMailWithAttachment(final String to, final String from, final String subject,
				final String messageText, final File attachmentFile) throws FileNotFoundException, EmailException
	{
		if (attachmentFile.exists())
		{
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(attachmentFile.getAbsolutePath());
			attachment.setDisposition(EmailAttachment.ATTACHMENT);

			return sendMailWithAttachment(to, null, from, subject, messageText, attachment);
		} else
		{
			throw new FileNotFoundException(attachmentFile.getAbsolutePath());
		}
	}

	/**
	 *
	 * Description: Sendet Email mit Anhang
	 *
	 * @param toMultiple
	 * @param from
	 * @param subject
	 * @param messageText
	 * @param attachment
	 * @return
	 * @throws EmailException Creation: 23.10.2015 by mst
	 */
	public String sendMailWithAttachment(final String toMultiple, final String bcc, final String from,
				final String subject, final String messageText, final EmailAttachment attachment) throws EmailException
	{
		ArrayList<EmailAttachment> attachmentsList = new ArrayList<EmailAttachment>();
		attachmentsList.add(attachment);
		return sendMailWithAttachment(toMultiple, bcc,from,subject, messageText,attachmentsList );
		
	}

	public String sendMailWithAttachment(final String toMultiple, final String bcc, final String from,
				final String subject, final String messageText, final ArrayList<EmailAttachment> attachmentList)
				throws EmailException
	{

		MultiPartEmail email = new MultiPartEmail();
		email.setSmtpPort(port);
		email.setHostName(emailConfigObject.getMailserver());

		String[] toArray = StringTool.splittStringWithCommas(toMultiple, ",");
		for (String to : toArray)
		{
			email.addTo(to);
		}

		if (bcc != null)
		{
			email.addBcc(bcc);
		}
		email.setFrom(from);
		email.setSubject(subject);
		email.setMsg(messageText);
		for (EmailAttachment attachment : attachmentList)
		{
			email.attach(attachment);
		}
		return email.send();
	}

	/**
	 *
	 * Description:
	 *
	 * @param to
	 * @param from
	 * @param subject
	 * @param messageTextPraefix
	 * @param e                  Creation: 22.10.2015 by mst
	 * @throws EmailException
	 * @throws FileNotFoundException
	 */
	public void sendExceptionNotifyEmail(String to, String from, String subject, String messageTextPraefix, Throwable e)
				throws FileNotFoundException, EmailException
	{
		/*
		 * ExceptionMailbody aufbereiten
		 */
		String messageText = "Fehlerbericht Exception:";
		messageText = messageText.concat("\n");
		String exMessage = ExceptionUtils.getStackTrace(e);
		for (StackTraceElement stack : e.getStackTrace())
		{
			messageText = messageText.concat("\n Stack-Meldung:");
			messageText = messageText.concat(String.format("Klasse: %s \nFile: %s \nZeile: %s \nMethode: %s ",
						stack.getClassName(), stack.getFileName(), stack.getLineNumber(), stack.getMethodName()));

		}
		messageText = messageText.concat("\n\n Stackmeldung Message: \n"
					+ exMessage);

		sendMail(to, from, subject, messageText);
	}

	/**
	 *
	 * Description:Send Mail with Exception Informations
	 *
	 * @param subject
	 * @param messageTextPraefix
	 * @param e
	 * @throws FileNotFoundException
	 * @throws EmailException        Creation: 10.03.2016 by mst
	 */
	public void sendExceptionNotifyEmail(String subject, String messageTextPraefix, Throwable e)
				throws FileNotFoundException, EmailException
	{
		String to = this.emailConfigObject.getTo();
		String from = this.emailConfigObject.getFrom();
		sendExceptionNotifyEmail(to, from, subject, messageTextPraefix, e);
	}

	/**
	 *
	 * Description:
	 *
	 * @param to
	 * @param from
	 * @param subject
	 * @param messageText
	 * @return
	 * @throws FileNotFoundException
	 * @throws EmailException        Creation: 22.10.2015 by mst
	 */
	public String sendMail(String toMultiple, String from, String subject, String messageText)
				throws FileNotFoundException, EmailException
	{
		boolean debug = true;
		String sysoutString = "Mail versendet an ";
		MultiPartEmail email = new MultiPartEmail();

		email.setSmtpPort(port);
		String[] toArray = StringTool.splittStringWithCommas(toMultiple, ",");
		for (String to : toArray)
		{
			email.addTo(to);
			sysoutString += to
						+ " ";
		}
		email.setHostName(emailConfigObject.getMailserver());
		email.setFrom(from);
		email.setSubject(subject);
		email.setMsg(messageText);
		if (debug)
			System.out.println(sysoutString);
		return email.send();
	}

	/**
	 *
	 * Description:
	 *
	 * @param to
	 * @param from
	 * @param subject
	 * @param messageText
	 * @return
	 * @throws FileNotFoundException
	 * @throws EmailException        Creation: 22.10.2015 by mst
	 */
	public String sendMail(String toMultiple, String bcc, String from, String subject, String messageText)
				throws FileNotFoundException, EmailException
	{
		MultiPartEmail email = new MultiPartEmail();
		email.setSmtpPort(port);
		email.setHostName(emailConfigObject.getMailserver());
		String[] toArray = StringTool.splittStringWithCommas(toMultiple, ",");
		for (String to : toArray)
		{
			email.addTo(to);
		}
		email.addBcc(bcc);
		email.setFrom(from);
		email.setSubject(subject);
		email.setMsg(messageText);
		return email.send();
	}

	/**
	 *
	 * Description: E-Mail mit Anhang aus Stream mit Apache Commons Email versenden
	 * (http://commons.apache.org/proper/commons-email/apidocs/index.html)
	 *
	 * @param mailserver
	 * @param username
	 * @param password
	 * @param absender
	 * @param empfaenger
	 * @param betreff
	 * @param text
	 * @return
	 * @throws IOException
	 * @throws EmailException Creation: 22.10.2015 by mst
	 */
	public static String sendeEmail(String mailserver, String username, String password, String absender,
				String toMultiple, String betreff, String text) throws IOException, EmailException
	{

		MultiPartEmail email = new MultiPartEmail();
		email.setSmtpPort(PORT);
		if (username != null && password != null)
		{
			DefaultAuthenticator defaultAuthenticator = new DefaultAuthenticator(username, password);
			email.setAuthenticator(defaultAuthenticator);
			email.setSSLOnConnect(false);
		}
		String[] toArray = StringTool.splittStringWithCommas(toMultiple, ",");
		for (String to : toArray)
		{
			email.addTo(to);
		}
		email.setHostName(mailserver);
		email.setFrom(absender);
		email.setCharset("UTF-8");
		email.setSubject(betreff);
		email.setMsg(text);
		// email.attach(new ByteArrayDataSource(anhangInputStream, anhangContentType),
		// anhangDateiName, anhangBeschreibung, EmailAttachment.ATTACHMENT);
		return email.send();
	}

	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @return the pORT
	 */
	public static int getPORT()
	{
		return PORT;
	}

	/**
	 * @param pORT the pORT to set
	 */
	public static void setPORT(int pORT)
	{
		PORT = pORT;
	}

	/**
	 * 
	 * Description:Validate the form of an email address.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws AddressException Creation: 30.09.2016 by mst
	 */
	public static boolean isValidEmailAddress(String emailAddress) throws AddressException
	{

		try
		{
			if (emailAddress != null)
			{
				InternetAddress emailAddr = new InternetAddress(emailAddress);
				emailAddr.validate();
				return true;
			} else
			{
				return false;
			}

		} catch (AddressException e)
		{
			return false;
		}
	}

}
