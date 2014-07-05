package org.moon.test.aop.spring;

import org.springframework.stereotype.Service;

/**
 *
 * @author wux
 */
@Service
public class Bank {

	private float money;

	/**
	 * 取款.
	 * @param account 帐号
	 * @param money 金额
	 * @return 帐户总金额
	 */
	public float deposit(final Account account, final float money) {
		// 日志记录
		// 帐户验证
		// Begin Transaction
		// 增加帐户金额
		this.money += money;
		// Commit Transaction
		return this.money;
	}

	/**
	 * 取款.
	 * @param account 账号
	 * @param money 金额
	 * @return 总金额
	 */
	public float withdraw(final Account account, final float money) {
		// 日志记录
		// 帐户验证
		// Begin Transaction
		// 减少帐户金额
		this.money -= money;
		// Commit Transaction
		return this.money;
	}

}