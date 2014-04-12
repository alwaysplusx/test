package org.moon.test.db;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

	public static final String H2_URL = "jdbc:h2:file:target/h2/data";
	public static final String HSQL_URL = "jdbc:hsqldb:file:target/hsqldb/data";
	public static final String DERBY_URL = "jdbc:derby:file:target/derby/data";
	public static final String DEFAULT_USERNAME = "sa";
	public static final String DEFAULT_PASSWORD = "";

	public static void initializeH2DataBase() {
		initDataBase(H2_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}

	public static void initializeHSQLDataBase() {
		initDataBase(HSQL_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	
	@Deprecated
	public static void initializeDerbyDataBase(){
		initDataBase(DERBY_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}

	public static void initializeH2DataBase(String username, String password) {
		initDataBase(H2_URL, username, password);
	}

	public static void initializeHSQLDataBase(String username, String password) {
		initDataBase(HSQL_URL, username, password);
	}
	
	@Deprecated
	public static void initializeDerbyDataBase(String username, String password){
		initDataBase(DERBY_URL, username, password);
	}
	
	private static void initDataBase(String url, String username, String password) {
		dropDB(url);
		try {
			DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}

	private static void dropDB(String url) {
		int index;
		if ((index = url.indexOf("file")) != -1) {
			File file = new File(url.substring(index + 5, url.length() - 5));
			file.deleteOnExit();
		}
	}

}
