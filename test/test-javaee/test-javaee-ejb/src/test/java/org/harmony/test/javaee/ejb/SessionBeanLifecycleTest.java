package org.harmony.test.javaee.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class SessionBeanLifecycleTest {

    private static EJBContainer container;

    @EJB
    private LifecycleBean bean;

    @BeforeClass
    public static void beforeClass() {
        container = EJBContainer.createEJBContainer();
    }

    @Before
    public void before() throws NamingException {
        container.getContext().bind("inject", this);
    }

    @Test
    public void test() {
        bean.test();
    }

    @AfterClass
    public static void afterClass() {
        container.close();
    }

    @Stateless
    public static class LifecycleBean {

        @PostConstruct
        private void postConstruct() throws Exception {
            System.out.println(">>>> post construct");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println(">>>> pre destroy");
        }

        public void test() {
        }

    }

}
