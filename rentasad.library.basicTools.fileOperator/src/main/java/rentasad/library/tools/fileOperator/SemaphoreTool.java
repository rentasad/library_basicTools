/**
 * 
 */
package rentasad.library.tools.fileOperator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Gustini GmbH (2017)
 * Creation: 07.06.2017
 * Library
 * rentasad.library.tools.fileOperator
 * 
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 */
public class SemaphoreTool
{
    private String tempDir;
    private final String semaphoreId;
    private String semaphoreFilePath;

    /**
     * 
     */
    public SemaphoreTool(
                         String id)
    {
        this.tempDir = System.getProperty("java.io.tmpdir");
        this.semaphoreId = id;
        String fileName = this.semaphoreId + "_sempaphore.lock";
        semaphoreFilePath = tempDir + File.separator + fileName;
    }

    public boolean activateSemaphore() throws FileNotFoundException
    {
        boolean success;

        File semFile = new File(semaphoreFilePath);
        if (!semFile.exists())
        {
            PrintWriter writer = new PrintWriter(semFile);
            writer.println("Semaphore ID:" + this.semaphoreId);
            writer.println("Sempaphore to prevent duplicate executions....");
            writer.close();
            success = semFile.exists();
        } else
        {
            System.err.println("Semaphore already exist...");
            success = false;
        }

        return success;
    }

    /**
     * 
     * Description: return true if semaphore exist
     * 
     * @return
     *         Creation: 07.06.2017 by mst
     */
    public boolean existSemaphore()
    {
        return new File(semaphoreFilePath).exists();
    }

    public boolean removeSemaphore()
    {
        File semFile = new File(semaphoreFilePath);

        if (semFile.exists())
        {
            return new File(semaphoreFilePath).delete();
        } else
        {
            return true;
        }
    }

    /**
     * @return the tempDir
     */
    public String getTempDir()
    {
        return tempDir;
    }

    /**
     * @return the semaphoreFilePath
     */
    public String getSemaphoreFilePath()
    {
        return semaphoreFilePath;
    }
    
   

}
