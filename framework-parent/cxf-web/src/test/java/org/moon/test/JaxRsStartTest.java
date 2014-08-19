package org.moon.test;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.Test;
import org.moon.test.jaxrs.UserService;

public class JaxRsStartTest {

	@Test
	public void test() throws Exception {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(UserService.class);
		sf.setResourceProvider(UserService.class, new SingletonResourceProvider(new UserService()));
		sf.setAddress("http://localhost:9000");
		sf.create();
		Thread.sleep(Long.MAX_VALUE);
	}
}
