package org.harmony.test.mybatis.repository;

import org.harmony.test.mybatis.persistence.User;

public interface UserRepository extends BaseRepository<User> {
    public User findUserByIdFetchGroup(Long id);
}
