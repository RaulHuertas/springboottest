package com.rhuertas.testapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/api")
public class TestController {
    @Value("${app.author}")
    private String author;
    @GetMapping("/test")
    public String testEndpoint() {
        return "hi, you can do this!"+ author;
    }
    
}
