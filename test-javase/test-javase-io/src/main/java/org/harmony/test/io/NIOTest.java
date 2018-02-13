package org.harmony.test.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NIOTest {

    public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    @Test
    public void testCopyFile() throws Exception {

        FileInputStream fis = new FileInputStream(ClassLoader.getSystemResource("text.dat").getFile());
        FileOutputStream fos = new FileOutputStream("target/nioCopy.dat");

        FileChannel fic = fis.getChannel();
        FileChannel foc = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);

        while (fic.read(buffer) != -1) {
            buffer.flip();// 翻转位置指针
            foc.write(buffer);
            buffer.clear();// 清空buffer
        }

        fic.close();
        foc.close();
        fis.close();
        fos.close();
    }

}
