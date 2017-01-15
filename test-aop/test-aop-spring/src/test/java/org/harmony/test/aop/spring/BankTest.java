package org.harmony.test.aop.spring;

import org.harmony.test.aop.spring.understanding.BankFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class BankTest {

    @Autowired
    private Bank bank;

    @Test
    public void depositTest() {
        bank.deposit(new Account("abc", "der"), 1f);
    }

    @Test
    public void withdrawTest() {
        Account account = new Account("david", "password");
        bank.withdraw(account, 1f);
    }

    public static void main(String[] args) {
        Bank bank = (Bank) BankFactory.getBean("bank");
        bank.deposit(new Account("abc", "def"), 1f);
    }

}
