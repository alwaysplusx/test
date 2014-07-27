package org.moon.test.ee.jms;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MessageHandlerTest {

	@EJB
	MessageHandler handler;
	EJBContainer container;

	@Before
	public void setUp() throws Exception {
		Properties props = new Properties();
		props.put("openejb.conf.file", "src/main/resources/openejb.xml");
		container = EJBContainer.createEJBContainer(props);
		container.getContext().bind("inject", this);
	}

	@After
	public void tearDown() throws Exception {
		container.close();
	}

	@Test
	public void testMessage() throws Exception {
		handler.sendMessage("simple message");
		assertEquals("simple message", handler.receiveMessage());
	}

}
