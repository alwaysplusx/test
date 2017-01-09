package org.harmony.test.javaee.jms;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageHandlerTest {

    @EJB
    MessageHandler handler;
    static EJBContainer container;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Properties props = new Properties();
        props.put("openejb.conf.file", "src/main/resources/openejb.xml");
        container = EJBContainer.createEJBContainer(props);
    }

    @Before
    public void before() throws NamingException {
        container.getContext().bind("inject", this);
    }

    @Test
    public void testMessage() throws Exception {
        handler.sendMessage("simple message");
        assertEquals("simple message", handler.receiveMessage(500));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        container.close();
    }

}
