package com.example.springfox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@EnableSwagger2
@SpringBootApplication
public class SpringfoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringfoxApplication.class, args);
    }

    @ResponseBody
    @GetMapping("/echo")
    public String echo() {
        return "{\"Hello\":\"World\"}";
    }

    @RequestMapping("/swagger-ui")
    public String swagger() {
        return "redirect:swagger-ui.html";
    }

    @Bean
    Docket userApis() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .groupName("user-api")//
                .apiInfo(apiInfo())//
                .select()//
                .paths(e -> e.contains("user"))//
                .build();
    }

    @Bean
    Docket newsApis() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .groupName("news-api")//
                .apiInfo(apiInfo())//
                .select()//
                .paths(Predicates.or(PathSelectors.regex("/news.*")))//
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("RESTful Api文档")//
                .description("使用spring-boot + springfox + swagger-ui开发示例")//
                .termsOfServiceUrl("http://www.foo.bar/")//
                .contact(new Contact("wuxii", "github.com/wuxii", "wuxii@foxmail.com"))//
                .version("1.0")//
                .build();
    }

}
