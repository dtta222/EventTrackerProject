package com.skilldistillery.restaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.restaurant.data.CustomerDAO;
import com.skilldistillery.restaurant.entities.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
    private CustomerDAO customerDAO;

    // Example GET endpoint to retrieve customer by ID
    @GetMapping("/{customerId}")
    public String getCustomerById(@PathVariable int customerId) {
        // Logic to retrieve customer by ID
        return "Customer with ID " + customerId;
    }

    // Example POST endpoint to create a new customer
    @PostMapping("/new_customer")
    public String createCustomer(@RequestBody String customerData) {
        // Logic to create a new customer
        return "New customer created";
    }
    
    @GetMapping
    public List<Customer> getAllCustomers() {
        // Logic to retrieve all customers from the database
        // Assuming Customer is an entity representing customers
        return customerDAO.getAllCustomers();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable int customerId, @RequestBody Customer updatedCustomer) {
        Customer updated = customerDAO.updateCustomer(customerId, updatedCustomer);
        if (updated != null) {
            return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        // Logic to delete a customer
        if (customerDAO.deleteCustomer(customerId)) {
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }


    // Add more endpoints for different actions related to customers

}
