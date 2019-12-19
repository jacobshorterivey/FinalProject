package com.skilldistillery.furever.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SkillTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Skill skill;
	
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
		skill = em.find(Skill.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("testing skill entity")
		void test1() {
			assertNotNull(skill);
			assertEquals("Grooming", skill.getName());
	}
	@Test
	@DisplayName("testing skill user mapping")
	void test2() {
		assertNotNull(skill);
		assertEquals(1, skill.getUsers().size());
	}
}
