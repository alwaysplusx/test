package org.harmony.test.javaee.jpa;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class EntityGraphTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        entityManager = EntityManagerUtils.getEntityManager();
    }

    @Test
    public void test() {
        EntityGraph<?> graph = entityManager.getEntityGraph("user.find");
        graph.addAttributeNodes("password");
        entityManager.createQuery("select o from User o").setHint("javax.persistence.loadgraph", graph).getResultList();
    }

}
