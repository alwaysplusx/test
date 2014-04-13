package org.moon.test.mybatis.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class UserTest {

	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
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
	}

	@Test
	public void test() {
		String namespace = User.class.getCanonicalName();
		sqlSession.insert(namespace + ".save", new User(1l,"test","abc123","OK"));
		assertEquals("count user is 1!", 1, sqlSession.selectOne(namespace + ".countAll"));
		User user = (User) sqlSession.selectOne(namespace + ".getById", 1l);
		assertNotEquals("user is not null", null, user);
		assertEquals("username is test!",user.getUsername(), "test");
		assertEquals("password is abcc123", user.getPassword(), "abc123");
		assertEquals("status is OK!", user.getStatus(), "OK");
	}

	@After
	public void tearDown() throws Exception {
	}
}
