package org.moon.test.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.moon.test.jpa.persistence.User;

public class SimpleTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("moon");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new User("name", "password"));
        em.getTransaction().commit();
        Query query = em.createQuery("select o from User o where o.username=:name");
        query.setParameter("name", "name");
        List<User> list = query.getResultList();
        System.out.println(list);
    }
}
