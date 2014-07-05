package org.moon.test.aop.spring;

import org.springframework.stereotype.Service;

@Service
public class Bank {

	private float money;

	public final float deposit(final Account account, final float money) {
		// ��־��¼
		// �ʻ���֤
		// Begin Transaction
		// �����ʻ����
		this.money += money;
		// Commit Transaction
		return this.money;
	}

	public float withdraw(Account account, float money) {
		// ��־��¼
		// �ʻ���֤
		// Begin Transaction
		// �����ʻ����
		this.money -= money;
		// Commit Transaction
		return this.money;
	}
	
}