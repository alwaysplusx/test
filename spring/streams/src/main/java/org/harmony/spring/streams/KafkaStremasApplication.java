package org.harmony.spring.streams;

import org.harmony.spring.streams.binding.UserChannelBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxii@foxmail.com
 */
@RestController
@SpringBootApplication
@EnableBinding(UserChannelBinding.class)
public class KafkaStremasApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(KafkaStremasApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/")
    public String echo() {
        kafkaTemplate.send("foo", "foo");
        return "{\"Hello\":\"World\"}";
    }

    @KafkaListener(topics = "foo", groupId = "foo")
    public void handleMessage(String msg) {
        System.err.println("receive message -> " + msg);
    }

}
