package rentasad.library.tools.commandExecutionTool.objects;

/**
 *
 * Gustini GmbH (2016)
 * Creation: 04.03.2016
 * Library
 * rentasad.library.tools.commandExecutionTool.objects
 *
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class JavaJobConfiguration
{


    public static final String PARAMETER_JOB_NAME = "JOB_NAME";
    public static final String PARAMETER_START_DIR = "START_DIR";
    public static final String PARAMETER_JAR_PATH = "JAR_PATH";
    public static final String PARAMETER_JVM_PATH = "JVM_PATH";
    public static final String PARAMETER_VM_ARGUMENTS = "VM_ARGUMENTS";
    public static final String PARAMETER_START_CLASS = "START_CLASS";

    private String jobName,startDir, jarPath, jvmPath, vmArguments;



    /**
     * @return the jobName
     */
    public String getJobName()
    {
        return jobName;
    }

    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    /**
     * @return the startDir
     */
    public String getStartDir()
    {
        return startDir;
    }

    /**
     * @param startDir the startDir to set
     */
    public void setStartDir(String startDir)
    {
        this.startDir = startDir;
    }

    /**
     * @return the jarPath
     */
    public String getJarPath()
    {
        return jarPath;
    }

    /**
     * @param jarPath the jarPath to set
     */
    public void setJarPath(String jarPath)
    {
        this.jarPath = jarPath;
    }

    /**
     * @return the jvmPath
     */
    public String getJvmPath()
    {
        return jvmPath;
    }

    /**
     * @param jvmPath the jvmPath to set
     */
    public void setJvmPath(String jvmPath)
    {
        this.jvmPath = jvmPath;
    }

    /**
     * @return the vmArguments
     */
    public String getVmArguments()
    {
        return vmArguments;
    }

    /**
     * @param vmArguments the vmArguments to set
     */
    public void setVmArguments(String vmArguments)
    {
        this.vmArguments = vmArguments;
    }

}
