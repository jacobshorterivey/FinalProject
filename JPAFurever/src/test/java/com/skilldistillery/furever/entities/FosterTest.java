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

class FosterTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Foster foster;

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
		foster = em.find(Foster.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test foster entity mappings")
	void testName() {
		assertNotNull(foster);
		assertEquals(4, foster.getMaxFoster());
	}

	@Test
	@DisplayName("test foster getting user")
	void testSpecies() {
		assertNotNull(foster);
		assertEquals("testUser", foster.getUser().getUsername());
	}


	@Test
	@DisplayName("test foster getting rating")
	void test3() {
		assertNotNull(foster);
		assertEquals(5, foster.getFosterReputations().get(0).getRating());
	}
	
	@Test
	@DisplayName("test if species is mapped correctly")
	void test4() {
		assertNotNull(foster);
		assertEquals("Dog", foster.getSpeciesList().get(0).getName());
	}
	
	@Test
	@DisplayName("test if breed is mapped correctly")
	void test5() {
		assertNotNull(foster);
		assertEquals("Shiba Inu", foster.getBreedList().get(0).getName());
	}
	
	@Test
	@DisplayName("test if trait is mapped correctly")
	void test6() {
		assertNotNull(foster);
		assertEquals("Playful", foster.getTraitList().get(0).getDescription());
	}
}
