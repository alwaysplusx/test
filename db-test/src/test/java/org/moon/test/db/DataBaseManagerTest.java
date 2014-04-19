package org.moon.test.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DataBaseManagerTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	@Ignore
	public void testInitializeHSQLDataBase() throws Exception {
		DataBaseManager.initializeHSQLDataBase();
	}

	@Test
	public void testInitializeH2DataBase() throws Exception {
		DataBaseManager.initializeH2DataBase();
	}

	@After
	public void tearDown() throws Exception {

	}
}
