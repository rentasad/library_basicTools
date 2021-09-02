package rentasad.library.tools.exceptions;
/**
 * 
 * Gustini GmbH (2015)
 * Creation: 22.12.2015
 * Library
 * gustini.library.exceptions
 * 
 * @author Matthias Staud
 *
 *
 * Description:Allgemeingueltige Exception, wenn mit Enums gearbeitet wird und bestimmte Enums noch nicht flaechendeckend implementiert wurden.
 *
 */
public class UnknownEnumException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String message;

    /**
     * @param message
     */
    public UnknownEnumException(
                                String message)
    {
        super();
        this.message = message;
    }
    
    

    /**
     * 
     */
    public UnknownEnumException()
    {
        super();
    }



    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public UnknownEnumException(
                                String message,
                                Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }



    /**
     * @param message
     * @param cause
     */
    public UnknownEnumException(
                                String message,
                                Throwable cause)
    {
        super(message, cause);
    }



    /**
     * @param cause
     */
    public UnknownEnumException(
                                Throwable cause)
    {
        super(cause);
    }



    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
}
