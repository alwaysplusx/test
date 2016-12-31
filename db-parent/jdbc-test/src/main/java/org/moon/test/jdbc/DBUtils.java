package org.moon.test.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {

    private static final Properties props = new Properties();

    static {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.txt");
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(DatabaseType type) throws SQLException {
        String url = props.getProperty(type.desc() + ".url");
        String user = props.getProperty(type.desc() + ".user");
        String password = props.getProperty(type.desc() + ".password");
        return DriverManager.getConnection(url, user, password);
    }

    public static void release(Connection connection) throws SQLException {
        release(connection, null, null);
    }

    public static void release(Connection connection, Statement stmt) throws SQLException {
        release(connection, stmt, null);
    }

    public static void release(Connection connection, Statement stmt, ResultSet resultSet) throws SQLException {
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
