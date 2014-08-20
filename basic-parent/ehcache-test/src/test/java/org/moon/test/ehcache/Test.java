package org.moon.test.ehcache;

public class Test {

	public static void main(String[] args) throws Exception {
		new Thread("Event-Thread") {
			@Override
			public void run() {
				try {
					EvenNode.main(new String[] { "-Dehcache.disk.store.dir=/src/test/resources/even.xml" });
				} catch (Exception e) {
				}
			}
		}.start();
		new Thread("Odd-Thread") {
			public void run() {
				try {
					OddNode.main(new String[] { "-Dehcache.disk.store.dir=/src/test/resources/odd.xml" });
				} catch (Exception e) {
				}
			}
		}.start();

		Thread.sleep(Long.MAX_VALUE);
	}
}
