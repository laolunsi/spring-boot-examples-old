package com.eknown.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eknown
 * @version 1.0
 * @since 2019/5/29 13:44
 */
@RestController
public class TestController {

    @GetMapping(value = "")
    public String index() {
        return "welcome, this is the first page";
    }
}
