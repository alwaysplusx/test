package org.moon.test.hc;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ConnectionReleaseTest {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(new HttpGet("http://www.baidu.com"));
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		System.out.println(entity.getContentType());
		System.out.println(entity.getContentEncoding());
		System.out.println(entity.getContentLength());
	}
	
}
