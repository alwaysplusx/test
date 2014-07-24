package org.moon.test.jdbc.meta;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.jdbc.DBUtils;

public class MetaDataTest {

	Connection conn;
	DatabaseMetaData metaData;

	@Before
	public void setUp() throws Exception {
		conn = DBUtils.getInstance().getConnection();
		metaData = conn.getMetaData();
	}

	@Test
	public void print() throws Exception {
		System.out.println("Database Name    : " + metaData.getDatabaseProductName());
		System.out.println("Database Version : " + metaData.getDatabaseProductVersion());
		System.out.println("Driver Name      : " + metaData.getDriverName());
		System.out.println("Driver Version   : " + metaData.getDriverVersion());
	}

	@Test
	public void testGetTableMetaData() throws Exception {
		StringBuffer o = new StringBuffer();
		Statement statement = conn.createStatement();
		ResultSetMetaData data = statement.executeQuery("select * from t_user").getMetaData();
		o.append("-------------------------------------------------------------------------------------------------").append("\n")
				.append("|    Name    |    TypeName    |    ClassName    |    Size    |").append("\n")
				.append("-------------------------------------------------------------------------------------------------").append("\n");
		for (int i = 1; i <= data.getColumnCount(); i++) {
			o.append("|    ").append(data.getColumnName(i)).append("	|	").append(data.getColumnType(i)).append("	|	").append(data.getColumnClassName(i)).append("	|	")
					.append(data.getColumnDisplaySize(i)).append("	|	").append(data.getColumnTypeName(i)).append("	|").append("\n");

		}
		o.append("-------------------------------------------------------------------------------------------------");
		System.out.println(o.toString());
		statement.close();
	}

	private static final int LEFT = 1;
	private static final int RIGHT = -1;

	public String appendToLength(String str, int length, int left) {
		if (str.length() < length) {
			if (LEFT == left) {
				str = appendToLength(" " + str, length, left * -1);
			} else {
				str = appendToLength(str + " ", length, left * -1);
			}
		}
		return str;
	}

	public static void main(String[] args) {
		String string = new MetaDataTest().appendToLength("def", 100, RIGHT);
		System.out.println(string);
	}

	@After
	public void tearDown() throws Exception {
		conn.close();
	}
}
