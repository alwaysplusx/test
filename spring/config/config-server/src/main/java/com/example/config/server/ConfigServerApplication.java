package com.example.config.server;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        // --spring.profiles.active=native
        System.setProperty("user.parent.dir", new File(System.getProperty("user.dir")).getParentFile().getAbsolutePath());
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
