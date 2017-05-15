package org.harmony.test.aop.aspectj;

import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class BankTest {

    @Test
    public void test() {
        Account account = new Account("wuxii", "abc123");
        new Bank().deposit(account, 120f);
    }

}
