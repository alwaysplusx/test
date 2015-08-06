package org.moon.test.pdfbox;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class PrinterTest {

    @Test
    @Ignore
    public void testPrinterStringString() {
        
    }

    @Test
    public void testPrinterString() throws Exception {
        assertEquals(true, Printer.print("src/test/resources/print.pdf"));
    }

}
