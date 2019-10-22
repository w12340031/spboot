package com.example.spboot.controller;

import com.example.spboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/html")
public class JspController {
    @Resource
    private UserService userService;
    private final Logger log=LoggerFactory.getLogger(JspController.class);
    @RequestMapping("/hi")
    public Object index() {
        log.debug("hello");
        //int a = 1 / 0;
        return "hello11124444";
    }

    @RequestMapping("thymeleaf")
    public String test(ModelMap map) {
        map.addAttribute("name", "lison");
        return "testThymeleaf";
    }

    @RequestMapping("/404.do")
    public Object error_404() {
        return "你要找的页面，被lison偷吃了！";
    }

    @RequestMapping("/testE")
    public String indexTest() {
        userService.testException();
        return "成功";
    }
}
