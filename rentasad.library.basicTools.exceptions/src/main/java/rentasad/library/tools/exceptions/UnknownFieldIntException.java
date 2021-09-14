package rentasad.library.tools.exceptions;

public class UnknownFieldIntException extends Exception
{

	/**
     * 
     */
    public UnknownFieldIntException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    public UnknownFieldIntException(
                                    String arg0,
                                    Throwable arg1,
                                    boolean arg2,
                                    boolean arg3)
    {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     * @param arg1
     */
    public UnknownFieldIntException(
                                    String arg0,
                                    Throwable arg1)
    {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public UnknownFieldIntException(
                                    String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public UnknownFieldIntException(
                                    Throwable arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = -4236390280885967166L;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getMessage()
	{
		// TODO Auto-generated method stub
		return "Es wurde eine unbekannte Feld-ID als Parameter aebergeben. ";
	}

}
