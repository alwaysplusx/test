package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.harmony.test.javaee.jpa.persistence.User;
import org.harmony.test.javaee.jpa.persistence.User_;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class CriteriaQueryTest2 {

    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        entityManager = EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.persist(new User("wuxii", "abc123"));
    }

    @Test
    public void testWithJpaMetamodel() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate predicate = cb.equal(root.get(User_.username), "wuxii");
        query.where(predicate);
        User user = entityManager.createQuery(query).getSingleResult();
        assertNotNull(user);
        assertEquals("wuxii", user.getUsername());
    }
}
