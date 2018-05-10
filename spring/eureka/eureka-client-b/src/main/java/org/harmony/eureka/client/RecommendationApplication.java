package org.harmony.eureka.client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.harmony.eureka.apis.Member;
import org.harmony.eureka.apis.MemberNotFoundException;
import org.harmony.eureka.apis.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wuxii@foxmail.com
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RequestMapping("/api/movie")
public class RecommendationApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RecommendationApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    private List<Movie> movies = Arrays.asList(new Movie("lion king", 6), new Movie("frozen", 6), //
            new Movie("shawshank redemption", 18), new Movie("the sendlot", 0), //
            new Movie("hook", 0));

    @GetMapping("/recommendations/{user}")
    @ResponseBody
    public List<Movie> recommendations(@PathVariable("user") String user) {
        Member member = restTemplate.getForObject("http://membership/api/member/{user}", Member.class, user);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        int age = member.getAge();
        return movies.stream().filter(e -> e.getLimit() < age).collect(Collectors.toList());
    }

}
