package org.moon.test.db;

import org.junit.Before;
import org.junit.Test;

public class DataBaseManagerTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testInitializeHSQLDataBase() {
		DataBaseManager.initializeHSQLDataBase();
	}

	@Test
	public void testInitializeH2DataBase() {
		DataBaseManager.initializeH2DataBase();
	}
	
	@Test
	public void testInitializeDerbyDataBase(){
		//DataBaseManager.initializeDerbyDataBase();
	}

	public void tearDown() throws Exception {

	}
}
