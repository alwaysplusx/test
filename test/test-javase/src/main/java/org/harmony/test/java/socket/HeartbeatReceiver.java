package org.harmony.test.java.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HeartbeatReceiver {

    InetAddress multicastAddress;
    Integer multicasePort;
    InetAddress hostAddress;
    boolean stopped;
    MulticastSocket socket;

    public HeartbeatReceiver(InetAddress multicastAddress, Integer multicastPort, InetAddress hostAddress) {
        this.multicastAddress = multicastAddress;
        this.multicasePort = multicastPort;
        this.hostAddress = hostAddress;
    }

    protected void init() {
        MulticaseReceiverThread receiverThread = new MulticaseReceiverThread();
        receiverThread.start();
    }

    private class MulticaseReceiverThread extends Thread {

        public MulticaseReceiverThread() {
            super("Heartbeat Receiver Thread");
            setDaemon(true);
        }

        @Override
        public void run() {
            byte[] buffer = new byte[MTU];
            try {
                socket = new MulticastSocket(multicasePort.intValue());
                socket.joinGroup(multicastAddress);
                while (!stopped) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    byte[] data = ungzip(packet.getData());
                    StringBuffer sb = new StringBuffer();
                    sb.append(">> ").append("receive datagram packet from ").append(multicastAddress).append(":").append(multicasePort).append(" message is :")
                            .append(new String(data, "utf-8"));
                    System.out.println(sb.toString());
                }
            } catch (IOException e) {

            } finally {
                if (socket != null && !socket.isClosed()) {
                    try {
                        socket.leaveGroup(multicastAddress);
                    } catch (IOException e) {
                    }
                    socket.close();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HeartbeatReceiver receiver = new HeartbeatReceiver(InetAddress.getByName("230.0.0.1"), 40001, null);
        receiver.init();
        Thread.sleep(Long.MAX_VALUE);
    }

    public static final int MTU = 1500;

    public static byte[] gzip(byte[] ungzipped) {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bytes);
            gzipOutputStream.write(ungzipped);
            gzipOutputStream.close();
        } catch (IOException e) {
        }
        return bytes.toByteArray();
    }

    public static byte[] ungzip(final byte[] gzipped) {
        byte[] ungzipped = new byte[0];
        try {
            final GZIPInputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(gzipped));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(gzipped.length);
            final byte[] buffer = new byte[MTU];
            int bytesRead = 0;
            while (bytesRead != -1) {
                bytesRead = inputStream.read(buffer, 0, MTU);
                if (bytesRead != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
            }
            ungzipped = byteArrayOutputStream.toByteArray();
            inputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
        }
        return ungzipped;
    }

}
