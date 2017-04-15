package org.harmony.test.mybatis;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.jdbcx.JdbcConnectionPool;
import org.harmony.test.mybatis.persistence.Country;
import org.harmony.test.mybatis.persistence.Group;
import org.harmony.test.mybatis.persistence.User;
import org.harmony.test.mybatis.repository.CountryRepository;
import org.harmony.test.mybatis.repository.GroupRepository;
import org.harmony.test.mybatis.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RepositoryTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private CountryRepository countryRepository;

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
        userRepository = sqlSession.getMapper(UserRepository.class);
        groupRepository = sqlSession.getMapper(GroupRepository.class);
        countryRepository = sqlSession.getMapper(CountryRepository.class);
        userRepository.deleteAll();
        groupRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    public void testCRUD() throws Exception {
        User user = new User("tester", "abc123");
        user.setId(1l);
        user.setCreateTime(new Date());
        user.setCreateUser("admin");
        user.setStatus("A");
        userRepository.save(user);
        assertEquals(1, userRepository.countAll());
        user.setStatus("B");
        userRepository.update(user);
        assertEquals("B", userRepository.findById(1l).getStatus());
        userRepository.delete(1l);
        assertEquals(0, userRepository.countAll());
    }

    @Test
    public void testUserAndGroupMapper() {
        Group group = new Group("Group A");
        group.setId(1l);
        groupRepository.save(group);
        Country country = new Country("Chinese");
        country.setId(1l);
        countryRepository.save(country);
        User user = new User("user1", "abc123");
        user.setId(1l);
        user.setStatus("A");
        user.setGroup(group);
        user.setCountry(country);
        userRepository.save(user);
        User u = userRepository.findUserByIdFetchGroup(1l);
        assertEquals("Group A", u.getGroup().getGroupName());
        assertEquals("Chinese", u.getCountry().getCountryName());
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.close();
    }
}
