package com.example.micrometer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MicrometerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrometerApplication.class, args);
    }

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Bean
    MeterRegistryCustomizer<MeterRegistry> registryCustomizer() {
        return registry -> registry.config().commonTags("");
    }

    @Bean
    MeterFilter meterFilter() {
        // micrometer exclude meter name start with jvm
        return MeterFilter.denyNameStartsWith("jvm");
    }

    @Bean
    ApplicationRunner runner(MeterRegistry mr) {
        return arg -> {
            this.executorService.scheduleWithFixedDelay(() -> {
                mr.timer("customer-task").record(Duration.ofMillis((long) (Math.random() * 1000)));
            }, 500, 500, TimeUnit.MILLISECONDS);

        };
    }
}
