/**
 * 
 */
package rentasad.library.tools.adressTools;

/**
 * Gustini GmbH (2019)
 * Creation: 04.07.2019
 * gustini.library.basicTools
 * rentasad.library.tools.adressTools
 * 
 * @author Matthias Staud
 *
 *
 * Description:Datenobjekt für Straße und Hausnummer
 *
 */
public class StreetObject
{
    private String street;
    private String houseNumber;
    
    /**
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }
    /**
     * @param street the street to set
     */
    public void setStreet(String street)
    {
        this.street = street;
    }
    /**
     * @return the houseNumber
     */
    public String getHouseNumber()
    {
        return houseNumber;
    }
    /**
     * @param houseNumber the houseNumber to set
     */
    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }
    /**
     * @param street
     * @param houseNumber
     */
    public StreetObject(
                        String street,
                        String houseNumber)
    {
        super();
        this.street = street;
        this.houseNumber = houseNumber;
    }

}
