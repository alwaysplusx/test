package org.harmony.spring.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxii@foxmail.com
 */
@RestController
@SpringBootApplication
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
		System.out.println("receive message -> " + msg);
	}

	@Bean
	ApplicationRunner runner() {
		return args -> {
			kafkaTemplate.send("foo", "bar");
		};
	}

}
