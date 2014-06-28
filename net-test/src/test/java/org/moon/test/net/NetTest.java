package org.moon.test.net;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class NetTest {

	public static void main(String[] args) throws Exception {
		URLConnection connection = new URL("http://www.baidu.com").openConnection();
		InputStream content = (InputStream) connection.getContent();
		byte[] buffer = new byte[1024 * 1024];
		content.read(buffer);
		System.out.println(new String(buffer));
	}

}
