package org.moon.test.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EntityManagerFactoryTest {

	private EntityManagerFactory emf;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("moon");
	}

	@Test
	public void test() {
		assertNotEquals("EntityManagerFactory is not null !", null, emf);
		assertNotEquals("EntityManager is not null !", null, emf.createEntityManager());
	}

	@After
	public void tearDown() throws Exception {
	}

}
