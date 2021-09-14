package rentasad.library.basicTools.ftpUtil.objects;

public class FtpSettings
{
	private String ftpHost;
	private String ftpUsername;
	private String ftpPassword;
	private int ftpPort;
	
	private String startDir;
	
	/**
	 * Default FTP Port is 21
	 */
	public FtpSettings()
	{
		this.ftpPort = 21;
	}
	
	/**
	 * Default FTP Port is 21
	 * @param ftpServer
	 * @param ftpUsername
	 * @param ftpPassword
	 */
	public FtpSettings(
						final String ftpServer,
						final String ftpUsername,
						final String ftpPassword)
	{
		super();
		this.ftpHost = ftpServer;
		this.ftpUsername = ftpUsername;
		this.ftpPassword = ftpPassword;
		this.ftpPort = 21;
	}


	public String getFtpHost()
	{
		return ftpHost;
	}


	public void setFtpHost(final String ftpHost)
	{
		this.ftpHost = ftpHost;
	}


	public String getFtpUsername()
	{
		return ftpUsername;
	}


	public void setFtpUsername(final String ftpUsername)
	{
		this.ftpUsername = ftpUsername;
	}


	public String getFtpPassword()
	{
		return ftpPassword;
	}


	public void setFtpPassword(final String ftpPassword)
	{
		this.ftpPassword = ftpPassword;
	}


	public int getFtpPort()
	{
		return ftpPort;
	}


	public void setFtpPort(int ftpPort)
	{
		this.ftpPort = ftpPort;
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
	
	
	
}