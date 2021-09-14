/**
 * 
 */
package rentasad.library.tools.adressTools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gustini GmbH (2019)
 * Creation: 04.07.2019
 * gustini.library.basicTools
 * rentasad.library.tools.adressTools
 * 
 * @author Matthias Staud
 *
 *
 * Description: Beinhaltet Hilfsmittel zur Behandlung von Adressen
 *
 */
public class AdressTool
{
    
    
        /**
         * 
         * Description: Splitt Adress  between street and housenumber
         * 
         * @param adressline
         * @return
         * Creation: 04.07.2019 by mst
         */
       public static StreetObject getStreetObjectFromAddressLine(String adressline)
       {
           String patternAdresslineSplit = "^([A-Za-zäöüß\\s\\d.,-]+?)\\s*([\\d\\s]+(?:\\s?[-|+/]\\s?\\d+)?\\s*[A-Za-z]?)?"; 
           Pattern streetPattern = Pattern.compile(patternAdresslineSplit);
           Matcher mStreet = streetPattern.matcher(adressline);
           StreetObject streetObject = null; 
           if (mStreet.matches())
           {
               if (mStreet.groupCount() == 2)
               {
                   streetObject = new StreetObject(mStreet.group(1),mStreet.group(2) );
               }
           }
           return streetObject;
       }
}
