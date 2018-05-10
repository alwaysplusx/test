package org.harmony.test.spring.boot.autoconfigure;

import org.harmony.test.spring.boot.servlet.GreetServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author wuxii@foxmail.com
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(GreetServlet.class)
@ConditionalOnProperty(prefix = "harmony.greet", name = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(GreetProperties.class)
public class GreetConfiguration {

    private final GreetProperties properties;

    public GreetConfiguration(GreetProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ServletRegistrationBean greetService() {
        return new ServletRegistrationBean(new GreetServlet(), properties.getUrl());
    }

}
