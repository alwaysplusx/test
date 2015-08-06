package org.moon.test.aop.spring.understanding;

import org.moon.test.aop.spring.Account;
import org.moon.test.aop.spring.Bank;
import org.moon.test.aop.spring.LoggerAspect;

/**
 *
 * @author wux
 */
public class BankProxy extends Bank {

    @Override
    public float deposit(Account account, float money) {
        new LoggerAspect().logging();
        return super.deposit(account, money);
    }

    @Override
    public float withdraw(Account account, float money) {
        new LoggerAspect().logging();
        return super.withdraw(account, money);
    }

}
