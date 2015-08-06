package org.moon.test.aop.spring.understanding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.aop.spring.Account;
import org.moon.test.aop.spring.Bank;

/**
 *
 * @author wux
 */
public class BankTest {

    private Bank bank;

    /**
     * @throws Exception �쳣
     */
    @Before
    public void setUp() throws Exception {
        bank = (Bank) BankFactory.getBean("bank");
    }

    /**
     * @throws Exception �쳣
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * ���Է���.
     */
    @Test
    public void proxyTest() {
        bank.deposit(new Account("abc", "def"), 1f);
    }

}
