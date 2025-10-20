package com.rhuertas.testapp;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Abs;

import akka.actor.AbstractActor;

public class CompleteOrderActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Order.class, this::onReceiveOrder)
                .build();
    }

    private void onReceiveOrder(Order order) {
        // Handle the order completion logic
    }
}
