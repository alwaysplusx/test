package com.example.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateoasApplication.class, args);
    }

    @ResponseBody
    @GetMapping("/echo")
    public String echo() {
        return "{\"Hello\":\"World\"}";
    }

}
