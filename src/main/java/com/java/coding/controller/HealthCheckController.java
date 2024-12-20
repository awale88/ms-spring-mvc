package com.java.coding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    public static final String UP = "Server Health: [UP]";

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String checkHealth() {return UP;}
}
