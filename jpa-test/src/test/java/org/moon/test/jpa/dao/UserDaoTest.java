package org.moon.test.jpa.dao;

import java.util.Date;

import javax.persistence.Query;

import org.moon.test.jpa.DB;
import org.moon.test.jpa.dao.impl.UserDaoJpaImpl;
import org.moon.test.jpa.persistence.User;
import org.moon.test.jpa.query.BondManager;
import org.moon.test.jpa.query.JPAUtil;

public class UserDaoTest {

	public static void main(String[] args) {
		UserDao userDao = new UserDaoJpaImpl();
		User user = new User("test", "abc123");
		user.setBirthday(new Date());
		user.setSex("Man");
		userDao.save(user);
		
		BondManager bm = new BondManager(User.class);
		bm.andEqual("username", "test");
		Query query = JPAUtil.buildQuery(DB.createEntityManager("moon"), bm);
		User u = (User) query.getSingleResult();
		System.out.println(u);
	}

}
