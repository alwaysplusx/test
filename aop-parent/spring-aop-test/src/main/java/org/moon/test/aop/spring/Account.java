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
	 * @param name 帐户名
	 * @param password 密码
	 */
	public Account(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * 获取名称.
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称.
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取密码.
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码.
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
