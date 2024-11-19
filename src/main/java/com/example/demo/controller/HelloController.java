package com.example.demo.controller;

import com.example.demo.MetricPublisher; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
       
        MetricPublisher.publishMetric("HelloWorldRequests", 1.0);

       
        return "Hello World!";
    }
}
