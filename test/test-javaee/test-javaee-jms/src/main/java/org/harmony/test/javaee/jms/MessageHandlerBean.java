package org.harmony.test.javaee.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@Stateless
public class MessageHandlerBean implements MessageHandler {

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource
    private Destination destination;

    @Override
    public void sendMessage(String message) throws JMSException {
        MessageHelper.sendMessage(message, connectionFactory, destination);
    }

    @Override
    public String receiveMessage(long waitTime) throws JMSException {
        Message message = MessageHelper.receiveMessage(waitTime, connectionFactory, destination);
        return ((ObjectMessage) message).getObject().toString();
    }

}
