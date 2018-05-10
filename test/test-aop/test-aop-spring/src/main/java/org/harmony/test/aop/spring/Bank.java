package org.harmony.test.aop.spring;

import org.springframework.stereotype.Service;

@Service
public class Bank {

    private float money;

    @Loggable
    public float deposit(final Account account, final float money) {
        this.money += money;
        return this.money;
    }

    public float withdraw(final Account account, final float money) {
        this.money -= money;
        return this.money;
    }

}