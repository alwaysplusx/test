package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.harmony.test.javaee.jpa.persistence.User;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class NamedQueryTest {

    private Long userId;
    private EntityManager entityManager;

    @Before
    public void before() {
        entityManager = EntityManagerUtils.getEntityManager();
        User user = new User("wuxii", "abc123");
        entityManager.persist(user);
        assertNotNull(user.getUserId());
        userId = user.getUserId();
    }

    @Test
    public void test() {
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("findById", User.class);
        namedQuery.setParameter("userId", userId);
        User user2 = namedQuery.getSingleResult();
        assertEquals("wuxii", user2.getUsername());
        assertEquals("abc123", user2.getPassword());
    }

}
