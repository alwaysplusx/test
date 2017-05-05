package org.harmony.test.framework.grpc;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.harmony.test.framework.grpc.HelloReply;

/**
 * @author wuxii@foxmail.com
 */
public class HelloWorldTest {

    private static final int port = 9999;
    private static HelloWorldServer server;

    @BeforeClass
    public static void beforeClass() throws IOException {
        server = new HelloWorldServer(port);
        server.start();
    }

    @Test
    public void test() {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", port);
        HelloReply reply = client.greet("World");
        assertEquals("Hello World", reply.getMessage());
    }

    @AfterClass
    public static void afterClass() {
        server.stop();
    }

}
