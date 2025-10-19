package com.rhuertas.testapp;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    CustomerRepository customerRepository;

    @Value("${app.author}")
    private String author;


    @GetMapping("/test")
    public String testEndpoint() {
        List<Customer> customers = customerRepository.findAll();
        Gson gson = new Gson();
        String jsonArray = gson.toJson(customers);
        //return customers.toString();
        return jsonArray;

    }
    
}
