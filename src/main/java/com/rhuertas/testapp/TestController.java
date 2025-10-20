package com.rhuertas.testapp;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import org.springframework.web.bind.annotation.RequestBody;

import com.proto.testapp.*;
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

    @PostMapping("/addCustomer")
    public String addCustomer( String firstName, String lastName, String email) {
        customerRepository.save(
            new Customer(firstName, lastName,email)
        );
        return "Customer added";
    }

    @PostMapping("/testJson")
    public String testJson(@RequestBody ObjectNode root) {
        logger.info("Received JSON payload: " + root.get("customerEmail"));
        return "JSON received";
    }

    @PostMapping("/makeOrder")
    public String makeOrder(@RequestBody com.proto.testapp.OrderRequest orderRequest) {
        // Here you would typically process the orderRequest and create an Order
        // For simplicity, we will just log the order details

        logger.info("Received order request: " + orderRequest.toString());
        logger.info("customerId: " + orderRequest.getCustomerEmail()); 
        // Simulate order processing
        return "Order received for customer: " + orderRequest.getCustomerEmail();
    }




}
