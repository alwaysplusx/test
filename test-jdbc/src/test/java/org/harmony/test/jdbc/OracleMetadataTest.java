package org.harmony.test.jdbc;

import static org.harmony.test.jdbc.DBPrintUtils.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.harmony.test.jdbc.DBUtils;
import org.harmony.test.jdbc.DBUtils.DatabaseType;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class OracleMetadataTest {

    private static Connection conn;

    static {
        try {
            conn = DBUtils.getConnection(DatabaseType.ORACLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printDatabaseMetaData() throws Exception {
        DatabaseMetaData metaData = conn.getMetaData();
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
        statement.setMaxRows(1);
        ResultSet resultSet = statement.executeQuery("select * from t_user");

        printRecord(resultSet);

    }
}
