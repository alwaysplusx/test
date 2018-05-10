package org.harmony.test.spring.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wuxii@foxmail.com
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping({ "", "/index" })
    public String index() {
        return "users";
    }

    @ResponseBody
    @GetMapping("/view")
    public User view() {
        return new User("wuxii", "abc123");
    }

}
