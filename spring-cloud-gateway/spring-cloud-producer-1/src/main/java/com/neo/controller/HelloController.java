package com.neo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class HelloController {

    private static final String TIME = "time";
    private static final String USER_ID = "userId";
    private static final String APP_ID = "appId";

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/hello")
    public String index() {
        return "hello world smile!";
    }

    @RequestMapping("/foo")
    public String foo(String foo) {
        return "hello " + foo + "!!";
    }

    // http://localhost:8888/customer
    @RequestMapping("/customer")
    public String customer(String param) {
        log.info("param = " + param);
        String userId = request.getHeader(USER_ID);
        String appId = request.getHeader(APP_ID);
        System.out.println(userId);
        System.out.println(appId);
        return "9001";
    }

}