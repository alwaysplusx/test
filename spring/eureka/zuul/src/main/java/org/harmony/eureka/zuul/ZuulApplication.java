package org.harmony.eureka.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author wuxii@foxmail.com
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
