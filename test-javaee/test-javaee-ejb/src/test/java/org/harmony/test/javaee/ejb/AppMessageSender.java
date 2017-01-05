package org.harmony.test.javaee.ejb;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
@Stateless
public class AppMessageSender {

    private static final Logger log = LoggerFactory.getLogger(AppMessageSender.class);

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource
    private Destination destination;

    public boolean send(Serializable message) {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            log.info("message listener -> {}", session.getMessageListener());
            producer = session.createProducer(destination);
            ObjectMessage om = session.createObjectMessage();
            om.setObject(message);
            producer.send(om);
        } catch (JMSException e) {
            log.error("发送失败{}", message, e);
            return false;
        } finally {
            try {
                if (producer != null) {
                    producer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                log.debug("", e);
            }
        }
        return true;
    }
}
