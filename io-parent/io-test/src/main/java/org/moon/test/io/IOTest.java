package org.moon.test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOTest {

    public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static boolean copyFile(String srcFile, String destFile) throws IOException {
        return copy(new FileInputStream(new File(srcFile)), new FileOutputStream(new File(destFile)));
    }

    public static boolean copy(InputStream input, OutputStream output) throws IOException {
        // org.apache.commons.io.IOUtils.copy(input, output);
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int index = 0;
        while (-1 != (index = input.read(buffer))) {
            output.write(buffer, 0, index);
        }
        return true;
    }

    public static boolean copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        int index = 0;
        while (-1 != (index = input.read(buffer))) {
            output.write(buffer, 0, index);
        }
        return true;
    }

    public static String toString(InputStream input) throws IOException {
        StringWriter sw = new StringWriter();
        copy(new InputStreamReader(input), sw);
        return sw.toString();
    }

}
