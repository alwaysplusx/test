package org.moon.test.ee;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StartContainerTest {

	private EJBContainer container;

	@Before
	public void setUp() throws Exception {
		Properties props = new Properties();
		props.put("openejb.conf.file", "src/main/resources/openejb.xml");
		container = EJBContainer.createEJBContainer(props);
	}

	@Test
	public void testStart() {

	}

	@After
	public void tearDown() throws Exception {
		container.close();
	}

}
