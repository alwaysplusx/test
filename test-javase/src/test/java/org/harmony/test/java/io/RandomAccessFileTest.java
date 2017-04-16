package org.harmony.test.java.io;

import java.io.File;
import java.io.RandomAccessFile;

@SuppressWarnings("all")
public class RandomAccessFileTest {

    public static void main(String[] args) throws Exception {
        String path = "src/main/resources/text.dat";
        RandomAccessFile rf = new RandomAccessFile(new File(path), "rw");
        //MappedByteBuffer
        System.out.println(rf.getFilePointer());
        /*rf.writeChar('a');
        rf.writeChar('b');
        rf.writeChar('c');
        rf.seek(2);
        rf.writeChar('d');
        rf.seek(0);
        StringBuffer sb = new StringBuffer();
        sb.append(rf.readChar()).append(rf.readChar()).append(rf.readChar());
        rf.readChar();
        System.out.println(sb.toString());*/
        
    }
}
