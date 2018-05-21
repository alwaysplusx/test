package org.harmony.test.spring.transaction.repository;

import org.harmony.test.spring.transaction.entity.Foo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wuxii@foxmail.com
 */
@Repository
public interface FooRepository extends CrudRepository<Foo, Long> {

}
