package org.gustini.library.basicTools;

/**
 * 
 * Gustini GmbH (2015)
 * Creation: 16.11.2015
 * Library
 * gustini.library.tools.utilities
 * 
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 */
public class OperatingSystemTool
{

    private OperatingSystemTool()
    {

    }

    /**
     * 
     * Description: Gibt True oder False zurueck ob das ausfuehrende System ein Windows ist oder nicht.
     * 
     * @return
     *         Creation: 16.11.2015 by mst
     */
    public static boolean isOperatingSystemWindows()
    {
        String osString = System.getProperty("os.name");
        if (osString.length() < 7)
        {
            return false;
        } else
        {
            String winString = osString.substring(0, 7);
            return "Windows".equalsIgnoreCase(winString);
        }
    }

    /**
     * 
     * Description: Return Hostname from System 
     * 
     * @return
     * Creation: 19.09.2017 by mst
     */
    public static String getHostName()
    {
        if (System.getProperty("os.name").startsWith("Windows"))
        {
            // Windows will always set the 'COMPUTERNAME' variable
            return System.getenv("COMPUTERNAME");
        } else
        {
            // If it is not Windows then it is most likely a Unix-like operating system
            // such as Solaris, AIX, HP-UX, Linux or MacOS.

            // Most modern shells (such as Bash or derivatives) sets the
            // HOSTNAME variable so lets try that first.
            String hostname = System.getenv("HOSTNAME");
            if (hostname != null)
            {
                return hostname;
            } else
            {
                return null;
            }
        }
    }
}
