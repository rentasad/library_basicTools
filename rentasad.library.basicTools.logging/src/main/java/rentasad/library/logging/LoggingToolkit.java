package rentasad.library.logging;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

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
    private static Logger logger = null;
    private LoggingToolkit()
    {
        // TODO Auto-generated constructor stub
    }

    /**
    *
    * Description: return a Logger instance with ConsoleAppender
    *
    * @return
    * Creation: 10.03.2016 by mst
    */
   public static Logger getLogger()
   {
       if (logger == null)
       {
           logger = org.apache.log4j.Logger.getRootLogger();
           SimpleLayout layout = new SimpleLayout();
           ConsoleAppender consoleAppender = new ConsoleAppender(layout);
           logger.addAppender(consoleAppender);
       }
       return logger;
   }
}
