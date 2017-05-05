package org.harmony.test.framework.grpc;

import java.io.IOException;

import com.harmony.test.framework.grpc.GreeterGrpc;
import com.harmony.test.framework.grpc.HelloReply;
import com.harmony.test.framework.grpc.HelloRequest;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * 
 * @author wuxii@foxmail.com
 */
public class HelloWorldServer {

    private Server server;
    private int port;

    public HelloWorldServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                HelloWorldServer.this.stop();
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
            server = null;
        }
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
