package org.moon.test.mybatis.repository;

import org.moon.test.mybatis.persistence.User;

public interface UserRepository {
	public void save(User user);
	public User getById(Long userId);
	public int countAll();
	public void delete(Long userId);
}
