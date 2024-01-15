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

class ReservationTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Reservation reservation;

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
		reservation = em.find(Reservation.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		reservation = null;
	}

	@Test
	void test_Post_entity_mapping() {
		assertNotNull(reservation);
		assertEquals("Confirmed", reservation.getStatus());
	}

}
