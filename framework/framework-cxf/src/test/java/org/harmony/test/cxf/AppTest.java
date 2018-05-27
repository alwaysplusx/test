package org.harmony.test.cxf;

import static org.junit.Assert.*;

import org.harmony.test.cxf.JaxWsClientManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class AppTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testServer() throws Exception {
        assertNotNull(context);
        Simple simple = JaxWsClientManager.getInstance().create(Simple.class, "http://localhost:8080/test");
        assertEquals("Hi wuxii", simple.sayHi("wuxii"));
    }

    @Test
    public void testClient() {
        Simple simple = (Simple) context.getBean("client");
        assertEquals("Hi wuxii", simple.sayHi("wuxii"));
    }

    @Test
    public void testSimpleClient() {
        Simple simple = (Simple) context.getBean("simpleClient");
        assertEquals("Hi wuxii", simple.sayHi("wuxii"));
    }

}
