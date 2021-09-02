package rentasad.library.basicTools.emailSendTool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rentasad.library.basicTools.emailSendTool.objects.EmailConfigObject;
import rentasad.library.basicTools.emailSendTool.objects.EmailConfigParameterEnum;

public class EmailConfigObjectTest {
	final String MAIL_SETTINGS_FILE_PATH_WRITE = "resources/test/mailSettingsTest.ini";
	final String MAIL_SETTINGS_FILE_PATH_READ = "resources/test/mailSettingsReadTest.ini";
	final String MAIL_SETTINGS_HOSTNAME = "mail.server.de";
	final String MAIL_SETTINGS_FROM = "From@Adress.de";
	final String MAIL_SETTINGS_TO = "to@mail.de";

	final String CONFIG_MAIL_SECTION = "MAILCONFIG";

	EmailConfigObject configObject = new EmailConfigObject();

	@BeforeEach
	public void setUp() throws Exception {
		configObject.setValue(MAIL_SETTINGS_HOSTNAME, EmailConfigParameterEnum.mailserver);
		configObject.setValue(MAIL_SETTINGS_FROM, EmailConfigParameterEnum.from);
		configObject.setValue(MAIL_SETTINGS_TO, EmailConfigParameterEnum.to);
	}

	@AfterEach
	public void tearDown() throws Exception {
		File mailsettingsFile = new File(MAIL_SETTINGS_FILE_PATH_WRITE);
		if (mailsettingsFile.exists())
			mailsettingsFile.delete();

	}

	@Test
	public void testWriteConfiguration() throws Exception {
		File file = new File(MAIL_SETTINGS_FILE_PATH_WRITE);
		assertTrue(EmailConfigObject.writeConfiguration(configObject, MAIL_SETTINGS_FILE_PATH_WRITE, CONFIG_MAIL_SECTION));
		assertTrue(file.exists());

	}

	@Test
	public void testReadConfiguration() throws Exception {
		File mailsettingsFile = new File(MAIL_SETTINGS_FILE_PATH_READ);

		EmailConfigObject configObject = EmailConfigObject.readConfiguration(mailsettingsFile, CONFIG_MAIL_SECTION);
		assertEquals(MAIL_SETTINGS_HOSTNAME, configObject.getMailserver());
		assertEquals(MAIL_SETTINGS_FROM, configObject.getFrom());
		assertEquals(MAIL_SETTINGS_TO, configObject.getTo());

	}

}
