package rentasad.library.configFileTool;

/**
 * 
 * Gustini GmbH (2015)
 * Creation: 12.11.2015
 * Library
 * gustini.library.tools.configFileTool
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class ConfigFileToolException extends Exception
{

    /** Use serialVersionUID for interoperability. */
    private static final long serialVersionUID = -3690313181519499249L;

    public ConfigFileToolException(String message)
    {
        super(message);
    }

    /**
     * 
     */
    public ConfigFileToolException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ConfigFileToolException(
                                   String message,
                                   Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public ConfigFileToolException(
                                   String message,
                                   Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public ConfigFileToolException(
                                   Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
    

}
