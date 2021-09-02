package rentasad.library.basicTools.ftpUtil.objects;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ftp.FTPFile;

public class FtpFileStatus
{
	
	
	FTPFile ftpFile;
	File localFile;
	private String fileName;
	private boolean existFTPFile;
	private boolean existLocalFile;
	private int ftpServerTimeDifference;
	private Calendar timestampFtpFile;
	private Calendar timestampLocalFile;
	private Date timeBetweenFiles;
	private int maxAgeInMinutes;
	private boolean semaphoreExist;
	private boolean crcCheckNeeded;
	
	
	
	
	public FtpFileStatus(
							FTPFile ftpFile,
							File localFile)
	{
		super();
		this.ftpFile = ftpFile;
		this.localFile = localFile;
	}

	
	
	
	/**
	 * @return the maxAgeInMinutes
	 */
	public int getMaxAgeInMinutes()
	{
		return maxAgeInMinutes;
	}




	/**
	 * @param maxAgeInMinutes the maxAgeInMinutes to set
	 */
	public void setMaxAgeInMinutes(int maxAgeInMinutes)
	{
		this.maxAgeInMinutes = maxAgeInMinutes;
	}




	/**
	 * @return the ftpFile
	 */
	public FTPFile getFtpFile()
	{
		return ftpFile;
	}

	/**
	 * @param ftpFile the ftpFile to set
	 */
	public void setFtpFile(FTPFile ftpFile)
	{
		this.ftpFile = ftpFile;
	}

	/**
	 * @return the localFile
	 */
	public File getLocalFile()
	{
		return localFile;
	}

	/**
	 * @param localFile the localFile to set
	 */
	public void setLocalFile(File localFile)
	{
		this.localFile = localFile;
	}

	/**
	 * @return the existFTPFile
	 */
	public boolean isExistFTPFile()
	{
		return existFTPFile;
	}

	/**
	 * @param existFTPFile the existFTPFile to set
	 */
	public void setExistFTPFile(boolean existFTPFile)
	{
		this.existFTPFile = existFTPFile;
	}

	/**
	 * @return the existLocalFile
	 */
	public boolean isExistLocalFile()
	{
		return existLocalFile;
	}

	/**
	 * @param existLocalFile the existLocalFile to set
	 */
	public void setExistLocalFile(boolean existLocalFile)
	{
		this.existLocalFile = existLocalFile;
	}

	

	/**
	 * @return the timestampFtpFile
	 */
	public Calendar getTimestampFtpFile()
	{
		return timestampFtpFile;
	}

	/**
	 * @param timestampFtpFile the timestampFtpFile to set
	 */
	public void setTimestampFtpFile(Calendar timestampFtpFile)
	{
		this.timestampFtpFile = timestampFtpFile;
	}



	/**
	 * @return the timestampLocalFile
	 */
	public Calendar getTimestampLocalFile()
	{
		return timestampLocalFile;
	}

	/**
	 * @param timestampLocalFile the timestampLocalFile to set
	 */
	public void setTimestampLocalFile(Calendar timestampLocalFile)
	{
		this.timestampLocalFile = timestampLocalFile;
	}

	/**
	 * @return the timeBetweenFiles
	 */
	public Date getTimeBetweenFiles()
	{
		return timeBetweenFiles;
	}

	/**
	 * @param timeBetweenFiles the timeBetweenFiles to set
	 */
	public void setTimeBetweenFiles(Date timeBetweenFiles)
	{
		this.timeBetweenFiles = timeBetweenFiles;
	}

	/**
	 * Alter in Millisekunden
	 * @return the ageOfFtpFile
	 * Gibt bei unbekanntem Alter null zurueck
	 */
	public Long getAgeOfFtpFile()
	{
		if (this.timestampFtpFile == null)
		{
			return null;
		}else
		{
			long timeNow = Calendar.getInstance().getTimeInMillis();
			long timeFtpFile = this.timestampFtpFile.getTimeInMillis();
			return timeNow - timeFtpFile;
		}

	}

	/**
	 * Alter in Millisekunden
	 * @return the ageOfLocalFile
	 */
	public Long getAgeOfLocalFile()
	{
		long timeNow = Calendar.getInstance().getTimeInMillis();
		long timeLocalFile = this.timestampLocalFile.getTimeInMillis();
		return timeNow - timeLocalFile;
	}




	/**
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}




	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}




	/**
	 * Differenz zwischen lokaler Systemzeit und FTP-Serverzeit in Minuten
	 * @return the ftpServerTimeDifference
	 */
	public int getFtpServerTimeDifference()
	{
		return ftpServerTimeDifference;
	}




	/**
	 * Differenz zwischen lokaler Systemzeit und FTP-Serverzeit in Minuten
	 * @param ftpServerTimeDifference the ftpServerTimeDifference to set
	 */
	public void setFtpServerTimeDifference(int ftpServerTimeDifference)
	{
		this.ftpServerTimeDifference = ftpServerTimeDifference;
	}




    /**
     * @return the semaphoreExist
     */
    public boolean isSemaphoreExist()
    {
        return semaphoreExist;
    }




    /**
     * @param semaphoreExist the semaphoreExist to set
     */
    public void setSemaphoreExist(boolean semaphoreExist)
    {
        this.semaphoreExist = semaphoreExist;
    }




    /**
     * @return the crcCheckNeeded
     */
    public boolean isCrcCheckNeeded()
    {
        return crcCheckNeeded;
    }




    /**
     * @param crcCheckNeeded the crcCheckNeeded to set
     */
    public void setCrcCheckNeeded(boolean crcCheckNeeded)
    {
        this.crcCheckNeeded = crcCheckNeeded;
    }

	
	
	
}
