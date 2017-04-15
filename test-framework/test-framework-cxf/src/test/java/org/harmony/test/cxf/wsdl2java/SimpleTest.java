package org.harmony.test.cxf.wsdl2java;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import org.harmony.test.cxf.JaxWsClientManager;
import org.harmony.test.cxf.JaxWsServerManager;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleTest {

    @BeforeClass
    public static void setUp() {
        JaxWsServerManager.getInstance().publish(org.harmony.test.cxf.Simple.class, "http://localhost:8080/test", new org.harmony.test.cxf.Simple() {
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
        Thread.sleep(1000);
    }

}
