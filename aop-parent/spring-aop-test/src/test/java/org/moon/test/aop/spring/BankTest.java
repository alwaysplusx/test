package org.moon.test.aop.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class BankTest {

	@Autowired
	private Bank bank;
	
	@Test
	public void test() {
		bank.deposit(new Account("abc", "der"), 1f);
		bank.withdraw(null, 1f);
	}

}
