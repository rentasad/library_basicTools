package gustini.library.tools.asciigenerationtool;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.gustini.library.tools.asciigenerationtool.AsciiGenerator;
import org.gustini.library.tools.asciigenerationtool.objects.AsciiDescriptionObject;
import org.gustini.library.tools.asciigenerationtool.objects.AsciiFieldFormatEnum;
import org.junit.Test;

public class AsciiGeneratorTest
{

    @Test
    /**
     * 
     * Description:Testet die Validierungsfunktion mit Beispielstrings
     * 
     * @throws Exception
     *             Creation: 17.12.2015 by mst
     */
    public void testIsValueValid() throws Exception
    {

        assertTrue(AsciiGenerator.isValueValid("2", new AsciiDescriptionObject(0, 1, 1, "Versendung = 2", AsciiFieldFormatEnum.INTEGER)));
        assertFalse(AsciiGenerator.isValueValid("22", new AsciiDescriptionObject(0, 1, 1, "Versendung = 2", AsciiFieldFormatEnum.INTEGER)));
        assertFalse(AsciiGenerator.isValueValid("A", new AsciiDescriptionObject(0, 1, 1, "Versendung = 2", AsciiFieldFormatEnum.INTEGER)));
        assertTrue(AsciiGenerator.isValueValid(" ", new AsciiDescriptionObject(0, 1, 1, "Versendung = 2", AsciiFieldFormatEnum.BLANK)));
        assertFalse(AsciiGenerator.isValueValid("", new AsciiDescriptionObject(0, 3, 3, "Versendung = 2", AsciiFieldFormatEnum.BLANK)));
        assertTrue(AsciiGenerator.isValueValid("      ", new AsciiDescriptionObject(0, 47, 52, "leer", AsciiFieldFormatEnum.BLANK)));
        assertFalse(AsciiGenerator.isValueValid("       ", new AsciiDescriptionObject(0, 47, 52, "leer", AsciiFieldFormatEnum.BLANK)));
        assertTrue(AsciiGenerator.isValueValid("9876543210", new AsciiDescriptionObject(0, 47, 56, "leer", AsciiFieldFormatEnum.LONG)));

    }

    @Test 
    public void testGenerateAsciFile() throws Exception
    {
        
        
//        AsciiGenerator.generateAsciFile("ressources/unitTests/asciiGenerator/asciTestFile.txt", null);
    }

    @Test
    public void testFillIntegerToLength() throws Exception
    {
        String result = AsciiGenerator.fillIntegerToLength("1", 6, true);
        assertTrue("000001".equals(result));
        
        result = AsciiGenerator.fillIntegerToLength("211", 6, true);
        assertTrue("000211".equals(result));
    }

    @Test
    public void testFillStringToLengthRightAligned() throws Exception
    {
        String result = AsciiGenerator.fillStringToLengthRightAligned("HA", 6);
        assertTrue("    HA".equals(result));
    }

}
