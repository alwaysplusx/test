package org.harmony.test.javaee;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.harmony.test.javaee.jpa.Amount;
import org.harmony.test.javaee.lock.AmountRemote;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class JpaLockTest {

	private static EJBContainer container;

	@EJB
	private AmountRemote amountService;

	private int count = 10;

	private Long id;

	@BeforeClass
	public static void beforeClass() {
		Properties props = new Properties();
		props.put("jdbc.harmony", "new://Resource?type=DataSource");
		props.put("jdbc.harmony.JdbcDriver", "org.h2.Driver");
		props.put("jdbc.harmony.JdbcUrl", "jdbc:h2:file:~/.h2/harmony");
		props.put("jdbc.harmony.UserName", "sa");
		props.put("jdbc.harmony.Password", "");
		container = EJBContainer.createEJBContainer(props);
	}

	@Before
	public void before() throws Exception {
		container.getContext().bind("inject", this);
		this.id = amountService.save(new Amount(BigDecimal.ZERO)).getId();
	}

	@Test
	public void test() throws InterruptedException {
		final CountDownLatch start = new CountDownLatch(count);
		final CountDownLatch end = new CountDownLatch(count);

		for (int i = 0; i < count; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						start.await();

						amountService.increment(id, new BigDecimal(100));

					} catch (Exception e) {
						e.printStackTrace();
					}
					end.countDown();

				}
			}).start();
			start.countDown();
		}

		end.await();
	}

	@AfterClass
	public static void tearDown() {
		container.close();
	}

}
