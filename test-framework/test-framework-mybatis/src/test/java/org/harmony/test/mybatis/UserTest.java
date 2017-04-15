package org.harmony.test.mybatis;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.jdbcx.JdbcConnectionPool;
import org.harmony.test.mybatis.persistence.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
        JdbcConnectionPool ds = JdbcConnectionPool.create(//
                props.getProperty("jdbc.url"), //
                props.getProperty("jdbc.username"), //
                props.getProperty("jdbc.password")//
        );
        Connection conn = ds.getConnection();
        Set<String> keys = props.stringPropertyNames();
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            if (key.startsWith("table")) {
                PreparedStatement statement = conn.prepareStatement(props.getProperty(key));
                statement.execute();
                statement.close();
            }
        }
        conn.close();
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test() {
        String namespace = User.class.getCanonicalName();
        User user = new User("test", "abc123");
        user.setId(1l);
        user.setStatus("OK");
        sqlSession.insert(namespace + ".save", user);
        assertEquals(1, (int) sqlSession.selectOne(namespace + ".countAll"));
        User u = (User) sqlSession.selectOne(namespace + ".findById", 1l);
        assertNotEquals("user is not null", null, u);
        assertEquals("username is test!", u.getUsername(), "test");
        assertEquals("password is abcc123", u.getPassword(), "abc123");
        assertEquals("status is OK!", u.getStatus(), "OK");
        sqlSession.update(namespace + ".delete", 1l);
        assertNull(sqlSession.selectOne(namespace + ".findById", 1l), "delete user, user is null!");
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.close();
    }
}
