package org.harmony.test.javaee.jms;

import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface MessageHandler {

    public void sendMessage(String message) throws JMSException;

    public String receiveMessage(long waitTime) throws JMSException;

}
