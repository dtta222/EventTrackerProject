package com.skilldistillery.restaurant.data;

import java.util.List;

import com.skilldistillery.restaurant.entities.Customer;

public interface CustomerDAO {
	
	Customer createCustomer(Customer customer);
	Customer updateCustomer(int customerId, Customer customer);
	boolean deleteCustomer(int customerId);
	Customer getCustomerById(int customerId);
	List<Customer> getAllCustomers();
}
