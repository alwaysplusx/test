package org.harmony.test.javaee.interceptor;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class LoggerInterceptorTest {

    private static EJBContainer container;
    @EJB(beanName = "FooService")
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
        assertEquals("Hi wuxii", service.sayHi("wuxii"));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        container.close();
    }

}
