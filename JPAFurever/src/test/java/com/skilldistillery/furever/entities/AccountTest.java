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

class AccountTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Account account;

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
		account = em.find(Account.class, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test address mapping")
	void test1() {
		assertNotNull(account);
		assertEquals(3, account.getId());
	}
	
	@Test
	@DisplayName("test username")
	void test2() {
		assertEquals("bakaie", account.getUsername());
	}
	
	@Test
	@DisplayName("test password")
	void test3() {
		assertEquals("test", account.getPassword());
	}
	
	@Test
	@DisplayName("test active")
	void test4() {
		assertEquals(true, account.isActive());
	}
	
	@Test
	@DisplayName("test role")
	void test5() {
		assertEquals("user", account.getRole());
	}
}