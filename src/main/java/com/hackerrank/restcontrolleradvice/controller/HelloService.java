package com.hackerrank.restcontrolleradvice.controller;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String greet(String id) {
        return "Hello, " + id;
    }
}
