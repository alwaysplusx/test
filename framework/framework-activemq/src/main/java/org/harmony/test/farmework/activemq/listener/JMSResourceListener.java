package org.harmony.test.farmework.activemq.listener;

import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.harmony.test.javaee.jms.DynamicMessageListener;
import org.harmony.test.javaee.jms.MessageHelper;

/**
 * @author wuxii@foxmail.com
 */
public class JMSResourceListener implements ServletContextListener {

    private DynamicMessageListener listener;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        ActiveMQQueue queue = new ActiveMQQueue("test.queue");
        try {
            MessageHelper.sendMessage("Hello World!", connectionFactory, queue);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            listener = new DynamicMessageListener(connectionFactory, queue);
            listener.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (listener != null) {
                listener.stop();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
