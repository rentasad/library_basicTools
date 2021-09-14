package rentasad.library.tools.adressTools;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class AdressToolTest
{


    @Test
    public void testGetStreetObjectFromAddressLine() throws Exception
    {
        
        String[] tests = {
                "Marienweg 1n",
                "An der Autobahn A7 64-68",
                "Osterende 14 und 15",
                "Schwalbenweg 12, 13, 14",
                "Lerchenstraße 17 - 21",
                "Straße des 17. Juni 105",
                "Ackerweg 5 Hof 3 Gebäude 8",
                "Markt 10 - 12"
        };
        for (String adressline : tests)
        {
            StreetObject so = AdressTool.getStreetObjectFromAddressLine(adressline);
            System.out.println(String.format("Straße: %s, Hausnummer: %s", so.getStreet(), so.getHouseNumber()));
            assertNotNull(so);
        }
        

    }

}
