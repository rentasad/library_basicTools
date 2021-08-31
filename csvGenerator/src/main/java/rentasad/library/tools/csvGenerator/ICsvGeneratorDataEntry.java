package rentasad.library.tools.csvGenerator;

import rentasad.library.tools.exceptions.UnknownFieldIntException;

public interface ICsvGeneratorDataEntry
{
	/**
	 *  Methode ist Voraussetzung faer das Funktionieren des CSV-Generators.
	 *  Gibt das Value der zugehaerigen Spalte zuraeck
	 * @param colNumber
	 * @return
	 * @throws UnknownFieldIntException 
	 */
	public String getValueEntry(int colNumber) throws UnknownFieldIntException;
	/**
	 * 
	 * Description: Gibt den Header der CSV-Datei als StringArray zuraeck
	 * 
	 * @return NULL wenn kein Header definiert ist.
	 * Creation: 20.10.2015 by mst
	 */
	public String[] getCSVHeaderStringArray();
}
