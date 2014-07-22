package org.moon.test.cxf.client;

import org.moon.test.cxf.publish.hello.Hello;
import org.moon.test.cxf.publish.hello.Person;

public class ProxyFactoryManagerTest {

	public static void main(String[] args) throws Exception {
		// ProxyFactoryManager.create(Hello.class,
		// "http://localhost:8080/test");
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("say hello ==> " + ProxyFactoryManager.create(Hello.class, "http://localhost:8080/ws/hi").sayHello(new Person("wu")));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

	}

}
