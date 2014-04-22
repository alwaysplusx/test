package org.moon.test.jpa.dao.impl;

import javax.persistence.EntityManager;

import org.moon.test.jpa.DB;
import org.moon.test.jpa.dao.UserDao;
import org.moon.test.jpa.persistence.User;

public class UserDaoJpaImpl implements UserDao {

	private EntityManager em;

	public UserDaoJpaImpl() {
		try {
			this.em = DB.createEntityManager("moon");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public void delete(User user) {
		em.remove(find(user.getUserId()));
	}

	@Override
	public User find(Long userId) {
		return em.find(User.class, userId);
	}

}
