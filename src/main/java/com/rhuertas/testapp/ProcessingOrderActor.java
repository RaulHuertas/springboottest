package com.rhuertas.testapp;
import akka.actor.AbstractActor;
import com.proto.testapp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
public class ProcessingOrderActor extends AbstractActor {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    ManagedChannel channel; 
    TestAppServiceGrpc.TestAppServiceBlockingStub stub;
    
    @Value("${app.grpcPort}")
    private int grpcPort;
    @Value("${app.grpcAddress}")
    private String grpcAddress;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(OrderRequest.class, msg -> {
                System.out.println("Processing order: " + msg);
                // Add order processing logic here
                Order withId = orderRepository.save(
                    new Order(
                        msg.getCustomerEmail(), 
                        Order.PROCESSING, 
                        msg.getItemsList().stream().map(item -> new Item(item.getName(), item.getPrice())).toList()
                    )
                );
                

            })
            .build();
    }
    @Override   
    public void preStart(){
        System.out.println("ProcessOrderActor started");
        channel = ManagedChannelBuilder.forAddress(grpcAddress, grpcPort).usePlaintext().build();
        stub = TestAppServiceGrpc.newBlockingStub(channel);

    }

    @Override
    public void postStop(){
        System.out.println("ProcessOrderActor stopped");
    }

}



