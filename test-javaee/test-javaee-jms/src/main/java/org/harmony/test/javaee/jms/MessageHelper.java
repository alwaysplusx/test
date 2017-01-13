package org.harmony.test.javaee.jms;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * @author wuxii@foxmail.com
 */
public class MessageHelper {

    public static void sendMessage(Serializable message, ConnectionFactory connectionFactory, Destination dest)
            throws JMSException {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(dest);
            ObjectMessage om = session.createObjectMessage();
            om.setObject(message);
            producer.send(om);
        } catch (JMSException e) {
            e.printStackTrace();
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
            }
        }
    }

    public static Message receiveMessage(long waitTime, ConnectionFactory connectionFactory, Destination dest)
            throws JMSException {
        Connection conn = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            // 创建连接
            conn = connectionFactory.createConnection();
            conn.start();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 与目的地建立会话
            consumer = session.createConsumer(dest);
            return consumer.receive(waitTime);
        } finally {
            consumer.close();
            session.close();
            conn.close();
        }
    }

}
