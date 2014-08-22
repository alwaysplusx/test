package org.moon.ii.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

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
			byte[] buffer = new byte[19];
			try {
				socket = new MulticastSocket(multicasePort.intValue());
				socket.joinGroup(multicastAddress);
				while (!stopped) {
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					socket.receive(packet);
					byte[] data = packet.getData();
					StringBuffer sb = new StringBuffer();
					sb.append(">> ").append("receive datagram packet from ").append(multicastAddress).append(":").append(multicasePort).append(" message is :")
							.append(new String(data));
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

}
