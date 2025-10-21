package com.rhuertas.testapp;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.errorprone.annotations.Keep;
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

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import akka.actor.ActorSystem;
import akka.actor.Actor;
import akka.actor.ActorRef;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    ManagedChannel channel; 
    TestAppServiceGrpc.TestAppServiceFutureStub stub;   

    @Value("${app.grpcPort}")
    private int grpcPort;
    @Value("${app.grpcAddress}")
    private String grpcAddress;

    @Value("${app.author}")
    private String author;

    Logger logger = LogManager.getLogger(TestController.class);

    TestController() {
        logger.info("TestController(by rax) initialized");
        channel = ManagedChannelBuilder.forAddress(grpcAddress, grpcPort).usePlaintext().build();
        stub = TestAppServiceGrpc.newFutureStub(channel);
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

        logger.info("Received order request: " + orderRequest.toString());
        logger.info("customerEmail: " + orderRequest.getCustomerEmail()); 
        logger.info("items: "+orderRequest.getItemsList().toString());
        stub.placeOrder(
            com.proto.testapp.Order.newBuilder()
            .setCustomerId(orderRequest.getCustomerEmail())
            .setStatus(Order.NEW)
            .addAllItems(orderRequest.getItemsList())
            .build()
        );


        return "Order being processed: " + orderRequest.getCustomerEmail();
 

    }





}
