package org.harmony.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author wuxii@foxmail.com
 */
@SpringBootApplication
public class EdgeServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }

    // @Bean
    // DiscoveryClientRouteDefinitionLocator discoveryRoutes(DiscoveryClient dc) {
    // return new DiscoveryClientRouteDefinitionLocator(dc, new DiscoveryLocatorProperties());
    // }

    @Bean
    RouteLocator gatewayRouters(RouteLocatorBuilder builder) {
        return builder.routes()//
                .route(r -> r.path("/member/**").uri("lb://mebmer/"))//
                .route(r -> r.path("/movie/**").uri("lb://movie/"))//
                .build();
    }

    // @Bean
    // RouteLocator gatewayRouters(RouteLocatorBuilder builder) {
    // return builder.routes()//
    // .route(r -> r.path("/start").uri("http://start.spring.io:80/"))//
    // .route(r -> r.path("/member/all").uri("lb://membership/member/all"))//
    // .route(r -> r.path("/member/u/**")//
    // .filters(s -> s.rewritePath("/member/u/(?<NAME>.*)", "/member/u/${NAME}"))//
    // .uri("lb://membership/"))//
    // .build();
    // }

}
