package org.moon.test.aop.spring;

/**
 *
 * @author wux
 */
public class Account {

	private String name;
	private String password;

	/**
	 * default constructor.
	 */
	public Account() {
	}

	/**
	 * @param name �ʻ���
	 * @param password ����
	 */
	public Account(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * ��ȡ����.
	 * @return ����
	 */
	public String getName() {
		return name;
	}

	/**
	 * ��������.
	 * @param name ����
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��ȡ����.
	 * @return ����
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ��������.
	 * @param password ����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
