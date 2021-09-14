package rentasad.library.tools.asciigenerationtool.objects;
/**
 * 
 * Gustini GmbH (2015)
 * Creation: 17.12.2015
 * Library
 * rentasad.library.tools.asciigenerationtool.objects
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class AsciiGeneratorException extends Exception
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3856081723300756419L;

    private final String message;


    public AsciiGeneratorException(Throwable e)
    {
        super(e);
        this.message = e.getMessage();
    }

    /**
     * @param message
     */
    public AsciiGeneratorException(
                                  final String message)
    {
        super();
        this.message = message;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage()
    {
        return message;
    }
    
    
}
