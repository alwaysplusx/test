package org.moon.test.aop.spring;

import org.springframework.stereotype.Service;

@Service
public class Bank {

	private float money;

	public final float deposit(final Account account, final float money) {
		// 日志记录
		// 帐户验证
		// Begin Transaction
		// 增加帐户金额
		this.money += money;
		// Commit Transaction
		return this.money;
	}

	public float withdraw(Account account, float money) {
		// 日志记录
		// 帐户验证
		// Begin Transaction
		// 减少帐户金额
		this.money -= money;
		// Commit Transaction
		return this.money;
	}
	
}