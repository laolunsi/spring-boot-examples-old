package com.eknown.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/6 16:01
 */
@RestController
public class HelloController {

    @GetMapping(value = "hello")
    public String hello() {
        return "hello, this is test interface";
    }

    @GetMapping(value = "info/{name}")
    public String info(@PathVariable("name") String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "hello" + name + ", this is your info";
    }

    @GetMapping(value = "info/list")
    public String infoList() {
        return "this is info list interface...";
    }
}
