package com.skilldistillery.furever.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TraitTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Trait trait;

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
		trait = em.find(Trait.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test1() {
		assertNotNull(trait);
		assertEquals("Intelligent", trait.getDescription());
	}
	
	@Test
	void test2() {
		assertNotNull(trait);
		assertEquals("Yuki", trait.getPets().get(0).getName());
	}

}
