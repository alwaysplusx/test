package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.harmony.test.javaee.jpa.onetoone.User3;
import org.harmony.test.javaee.jpa.onetoone.UserProfile;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class OneToOneTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        entityManager = EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User3 o").executeUpdate();
        entityManager.createQuery("delete from UserProfile o").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.persist(new User3("wuxii", new UserProfile("wuxii-proflie")));
    }

    @Test
    public void test() {
        User3 user3 = entityManager.find(User3.class, "wuxii");
        entityManager.close();
        assertNotNull(user3.getUserProfile());
    }

}
