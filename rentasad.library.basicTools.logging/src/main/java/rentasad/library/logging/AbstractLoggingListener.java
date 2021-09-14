package rentasad.library.logging;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public abstract class AbstractLoggingListener
{

    private final static List<Logger> loggerList = new ArrayList<Logger>();

    public AbstractLoggingListener()
    {
        // TODO Auto-generated constructor stub
    }

    public static void addLogger(Logger logger)
    {
        if (!loggerList.contains(logger))
        {
            loggerList.add(logger);
        }
    }

    public static void removeLogger(Logger logger)
    {
        if (!loggerList.contains(logger))
        {
            loggerList.remove(logger);
        }
    }

    /**
    *
    * Description: if Logger is set, logging Message
    *
    * @param message
    * @param levelInt (from {@link Level}
    * Creation: 10.03.2016 by mst
    */
   public static void logMessage(String message, int  levelInt)
   {
       if (!loggerList.isEmpty())
       {
           for (Logger logger : loggerList)
           {
               
               logger.log(Level.toLevel(levelInt), message);
           }
       }
   }
/**
 * 
 * Description: 
 * 
 * @param message
 * @param e
 * Creation: 11.07.2019 by mst
 */
public static void logError(String message, Exception e)
   {
       if (!loggerList.isEmpty())
       {
           for (Logger logger : loggerList)
           {
               logger.log(Level.ERROR, message);
           }
       }
   }

    /**
     * @return the loggerlist
     */
    public static List<Logger> getLoggerlist()
    {
        return loggerList;
    }

}
