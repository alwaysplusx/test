package org.moon.test.cxf.publish.supplier;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
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
				try {
					Thread.sleep(1000 * 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		SupplierService client = ProxyFactoryManager.create(SupplierService.class, address);
		Client clientProxy = ClientProxy.getClient(client);
		HTTPConduit conduit = (HTTPConduit)clientProxy.getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setReceiveTimeout(1000 * 65);
		policy.setConnectionTimeout(1000 * 65);
		conduit.setClient(policy);
		assertTrue(client.saveOrUpdate(null));
	}

}
