package org.harmony.test.framework.grpc;

import java.io.IOException;

import org.harmony.test.framework.grpc.HelloWorldServer.GreeterImpl;

import com.harmony.test.framework.grpc.FooGrpc;
import com.harmony.test.framework.grpc.FooGrpc.FooBlockingStub;
import com.harmony.test.framework.grpc.FooReply;
import com.harmony.test.framework.grpc.FooRequest;
import com.harmony.test.framework.grpc.GreeterGrpc;
import com.harmony.test.framework.grpc.GreeterGrpc.GreeterBlockingStub;
import com.harmony.test.framework.grpc.HelloReply;
import com.harmony.test.framework.grpc.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @author wuxii@foxmail.com
 */
public class MulitServerTest {

    private static final int port = 9999;

    public static void main(String[] args) throws IOException {
        Server server = ServerBuilder.forPort(port)//
                .addService(new GreeterImpl())//
                .addService(new FooImpl())//
                .build();//
        server.start();

        ManagedChannel channel = ManagedChannelBuilder//
                .forAddress("127.0.0.1", port)//
                .usePlaintext(true)//
                .build();

        GreeterBlockingStub greeter = GreeterGrpc.newBlockingStub(channel);
        HelloRequest helloRequest = HelloRequest//
                .newBuilder()//
                .setName("wuxii")//
                .build();
        HelloReply helloReply = greeter.sayHello(helloRequest);
        System.out.println(helloReply.getMessage());

        FooBlockingStub foo = FooGrpc.newBlockingStub(channel);
        FooRequest fooRequest = FooRequest//
                .newBuilder()//
                .setName("wuxii")//
                .build();
        FooReply fooReply = foo.test(fooRequest);
        System.out.println(fooReply.getMessage());
    }

    static class FooImpl extends FooGrpc.FooImplBase {

        @Override
        public void test(FooRequest request, StreamObserver<FooReply> responseObserver) {
            FooReply reply = FooReply.newBuilder().setMessage("foo " + request.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

    }

}
