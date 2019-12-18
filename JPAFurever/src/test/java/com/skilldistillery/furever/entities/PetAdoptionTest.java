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

class PetAdoptionTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private PetAdoption petAdoption;

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
		petAdoption = em.find(PetAdoption.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test pet adoption entity mappings")
	void testName() {
		assertNotNull(petAdoption);
		assertEquals("2019-12-17T00:00", petAdoption.getDateRequested().toString());
	}

//	@Test
//	@DisplayName("test pet adoption species mapping")
//	void testSpecies() {
//		assertNotNull(foster);
//		assertEquals("Dog", foster.getUser().get  );
//	}
}
