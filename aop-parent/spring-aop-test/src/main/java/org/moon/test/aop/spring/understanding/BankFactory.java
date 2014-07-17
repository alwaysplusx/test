package org.moon.test.aop.spring.understanding;

/**
 *
 * @author wux
 */
public class BankFactory {

	/**
	 * @param beanName ����
	 * @return ������
	 */
	public static Object getBean(String beanName) {
		if ("bank".equals(beanName)) {
			return new BankProxy();
		}
		return null;
	}

}
