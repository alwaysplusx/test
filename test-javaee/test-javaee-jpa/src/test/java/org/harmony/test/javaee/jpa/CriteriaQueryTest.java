package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.harmony.test.javaee.jpa.persistence.User;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class CriteriaQueryTest {

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
    public void testCriteriaQuery() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Path<String> usernamePath1 = root.get("username");
        Predicate predicate1 = cb.equal(usernamePath1, "wuxii");
        query.where(predicate1);
        TypedQuery<User> typedQuery = entityManager.createQuery(query);
        User user = typedQuery.getSingleResult();
        assertEquals("wuxii", user.getUsername());
        assertEquals("abc123", user.getPassword());

        // 更新
        CriteriaUpdate<User> updateQuery = cb.createCriteriaUpdate(User.class);
        Root<User> root2 = updateQuery.from(User.class);
        Path<String> usernamePath2 = root2.get("username");
        Path<String> userIdPath1 = root2.get("userId");
        updateQuery.set(usernamePath2, "wuxii1");
        Predicate predicate2 = cb.equal(userIdPath1, user.getUserId());
        updateQuery.where(predicate2);
        entityManager.getTransaction().begin();
        int effected = entityManager.createQuery(updateQuery).executeUpdate();
        entityManager.getTransaction().commit();
        assertEquals(1, effected);
        entityManager.clear();
        User user2 = entityManager.createQuery("select o from User o where o.userId=?1", User.class).setParameter(1, user.getUserId()).getSingleResult();
        assertEquals("wuxii1", user2.getUsername());

        // 删除
        CriteriaDelete<User> deleteQuery = cb.createCriteriaDelete(User.class);
        Root<User> root3 = deleteQuery.from(User.class);
        Path<Long> userId = root3.get("userId");
        deleteQuery.where(cb.equal(userId, user.getUserId()));
        entityManager.getTransaction().begin();
        effected = entityManager.createQuery(deleteQuery).executeUpdate();
        entityManager.getTransaction().commit();
        assertEquals(1, effected);
    }

}
