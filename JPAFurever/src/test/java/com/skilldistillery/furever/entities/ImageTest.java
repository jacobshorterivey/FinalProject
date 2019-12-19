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

class ImageTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Image image;
	
	

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
		image = em.find(Image.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		image = null;
		em.close();
	}

	@Test
	@DisplayName("test pet adoption mapping is correct")
	void test1() {
		assertNotNull(image);
		assertEquals("https://www.westparkanimalhospital.com/wp-content/uploads/2019/05/WestPark_iStock-600994082-1024x706-1.webp", image.getImageUrl());
	}
}
