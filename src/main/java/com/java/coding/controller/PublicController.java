package com.java.coding.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublicController {

    @GetMapping(value = "/test")
    public String helloWorld(){
        return "Hello From Controller.";
    }

    @GetMapping(value="/test2", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        return "This is Home page";
    }

    @GetMapping(value="/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        return "Hello there!";
    }
}
