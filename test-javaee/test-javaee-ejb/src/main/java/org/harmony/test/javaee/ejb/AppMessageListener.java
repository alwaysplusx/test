package org.harmony.test.javaee.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
@MessageDriven(activationConfig = { //
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms.queue") }//
)
public class AppMessageListener implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(AppMessageListener.class);

    public void onMessage(Message message) {
        log.info("on message >> {}", message);
    }

}
