package com.skilldistillery.furever.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("Furever");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
	
	}

	@Test
	@DisplayName("test user entity mappings")
	void username() {
		assertNotNull(user);
		assertEquals("secondUser", user.getUsername());
	}
	
	@Test
	@DisplayName("test user relationship mappings to address")
	void address() {
		assertNotNull(user);
		assertEquals("CO", user.getAddress().getStateAbbr());
	}
	@Test
	@DisplayName("test user relationship mappings to shelter")
	void shelter() {
		assertNotNull(user);
		assertEquals(1, user.getShelters().size());
	}
	@Test
	@DisplayName("test user relationship mappings to pet adoption")
	void petAdoption() {
		assertNotNull(user);
		assertEquals(1, user.getAdoptions().size());
	}

}
