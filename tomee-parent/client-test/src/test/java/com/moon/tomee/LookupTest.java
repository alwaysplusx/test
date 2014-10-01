package com.moon.tomee;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LookupTest {

	EJBContainer container;

	@Before
	public void setUp() {
		Properties props = new Properties();
		props.setProperty("openejb.embedded.remotable", "true");
		container = EJBContainer.createEJBContainer(props);
	}

	@After
	public void tearDown() {
		container.close();
	}

	@Test
	public void testStandaloneLookup() throws Exception {
		Properties p = new Properties();
		p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
		InitialContext ctx = new InitialContext(p);
		SimpleRemote bean = (SimpleRemote) ctx.lookup("SimpleBean");
		assertNotNull(bean);
		assertEquals("Hi wuxii", bean.sayHi("wuxii"));
	}

	@Test
	public void testStandaloneLookupByHttp() throws Exception {
		Properties p = new Properties();
		p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
		p.put("java.naming.provider.url", "ejbd://localhost:4201");
		InitialContext ctx = new InitialContext(p);
		SimpleRemote bean = (SimpleRemote) ctx.lookup("SimpleBean");
		assertNotNull(bean);
		assertEquals("Hi wuxii", bean.sayHi("wuxii"));
	}

	public static void main(String[] args) throws Exception {
		Properties p = new Properties();
		p.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("java.naming.provider.url", "http://127.0.0.1:8080/tomee/ejb");
		// p.put("java.naming.security.principal", "myuser");
		// p.put("java.naming.security.credentials", "mypass");
		InitialContext ctx = new InitialContext(p);
		SimpleRemote bean = (SimpleRemote) ctx.lookup("SimpleBean");
		assertNotNull(bean);
	}

}
