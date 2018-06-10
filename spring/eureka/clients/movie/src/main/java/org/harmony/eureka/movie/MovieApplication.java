package org.harmony.eureka.movie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.harmony.eureka.apis.Member;
import org.harmony.eureka.apis.MemberNotFoundException;
import org.harmony.eureka.apis.Movie;
import org.harmony.eureka.apis.MyFavorite;
import org.harmony.eureka.movie.api.MemberClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author wuxii@foxmail.com
 */
// @EnableDiscoveryClient
// @EnableHystrix
@EnableFeignClients
@SpringBootApplication
@RestController
@RequestMapping("/movie")
public class MovieApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MovieApplication.class, args);
    }

    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate primaryRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // private static final Logger log = LoggerFactory.getLogger(MovieApplication.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @LoadBalanced
    private RestTemplate primaryRestTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MemberClient memberClient;

    private List<Movie> movies = Arrays.asList(new Movie("lion king", 6), new Movie("frozen", 6), //
            new Movie("shawshank redemption", 18), new Movie("the sendlot", 0), //
            new Movie("hook", 0));

    @GetMapping("/zipkin/1")
    public String zipkin1() {
        return primaryRestTemplate.getForObject("http://member/member/u/wuxii", String.class);
    }

    @GetMapping("/zipkin/2")
    public String zipkin2() {
        return restTemplate.getForObject("http://member/member/u/wuxii", String.class);
    }

    @GetMapping("/zipkin/3")
    public String zipkin3() {
        try {
            return objectMapper.writeValueAsString(memberClient.member("wuxii"));
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    @GetMapping("/all")
    public List<Movie> all() {
        return movies;
    }

    @GetMapping("/favorite/{user}")
    public MyFavorite favorite(@PathVariable("user") String user) {
        Member member = memberClient.member(user);
        return new MyFavorite(member, movies);
    }

    @GetMapping("/recommendations/{user}")
    @HystrixCommand(fallbackMethod = "fallback")
    public List<Movie> recommendations(@PathVariable("user") String user) {
        Member member = primaryRestTemplate.getForObject("http://member/member/u/{user}", Member.class, user);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        int age = member.getAge();
        return movies.stream().filter(e -> e.getLimit() < age).collect(Collectors.toList());
    }

    // 容错方法， enable hystrix，方法参数要与被容错的方法相同
    public List<Movie> fallback(String user) {
        return movies.stream().filter(e -> e.getLimit() == 0).collect(Collectors.toList());
    }

}
