package org.harmony.eureka.movie.api;

import org.harmony.eureka.apis.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuxii@foxmail.com
 */
@FeignClient("member")
public interface MemberClient {

    @RequestMapping("/member/u/{name}")
    Member member(@PathVariable("name") String name);

}
