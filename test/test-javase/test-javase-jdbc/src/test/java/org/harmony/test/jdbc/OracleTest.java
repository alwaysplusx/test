package org.harmony.test.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.harmony.test.jdbc.DBUtils.DatabaseType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
@Ignore
public class OracleTest {

    static Connection conn;
    static DatabaseMetaData metaData;

    @BeforeClass
    public static void beforeClass() throws Exception {
        conn = DBUtils.getConnection(DatabaseType.ORACLE);
        metaData = conn.getMetaData();
    }

    @Test
    public void test() throws SQLException {
        printAll(metaData.getSchemas());
        printAll(metaData.getCatalogs());
        printAll(metaData.getTables("platform", null, null, new String[] { "TABLE" }));
    }

    private static void printAll(ResultSet result) throws SQLException {
        while (result.next()) {
            try {
                for (int i = 1;; i++) {
                    System.out.print(result.getString(i) + ", ");
                }
            } catch (Exception e) {
            }
            System.out.println();
        }
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        conn.close();
    }
}
