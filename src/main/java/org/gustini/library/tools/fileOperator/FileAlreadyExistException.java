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
 * Description: Exception to throw if target File exist
 *
 */
public class FileAlreadyExistException extends IOException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public FileAlreadyExistException()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public FileAlreadyExistException(
                                     String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public FileAlreadyExistException(
                                     Throwable arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     * @param arg1
     */
    public FileAlreadyExistException(
                                     String arg0,
                                     Throwable arg1)
    {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }



}
