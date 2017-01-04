package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.harmony.test.javaee.jpa.manytoone.Order;
import org.harmony.test.javaee.jpa.manytoone.OrderItem;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class ManyToOneTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        entityManager = EntityManagerUtils.getEntityManager();
    }

    @Before
    public void before() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from OrderItem o").executeUpdate();
        entityManager.createQuery("delete from Order o").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Test
    public void testPersit() {
        Order order = new Order("ORDER-19390383", new OrderItem("ITEM-0001"), new OrderItem("ITEM-0002"));
        entityManager.persist(order);

        List<OrderItem> items = entityManager.createQuery("SELECT o from OrderItem o", OrderItem.class).getResultList();
        assertEquals(2, items.size());
        assertEquals(order.getId(), items.get(0).getOrder().getId());
        assertEquals(order.getId(), items.get(1).getOrder().getId());
    }

    @Test
    public void testMerge() {
        Order order = new Order("ORDER-19390383");
        entityManager.persist(order);

        order.setOrderNo("Order-19390384");
        order.setItems(new ArrayList<>(Arrays.asList(new OrderItem("ITEM-0001"), new OrderItem("ITEM-0002"))));
        entityManager.merge(order);
    }

    @Test
    @Ignore
    public void testRemove() {
        Order order = new Order("ORDER-19390383", new OrderItem("ITEM-0001"), new OrderItem("ITEM-0002"));
        entityManager.persist(order);

        Order order2 = new Order();
        order.setId(order.getId());
        entityManager.remove(order2);
        // Transaction not commit?

        Long count = entityManager.createQuery("select count(o) from OrderItem o", Long.class).getSingleResult();
        assertEquals(0l, count.longValue());
    }

}
