package org.moon.test.barcode;

import java.io.FileOutputStream;
import java.io.IOException;

import org.moon.test.barcode.Code128;

@SuppressWarnings("resource")
public class Code128Test {

    public void testCode128() throws IOException {
        new FileOutputStream("target/barcode1.jpg").write(Code128.toByteArray("W1407170001"));
        new FileOutputStream("target/barcode2.jpg").write(Code128.toByteArray("W1407170002"));
        new FileOutputStream("target/barcode3.jpg").write(Code128.toByteArray("W1407170003"));
        new FileOutputStream("target/barcode4.jpg").write(Code128.toByteArray("W1407170004"));
    }

}