package com.example.jta;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@SpringBootApplication
public class JtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtaApplication.class, args);
    }

    private static final String DESTINATION = "message";

    @RestController
    public static class UserController {

        private UserRepository userRepository;
        private JmsTemplate jmsTemplate;

        public UserController(UserRepository userRepository, JmsTemplate jmsTemplate) {
            this.userRepository = userRepository;
            this.jmsTemplate = jmsTemplate;
        }

        @GetMapping
        public List<User> read() {
            return Lists.newArrayList(userRepository.findAll());
        }

        @PostMapping
        @Transactional
        public void write(@RequestBody Map<String, String> payload, @RequestParam("rollback") Optional<Boolean> rollback) {
            String id = UUID.randomUUID().toString();
            String username = payload.get("username");
            String description = payload.get("description");
            userRepository.save(new User(id, username, description));
            jmsTemplate.convertAndSend(DESTINATION, "Hello " + username);
        }

    }

    public static class UserMessageListener {

        @JmsListener(destination = DESTINATION)
        public void onMessage(String msg) {
            System.out.println("on message: " + msg);
        }

    }

}
