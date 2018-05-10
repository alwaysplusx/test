package org.harmony.test.javaee.interceptor;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class InterceptorBindingTest {

    private static EJBContainer container;
    @EJB(beanName = "BarService")
    private Service service;

    @Before
    public void beforeClass() throws Exception {
        container = EJBContainer.createEJBContainer();
    }

    @Before
    public void before() throws NamingException {
        container.getContext().bind("inject", this);
    }

    @Test
    public void testInterceptor() {
        // 需要在META-INF/beans.xml中配置拦截器
        assertEquals("Hi wuxii", service.sayHi("wuxii"));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        container.close();
    }
}
