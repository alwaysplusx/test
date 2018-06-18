package org.harmony.spring.streams.handler;

import org.apache.kafka.streams.kstream.KTable;
import org.harmony.spring.streams.binding.UserChannelBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author wuxii@foxmail.com
 */
@Component
public class ActionCountHandler {

    private static final Logger log = LoggerFactory.getLogger(ActionCountHandler.class);

    @StreamListener
    public void process(@Input(UserChannelBinding.ACTION_COUNT_IN) KTable<String, Long> counts) {
        counts.toStream()//
                .foreach((key, value) -> log.info("action counts, {}={}", key, value));
    }

}
