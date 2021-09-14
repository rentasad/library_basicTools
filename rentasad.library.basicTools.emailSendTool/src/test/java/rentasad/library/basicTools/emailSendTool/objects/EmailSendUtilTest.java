package rentasad.library.basicTools.emailSendTool.objects;

/**
 * 
 * Gustini GmbH (2018)
 * Creation: 04.12.2018
 * gustini.library.basicTools.emailSendTool
 * rentasad.library.basicTools.emailSendTool.objects
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class EmailSendUtilTest
{

    @org.junit.jupiter.api.Test
    public void testGetMailSession() throws Exception
    {
        String host = "192.168.111.72";
        EmailSendUtil util = new EmailSendUtil(host);
        String to = "matthias.staud@gustini.de";
        String from = "it@gustini.de";
        String subject = "TEST JAvaMail";
        String message = "Inhalt der Nachricht.";
        
        
        util.sendMail(to, from, subject, message);
    }

}
