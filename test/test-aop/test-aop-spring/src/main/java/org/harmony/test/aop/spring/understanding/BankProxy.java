package org.harmony.test.aop.spring.understanding;

import org.harmony.test.aop.spring.Account;
import org.harmony.test.aop.spring.Bank;
import org.harmony.test.aop.spring.LoggerAspect;

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
