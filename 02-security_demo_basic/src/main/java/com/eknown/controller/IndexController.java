package com.eknown.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author eknown
 * @version 1.0
 * @since 2019/5/29 13:44
 */
@RestController
@RequestMapping(value = "")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = "")
    public String index() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        return "welcome, this is the first page : " + SecurityContextHolder.getContext().getAuthentication().toString();
    }
}
