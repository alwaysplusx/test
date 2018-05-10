package org.harmony.test.javaee.jms;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class DynamicMessageListenerTest {

    private static EJBContainer container;

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource
    private Destination dest;

    private DynamicMessageListener listener;

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
        listener = new DynamicMessageListener(connectionFactory, dest);
        listener.start();
    }

    @Test
    public void test() throws JMSException, InterruptedException {
        MessageHelper.sendMessage("Hello World!", connectionFactory, dest);
        Thread.sleep(1000);
    }

    @After
    public void after() throws JMSException {
        listener.stop();
    }

    @AfterClass
    public static void afterClass() {
        container.close();
    }

}
