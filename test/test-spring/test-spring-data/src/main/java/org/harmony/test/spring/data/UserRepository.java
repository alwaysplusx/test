package org.harmony.test.spring.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuxii@foxmail.com
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, String> {

}
