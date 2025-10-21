package com.rhuertas.testapp;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    public Order findByCustomerId(String customerId);
    public List<Order> findByStatus(int status);
    public List<Order> findByCustomerPhoneNumber(int customerPhoneNumber); 


}
