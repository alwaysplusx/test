package org.harmony.test.javaee.jms;

import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface MessageHandler {

    /**
     * 发送一个文本消息
     * 
     * @param message
     * @throws JMSException
     */
    public void sendMessage(String message) throws JMSException;

    /**
     * 接收消息
     * 
     * @return
     * @throws JMSException
     */
    public Object receiveMessage(long waitTime) throws JMSException;

}
