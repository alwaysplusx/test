package org.moon.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    private String url = "jdbc:h2:file:target/h2/data";
    private String username = "sa";
    private String password = "";

    private DBUtils() {
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static DBUtils getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static DBUtils instance = new DBUtils();
    }

    public void release(Connection connection) throws SQLException {
        release(connection, null, null);
    }

    public void release(Connection connection, Statement stmt) throws SQLException {
        release(connection, stmt, null);
    }

    public void release(Connection connection, Statement stmt, ResultSet resultSet) throws SQLException {
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
