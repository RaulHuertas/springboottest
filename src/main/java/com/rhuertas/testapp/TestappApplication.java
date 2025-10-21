package com.rhuertas.testapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestappApplication implements CommandLineRunner{

	@Autowired
	private CustomerRepository repository;	
	public static void main(String[] args) {
		SpringApplication.run(TestappApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//repository.deleteAll();
		//repository.save(new Customer("Alice", "Wonderland"));
		//repository.save(new Customer("Bob", "Builder"));

		//// fetch all customers
		//System.out.println("Customers found with findAll():");
		//System.out.println("-------------------------------");
		//for (Customer customer : repository.findAll()) {
		//	System.out.println(customer);
		//}
		//System.out.println();

		////fetch an individual customer
		//System.out.println("Customers found with findByLastName('Builder'):");
		//for(Customer customer : repository.findByLastName("Builder")) {
		//	System.out.println(customer);
		//}


	}
}
