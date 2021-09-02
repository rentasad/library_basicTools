package rentasad.library.basicTools.ftpUtil.Exceptions;

public class FtpRemotePathNotExistException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2568478110896800121L;
	private String message;

	public FtpRemotePathNotExistException(
											String message)
	{
		super();
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		// TODO Auto-generated method stub
		return this.message;
	}
	
}
