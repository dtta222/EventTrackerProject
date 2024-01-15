package com.skilldistillery.restaurant.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerDAOImpl implements CustomerDAO {
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public Customer createCustomer(Customer customer) {
		em.persist(customer);
        return customer;
	}

	@Override
	public Customer updateCustomer(int customerId, Customer updatedCustomer) {
	    Customer existingCustomer = em.find(Customer.class, customerId);
	    if (existingCustomer != null) {
	        // Update only the fields that are allowed to be updated
	        existingCustomer.setFirstName(updatedCustomer.getFirstName());
	        existingCustomer.setLastName(updatedCustomer.getLastName());
	        // Update other fields as needed...

	        return em.merge(existingCustomer);
	    } else {
	        return null; // or throw a custom exception
	    }
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		boolean deleted = false;
		Customer toDelete = em.find(Customer.class, customerId);
		if (toDelete != null) {
			em.remove(toDelete);
			deleted = true;
		}
		return deleted;
	}
	

	@Override
	public Customer getCustomerById(int customerId) {
		return em.find(Customer.class, customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
	}

}
