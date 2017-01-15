package org.harmony.test.aop.spring.understanding;

public class BankFactory {

    public static Object getBean(String beanName) {
        if ("bank".equals(beanName)) {
            return new BankProxy();
        }
        return null;
    }

}
