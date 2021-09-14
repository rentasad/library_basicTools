package rentasad.library.excelGenerator.exceptions;

public class SheetNotFoundException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sheetName;
	
	 public SheetNotFoundException(
									String sheetName)
	{
		super();
		this.sheetName = sheetName;
	}

	@Override
	public String getMessage()
	{
		// TODO Auto-generated method stub
		return "Folgendes Sheet wurde nicht gefunden: " + sheetName;
	}

		
	
	

}
