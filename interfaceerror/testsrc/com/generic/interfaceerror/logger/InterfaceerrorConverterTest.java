package com.generic.interfaceerror.logger;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.dataimport.batch.converter.impl.DefaultImpexConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Ensure column are correctly added to the impex definition
 *
 * @author capgemini on 09/04/2014.
 */

@UnitTest
public class InterfaceerrorConverterTest {
    private DefaultImpexConverter converter;

    @Before
    public void setUp() {
        converter = new InterfaceerrorImpexConverter();
    }

    @Test
    public void testSetHeader() {
        converter.setHeader("INSERT_UPDATE ;");
        assertEquals("INSERT_UPDATE ;;;", converter.getHeader());
    }

    @Test
    public void testSetHeaderWithJavaCode() {
        final String javaCode = "some java code with a semicolon;\n";
        converter.setHeader(javaCode + "INSERT_UPDATE ;");
        assertEquals(javaCode + "INSERT_UPDATE ;;;", converter.getHeader());
    }

    @Test
    public void testSetHeaderWithTypo() {
        converter.setHeader("UPDTE ;");
        assertNull(" Header should be null when no mode has a typo", converter.getHeader());
    }

    @Test
    public void testSetHeaderWithoutMode() {
        converter.setHeader(" ;");
        assertNull(" Header should be null when no mode is set", converter.getHeader());
    }


    /**
     * Un test pour v�rifier que l'�chappement fonctionne bien (ce qui n'est pas le cas OOB)
     */
    @Test
    public void testRowConversionContainingSemicol() {
        converter.setImpexRow(" ;plop{1}{2}; t{3} ;{4};");
        final Map<Integer, String> row = new HashMap<>();

        row.put(Integer.valueOf(1), "A;B");
        row.put(Integer.valueOf(2), ";C ;D \r\n EF");

        row.put(Integer.valueOf(3), " zou\" hey");

        row.put(Integer.valueOf(4), " &amp;pizza");


        final String result = converter.convert(row, Long.valueOf(15));

        final String expected = " ;;;\"plopA;B;C ;D \r\n EF\";\" t zou\"\" hey \";\" &amp;pizza\"";

        assertEquals(expected, result);

    }


}
