package org.moon.test.http.soap;

import java.io.IOException;

import javax.xml.ws.Endpoint;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.http.ws.HelloWs;

public class InvokWebService {

    static String address = "http://localhost:8081/test";
    
    Endpoint publish;
    
    @Before
    public void setUp() {
        publish = Endpoint.publish(address, new HelloWs());
    }

    @After
    public void tearDown() {
        publish.stop();
    }
    
    @Test
    public void testSoap() throws IOException {
        String soapData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.moon.test/ws/\">"
                           +"<soapenv:Header/>"
                           +"<soapenv:Body>"
                           +"<soap:sayHi>"
                                 +"<name>?</name>"
                              +"</soap:sayHi>"
                           +"</soapenv:Body>"
                        +"</soapenv:Envelope>";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpEntity entity = new StringEntity(soapData);
        HttpPost post = new HttpPost(address);
        BasicHeader contentType = new BasicHeader("Content-Type","text/xml; charset=UTF-8");
        post.setHeaders(new BasicHeader[] { contentType });
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
    
    @Test
    public void testAuthorization() throws Exception {
        String soapData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.moon.test/ws/\">"
                           +"<soapenv:Header/>"
                           +"<soapenv:Body>"
                           +"<soap:sayHi>"
                                 +"<name>one</name>"
                              +"</soap:sayHi>"
                           +"</soapenv:Body>"
                        +"</soapenv:Envelope>";
        /*CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope("localhost", 8081), new UsernamePasswordCredentials("abc", "123"));
        HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();*/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpEntity entity = new StringEntity(soapData);
        HttpPost post = new HttpPost(address);
        BasicHeader contentType = new BasicHeader("Content-Type","text/xml; charset=UTF-8");
        BasicHeader authorization = new BasicHeader("Authorization","Basic64Encode");
        post.setHeaders(new BasicHeader[] { contentType, authorization });
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }    
}
