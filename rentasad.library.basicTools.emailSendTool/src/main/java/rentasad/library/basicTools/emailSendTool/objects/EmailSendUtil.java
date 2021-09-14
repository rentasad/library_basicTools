package rentasad.library.basicTools.emailSendTool.objects;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * Gustini GmbH (2018)
 * Creation: 04.12.2018
 * gustini.library.basicTools.emailSendTool
 * rentasad.library.basicTools.emailSendTool.objects
 * 
 * @author Matthias Staud
 *
 *         Description: MailSend Utility based on Javax.Mail
 *
 */
public class EmailSendUtil
{
    Session session;
    public EmailSendUtil(String host)
    {
        this.session = getMailSession(host);
    }

    /**
     * 
     * Description: Für Sitzungen ohne Authentifizierung 
     * 
     * @param host
     * @return
     * Creation: 04.12.2018 by mst
     */
    public static Session getMailSession(String host)
    {
      final Properties props = new Properties();

      // Zum Senden
      props.setProperty( "mail.smtp.host", host );
      props.setProperty( "mail.smtp.auth", "false" );
      props.setProperty( "mail.smtp.port", "25" );
      props.setProperty( "mail.smtp.socketFactory.port", "25" );
//      props.setProperty( "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory" );
      props.setProperty( "mail.smtp.socketFactory.fallback", "false" );
      props.put("mail.smtp.timeout", 1000);
      return Session.getInstance(props);
        }
    
    
    /**
     * 
     * Description: 
     * 
     * @param to
     * @param from
     * @param subject
     * @param message
     * Creation: 04.12.2018 by mst
     */
    public void sendMail(String to, String from, String subject, String message)
    {
        
        EmailSendUtil.sendMail(this.session, to, from, subject, message);
    }
    
    /**
     * 
     * Description: 
     * 
     * @param session
     * @param to
     * @param from
     * @param subject
     * @param messageContent
     * Creation: 04.12.2018 by mst
     */
    public static void sendMail(Session session, String to, String from, String subject, String messageContent)
    {
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(messageContent);

            // Send message,
            System.out.println(String.format("Starte Mailversand von %s zu %s.", from, to));
            Transport.send(message);
            System.out.println("Send message successfully....");
         } catch (MessagingException mex) {
             System.out.println("Fehler beim Versand: " + mex.getMessage() );
            mex.printStackTrace();
         }
        
    }

}
