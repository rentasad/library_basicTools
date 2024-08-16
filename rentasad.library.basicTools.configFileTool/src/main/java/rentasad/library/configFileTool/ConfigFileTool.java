package rentasad.library.configFileTool;

import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.ini4j.ConfigParser.ConfigParserException;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;
import rentasad.library.logging.AbstractLoggingListener;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.logging.Level;

/**
 * Gustini GmbH (2015)
 * Creation: 18.02.2015
 * Rentasad Library
 * rentasad.lib.tools.configFileTool
 *
 * @author Matthias Staud
 * <p>
 * <p>
 * Description:
 * <p>
 * Tool zum Erstellen und Auslesen von INI-Dateien
 */
@Log
public class ConfigFileTool extends AbstractLoggingListener
{
	private static final char[] PASSWORD = "48joVKpQ+jIkzy-oW!0A".toCharArray();
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, };

	/**
	 *
	 */
	private ConfigFileTool()
	{
		super();
	}

	/**
	 * @param fileName
	 * @param sektionString (Sektion von Variablen einer INI-Datei)
	 * @param configMap     ( Enthaelt Key und Value der zu speichernden Variablen.)
	 * @throws InvalidFileFormatException
	 * @throws IOException
	 */
	public static void writeConfiguration(String fileName, String sektionString, Map<String, String> configMap) throws IOException
	{
		Wini ini = new Wini();

		String[] keyStringArray = configMap.keySet()
										   .toArray(new String[0]);

		Ini.Section section = ini.add(sektionString);
		for (int i = 0; i < keyStringArray.length; i++)
		{
			section.add(keyStringArray[i], configMap.get(keyStringArray[i]));
		}
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
		ini.store(fileOutputStream);
		fileOutputStream.close();
	}

	/**
	 * Description:
	 *
	 * @param fileName
	 * @param sektionString
	 * @return
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ConfigFileToolException
	 * @throws
	 * @throws ConfigParserException      Creation: 18.02.2015 by mst
	 */
	public static Map<String, String> readConfiguration(String fileName, String sektionString) throws IOException, ConfigFileToolException
	{
		File configFile = new File(fileName);
		if (configFile.exists())
		{
			String message = String.format("Section %s of ConfigFile %s loaded.", sektionString, new File(fileName).getAbsolutePath());
			logMessage(message, java.util.logging.Level.FINER);
			//            System.out.println(new File(fileName).getAbsolutePath());
			Map<String, String> configMap = new HashMap<String, String>();
			Wini ini = new Wini();

			ini.load(new FileReader(new File(fileName)));
			Ini.Section section = ini.get(sektionString);
			if (section != null)
			{
				Set<String> keySet = section.keySet();
				String[] keysArray = keySet.toArray(new String[0]);
				for (int i = 0; i < keysArray.length; i++)
				{
					configMap.put(keysArray[i], section.get(keysArray[i]));
				}
			}
			else
			{
				message = "Folgende Section wurde in der INI-Datei nicht gefunden: " + sektionString;
				logMessage(message, Level.SEVERE);
				throw new ConfigFileToolException(message);
			}

			return configMap;
		}
		else
		{
			throw new FileNotFoundException(configFile.getAbsolutePath());

		}
	}

	/**
	 * Reads the configuration from a resource file.
	 *
	 * @param fileName      The name of the resource file.
	 * @param sektionString The section of the variables in the INI file.
	 * @return A map containing the keys and values of the variables.
	 * @throws IOException             If an I/O error occurs while reading the file.
	 * @throws ConfigFileToolException If the specified file is not found or the section is not found in the file.
	 */
	public static Map<String, String> readConfigurationFromResources(final String fileName, final String sektionString) throws IOException, ConfigFileToolException
	{
		InputStream inputStream = null;
		try {
			URL resourceUrl = ConfigFileTool.class.getClassLoader().getResource(fileName);
			if (resourceUrl == null) {
				throw new IllegalArgumentException(fileName + " is not found in resources.");
			}

			inputStream = Objects.requireNonNull(resourceUrl.openStream(), fileName + " is not found in resources.");
			// Hier weiterhin Ihre Logik zur Verarbeitung der InputStream
			return readConfiguration(inputStream, sektionString);
		} catch (IllegalArgumentException e) {
			// Loggen der Fehlermeldung mit dem Versuch, den vollständigen Pfad anzuzeigen
			log.severe("Die Konfigurationsdatei '" + fileName + "' konnte nicht im Klassenpfad gefunden werden. \r\nVersuchter Pfad: " + (ConfigFileTool.class.getClassLoader().getResource(fileName) != null ? ConfigFileTool.class.getClassLoader().getResource(fileName).toString() : "Nicht verfügbar"));
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public static Map<String, String> readConfiguration(InputStream inputStream, String sektionString) throws IOException, ConfigFileToolException
	{

		//            System.out.println(new File(fileName).getAbsolutePath());
		Map<String, String> configMap = new HashMap<String, String>();
		Wini ini = new Wini();

		ini.load(inputStream);
		Ini.Section section = ini.get(sektionString);
		if (section != null)
		{
			Set<String> keySet = section.keySet();
			String[] keysArray = keySet.toArray(new String[0]);
			for (int i = 0; i < keysArray.length; i++)
			{
				configMap.put(keysArray[i], section.get(keysArray[i]));
			}
		}
		else
		{
			String message = "Folgende Section wurde in der INI-Datei nicht gefunden: " + sektionString;
			logMessage(message, Level.SEVERE);
			throw new ConfigFileToolException(message);
		}
		return configMap;
	}

	/**
	 * Description:
	 *
	 * @param fileName
	 * @return Map<String, Map < String, String>>
	 * @throws ConfigFileToolException Creation: 23.02.2016 by mst
	 */
	public static Map<String, Map<String, String>> readIniFileWithAllSections(final String fileName) throws ConfigFileToolException
	{
		Map<String, Map<String, String>> sectionConfigMap = new HashMap<String, Map<String, String>>();
		String[] sections;
		try
		{
			sections = getSections(fileName);
			for (String sectionName : sections)
			{
				sectionConfigMap.put(sectionName, readConfiguration(fileName, sectionName));
			}

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sectionConfigMap;
	}

	/**
	 * Description:
	 *
	 * @param fileName
	 * @return Map<String, Map < String, String>>
	 * @throws ConfigFileToolException Creation: 23.02.2016 by mst
	 */
	public static Map<String, Map<String, String>> readIniFileWithAllSectionsFromResources(final String fileName) throws ConfigFileToolException
	{
		Map<String, Map<String, String>> sectionConfigMap = new HashMap<>();
		String[] sections;

		try
		{
			sections = getSectionsFromResources(fileName);
			for (String sectionName : sections)
			{
				sectionConfigMap.put(sectionName, readConfigurationFromResources(fileName, sectionName));
			}

		} catch (IOException e)
		{
			throw new ConfigFileToolException((e));
		}
		return sectionConfigMap;
	}

	/**
	 * Description: Gibt verfuegbare Sektionen der INI-Datei zurueck
	 *
	 * @param fileName
	 * @return
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException
	 * @throws IOException                Creation: 15.10.2015 by mst
	 */
	public static String[] getSections(String fileName) throws IOException
	{
		Wini ini = new Wini();
		Set<String> sectionSet = ini.keySet();
		File file = new File(fileName);

		String filePatj = file.getAbsolutePath();
		System.out.println(filePatj);
		ini.load(new FileReader(file));
		return sectionSet.toArray(new String[0]);
	}

	/**
	 * Description: Gibt verfuegbare Sektionen der INI-Datei zurueck
	 *
	 * @param fileName
	 * @return
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException
	 * @throws IOException                Creation: 15.10.2015 by mst
	 */
	public static String[] getSectionsFromResources(String fileName) throws IOException
	{
		byte[] bytes = IOUtils.toByteArray(ConfigFileTool.class.getClassLoader()
															   .getResourceAsStream(fileName));
		InputStream inputStream = ConfigFileTool.class.getClassLoader()
													  .getResourceAsStream(fileName);
		if (inputStream == null)
		{
			throw new IllegalArgumentException(fileName + " is not found");
		}
		Wini ini = new Wini();
		Set<String> sectionSet = ini.keySet();

		ini.load(inputStream);
		return sectionSet.toArray(new String[0]);
	}

	/**
	 * Description: Gibt verfuegbare Sektionen der INI-Datei zurueck
	 *
	 * @param fileName
	 * @return
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException
	 * @throws IOException                Creation: 15.10.2015 by mst
	 */
	public static Set<String> getSectionsAsSetFromResource(String fileName) throws IOException
	{
		byte[] bytes = IOUtils.toByteArray(ConfigFileTool.class.getClassLoader()
															   .getResourceAsStream(fileName));
		InputStream inputStream = ConfigFileTool.class.getClassLoader()
													  .getResourceAsStream(fileName);
		if (inputStream == null)
		{
			throw new IllegalArgumentException(fileName + " is not found");
		}
		Wini ini = new Wini();
		Set<String> sectionSet = ini.keySet();
		ini.load(inputStream);
		return sectionSet;
	}

	/**
	 * Description: Gibt verfuegbare Sektionen der INI-Datei zurueck
	 *
	 * @param fileName
	 * @return
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException
	 * @throws IOException                Creation: 15.10.2015 by mst
	 */
	public static Set<String> getSectionsAsSet(String fileName) throws IOException
	{
		Wini ini = new Wini();
		Set<String> sectionSet = ini.keySet();
		ini.load(new FileReader(new File(fileName)));
		return sectionSet;
	}

	public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException
	{
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
	}

	/*
	 * Encode ByteArray to a base64 encoded String
	 */
	private static String base64Encode(byte[] bytes)
	{
		// NB: This class is internal, and you probably should use another impl
		return Base64.getEncoder()
					 .encodeToString(bytes);
	}

	public static String decrypt(String property) throws GeneralSecurityException, IOException
	{
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	/**
	 * Description: decode Base64 String to byteArray
	 *
	 * @param property
	 * @return
	 * @throws IOException Creation: 29.07.2016 by mst
	 */
	private static byte[] base64Decode(String property) throws IOException
	{
		// NB: This class is internal, and you probably should use another impl
		return Base64.getDecoder()
					 .decode(property);
	}

}
