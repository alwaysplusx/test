package org.moon.test.cxf.publish.supplier;

import static org.junit.Assert.fail;

import java.util.List;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.cxf.publish.hello.Hello;
import org.moon.test.cxf.publish.hello.HelloService;

public class SupplierServiceTest {

	private String address = "http://localhost:8080/ws/suppier";
	private Server server;
	
	@Before
	public void setUp() throws Exception {
		JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setAddress(address);
		serverFactory.setServiceClass(Hello.class);
		serverFactory.setServiceBean(new HelloService());
		server = serverFactory.create();
	}

	@After
	public void tearDown() throws Exception {
		server.destroy();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public static void main(String[] args) {
		JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setAddress("http://localhost:8080/ws/supplier");
		serverFactory.setServiceClass(SupplierService.class);
		serverFactory.setServiceBean(new SupplierService(){
			@Override
			public void saveOrUpdate(List<Supplier> suppliers) {
				System.err.println("===> supplier save or update");
			}
		});
		serverFactory.create();
	}

}
