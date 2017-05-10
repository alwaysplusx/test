package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.harmony.test.javaee.jpa.onetoone.User;
import org.harmony.test.javaee.jpa.onetoone.UserProfile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class OneToOneTest {

    private static EJBContainer container;
    @EJB(beanName = "oneToOne")
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

    @Stateless(name = "oneToOne")
    public static class TestBean {

        @PersistenceContext(unitName = "harmony")
        private EntityManager entityManager;

        public void test() {
            entityManager.persist(new User("wuxii", new UserProfile("wuxii-proflie")));
        }

        public void testFind() {
            List<User> list = entityManager.createQuery("select o from User o", User.class).getResultList();
            String beforeLoader = list.toString();
            list.get(0).getUserProfile();
            System.out.println("before fetch >" + beforeLoader + "\nafter fetch  >" + list.toString());
            assertEquals(beforeLoader, list.toString());
        }
    }
}
