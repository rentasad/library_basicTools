/**
 * 
 */
package rentasad.library.tools.commandExecutionTool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;

/**
 * @author mst
 *
 */
public class JavaReflectionRunner
{

	/**
	 * 
	 */
	public JavaReflectionRunner()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		URLClassLoader child;
		try
		{
			child = new URLClassLoader(new URL[]
			{ new URL("file:///c:\\Development\\Workspace New\\gustini.library\\gustini.library.statistics\\target\\gustini.library.statistics-1.0.8-jar-with-dependencies.jar") },
						JavaReflectionRunner.class.getClassLoader());
			Class<?> clazz = Class.forName("org.gustini.library.statistics.paketstatistik.PaketStatistikUpdaterMain",
						true, child);
			Object messageObj = clazz.newInstance();

			Method m;
			Method[] methods = clazz.getMethods();
			for (Method me : methods)
			{
				if (me.getName().equals("startPaketStatistikUpdate"))
				{
					m = me;
					String methodName = "startPaketStatistikUpdate";
					System.out.println("parametercount: " + m.getParameterCount());
					// clazz.getMethod(methodName, String.class);

					String configFilePath = "c:\\Development\\Workspace New\\gustini.library\\gustini.library.statistics\\resources\\config\\paketzaehler.ini";
					String generalSettingsInipath = "c:\\Development\\Workspace New\\gustini.library\\gustini.library.statistics\\resources\\config\\generalsettings.ini";
					m.setAccessible(true);
					m.invoke(messageObj, configFilePath, generalSettingsInipath);
				}
				System.out.println(me.getName());
			}

		} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException
					| SecurityException | IllegalArgumentException | InvocationTargetException e)
		{

			e.printStackTrace();
		}

	}

}
