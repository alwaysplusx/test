package org.moon.test.mybatis.repository;

import static org.junit.Assert.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.mybatis.persistence.User;

public class UserRepositoryTest {

	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
		sqlSession = sqlSessionFactory.openSession();
		userRepository = sqlSession.getMapper(UserRepository.class);
	}

	@Test
	public void test() throws Exception {
		userRepository.save(new User(1l,"test","abc123","OK"));
		assertEquals("count users size is 1!", 1, userRepository.countAll());
		User user = userRepository.getById(1l);
		assertNotEquals("user is not null", null, user);
		assertEquals("username is test!",user.getUsername(), "test");
		assertEquals("password is abcc123", user.getPassword(), "abc123");
		assertEquals("status is OK!", user.getStatus(), "OK");
	}

	@After
	public void tearDown() throws Exception {
		sqlSession.close();
	}
}
