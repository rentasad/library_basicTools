package rentasad.library.configFileTool;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.io.IOUtils;
import org.ini4j.ConfigParser.ConfigParserException;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import rentasad.library.logging.AbstractLoggingListener;


/**
 *
 * Gustini GmbH (2015)
 * Creation: 18.02.2015
 * Rentasad Library
 * rentasad.lib.tools.configFileTool
 *
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 *         Tool zum Erstellen und Auslesen von INI-Dateien
 */

public class  ConfigFileTool extends AbstractLoggingListener
{
    private static final char[] PASSWORD = "48joVKpQ+jIkzy-oW!0A".toCharArray();
    private static final byte[] SALT =
    { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, };

    /**
     *
     */
    private ConfigFileTool()
    {
        super();
    }

    /**
     *
     * @param fileName
     * @param sektionString
     *            (Sektion von Variablen einer INI-Datei)
     * @param configMap
     *            ( Enthaelt Key und Value der zu speichernden Variablen.)
     * @throws InvalidFileFormatException
     * @throws IOException
     */
    public static void writeConfiguration(String fileName, String sektionString, Map<String, String> configMap) throws IOException
    {
        Wini ini = new Wini();

        String[] keyStringArray = configMap.keySet().toArray(new String[0]);

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
     *
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
     *             @throws
     *             ConfigParserException
     *             Creation: 18.02.2015 by mst
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
            } else
            {
                message = "Folgende Section wurde in der INI-Datei nicht gefunden: " + sektionString;
                logMessage(message, Level.SEVERE);
                throw new ConfigFileToolException(message);
            }

            return configMap;
        } else
        {
            throw new FileNotFoundException(configFile.getAbsolutePath());

        }
    }

    /**
     *
     * @param fileName
     * @param sektionString
     * @return
     * @throws IOException
     * @throws ConfigFileToolException
     */
    public static Map<String, String> readConfigurationFromResources(final String fileName, final String sektionString) throws IOException,
            ConfigFileToolException
    {
        byte[] bytes = IOUtils.toByteArray(ConfigFileTool.class.getClassLoader().getResourceAsStream(fileName));
        InputStream inputStream = ConfigFileTool.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null)
        {
            throw new IllegalArgumentException(fileName + " is not found");
        }
//        String value =
//                new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        return readConfiguration(inputStream, sektionString);
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
            } else
            {
                String message = "Folgende Section wurde in der INI-Datei nicht gefunden: " + sektionString;
                logMessage(message, Level.SEVERE);
                throw new ConfigFileToolException(message);
            }
            return configMap;
        }

    
    
    /**
     *
     * Description:
     *
     * @param fileName
     * @return Map<String, Map<String, String>>
     * @throws ConfigFileToolException
     * Creation: 23.02.2016 by mst
     */
    public static Map<String, Map<String, String>> readIniFileWithAllSections(final String fileName) throws ConfigFileToolException
    {
        Map<String, Map<String, String>> sectionConfigMap = new HashMap<String, Map<String,String>>();
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
     *
     * Description: Gibt verfuegbare Sektionen der INI-Datei zurueck
     *
     * @param fileName
     * @return
     * @throws InvalidFileFormatException
     * @throws FileNotFoundException
     * @throws IOException
     *             Creation: 15.10.2015 by mst
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
     *
     * Description: Gibt verfuegbare Sektionen der INI-Datei zurueck
     *
     * @param fileName
     * @return
     * @throws InvalidFileFormatException
     * @throws FileNotFoundException
     * @throws IOException
     *             Creation: 15.10.2015 by mst
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
        return Base64.getEncoder().encodeToString(bytes);
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
     * 
     * Description: decode Base64 String to byteArray 
     * 
     * @param property
     * @return
     * @throws IOException
     * Creation: 29.07.2016 by mst
     */
    private static byte[] base64Decode(String property) throws IOException
    {
        // NB: This class is internal, and you probably should use another impl
        return Base64.getDecoder().decode(property);
    }

}
