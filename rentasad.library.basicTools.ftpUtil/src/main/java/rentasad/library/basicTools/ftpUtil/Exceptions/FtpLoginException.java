package rentasad.library.basicTools.ftpUtil.Exceptions;

public class FtpLoginException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	

	public FtpLoginException(
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
		System.err.println(this.message);
		return super.getMessage();
	}

}
