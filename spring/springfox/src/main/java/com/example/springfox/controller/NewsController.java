package com.example.springfox.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springfox.entity.News;

/**
 * @author wuxii@foxmail.com
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @GetMapping("/all")
    public List<News> all() {
        return Arrays.asList(new News("foo", "bar"), new News("baz", "qux"));
    }

}
