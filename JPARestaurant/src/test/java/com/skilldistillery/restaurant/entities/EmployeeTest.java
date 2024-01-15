package com.skilldistillery.restaurant.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class EmployeeTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Employee employee;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPARestaurant");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		employee = em.find(Employee.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		employee = null;
	}

	@Test
	void test_Post_entity_mapping() {
		assertNotNull(employee);
		assertEquals("Davis", employee.getLastName());
	}

}
