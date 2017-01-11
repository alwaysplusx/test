package org.harmony.test.javaee.jms;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.harmony.tes.test.OpenEJBConfiguration;
import org.harmony.tes.test.runner.OpenEJBRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OpenEJBRunner.class)
@OpenEJBConfiguration(location = "src/main/resources/openejb.xml")
public class MessageHandlerTest2 {

    @EJB
    private MessageHandler handler;

    @Test
    public void testMessage() throws Exception {
        handler.sendMessage("simple message");
        assertEquals("simple message", handler.receiveMessage(500));
    }

}
