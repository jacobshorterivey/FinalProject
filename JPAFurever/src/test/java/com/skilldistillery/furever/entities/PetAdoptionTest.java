package com.skilldistillery.furever.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PetAdoptionTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private PetAdoption adoption;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("Furever");
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
		void setUp() throws Exception {
			em = emf.createEntityManager();
			adoption= em.find(PetAdoption.class, 1);
			
	}
	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("test pet adoption mapping is correct")
	void test1() {
		assertNotNull(adoption);
		assertEquals("1", adoption.getId());
	}
	
	@Test
	@DisplayName("test pet adoption mapping is correct")
	void test2() {
		assertNotNull(adoption);
		assertEquals("2", adoption.getUser());
	}
}
