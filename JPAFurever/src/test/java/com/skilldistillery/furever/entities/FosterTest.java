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

//	@Test
//	@DisplayName("test foster species mapping")
//	void testSpecies() {
//		assertNotNull(foster);
//		assertEquals("Dog", foster.getUser().get  );
//	}

}
