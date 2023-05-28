package com.javaguidance.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
