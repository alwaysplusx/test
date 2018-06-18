package org.harmony.spring.streams.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.harmony.spring.streams.binding.UserChannelBinding;
import org.harmony.spring.streams.event.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author wuxii@foxmail.com
 */
@Component
public class UserEventSources implements ApplicationRunner {

    private static final List<String> users = Arrays.asList("foo", "bar", "baz", "qux", "zhangsan", "lisi", "wangwu");
    private static final List<String> actions = Arrays.asList("diving", "running", "skiing", "badminton", "softball", "shooting", "fencing");

    private static final Logger log = LoggerFactory.getLogger(UserEventSources.class);

    private MessageChannel userEventChannel;

    @Autowired
    public UserEventSources(UserChannelBinding binding) {
        this.userEventChannel = binding.userEventsOut();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            UserEvent event = new UserEvent(random(users), random(actions), Math.random() > .5 ? 10 : 1000);
            Message<UserEvent> message = MessageBuilder//
                    .withPayload(event)//
                    .setHeader(KafkaHeaders.MESSAGE_KEY, event.getName().getBytes())//
                    .build();
            try {
                this.userEventChannel.send(message);
                log.info("sent mesage -> {}", event);
            } catch (Exception e) {
                log.error("", e);
            }
        }, 1, 1, TimeUnit.SECONDS);

    }

    private static <T> T random(List<T> ts) {
        return ts.get(new Random().nextInt(ts.size()));
    }
}
