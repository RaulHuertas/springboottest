package com.rhuertas.testapp;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;



public class Order {
    public enum Status {
        RECEIVED("RECEIVED"),
        PROCESSING("PROCESSING"),
        COMPLETED("COMPLETED"),;

        public final String label;

        private Status(String label) {
            this.label = label;
        }
    }

    @Id
    private String id;
    private String customerId;
    private int customerPhoneNumber;
    private Status status;
    private List<String> items;
    private OffsetDateTime ts;

    public Order() {
    }

    public Order(
        String customerId, 
        int customerPhoneNumber,
        Status status, 
        List<String> items
    ) {
        this.customerId = customerId;
        this.customerPhoneNumber = customerPhoneNumber;
        this.status = status;
        this.items = items;
    }    

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getItems() {
        return items;
    }


}
