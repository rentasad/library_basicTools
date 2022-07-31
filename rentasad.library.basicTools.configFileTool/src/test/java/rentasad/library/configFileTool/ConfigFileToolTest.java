package rentasad.library.configFileTool;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class ConfigFileToolTest
{

	/**
	 *
	 */
	@Test void readConfigurationFromResources() throws IOException, ConfigFileToolException
	{
		/**
		 * [TEST]
		 * source=test
		 * firstname=Max
		 * lastname=Mustermann
		 */
		String configFile = "config/test.ini";
		Map<String,String> configMap = ConfigFileTool.readConfigurationFromResources(configFile, "TEST");
		assertEquals("test",configMap.get("source"));
		assertEquals("Max",configMap.get("firstname"));
		assertEquals("Mustermann",configMap.get("lastname"));
	}

	@Test void getSectionsFromResources() throws IOException
	{		String configFile = "config/test.ini";
		String[] sectionsFromResources = ConfigFileTool.getSectionsFromResources(configFile);
		assertEquals("TEST", sectionsFromResources[0]);

	}
}