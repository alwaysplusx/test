package org.moon.test.jdbc.meta;

import static org.moon.test.jdbc.meta.DBPrintUtils.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
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
        ResultSet tables = metaData.getTables(conn.getCatalog(), "PUBLIC", null, new String[]{"TABLE"});
        ResultSetMetaData data = tables.getMetaData();
        System.out.println("Table Name       : " + data.getTableName(1));
        printTableStruts(data);
    }

    @Test
    public void testGetTableInfo() throws Exception {
        Statement statement = conn.createStatement();
        ResultSetMetaData data = statement.executeQuery("select * from t_user").getMetaData();
        printTableStruts(data);
        statement.close();
    }

    @Test
    public void testGetConstraint() throws Exception {
        ResultSet primaryKeys = metaData.getPrimaryKeys(conn.getCatalog(), "PUBLIC", "T_USER");
        System.out.println("Table `t_user` primary keys:");
        printRecord(primaryKeys);
        //ResultSet importedKeys = metaData.getImportedKeys(conn.getCatalog(), "PUBLIC", "T_USER");
        //System.out.println("Table `t_user` imported keys");
        //printRecord(importedKeys);
    }
    
}
