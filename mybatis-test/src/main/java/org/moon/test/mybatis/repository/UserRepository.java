package org.moon.test.mybatis.repository;

import org.moon.test.mybatis.persistence.User;

public interface UserRepository extends BaseRepository<User> {
	public User findUserByIdFetchGroup(Long id);
}
