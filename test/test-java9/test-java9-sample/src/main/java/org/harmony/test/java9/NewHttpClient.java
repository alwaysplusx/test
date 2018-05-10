package org.harmony.test.java9;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NewHttpClient {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // XXX just for show up, jdk.incubator.http
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI("http://www.baidu.com"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandler.asString());
        System.out.println(response.body());
    }

}
