package org.moon.test.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.junit.Test;

public class IOTest {

    public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        print("please input filename:");
        String text = br.readLine();
        FileWriter fw = new FileWriter(new File("target/" + text), true);
        while (true) {
            print("please input content:");
            text = br.readLine();
            if ("exit".equalsIgnoreCase(text)) {
                break;
            }
            fw.write(text);
        }
        fw.flush();
        fw.close();
    }

    @Test
    public void testCopyFile() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("text.dat");
        FileOutputStream fos = new FileOutputStream(new File("target/ioCopy.dat"));
        copy(is, fos);
        is.close();
        fos.close();
    }

    public static boolean copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int index = 0;
        while (-1 != (index = input.read(buffer))) {
            output.write(buffer, 0, index);
        }
        return true;
    }

    private static void print(String text) {
        System.out.print(text);
    }

}
