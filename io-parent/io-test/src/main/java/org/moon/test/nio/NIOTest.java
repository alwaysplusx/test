package org.moon.test.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@SuppressWarnings("all")
public class NIOTest {

	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public static void copyFile(String srcFile, String destFile) throws IOException {
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(destFile);
		FileChannel fic = fis.getChannel();
		FileChannel foc = fos.getChannel();
		// fic.transferTo(0, fic.size(), foc);
		ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
		while (true) {
			buffer.clear();
			if(fic.read(buffer) == -1) break;
			buffer.flip();
			foc.write(buffer);
		}
	}
	
	public static void main(String[] args) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
		FileInputStream fis = new FileInputStream("d:/a.wsdl");
		System.out.println(buffer.position());
		fis.getChannel().read(buffer);
		System.out.println(buffer.position());
		buffer.flip();
		System.out.println(buffer.position());
	}
	
}
