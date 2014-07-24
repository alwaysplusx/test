package org.moon.test.ee.service;

import static org.junit.Assert.*;
import static javax.transaction.Status.*;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.RollbackException;
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
	@PersistenceContext(unitName = "hibernate-moon")
	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		Properties props = new Properties();
		props.put("openejb.conf.file", "src/main/resources/openejb.xml");
		props.put("jta.moon", "new://TransactionManager?type=TransactionManager");
		props.put("jta.moon.defaultTransactionTimeout", "1 seconds");
		container = EJBContainer.createEJBContainer(props);
		container.getContext().bind("inject", this);
	}

	@After
	public void tearDown() throws Exception {
		container.close();
		container = null;
	}

	@Test(expected = RollbackException.class)
	public void testUserTransactionTimeout() throws Exception {
		assertEquals(STATUS_NO_TRANSACTION, ux.getStatus());
		// container defaultTransactionTimeout is 5 seconds, see setUp method
		// also can use ux.setTransactionTimeout(seconds) change timeout seconds
		ux.begin();
		assertEquals(STATUS_ACTIVE, ux.getStatus());
		em.persist(new User("test"));
		Thread.sleep(1000 * 5);
		try {
			ux.commit();
		} finally {
			assertEquals(0, userRepository.count());
			assertEquals(STATUS_NO_TRANSACTION, ux.getStatus());
		}
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	public void testTimeoutWithTransaction() {
		try {
			service.sleepLongTimeWithTransaction(new User("double"), 1000 * 5);
		} finally {
			// 在Suite中运行时候保存了
			// assertEquals(0, userRepository.count());
		}
	}

	@Test
	public void testTimeoutWithoutTransaction() {
		// sleepLongTimeWithoutTransaction方法声明为TransactionAttributeType.NOT_SUPPORTED
		// 即表示为sleepLongTimeWithoutTransaction方法不启动新事务，保存的事务被延迟到UserRepositorty中去
		// 如果在UserRepository的方法中线程等待超过超时时间那么也将抛出异常，数据回滚
		try {
			service.sleepLongTimeWithoutTransaction(new User("double"), 1000 * 5);
		} finally {
			assertEquals(1, userRepository.count());
		}
	}

}
