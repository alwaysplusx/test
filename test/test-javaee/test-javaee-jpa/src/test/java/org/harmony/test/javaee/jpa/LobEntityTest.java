package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.harmony.test.javaee.jpa.persistence.LobEntity;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class LobEntityTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        entityManager = EntityManagerUtils.getEntityManager();
    }

    @Test
    public void test() {
        LobEntity lobEntity = new LobEntity();
        lobEntity.setId("123");
        lobEntity.setExceptions(new Exception[] { new Exception("what?") });
        entityManager.persist(lobEntity);

        LobEntity lobEntity2 = entityManager.find(LobEntity.class, "123");
        assertNotNull(lobEntity2);
        assertEquals(lobEntity.getExceptions().length, 1);
        assertEquals("what?", lobEntity2.getExceptions()[0].getMessage());
    }

}
