package org.harmony.test.socket.keepalive;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        String address = "localhost";
        int port = 8008;
        if (args.length > 0) {
            address = args[0].trim();
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        Client client = Client.create(address, port);
        client.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("send>");
            String line = scanner.nextLine();
            if (".q".equals(line)) {
                break;
            }
            log.info("send message {}", line);
            client.send(line);
        }

        scanner.close();
    }

    public static Client create(String address, int port) {
        Client client = new Client(address, port);
        client.init();
        return client;
    }

    private String address;
    private int port;

    private boolean running;

    private Socket client;
    private long lastAccessTime;

    private long keepAliveInterval = 5 * 1000;

    private Thread keepAliveWroker;

    private Thread clientWroker;

    protected Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    protected void init() {
        this.keepAliveWroker = new Thread(new KeepAliveWroker());
        this.clientWroker = new Thread(new ClientWroker());
    }

    public void start() throws IOException {
        running = true;
        client = new Socket(address, port);
        lastAccessTime = System.currentTimeMillis();
        keepAliveWroker.start();
        clientWroker.start();
    }

    public void send(Object o) throws IOException {
        OutputStream os = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(o);
    }

    public void stop() {
        running = false;
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        keepAliveWroker.interrupt();
        clientWroker.interrupt();
    }

    private class KeepAliveWroker implements Runnable {

        @Override
        public void run() {
            while (running) {
                if (System.currentTimeMillis() - lastAccessTime > keepAliveInterval) {
                    try {
                        log.info("send keep-alive message");
                        Client.this.send("keepalive");
                        lastAccessTime = System.currentTimeMillis();
                    } catch (IOException e) {
                        log.error("", e);
                        Client.this.stop();
                    }
                } else {
                    try {
                        Thread.sleep(keepAliveInterval - 1000);
                    } catch (InterruptedException e) {
                        log.error("", e);
                    }
                }
            }
        }
    }

    private class ClientWroker implements Runnable {

        @Override
        public void run() {
            while (running) {
                try {
                    InputStream is = client.getInputStream();
                    if (is.available() > 0) {
                        ObjectInputStream ois = new ObjectInputStream(is);
                        Object obj = ois.readObject();
                        log.info("server return message: {}", obj);
                    } else {
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                    log.error("", e);
                    Client.this.stop();
                }
            }
        }

    }

}
