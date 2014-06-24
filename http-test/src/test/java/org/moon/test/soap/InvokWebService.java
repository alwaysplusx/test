package org.moon.test.soap;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class InvokWebService {

	public static void main(String[] args) throws Exception {

		String soapData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:jmc.com.cn:ets\">   "
							+ "<soapenv:Header/>   "
							+ "<soapenv:Body>      "
								+ "<urn:mt_E201_request>"
									+ "<MBLNR>4900033476</MBLNR>"
									+ "<MJAHR>2014</MJAHR>"
									+ "<WERKS>26FD</WERKS>"
								+ "</urn:mt_E201_request>"
							+ "</soapenv:Body>"
						+ "</soapenv:Envelope>";
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope("192.28.124.79", 50000), new UsernamePasswordCredentials("ELESPI", "12341234"));
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpEntity entity = new StringEntity(soapData);
		HttpPost post = new HttpPost("http://192.28.124.79:50000/sap/xi/engine?type=entry&version=3.0&Sender.Service=ETS&Interface=urn:jmc.com.cn:ets%5Esi_E201_outbound");
		post.setEntity(entity);
		CloseableHttpResponse response = httpClient.execute(post);
		System.out.println(EntityUtils.toString(response.getEntity()));
	}
}
