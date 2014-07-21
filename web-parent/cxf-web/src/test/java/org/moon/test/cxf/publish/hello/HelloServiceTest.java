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
import org.moon.test.cxf.publish.hello.HelloService;
import org.moon.test.cxf.publish.hello.Person;

public class HelloServiceTest {

	private String address = "http://localhost:8080/ws/hi";
	private Server server;
	
	@Before
	public void setUp() {
		JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setAddress(address);
		serverFactory.setServiceClass(Hello.class);
		serverFactory.setServiceBean(new HelloService());
		server = serverFactory.create();
	}

	@Test
	public void helloWebserviceTest() {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setAddress(address);
		proxyFactory.setServiceClass(Hello.class);
		Hello service = (Hello) proxyFactory.create();
		assertEquals("Hello wu", service.sayHello(new Person("wu")));
	}

	@Test
	public void serviceManagerTest() throws Exception {
		ProxyFactoryManager.create(Hello.class, address).sayHello(new Person());
	}
	
	@After
	public void teardown() {
		server.destroy();
	}
	
	public static void main(String[] args) {
		JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setAddress("http://localhost:8080/ws/hi");
		serverFactory.setServiceClass(Hello.class);
		serverFactory.setServiceBean(new HelloService());
		serverFactory.create();
	}
	
}
