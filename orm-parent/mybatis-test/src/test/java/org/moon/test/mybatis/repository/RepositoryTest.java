package org.moon.test.mybatis.repository;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.db.DataBaseManager;
import org.moon.test.mybatis.persistence.Country;
import org.moon.test.mybatis.persistence.Group;
import org.moon.test.mybatis.persistence.User;

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
		DataBaseManager.initializeH2DataBase();
		Set<String> keys = props.stringPropertyNames();
		DataSource ds = DataBaseManager.getDataSource();
		Connection conn = ds.getConnection();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if(key.startsWith("table")){
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
		User user = new User("tester","abc123");
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
		User user = new User("user1","abc123");
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
