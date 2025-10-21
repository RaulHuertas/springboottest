package com.rhuertas.testapp;
import org.springframework.beans.factory.annotation.Autowired;

import com.proto.testapp.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class ProcessingOrderImpl extends TestAppServiceGrpc.TestAppServiceImplBase {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void placeOrder(com.proto.testapp.Order order, StreamObserver <OrderResponse> responseObserver) {

        String orderId = order.getId();
        int status = 2;
        //Delay
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Simulate a 90% success rate
        if (Math.random() < 0.1) {
            status = Order.CANCELLED; //Failed
        }else {
            status = Order.COMPLETED; //Success
        }
        //call smpp here

        var orderUpdate = orderRepository.findById(orderId).get();
        orderUpdate.setStatus(status);

        orderRepository.save(orderUpdate);

        var responseBuilder = OrderResponse.newBuilder();
        responseBuilder.setOrderId(orderId);
        responseBuilder.setStatus(status);

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }


  }