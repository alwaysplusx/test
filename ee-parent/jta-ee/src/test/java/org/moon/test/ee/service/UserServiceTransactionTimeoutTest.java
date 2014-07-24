package org.moon.test.ee.service;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.ee.persistence.User;
import org.moon.test.ee.repository.UserRepository;

public class UserServiceTransactionTimeoutTest {

	private EJBContainer container;
	@EJB(beanName = "UserServiceDefaultImpl")
	private UserService service;
	@Resource
	private UserTransaction ux;
	@EJB
	private UserRepository userRepository;

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

	@Test(expected = RollbackException.class)
	public void testUserTransactionTimeout() throws Exception {
		assertEquals(Status.STATUS_NO_TRANSACTION, ux.getStatus());
		ux.setTransactionTimeout(1);
		ux.begin();
		assertEquals(Status.STATUS_ACTIVE, ux.getStatus());
		Thread.sleep(1000 * 5);
		try {
			ux.commit();
		} finally {
			assertEquals(Status.STATUS_NO_TRANSACTION, ux.getStatus());
		}
	}

	@Test
	public void testTimeout() {
		service.sleepLongTimeWithTransaction(new User("double", 21, "F", "08678899876", Calendar.getInstance()), 1000 * 600 + 1000 * 10);
		assertEquals(1, userRepository.count());
	}

}
