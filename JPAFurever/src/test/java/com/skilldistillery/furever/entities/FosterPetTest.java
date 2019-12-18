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

class FosterPetTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private FosterPet fosterPet;

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
		fosterPet = em.find(FosterPet.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test fosterpet mappiong is correct")
	void test1() {
		assertNotNull(fosterPet);
		assertEquals(1, fosterPet.getId());
	}
	
	@Test
	@DisplayName("test if foster mapping is correct")
	void test2() {
		assertEquals(4, fosterPet.getFoster().getMaxFoster());
	}
	
	@Test
	@DisplayName("test pet mapping is correct")
	void test3() {
		assertEquals("Leeloo", fosterPet.getPet().getName());
	}
	
	@Test
	@DisplayName("test date requested")
	void test5() {
		assertEquals(null, fosterPet.getDateRequested());
	}
	
	@Test
	@DisplayName("test active")
	void test6() {
		assertEquals(false, fosterPet.isActive());
	}
	
	@Test
	@DisplayName("test notes")
	void test7() {
		assertEquals(null, fosterPet.getNotes());
	}
	
	@Test
	@DisplayName("date completed")
	void test8() {
		assertEquals(null, fosterPet.getDateCompleted());
	}
}