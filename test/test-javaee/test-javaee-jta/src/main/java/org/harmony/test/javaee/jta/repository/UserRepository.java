package org.harmony.test.javaee.jta.repository;

import java.util.List;

import org.harmony.test.javaee.jta.persistence.User;

public interface UserRepository {

    public User saveUser(User user);
    
    public void deleteUser(User user);
    
    public User updateUser(User user);
    
    public void delete();

    public long count();
    
    public User findUserById(Long id);
    
    public List<User> findUserByUsername(String username);
}
