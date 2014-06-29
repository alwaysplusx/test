package org.moon.test.net;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.moon.test.io.IOTest;

public class NetTest {

	public static void main(String[] args) throws Exception {
		URLConnection connection = new URL("http://www.baidu.com").openConnection();
		InputStream content = (InputStream) connection.getContent();
		String string = IOTest.toString(content);
		System.out.println(string);
	}

}
