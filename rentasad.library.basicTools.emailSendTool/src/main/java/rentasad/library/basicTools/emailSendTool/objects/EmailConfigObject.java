package rentasad.library.basicTools.emailSendTool.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.ini4j.ConfigParser.ConfigParserException;
import org.ini4j.InvalidFileFormatException;

import rentasad.library.configFileTool.ConfigFileTool;
import rentasad.library.configFileTool.ConfigFileToolException;
import rentasad.library.tools.exceptions.UnknownTableEnumException;

/**
 *
 * Gustini GmbH (2015)
 * Creation: 22.10.2015
 * Rentasad Library
 * rentasad.lib.tools.emailTool.objects
 *
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 */
public class EmailConfigObject
{
    private String mailserver;
    private String username;
    private String password;
    private String from;
    private String to;
    private String subject;
    private String text;
    private Integer port;

    /**
     *
     * @param mailserver
     * @param username
     * @param password
     */
    public EmailConfigObject(
                             String mailserver,
                             String username,
                             String password)
    {
        super();
        this.mailserver = mailserver;
        this.username = username;
        this.password = password;
    }
    
    /**
    *
    * @param mailserver
    */
   public EmailConfigObject(
                            String mailserver
                           )
   {
       super();
       this.mailserver = mailserver;
       this.username = null;
       this.password = null;
   }

    /**
     *
     */
    public EmailConfigObject()
    {
        super();
    }

    /**
     *
     * Description: E-Mail
     *
     * @param fileName
     * @param sectionName
     * @return
     * @throws InvalidFileFormatException
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ConfigParserException
     * @throws UnknownTableEnumException
     *             Creation: 22.10.2015 by mst
     * @throws ConfigFileToolException
     */
    public static EmailConfigObject readConfiguration(File fileName, String sectionName)
                throws InvalidFileFormatException, FileNotFoundException, IOException, ConfigParserException, UnknownTableEnumException, ConfigFileToolException
    {
        EmailConfigObject emailConfigObject = new EmailConfigObject();
        Map<String, String> configMap = ConfigFileTool.readConfiguration(fileName.getAbsolutePath(), sectionName);
        for (EmailConfigParameterEnum enumItem : EmailConfigParameterEnum.values())
        {
        	
            if (configMap.containsKey(enumItem.name()))
            {
                emailConfigObject.setValue(configMap.get(enumItem.name()), enumItem);
            }
        }
        return emailConfigObject;
    }

    /**
     *
     * Description:Erstellt aus E-MailConfig-Object eine INI-Konfigurationsdatei
     *
     * @param emailConfigObject
     * @param fileName
     * @param sectionName
     * @return
     * @throws UnknownTableEnumException
     * @throws InvalidFileFormatException
     * @throws IOException
     *             Creation: 22.10.2015 by mst
     */
    public static boolean writeConfiguration(EmailConfigObject emailConfigObject, String fileName, String sectionName) throws UnknownTableEnumException, InvalidFileFormatException, IOException
    {
        Map<String, String> configMap = new HashMap<String, String>();
        for (EmailConfigParameterEnum enumItem : EmailConfigParameterEnum.values())
        {
            if (emailConfigObject.getValue(enumItem) != null)
            {
                String value = emailConfigObject.getValue(enumItem);
                String key = enumItem.name();
                configMap.put(key, value);
            }
        }
        ConfigFileTool.writeConfiguration(fileName, sectionName, configMap);
        return (new File(fileName).exists());
    }

    /*
     * ************************************************************
     * GETTER / SETTER
     * ***********************************************************
     */
    /**
     *
     * Description:Schreibe Wert in Konfiguration
     *
     * @param propertyValue
     * @param enumItem
     *            Creation: 22.10.2015 by mst
     * @throws UnknownTableEnumException
     */
    public void setValue(String propertyValue, EmailConfigParameterEnum enumItem) throws UnknownTableEnumException
    {
        switch (enumItem)
        {
            case from:
                this.from = propertyValue;
                break;
            case mailserver:
                this.mailserver = propertyValue;
                break;
            case password:
                this.password = propertyValue;
                break;
            case to:
                this.to = propertyValue;
                break;
            case username:
                this.username = propertyValue;
                break;
            case port:
                this.port = Integer.valueOf(propertyValue);
                break;
            default:
                System.err.println("Unbekanntes Enum: " + enumItem.name());
                throw new UnknownTableEnumException();
        }
    }

    /**
     * Gibt Value anhand des enumItems zurueck
     */
    public String getValue(EmailConfigParameterEnum enumItem) throws UnknownTableEnumException
    {
        switch (enumItem)
        {
            case from:
                return this.from;
            case mailserver:
                return this.mailserver;
            case password:
                return this.password;
            case to:
                return this.to;
            case username:
                return this.username;
            case port:
                if (this.port != null)
                {
                    return this.port.toString();
                } else
                {
                    return null;
                }
            default:
                throw new UnknownTableEnumException();
        }
    }

    /**
     * @return the mailserver
     */
    public String getMailserver()
    {
        return mailserver;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @return the from
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * @return the to
     */
    public String getTo()
    {
        return to;
    }

    /**
     * @return the subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }

    /**
     * @return the port
     */
    public Integer getPort()
    {
        return port;
    }

}
