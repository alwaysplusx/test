package org.moon.test.cxf;

import org.moon.test.cxf.Simple;
import org.moon.test.cxf.client.JaxWsServerManager;

public class JaxWsServerManagerTest {

	public static void main(String[] args) {
		JaxWsServerManager.getInstance().publish(Simple.class, "http://localhost:8080/test", new Simple() {
			@Override
			public String sayHi(String name) {
				System.out.println("name say hi");
				return "Hi " + name;
			}
		});
	}

}
