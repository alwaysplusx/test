package org.moon.ii.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class HeartbeatSender {

	InetAddress multicastAddress;
	Integer multicastPort;
	Integer timeToLive;
	InetAddress hostAddress;
	boolean stopped = false;
	MulticastServerThread serverThread;

	public HeartbeatSender(InetAddress multicastAddress, Integer multicastPort, Integer timeToLive, InetAddress hostAddress) {
		this.multicastAddress = multicastAddress;
		this.multicastPort = multicastPort;
		this.timeToLive = timeToLive;
		this.hostAddress = hostAddress;
	}

	protected void init() {
		serverThread = new MulticastServerThread();
		serverThread.start();
	}

	private class MulticastServerThread extends Thread {
		private MulticastSocket socket;

		public MulticastServerThread() {
			super("Heartbeat Sender Thread");
			setDaemon(true);
		}

		@Override
		public void run() {
			while (!stopped) {
				try {
					socket = new MulticastSocket(multicastPort.intValue());
					socket.setTimeToLive(timeToLive.intValue());
					socket.joinGroup(multicastAddress);
					while (!stopped) {
						String local = InetAddress.getLocalHost().getHostAddress() + ":" + socket.getLocalPort();
						byte[] buffer = local.getBytes();
						DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastAddress, multicastPort);
						socket.send(packet);
						System.out.println(">> send datagram packet to " + multicastAddress + ":" + multicastPort);
						try {
							synchronized (this) {
								wait(5000);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (socket != null && !socket.isClosed()) {
						try {
							socket.leaveGroup(multicastAddress);
						} catch (IOException e) {
							e.printStackTrace();
						}
						socket.close();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		HeartbeatSender sender = new HeartbeatSender(InetAddress.getByName("230.0.0.1"), 40001, 255, null);
		sender.init();
		Thread.sleep(Long.MAX_VALUE);
	}

}
