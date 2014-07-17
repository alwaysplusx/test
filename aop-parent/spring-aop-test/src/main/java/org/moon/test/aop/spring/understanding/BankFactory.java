package org.moon.test.aop.spring.understanding;

/**
 *
 * @author wux
 */
public class BankFactory {

	/**
	 * @param beanName 名称
	 * @return 代理类
	 */
	public static Object getBean(String beanName) {
		if ("bank".equals(beanName)) {
			return new BankProxy();
		}
		return null;
	}

}
