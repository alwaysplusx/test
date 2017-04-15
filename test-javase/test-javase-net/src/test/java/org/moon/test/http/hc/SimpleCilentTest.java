package org.moon.test.http.hc;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class SimpleCilentTest {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = null;
        String responseBody = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://www.163.com");
            responseBody = httpClient.execute(httpGet, new BasicResponseHandler());
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
    }

}
