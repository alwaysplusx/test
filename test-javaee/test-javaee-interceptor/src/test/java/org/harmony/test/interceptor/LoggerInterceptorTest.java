package org.harmony.test.interceptor;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;

import org.harmony.test.javaee.interceptor.SimpleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@Stateless
public class LoggerInterceptorTest {

    private EJBContainer container;
    @EJB
    private SimpleService test;

    @Before
    public void setUp() throws Exception {
        Properties props = new Properties();
        container = EJBContainer.createEJBContainer(props);
        container.getContext().bind("inject", this);
    }

    @Test
    public void testInterceptor() {
        test.execute();
        // must exists classPath/META-INF/beans.xml
        test.executeWithAnnotation();
    }

    @After
    public void tearDown() throws Exception {
        container.close();
    }

}
