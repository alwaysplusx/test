package org.harmony.test.javaee.jpa;

import javax.persistence.EntityManager;

import org.harmony.test.javaee.jpa.persistence.User2;

/**
 * @author wuxii@foxmail.com
 */
public class User2Test {

    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager2();
        User2 user = new User2();
        user.setUsername("wuxii");
        user.setHobbies(new String[] { "swimming", "football" });
        // store in database
        // USERNAME    HOBBIES  
        //  wuxii       aced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b470200007870000000027400087377696d6d696e67740008666f6f7462616c6c
        entityManager.persist(user);
    }

}
