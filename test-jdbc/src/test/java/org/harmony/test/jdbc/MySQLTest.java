package org.harmony.test.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.harmony.test.jdbc.DBUtils.DatabaseType;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author wuxii@foxmail.com
 */
public class MySQLTest {

    static Connection conn;
    static DatabaseMetaData metaData;

    @BeforeClass
    public static void beforeClass() throws Exception {
        conn = DBUtils.getConnection(DatabaseType.MYSQL);
        metaData = conn.getMetaData();
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        conn.close();
    }
}
