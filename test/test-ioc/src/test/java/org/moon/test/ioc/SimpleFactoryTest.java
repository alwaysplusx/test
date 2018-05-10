package org.moon.test.ioc;

import org.harmony.test.ioc.SimpleBean;
import org.harmony.test.ioc.SimpleBeanFactory;

public class SimpleFactoryTest {

    public static void main(String[] args) throws Exception {
        SimpleBeanFactory factory = new SimpleBeanFactory("src/main/resources/beans.xml");
        SimpleBean bean = (SimpleBean) factory.getBean("simpleBean");
        System.out.println(bean.sayHi("test"));
    }

}
