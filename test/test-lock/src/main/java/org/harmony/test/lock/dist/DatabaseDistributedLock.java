package org.harmony.test.lock.dist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.harmony.test.lock.LockException;

/**
 * @author wuxii@foxmail.com
 */
public class DatabaseDistributedLock {

    private Connection connection;

    private DataSource datasource;

    private String lockName;

    public static DatabaseDistributedLock create(DataSource ds, String lockName) {
        DatabaseDistributedLock lock = new DatabaseDistributedLock(ds, lockName);
        try {
            lock.init();
        } catch (SQLException e) {
            throw new LockException("database lock init failed");
        }
        return lock;
    }

    public DatabaseDistributedLock(DataSource datasource, String lockName) {
        this.datasource = datasource;
        this.lockName = lockName;
    }

    public void init() throws SQLException {
        connection = datasource.getConnection();
        connection.setAutoCommit(false);
    }

    public void lock() {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from lock where lock_name='" + lockName + "' for update");
            pst.executeQuery();
        } catch (SQLException e) {
            throw new LockException("unable get lock", e);
        }
    }

    public void unlock() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new LockException("release lock failed", e);
        }
    }

}
