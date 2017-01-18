package org.harmony.test.farmework.activemq.controller;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.harmony.test.javaee.jms.MessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxii@foxmail.com
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @RequestMapping(path = "/send", method = { RequestMethod.GET, RequestMethod.POST })
    public String send(@RequestParam("message") String message) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        ActiveMQQueue queue = new ActiveMQQueue("queue");
        try {
            MessageHelper.sendMessage(message, connectionFactory, queue);
        } catch (JMSException e) {
            return e.toString();
        }
        return "success";
    }

}
