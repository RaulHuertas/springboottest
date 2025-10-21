package com.rhuertas.testapp;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;


public class Order {
    static final int PROCESSING = 1;
    static final int COMPLETED = 2; 
    static final int CANCELLED = 3;

    @Id
    private String id;
    private String customerId;
    private int  status;
    private List<Item> items;
    private OffsetDateTime timestamp;

    public Order() {
    }

    public Order(
        String customerId, 
        int status, 
        List<Item> items
    ) {
        this.customerId = customerId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
