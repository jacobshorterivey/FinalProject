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

class AddressTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Address address;

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
		address = em.find(Address.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test address mapping")
	void test1() {
		assertNotNull(address);
		assertEquals(2, address.getId());
	}
	
	@Test
	@DisplayName("test street")
	void test2() {
		assertEquals("2129 W Chenango Ave", address.getStreet());
	}
	
	@Test
	@DisplayName("test street 2")
	void test3() {
		assertEquals("Unit A", address.getStreet2());
	}
	
	@Test
	@DisplayName("city")
	void test4() {
		assertEquals("Littleton", address.getCity());
	}
	
	@Test
	@DisplayName("state abbreviation")
	void test5() {
		assertEquals("CO", address.getStateAbbr());
	}
	
	@Test
	@DisplayName("zip code")
	void test6() {
		assertEquals(80120, address.getZip());
	}
}

