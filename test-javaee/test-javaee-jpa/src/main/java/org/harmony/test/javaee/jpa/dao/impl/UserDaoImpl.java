package org.harmony.test.javaee.jpa.dao.impl;

import javax.persistence.EntityManager;

import org.harmony.test.javaee.jpa.dao.UserDao;
import org.harmony.test.javaee.jpa.persistence.User;

public class UserDaoImpl implements UserDao {

    private EntityManager em;

    public UserDaoImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
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
