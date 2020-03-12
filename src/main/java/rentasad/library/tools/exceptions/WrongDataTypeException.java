package rentasad.library.tools.exceptions;

public class WrongDataTypeException extends Exception
{
	String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1540597512534793531L;

	public WrongDataTypeException(
									String message)
	{
		super();
		this.message = message;
	}

	public WrongDataTypeException()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		return this.message;
	}
	
	

}
