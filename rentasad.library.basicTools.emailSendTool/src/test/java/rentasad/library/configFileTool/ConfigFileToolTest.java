package rentasad.library.configFileTool;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConfigFileToolTest
{

	@Test void readConfigurationFromResources() throws IOException, ConfigFileToolException
	{
		Map<String, String> configMap = ConfigFileTool.readConfigurationFromResources("config/test.ini", "TEST");
		assertEquals("src",configMap.get("source"));
	}
}