package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.harmony.test.javaee.jpa.manytoone.Order;
import org.harmony.test.javaee.jpa.manytoone.OrderItem;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class ManyToOneTest {

    private static EJBContainer container;
    @EJB(beanName = "manyToOne")
    private TestBean bean;

    @BeforeClass
    public static void beforeClass() {
        Properties props = new Properties();
        props.put("jdbc.harmony", "new://Resource?type=DataSource");
        props.put("jdbc.harmony.JdbcDriver", "org.h2.Driver");
        props.put("jdbc.harmony.JdbcUrl", "jdbc:h2:file:~/.h2/harmony");
        props.put("jdbc.harmony.UserName", "sa");
        props.put("jdbc.harmony.Password", "");
        container = EJBContainer.createEJBContainer(props);
    }

    @Before
    public void before() throws Exception {
        container.getContext().bind("inject", this);
    }

    @Test
    public void test() {
        bean.test();
        bean.testFind();
    }

    @Stateless(name = "manyToOne")
    public static class TestBean {

        @PersistenceContext(unitName = "harmony")
        private EntityManager entityManager;

        public void test() {
            Order order = new Order("ORDER-19390383", new OrderItem("ITEM-0001"), new OrderItem("ITEM-0002"));
            entityManager.persist(order);
        }

        public void testFind() {
            List<Order> list = entityManager.createQuery("select o from Order o", Order.class).getResultList();
            String beforeLoader = list.toString();
            list.get(0).getItems().size();
            System.out.println("before fetch >" + beforeLoader + "\nafter fetch  >" + list.toString());
            assertNotEquals(beforeLoader, list.toString());
        }
    }
}
