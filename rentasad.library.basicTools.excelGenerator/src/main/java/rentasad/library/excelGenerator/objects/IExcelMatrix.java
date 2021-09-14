package rentasad.library.excelGenerator.objects;

/**
 * 
 * @author Matthi
 * Klasse 
 *
 */
public interface IExcelMatrix
{
	/**
	 * getExcelGeneratorValueEntrys gibt ein Array mit ExcelGeneratorValueEntrys zurueck.
	 * Diese Eintraege geben die Tabellenstruktur und Inhalte des Datenobjektes zurueck.
	 * @return
	 */
	public ExcelGeneratorValueEntry[] getExcelGeneratorValueEntries();
	
}
