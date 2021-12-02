package rentasad.library.logging;

import java.util.logging.Logger;

/**
 *
 * Gustini GmbH (2016)
 * Creation: 10.03.2016
 * Library
 * gustini.library.tools.logging
 *
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class LoggingToolkit
{
	private static final String LOGGER_DEFAULT_NAME = "DEFAULT_LOGGER";
    private static Logger logger = null;
    private LoggingToolkit()
    {
    }

    /**
    *
    * Description: return a Logger instance with ConsoleAppender
    *
    * @return
    * Creation: 10.03.2016 by mst
    */
   public static Logger getLogger(String loggerName)
   {
       if (logger == null)
       {
           logger = Logger.getLogger(loggerName);

       }
       return logger;
   }
   
   public static Logger getLogger()
   {
       if (logger == null)
       {
           logger = Logger.getLogger(LOGGER_DEFAULT_NAME);

       }
       return logger;
   }
}
