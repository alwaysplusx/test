package org.harmony.test.javaee.jta.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;

@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "harmony")
    private EntityManager entityManager;

    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.find(User.class, user.getUserId()));
    }

    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public long count() {
        return (Long) entityManager.createQuery("select count(o) from User o").getSingleResult();
    }

    @Override
    public User findUserById(Long id) {
        return (User) entityManager.createQuery("select o from User o where o.id = ?1").setParameter(1, id)
                .getSingleResult();
    }

    @Override
    public List<User> findUserByUsername(String username) {
        return entityManager.createQuery("select o from User o where o.username = ?1").setParameter(1, username)
                .getResultList();
    }

    @Override
    public void delete() {
        entityManager.createQuery("delete from User").executeUpdate();
    }

}
