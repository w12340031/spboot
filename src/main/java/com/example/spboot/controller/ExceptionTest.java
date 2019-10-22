package com.example.spboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTest {
    @RequestMapping("/404.do")
    public Object error_404() {
        return "你要找的页面，被lison偷吃了！";
    }
}
