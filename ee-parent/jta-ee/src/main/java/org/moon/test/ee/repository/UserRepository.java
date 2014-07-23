package org.moon.test.ee.repository;

import java.util.List;

import org.moon.test.ee.persistence.User;

public interface UserRepository {

	public void saveUser(User user);
	
	public void deleteUser(User user);
	
	public void updateUser(User user);
	
	public void delete();

	public long count();
	
	public User findUserById(Long id);
	
	public List<User> findUserByUsername(String username);
}
