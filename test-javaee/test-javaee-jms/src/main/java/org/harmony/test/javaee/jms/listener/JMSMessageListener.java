package org.harmony.test.javaee.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
// @MessageDriven(mappedName = "queue")
public class JMSMessageListener implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(JMSMessageListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                log.info("text message {}", ((TextMessage) message).getText());
            } else if (message instanceof ObjectMessage) {
                log.info("object message {}", ((ObjectMessage) message).getObject());
            } else {
                log.info("other type message {}", message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
