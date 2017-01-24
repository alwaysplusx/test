package com.harmony.test.java.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harmony.test.java.io.NIOTcpServer.Handler;

/**
 * @author wuxii@foxmail.com
 */
public class NIOServerAndClientTest {

    // private static final Logger clientLog =
    // LoggerFactory.getLogger("Client");

    // private static int serverCount = 0;
    // private static int clientCount = 0;

    public static void main(String[] args) throws Exception {
        new Thread(new NIOTcpServer(instance.new ServerHandler())).start();
    }

    private static final NIOServerAndClientTest instance = new NIOServerAndClientTest();

    public class ServerHandler implements Handler {

        private final Logger log = LoggerFactory.getLogger(ServerHandler.class);

        public void handleAccept(SelectionKey key) throws IOException {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            log.info("Server: accept client socket " + socketChannel);
            socketChannel.configureBlocking(false);
            socketChannel.register(key.selector(), SelectionKey.OP_READ);
        }

        public void handleRead(SelectionKey key) throws IOException {
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            SocketChannel socketChannel = (SocketChannel) key.channel();
            while (true) {
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    log.info("Server: data = " + new String(byteBuffer.array(), 0, readBytes));
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                    break;
                }
            }
            socketChannel.close();
        }

        public void handleWrite(SelectionKey key) throws IOException {
            ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
            byteBuffer.flip();
            SocketChannel socketChannel = (SocketChannel) key.channel();
            socketChannel.write(byteBuffer);
            if (byteBuffer.hasRemaining()) {
                key.interestOps(SelectionKey.OP_READ);
            }
            byteBuffer.compact();
        }

    }

}
