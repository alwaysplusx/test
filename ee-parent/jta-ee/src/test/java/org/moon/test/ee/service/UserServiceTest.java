package org.moon.test.ee.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.ee.persistence.User;
import org.moon.test.ee.repository.UserRepository;

public class UserServiceTest {

	private EJBContainer container;
	@EJB(beanName = "UserServiceBeanImpl")
	private UserService serviceBean;
	@EJB(beanName = "UserServiceContainerImpl")
	private UserService serviceContainer;
	@EJB
	private UserRepository userRepository;
	List<User> users = new ArrayList<User>();

	@Before
	public void setUp() throws Exception {
		Properties props = new Properties();
		props.put("openejb.conf.file", "src/main/resources/openejb.xml");
		container = EJBContainer.createEJBContainer(props);
		container.getContext().bind("inject", this);
		for (int i = 0; i < 10000; i++) {
			users.add(new User("user_" + i, i, "F", "10024125125125" + i, Calendar.getInstance()));
		}
	}

	@After
	public void tearDown() throws Exception {
		container.close();
	}

	@Test
	public void testContainerBatchSave() {
		assertNotNull(serviceContainer);
		final UserService service = serviceContainer;
		long start = System.currentTimeMillis();
		service.batchSave(users);
		System.err.println("container use " + (System.currentTimeMillis() - start) + "ms");
		assertEquals(10000, userRepository.count());
	}

	@Test
	public void testBeanBatchSave() {
		assertNotNull(serviceBean);
		final UserService service = serviceBean;
		long start = System.currentTimeMillis();
		service.batchSave(users);
		System.err.println("bean use " + (System.currentTimeMillis() - start) + "ms");
		assertEquals(10000, userRepository.count());
	}

}
