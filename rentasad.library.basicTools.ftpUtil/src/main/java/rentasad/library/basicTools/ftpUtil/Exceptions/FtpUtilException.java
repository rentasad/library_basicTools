/**
 * 
 */
package rentasad.library.basicTools.ftpUtil.Exceptions;

/**
 * Gustini GmbH (2016)
 * Creation: 17.02.2016
 * Library
 * gustini.library.tools.ftputil.Exceptions
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class FtpUtilException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public FtpUtilException()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public FtpUtilException(
                            String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public FtpUtilException(
                            Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public FtpUtilException(
                            String message,
                            Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public FtpUtilException(
                            String message,
                            Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
