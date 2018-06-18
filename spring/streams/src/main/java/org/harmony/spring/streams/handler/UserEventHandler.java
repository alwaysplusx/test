package org.harmony.spring.streams.handler;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.harmony.spring.streams.binding.UserChannelBinding;
import org.harmony.spring.streams.event.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author wuxii@foxmail.com
 */
@Component
public class UserEventHandler {

    private static final Logger log = LoggerFactory.getLogger(UserEventHandler.class);

    @StreamListener
    @SendTo(UserChannelBinding.ACTION_COUNT_OUT)
    public KStream<String, Long> process(@Input(UserChannelBinding.USER_EVENT_IN) KStream<String, UserEvent> events) {
        return events.filter((k, v) -> v.getTimes() > 10)//
                .map((k, v) -> {
                    log.info("process event {}={}", k, v);
                    return new KeyValue<>(v.getAction(), "0");
                })//
                .groupByKey()//
                .count(Materialized.as(UserChannelBinding.ACTION_COUNT_MV))//
                .toStream();
    }

}
