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

class BreedTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Breed breed;

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
		breed = em.find(Breed.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test breed entity mappings")
	void testName() {
		assertNotNull(breed);
		assertEquals("Shiba Inu", breed.getName());
	}
	@Test
	@DisplayName("test breed entity mappings")
	void testHairType() {
		assertNotNull(breed);
		assertEquals("Double-coat short", breed.getHairType());
	}
}
