package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.harmony.test.javaee.jpa.manytoone.Order;
import org.harmony.test.javaee.jpa.manytoone.OrderItem;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class JpaFetchTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        entityManager = EntityManagerUtils.getEntityManager();
        entityManager.persist(new Order("1", new OrderItem("1-1")));
        entityManager.persist(new Order("2"));
        entityManager.persist(new Order("3", new OrderItem("3-1"), new OrderItem("3-2")));
    }

    @Test
    public void testFetch() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        root.fetch("items");
        query.orderBy(builder.asc(root.get("id")));
        List<Order> list = entityManager.createQuery(query).getResultList();
        // 重复的数据, default inner join如果存在多条明显将导致重复的主单查询结果, 需要使用distinct
        assertEquals(1l, list.get(0).getId().longValue());
        assertEquals(3l, list.get(1).getId().longValue());
        assertEquals(3l, list.get(2).getId().longValue());
    }

    @Test
    public void testFetch2() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        // query.distinct(true);
        Root<Order> root = query.from(Order.class);
        root.fetch("items", JoinType.LEFT);
        query.orderBy(builder.asc(root.get("id")));
        // left join 主单中无明显的数据将可以取得, 但是结果还是有重复的
        List<Order> list = entityManager.createQuery(query).getResultList();
        for (Order order : list) {
            System.out.println(order);
        }
    }

    @Test
    public void testNoFetch() {
        // DEFAULT
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        query.from(Order.class);
        List<Order> list = entityManager.createQuery(query).getResultList();
        for (Order order : list) {
            System.out.println(order);
        }
    }

    @Test
    public void testSelect() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root.get("items"));
        // HIBERNATE not have join, eclipse has one
        System.out.println(root.getJoins());
        List list = entityManager.createQuery(query).getResultList();
        for (Object item : list) {
            // result type is OrderItem
            System.out.println(item);
        }
    }

}
