package org.harmony.test.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author wuxii@foxmail.com
 */
@Controller
@RequestMapping("/")
@SpringBootApplication
public class WeixinApplication {

    static final String TOKEN = "token";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WeixinApplication.class, args);
    }

    @ResponseBody
    @RequestMapping
    public String sayHi(NativeWebRequest webRequest) {
        return webRequest.getParameter("echostr");
    }

}
