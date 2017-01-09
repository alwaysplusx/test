package org.harmony.test.jdbc;

import static org.harmony.test.jdbc.DBPrintUtils.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.harmony.test.jdbc.DBUtils;
import org.harmony.test.jdbc.DBUtils.DatabaseType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class H2MetaDataTest {

    Connection conn;
    DatabaseMetaData metaData;

    @Before
    public void setUp() throws Exception {
        conn = DBUtils.getConnection(DatabaseType.H2);
        metaData = conn.getMetaData();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void printDatabaseMetaData() throws Exception {
        System.out.println("Database Name    : " + metaData.getDatabaseProductName());
        System.out.println("Database Version : " + metaData.getDatabaseProductVersion());
        System.out.println("Driver Name      : " + metaData.getDriverName());
        System.out.println("Driver Version   : " + metaData.getDriverVersion());
        ResultSet tables = metaData.getTables(conn.getCatalog(), "PUBLIC", null, new String[] { "TABLE" });
        ResultSetMetaData data = tables.getMetaData();
        System.out.println("Table Name       : " + data.getTableName(1));
        printTableStruts(data);
    }

    @Test
    public void testGetTableInfo() throws Exception {
        Statement statement = conn.createStatement();
        statement.setFetchSize(1);
        statement.setMaxRows(2);
        ResultSet resultSet = statement.executeQuery("select * from t_user");

        printRecord(resultSet);

        ResultSetMetaData data = resultSet.getMetaData();
        printTableStruts(data);
        statement.close();
    }

    @Test
    public void testGetConstraint() throws Exception {
        ResultSet primaryKeys = metaData.getPrimaryKeys(conn.getCatalog(), "PUBLIC", "T_USER");
        System.out.println("Table `t_user` primary keys:");
        printRecord(primaryKeys);
        // ResultSet importedKeys = metaData.getImportedKeys(conn.getCatalog(),
        // "PUBLIC", "T_USER");
        // System.out.println("Table `t_user` imported keys");
        // printRecord(importedKeys);
    }

}
