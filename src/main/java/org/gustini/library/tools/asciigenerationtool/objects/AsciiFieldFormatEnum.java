package org.gustini.library.tools.asciigenerationtool.objects;
/**
 * 
 * Gustini GmbH (2015)
 * Creation: 16.12.2015
 * Library
 * org.gustini.library.tools.asciigenerationtool.objects
 * 
 * @author Matthias Staud
 *
 *
 * Description:Datensatzbeschreibungstypen fuer ASCII-Generator
 *
 */
public enum AsciiFieldFormatEnum
{
    /*
     * Leeres Feld
     */
    BLANK,
    /*
     * Nummerisch
     */
    NUM,
    /**
     * int
     */
    INTEGER,
    /**
     * Double
     */
    DOUBLE,
    /**
     * alpha-numerische Zeichen
     */
    ALN,
    /**
     * String
     */
    STRING,
    LONG;

}
