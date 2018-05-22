package org.harmony.test.socket.keepalive;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        int port = 8008;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        Server.create(port).start();
        Thread.sleep(Long.MAX_VALUE);
    }

    public static Server create(int port) {
        Server server = new Server(port);
        server.init();
        return server;
    }

    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private final int port;
    private boolean running;

    private long timeout = 30 * 1000;
    private Thread connectionWatcher;

    protected Server(int port) {
        this.port = port;
    }

    protected void init() {
        this.connectionWatcher = new Thread(new ConnectionWatcher());
    }

    public void start() {
        running = true;
        connectionWatcher.start();
    }

    public void stop() {
        running = false;
        connectionWatcher.interrupt();
    }

    private class ConnectionWatcher implements Runnable {

        @Override
        public void run() {
            try (ServerSocket ss = new ServerSocket(port, 5)) {
                log.info("server socket run on port {} wait for client connect", port);
                while (running) {
                    Socket s = ss.accept();
                    log.info("one client connector at port {}", s.getPort());
                    new Thread(new SocketConnectionWorker(s)).start();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }

    }

    private class SocketConnectionWorker implements Runnable, Closeable, AutoCloseable {

        private Socket client;

        private long lastAccessTime;

        public SocketConnectionWorker(Socket s) {
            this.client = s;
            this.lastAccessTime = System.currentTimeMillis();
        }

        @Override
        public void run() {
            while (running) {
                if (System.currentTimeMillis() - lastAccessTime > timeout) {
                    log.info("close client socket at port {}", client.getPort());
                    try {
                        close();
                    } catch (IOException e) {
                        log.error("", e);
                    }
                    return;
                }
                echo();
            }
        }

        private void echo() {
            Object inObj = null;

            try {
                InputStream is = client.getInputStream();
                if (is.available() > 0) {
                    ObjectInputStream ois = new ObjectInputStream(is);
                    inObj = ois.readObject();
                    lastAccessTime = System.currentTimeMillis();
                    log.info("receive object from client({}): {}", client.getPort(), inObj);
                }
            } catch (Exception e) {
                log.error("", e);
            }

            if (inObj != null) {
                try {
                    OutputStream os = client.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject("echo");
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }

        @Override
        public void close() throws IOException {
            client.close();
        }

    }

}
