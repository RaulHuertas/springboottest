package com.rhuertas.testapp;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    CustomerRepository customerRepository;

    @Value("${app.author}")
    private String author;


    Logger logger = LogManager.getLogger(TestController.class);

    TestController() {
        logger.info("TestController(by rax) initialized");
    }

    @GetMapping("/test")
    public String testEndpoint() {
        List<Customer> customers = customerRepository.findAll();
        Gson gson = new Gson();
        String jsonArray = gson.toJson(customers);
        //return customers.toString();
        return jsonArray;

    }
    
}
