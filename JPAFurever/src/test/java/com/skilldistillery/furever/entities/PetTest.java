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

class PetTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Pet pet;

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
		pet = em.find(Pet.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test pet mapping")
	void test1() {
		assertNotNull(pet);
		assertEquals(1, pet.getId());
	}
	
	@Test
	@DisplayName("test name")
	void test2() {
		assertEquals("Leeloo", pet.getName());
	}
	
	@Test
	@DisplayName("test size")
	void test3() {
		assertEquals("average", pet.getSize());
	}
	
	@Test
	@DisplayName("test age")
	void test4() {
		assertEquals(4, pet.getAge());
	}
	
	@Test
	@DisplayName("test color")
	void test5() {
		assertEquals("Golden", pet.getColor());
	}
	
	@Test
	@DisplayName("test fixed")
	void test6() {
		assertEquals(true, pet.isFixed());
	}
	
	
	@Test
	@DisplayName("test spec conditions")
	void test7() {
		assertEquals("None", pet.getSpecialConditions());
	}
	
	@Test
	@DisplayName("test if adopted boolean is true or false")
	void test8() {
		assertEquals(false, pet.isAdopted());
	}
	
	@Test
	@DisplayName("test if mappoing works")
	void test9() {
		assertEquals("Littleton", pet.getShelter().getAddress().getCity());
	}
	
	@Test
	@DisplayName("test pet to trait mappings")
	void test10() {
		assertEquals("Playful", pet.getTraits().get(0).getDescription());
	}
}
	