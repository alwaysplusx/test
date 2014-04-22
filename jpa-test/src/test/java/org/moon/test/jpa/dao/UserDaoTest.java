package org.moon.test.jpa.dao;

import org.moon.test.jpa.dao.impl.UserDaoJpaImpl;
import org.moon.test.jpa.persistence.User;

public class UserDaoTest {

	public static void main(String[] args) {
		UserDao userDao = new UserDaoJpaImpl();
		User user = new User("test", "abc123");
		userDao.save(user);
	}
}
