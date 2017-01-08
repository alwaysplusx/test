package org.harmony.test.javaee.jpa;

import javax.persistence.EntityManager;

import org.harmony.test.javaee.jpa.events.EventEntity;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class EventTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() throws Exception {
        entityManager = EntityManagerUtils.getEntityManager();
    }

    @Test
    public void test() {
        EventEntity entity = new EventEntity("wuxii");
        entityManager.persist(entity);
    }

}
