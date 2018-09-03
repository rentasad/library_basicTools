package org.gustini.library.tools.commandExecutionTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.io.IOUtils;

/**
 *
 * Gustini GmbH (2015)
 * Creation: 22.01.2015
 * Rentasad Library
 * rentasad.lib.tools
 *
 * @author Matthias Staud
 *
 *
 *         Description:
 *         fuehrt ein uebergebenes DOS-Komando aus
 *         Ohne Ausgabe wiederzugeben.
 */
public class CommandExecuter
{
    public static final String PARAMETER_JOB_NAME = "JOB_NAME";
    public static final String PARAMETER_START_DIR = "START_DIR";
    public static final String PARAMETER_JAR_PATH = "JAR_PATH";
    public static final String PARAMETER_JVM_PATH = "JVM_PATH";
    public static final String PARAMETER_VM_ARGUMENTS = "VM_ARGUMENTS";
    public static final String PARAMETER_START_CLASS = "START_CLASS";
    public static final String PARAMETER_CRON_EXPRESSION = "CRON_EXPRESSION";
    public static final String PARAMETER_RUNNING_ARGUMENTS = "RUNNING_ARGUMENTS";
    /**
     *
     * Description: Execute the given command
     *
     * @param command
     *            Das auszufuehrende CVS-Kommando
     *            Creation: 04.03.2016 by mst
     */
    public static synchronized void connect(String command)
    {
        try
        {
            System.out.println(command);
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            process.destroy();

        } catch (IOException e)
        {
            System.out.println("CVSCommandExecuter.java IOException :" + e);
            e.printStackTrace();
            // return null;
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * Description:
     *
     * @param jobConfiguration
     * Creation: 04.03.2016 by mst
     * @throws IOException
     * @throws InterruptedException
     */
    public static Process startJobName(Map<String, String> jobConfigMap) throws IOException, InterruptedException
    {
        File javaRuntimeExecuteFile = new File(jobConfigMap.get(PARAMETER_JVM_PATH));
        if (!javaRuntimeExecuteFile.exists())
        {
            throw new FileNotFoundException(jobConfigMap.get(PARAMETER_JVM_PATH));
        }
/*
 * start %JDKPATH%\javaw.exe -classpath %MYCLASSPATH% %STARTCLASS% %ARGUMENTS%
 */

        String jvmPath = jobConfigMap.get(PARAMETER_JVM_PATH);
        String jarFilePath = jobConfigMap.get(PARAMETER_JAR_PATH);
        String startClass = jobConfigMap.get(PARAMETER_START_CLASS);
        String startDir = jobConfigMap.get(PARAMETER_START_DIR);
        String vmArguments= jobConfigMap.get(PARAMETER_VM_ARGUMENTS);
        String runningArguments = jobConfigMap.get(PARAMETER_RUNNING_ARGUMENTS);
        String command = "cmd /c " + jvmPath + " " + vmArguments + " -classpath " +jarFilePath + " " + startClass + " " + runningArguments;
        System.out.println(command);
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(command, null, new File(startDir));

        proc.waitFor();
        proc.destroy();
        return proc;
    }
    /**
     *
     * Description:Konvertiert einen InputStream zu einem String
     *
     * @param inputStream
     * @return
     * Creation: 10.03.2016 by mst
     * @throws IOException
     */
    public static String getOutputStringFromOutputStream(InputStream inputStream) throws IOException
    {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer);
        return writer.toString();

    }
}
