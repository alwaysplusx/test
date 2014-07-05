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
	 * ȡ��.
	 * @param account �ʺ�
	 * @param money ���
	 * @return �ʻ��ܽ��
	 */
	public float deposit(final Account account, final float money) {
		// ��־��¼
		// �ʻ���֤
		// Begin Transaction
		// �����ʻ����
		this.money += money;
		// Commit Transaction
		return this.money;
	}

	/**
	 * ȡ��.
	 * @param account �˺�
	 * @param money ���
	 * @return �ܽ��
	 */
	public float withdraw(final Account account, final float money) {
		// ��־��¼
		// �ʻ���֤
		// Begin Transaction
		// �����ʻ����
		this.money -= money;
		// Commit Transaction
		return this.money;
	}

}