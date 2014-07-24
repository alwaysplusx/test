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
//		props.put("jdbc.moon", "new://Resource?type=DataSource");
//		props.put("jdbc.moon.JdbcDriver", "org.h2.Driver");
//		props.put("jdbc.moon.JdbcUrl", "jdbc:h2:file:~/test/ee/data");
//		props.put("jdbc.moon.UserName", "sa");
//		props.put("jdbc.moon.Password", "");
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
