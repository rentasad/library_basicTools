/**
 * 
 */
package org.gustini.library.tools.fileOperator;

import java.io.IOException;

/**
 * Gustini GmbH (2016)
 * Creation: 15.12.2016
 * Library
 * org.gustini.library.tools.fileOperator
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class FileCouldNotDeleteException extends IOException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public FileCouldNotDeleteException()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public FileCouldNotDeleteException(
                                       String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public FileCouldNotDeleteException(
                                       Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public FileCouldNotDeleteException(
                                       String message,
                                       Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
