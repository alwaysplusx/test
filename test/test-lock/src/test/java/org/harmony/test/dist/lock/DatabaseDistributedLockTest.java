package org.harmony.test.dist.lock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.harmony.test.lock.dist.DatabaseDistributedLock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
public class DatabaseDistributedLockTest {

    private static final Logger log = LoggerFactory.getLogger(DatabaseDistributedLockTest.class);

    private DataSource datasource;
    private int count = 100;

    @Before
    public void setup() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:file:~/.harmony/test/lock;LOCK_TIMEOUT=30000");
        ds.setUser("sa");
        ds.setPassword("sa");
        this.datasource = ds;

        Connection conn = ds.getConnection();
        conn.prepareStatement("create table if not exists lock (lock_name varchar(50))").execute();
        ResultSet resultSet = conn.prepareStatement("select 1 from lock where lock_name='foo'").executeQuery();
        if (!resultSet.next()) {
            resultSet.close();
            conn.prepareStatement("insert into lock(lock_name) values('foo')").execute();
        }
        conn.commit();
        conn.close();
    }

    @Test
    public void test() throws Exception {
        CountDownLatch start = new CountDownLatch(count);
        CountDownLatch end = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                DatabaseDistributedLock lock = null;
                try {
                    lock = DatabaseDistributedLock.create(datasource, "foo");
                    start.await();

                    lock.lock();
                    log.info("{} get lock", name);
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        log.error("", e);
                    }
                } catch (Exception e) {
                    log.error("", e);
                } finally {
                    if (lock != null) {
                        lock.unlock();
                        log.info("{} release lock", name);
                    }
                }
                end.countDown();
            }).start();

            start.countDown();

        }
        end.await();
    }

    @After
    public void tearDown() {
    }

}
