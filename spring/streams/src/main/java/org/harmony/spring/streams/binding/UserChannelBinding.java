package org.harmony.spring.streams.binding;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.harmony.spring.streams.event.UserEvent;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author wuxii@foxmail.com
 */
public interface UserChannelBinding {

    String USER_EVENT_IN = "user-event-in";
    String USER_EVENT_OUT = "user-event-out";

    String ACTION_COUNT_MV = "count-mv";

    String ACTION_COUNT_OUT = "action-count-out";
    String ACTION_COUNT_IN = "action-count-in";

    @Output(USER_EVENT_OUT)
    MessageChannel userEventsOut();

    @Input(USER_EVENT_IN)
    KStream<String, UserEvent> userEventsIn();

    //
    @Output(ACTION_COUNT_OUT)
    KStream<String, Long> actionCountOut();

    @Input(ACTION_COUNT_IN)
    KTable<String, Long> actionCountIn();

}
