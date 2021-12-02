/**
 * 
 */
package rentasad.library.logging;

import java.util.logging.Level;

import org.junit.jupiter.api.Test;

/**
 * @author mst
 *
 */
public class LoggingToolkitTest
{

	@Test
	public void testGetLoggerString()
	{
		java.util.logging.Logger logger = LoggingToolkit.getLogger(LoggingToolkitTest.class.getName());
		logger.info("INFO");
		logger.log(Level.WARNING, "WARNING");
	}

}
