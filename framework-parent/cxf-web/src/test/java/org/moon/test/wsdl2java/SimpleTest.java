package org.moon.test.wsdl2java;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.moon.test.cxf.JaxWsClientManager;
import org.moon.test.cxf.JaxWsServerManager;

public class SimpleTest {

    @BeforeClass
    public static void setUp() {
        JaxWsServerManager.getInstance().publish(org.moon.test.cxf.Simple.class, "http://localhost:8080/test", new org.moon.test.cxf.Simple() {
            @Override
            public String sayHi(String name) {
                System.out.println("say hi to " + name);
                return "Hi " + name;
            }
        });
    }

    @Test
    public void testAsyncResponse() throws Exception {
        Simple simple = JaxWsClientManager.getInstance().create(Simple.class, "http://localhost:8080/test");
        Response<SayHiResponse> response = simple.sayHiAsync("wuxii");
        SayHiResponse sayHiResponse = response.get();
        assertEquals("Hi wuxii", sayHiResponse.getReturn());
    }

    @Test
    public void testAsyncHandler() throws Exception {
        Simple simple = JaxWsClientManager.getInstance().create(Simple.class, "http://localhost:8080/test");
        simple.sayHiAsync("wuxii", new AsyncHandler<SayHiResponse>() {
            @Override
            public void handleResponse(Response<SayHiResponse> res) {
                try {
                    String ret = res.get().getReturn();
                    System.err.println(ret);
                } catch (InterruptedException | ExecutionException e) {
                }
            }
        });
        Thread.sleep(1000 * 3);
    }

}
