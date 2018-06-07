package org.harmony.eureka.client;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.harmony.eureka.apis.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxii@foxmail.com
 */
// @EnableDiscoveryClient
@SpringBootApplication
@RestController
@RequestMapping("/member")
public class MemberApplication {

    private static final Logger log = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MemberApplication.class, args);
    }

    private List<Member> members = Arrays.asList(new Member("wuxii", 18), new Member("david", 10), //
            new Member("mary", 22), new Member("linda", 30), new Member("kent", 7));

    @GetMapping("/u/{name}")
    public Member member(@PathVariable("name") String name) {
        log.info("member service invoke");
        Optional<Member> member = members.stream().filter(e -> name.equals(e.getName())).findFirst();
        return member.isPresent() ? member.get() : null;
    }

    @GetMapping("/all")
    public List<Member> all() {
        return members;
    }

}
