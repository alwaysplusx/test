package org.moon.test.cxf.publish.hello;

import static org.junit.Assert.*;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.cxf.client.ProxyFactoryManager;
import org.moon.test.cxf.publish.hello.Hello;
import org.moon.test.cxf.publish.hello.Person;

public class HelloServiceTest {

	private String address = "http://localhost:8080/ws/hi";
	private Server server;

	@Before
	public void setUp() {
		JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setAddress(address);
		serverFactory.setServiceClass(Hello.class);
		serverFactory.setServiceBean(new Hello() {
			@Override
			public String sayHello(Person person) {
				return "Hello " + person.getName();
			}
		});
		server = serverFactory.create();
	}

	@Test
	public void helloWebserviceTest() {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setAddress(address);
		proxyFactory.setServiceClass(Hello.class);
		Hello service = (Hello) proxyFactory.create();
		assertEquals("Hello test", service.sayHello(new Person("test")));
	}

	@Test
	public void serviceManagerTest() throws Exception {
		assertEquals("Hello test", ProxyFactoryManager.create(Hello.class, address).sayHello(new Person("test")));
	}

	@After
	public void teardown() {
		server.destroy();
	}

}
