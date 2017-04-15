package org.harmony.test.javaee.jpa.dao;

import org.harmony.test.javaee.jpa.persistence.User;

public interface UserDao {

    public User save(User user);

    public void delete(User user);

    public User find(Long userId);

}
