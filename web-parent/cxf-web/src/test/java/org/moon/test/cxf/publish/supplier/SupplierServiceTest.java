package org.moon.test.cxf.publish.supplier;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.cxf.client.ProxyFactoryManager;

public class SupplierServiceTest {

	private String address = "http://localhost:8080/ws/suppier";
	private Server server;

	@Before
	public void setUp() throws Exception {
		JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setAddress(address);
		serverFactory.setServiceClass(SupplierService.class);
		serverFactory.setServiceBean(new SupplierService() {
			@Override
			public boolean saveOrUpdate(List<Supplier> suppliers) {
				return true;
			}
		});
		server = serverFactory.create();
	}

	@After
	public void tearDown() throws Exception {
		server.destroy();
	}

	@Test
	public void testSupplierSaveOrUpdate() throws Exception {
		assertTrue(ProxyFactoryManager.create(SupplierService.class, address).saveOrUpdate(new ArrayList<Supplier>()));
	}

}
