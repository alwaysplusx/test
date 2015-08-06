package org.moon.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.moon.test.file.FileUtil;

public class DataBaseManager {

    public static final String H2_URL = "jdbc:h2:file:target/h2/data";
    public static final String HSQL_URL = "jdbc:hsqldb:file:target/hsqldb/data";
    public static final String DEFAULT_USERNAME = "sa";
    public static final String DEFAULT_PASSWORD = "";
    public static final String DEFAULT_JNDI_DATASOURCE = "jdbc/datasource";
    private static DataSource ds;

    public static void initializeH2DataBase() throws Exception {
        initializeH2DataBase(DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public static void initializeHSQLDataBase() throws Exception {
        initializeHSQLDataBase(DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public static void initializeH2DataBase(String username, String password) throws Exception {
        initDataBase(H2_URL, username, password);
        ds = new org.h2.jdbcx.JdbcDataSource();
        ((org.h2.jdbcx.JdbcDataSource) ds).setURL(H2_URL);
        ((org.h2.jdbcx.JdbcDataSource) ds).setUser(DEFAULT_USERNAME);
        ((org.h2.jdbcx.JdbcDataSource) ds).setPassword(DEFAULT_PASSWORD);
        // setContextDataSource(ds);
    }

    public static void initializeHSQLDataBase(String username, String password) throws Exception {
        initDataBase(HSQL_URL, username, password);
        ds = new org.hsqldb.jdbc.JDBCDataSource();
        ((org.hsqldb.jdbc.JDBCDataSource) ds).setUrl(HSQL_URL);
        ((org.hsqldb.jdbc.JDBCDataSource) ds).setUser(DEFAULT_USERNAME);
        ((org.hsqldb.jdbc.JDBCDataSource) ds).setPassword(DEFAULT_PASSWORD);
        // setContextDataSource(ds);
    }

    private static void initDataBase(String url, String username, String password) throws Exception {
        dropDB(url);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new Exception("数据库初始化失败!");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static void dropDB(String url) {
        int index;
        if ((index = url.indexOf("file")) != -1) {
            FileUtil.deleteDirectory(url.substring(index + 5, url.length() - 5));
        }
    }

    public static DataSource getDataSource() throws Exception {
        if (ds == null)
            throw new Exception("数据源未初始化!");
        return ds;
    }

    /*
     * private static void setContextDataSource(DataSource ds) { try { Context
     * ctx = new InitialContext(); ctx.bind(DEFAULT_JNDI_DATASOURCE, ds); }
     * catch (NamingException e) { e.printStackTrace(); } }
     */

}
