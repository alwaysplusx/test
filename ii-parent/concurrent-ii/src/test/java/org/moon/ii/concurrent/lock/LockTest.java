package org.moon.ii.concurrent.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockTest {

	static Logger log = LoggerFactory.getLogger(LockTest.class);
	static Factory factory = new Factory();

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					factory.consume();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Consumer-A").start();

		new Thread(new Runnable() {
			int i = 0;

			@Override
			public void run() {
				while (true) {
					factory.produce("B" + (i++));
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Producer-A").start();

		new Thread(new Runnable() {
			int i = 0;

			@Override
			public void run() {
				while (true) {
					factory.produce("C" + (i++));
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Producer-B").start();

	}

	static class Factory {

		List<String> data = new LinkedList<String>();
		// private Queue<String> queue = new ArrayBlockingQueue<String>(10);
		private ReadWriteLock lock = new ReentrantReadWriteLock();

		public String consume() {
			lock.writeLock().lock();
			String result = null;
			try {
				if (!data.isEmpty())
					result = data.remove(0);
				log.info("consume " + result);
				return result;
			} finally {
				lock.writeLock().unlock();
			}
		}

		public void produce(String message) {
			lock.writeLock().lock();
			try {
				Thread.sleep(1000);
				data.add(message);
				log.info("produce " + message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
		}
	}

}
