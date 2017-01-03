package org.harmony.test.javaee.jpa;

import static org.junit.Assert.*;

import org.harmony.test.javaee.jpa.EntityManagerUtils.JPAType;
import org.harmony.test.javaee.jpa.dao.UserDao;
import org.harmony.test.javaee.jpa.dao.impl.UserDaoImpl;
import org.harmony.test.javaee.jpa.persistence.User;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class JpaUserTest {

    private static UserDao userDao;

    @BeforeClass
    public static void beforeClass() {
        userDao = new UserDaoImpl(EntityManagerUtils.getEntityManager(JPAType.Eclipselink));
    }

    @Test
    public void testUser() {
        User user = userDao.save(new User("wuxii", "abc123"));
        assertNotNull(user.getUserId());
        User user2 = userDao.find(user.getUserId());
        assertEquals(user.getUsername(), user2.getUsername());
        assertEquals(user.getPassword(), user2.getPassword());
    }

}
