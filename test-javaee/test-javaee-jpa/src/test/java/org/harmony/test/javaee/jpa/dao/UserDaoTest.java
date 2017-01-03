package org.harmony.test.javaee.jpa.dao;

import java.util.Date;

import javax.persistence.Query;

import org.harmony.test.javaee.jpa.DB;
import org.harmony.test.javaee.jpa.dao.UserDao;
import org.harmony.test.javaee.jpa.dao.impl.UserDaoJpaImpl;
import org.harmony.test.javaee.jpa.persistence.User;
import org.harmony.test.javaee.jpa.query.BondManager;
import org.harmony.test.javaee.jpa.query.JPAUtil;

public class UserDaoTest {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoJpaImpl();
        User user = new User("test", "abc123");
        user.setBirthday(new Date());
        user.setSex("Man");
        userDao.save(user);
        
        BondManager bm = new BondManager(User.class);
        bm.andEqual("username", "test");
        Query query = JPAUtil.buildQuery(DB.createEntityManager("moon"), bm);
        User u = (User) query.getSingleResult();
        System.out.println(u);
    }

}
