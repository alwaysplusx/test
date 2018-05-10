package org.harmony.test.javaee.jms;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.embeddable.EJBContainer;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class MessageListenerTest {

    public static EJBContainer container;

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource
    private Destination dest;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Properties props = new Properties();
        // JMS ResourceAdapter
        props.put("MyJmsResourceAdapter", "new://Resource?type=ActiveMQResourceAdapter");
        props.put("MyJmsResourceAdapter.BrokerXmlConfig", "broker:(tcp://localhost:61616)?useJmx=false");
        props.put("MyJmsResourceAdapter.ServerUrl", "tcp://localhost:61616");
        // MDB Container
        props.put("MyJmsMdbContainer", "new://Container?type=MESSAGE");
        props.put("MyJmsMdbContainer.ResourceAdapter", "MyJmsResourceAdapter");
        // ConnectionFactory
        props.put("jms.connectionFactory", "new://Resource?type=QueueConnectionFactory");
        props.put("jms.connectionFactory.ResourceAdapter", "MyJmsResourceAdapter");
        // Queue
        props.put("jms.queue", "new://Resource?type=Queue");
        container = EJBContainer.createEJBContainer(props);
    }

    @Before
    public void before() throws Exception {
        container.getContext().bind("inject", this);
    }

    @Test
    public void test() throws Exception {
        MessageHelper.sendMessage("Hello World!", connectionFactory, dest);
        Thread.sleep(1000);
    }

    @AfterClass
    public static void afterClass() {
        container.close();
    }

    @MessageDriven(activationConfig = { //
            @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms.queue") //
    })
    public static class JMSMessageListener implements MessageListener {

        @Override
        public void onMessage(Message message) {
            try {
                if (message instanceof TextMessage) {
                    System.err.println("text message " + ((TextMessage) message).getText());
                } else if (message instanceof ObjectMessage) {
                    System.err.println("object message " + ((ObjectMessage) message).getObject());
                } else {
                    System.err.println("other type message " + message);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
