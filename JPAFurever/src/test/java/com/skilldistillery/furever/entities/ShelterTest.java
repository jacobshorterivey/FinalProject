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

class ShelterTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Shelter shelter;

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
		shelter = em.find(Shelter.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test shelter mapping")
	void test1() {
		assertNotNull(shelter);
		assertEquals(1, shelter.getId());
	}
	
	@Test
	@DisplayName("test phone number")
	void test2() {
		assertNotNull(shelter);
		assertEquals("3037032938", shelter.getPhone());
	}
	
	@Test
	@DisplayName("test name")
	void test3() {
		assertNotNull(shelter);
		assertEquals("Humane Society Of The South Platte Valley", shelter.getName());
	}
	
	@Test
	@DisplayName("test website url")
	void test4() {
		assertNotNull(shelter);
		assertEquals("hsspv.org", shelter.getWebsiteUrl());
	}
	
	@Test
	@DisplayName("test shelter email")
	void test5() {
		assertNotNull(shelter);
		assertEquals("info@hsspv.org", shelter.getEmail());
	}
	
	@Test
	@DisplayName("test is shelter is active")
	void test6() {
		assertNotNull(shelter);
		assertEquals(true, shelter.isActive());
	}
	
//	@Test
//	@DisplayName("test address id")
//	void test7() {
//		assertNotNull(shelter);
//		assertEquals(2, shelter.getAddressId());
//	}
	
	@Test
	@DisplayName("test username")
	void test8() {
		assertNotNull(shelter);
		assertEquals("testShelter", shelter.getUsername());
	}
	
	@Test
	@DisplayName("test password")
	void test9() {
		assertNotNull(shelter);
		assertEquals("test", shelter.getPassword());
	}

	@Test
	@DisplayName("test role")
	void test10() {
		assertNotNull(shelter);
		assertEquals("shelter", shelter.getRole());
	}
	@Test
	@DisplayName("test relationship to User")
	void test11() {
		assertNotNull(shelter);
		assertEquals(1, shelter.getUsers().size());
	}
	
	//TODO test relationship with Images
}