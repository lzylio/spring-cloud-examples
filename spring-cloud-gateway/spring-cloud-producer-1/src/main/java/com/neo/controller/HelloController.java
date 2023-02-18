package com.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
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

    @RequestMapping("/customer")
    public String customer() {
        String userId = request.getHeader(USER_ID);
        String appId = request.getHeader(APP_ID);
        System.out.println(userId);
        System.out.println(appId);
        return "9001";
    }

}