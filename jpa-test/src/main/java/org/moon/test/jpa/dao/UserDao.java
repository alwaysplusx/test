package org.moon.test.jpa.dao;

import org.moon.test.jpa.persistence.User;

public interface UserDao {

	public void save(User user);

	public void delete(User user);

	public User find(Long userId);

}
