package rentasad.library.basicTools.emailSendTool;

import org.junit.jupiter.api.Test;

import rentasad.library.basicTools.emailSendTool.objects.EmailConfigObject;
import rentasad.library.basicTools.emailSendTool.objects.EmailConfigParameterEnum;

public class EmailSendToolTest
{

    @Test
    public void testSendMailString() throws Exception
    {
    	
    	final String smtpHost = "gstvmtask";
    	final String smtpPort = "25";
    	final String smtpFrom = "it@gustini.de";
    	final String smtpTo = "matthias.staud@gustini.de";
    	
    	
    	
    	
        EmailConfigObject emailConfigObject = new EmailConfigObject(smtpHost);
        emailConfigObject.setValue(smtpPort, EmailConfigParameterEnum.port);
        emailConfigObject.setValue(smtpFrom, EmailConfigParameterEnum.from);
        emailConfigObject.setValue(smtpTo, EmailConfigParameterEnum.to);

        EmailSendTool emailSendTool = new EmailSendTool(emailConfigObject);
        emailSendTool.sendMail(smtpTo, smtpFrom, "UNIT_TEST testSendMailString", "This is a Unit-Test of the Modul rentasad.library.basicTools.emailSendTool EmailSendToolTest");
//        EmailSendTool.sendeEmail("192.168.111.253",null, null, "it@gustini.de", "matthias.staud@gustini.de,matthias@familie-staud.de", "TEST1-SUBJECT", "TEST1 INHALT");
    }
    @Test
    public void testMultipleMail() throws Exception
    {
    	final String smtpHost = "192.168.111.72";
    	final String smtpPort = "25";
    	final String smtpFrom = "it@gustini.de";
    	final String smtpTo = "matthias.staud@gustini.de, it@gustini.de";
    	
    	
    	
    	
        EmailConfigObject emailConfigObject = new EmailConfigObject(smtpHost);
        emailConfigObject.setValue(smtpPort, EmailConfigParameterEnum.port);
        emailConfigObject.setValue(smtpFrom, EmailConfigParameterEnum.from);
        emailConfigObject.setValue(smtpTo, EmailConfigParameterEnum.to);

        EmailSendTool emailSendTool = new EmailSendTool(emailConfigObject);
        emailSendTool.sendMail(smtpTo, smtpFrom, "UNIT_TEST testSendMailString", "This is a Unit-Test of the Modul rentasad.library.basicTools.emailSendTool EmailSendToolTest");
    }
}
