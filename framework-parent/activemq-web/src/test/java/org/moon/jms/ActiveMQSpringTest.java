package org.moon.jms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-context.xml")
public class ActiveMQSpringTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private Destination destination;

    @Test
    public void testStart() {
        assertNotNull(context);
    }

    @Test
    public void testMessage() throws Exception {
        assertNotNull(connectionFactory);
        assertNotNull(destination);
        Connection connection1 = connectionFactory.createConnection();
        Session session1 = connection1.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session1.createProducer(destination);
        TextMessage textMessage1 = session1.createTextMessage();
        textMessage1.setText("simple text message");
        producer.send(textMessage1);
        producer.close();
        session1.close();
        connection1.close();

        Connection connection2 = connectionFactory.createConnection();
        connection2.start();
        Session session2 = connection2.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session2.createConsumer(destination);
        Message message = consumer.receive();
        TextMessage textMessage2 = (TextMessage) message;
        assertEquals("simple text message", textMessage2.getText());
    }

}
