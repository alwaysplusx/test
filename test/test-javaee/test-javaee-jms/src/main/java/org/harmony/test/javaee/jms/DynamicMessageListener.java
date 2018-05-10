package org.harmony.test.javaee.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

public class DynamicMessageListener implements MessageListener {

    private Destination dest;
    private ConnectionFactory connectionFactory;

    // scop resource
    private Connection conn;
    private Session session;
    private MessageConsumer consumer;

    public DynamicMessageListener(ConnectionFactory connectionFactory, Destination dest) {
        this.connectionFactory = connectionFactory;
        this.dest = dest;
    }

    public void start() throws JMSException {
        conn = connectionFactory.createConnection();
        conn.start();
        session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumer = session.createConsumer(dest);
        consumer.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        System.err.println("DynamicMessageListener invoke and receive message: " + message);
    }

    public void stop() throws JMSException {
        consumer.close();
        session.close();
        conn.close();
    }

}