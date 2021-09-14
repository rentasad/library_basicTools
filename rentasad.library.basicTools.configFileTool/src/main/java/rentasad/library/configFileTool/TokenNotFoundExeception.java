package rentasad.library.configFileTool;

public class TokenNotFoundExeception extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		System.err.println("Angefordertes Token der Configuration wurde nicht gefunden.");
		return super.getMessage();
	}

}
