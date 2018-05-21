package org.harmony.test.spring.transaction.service;

import org.harmony.test.spring.transaction.entity.Foo;
import org.harmony.test.spring.transaction.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuxii@foxmail.com
 */
@Service
public class BarService {

    @Autowired
    private FooRepository fooRepository;

    public String readAsDefault(Long id) {
        return readName(id);
    }

    // 串行
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String readSerializable(Long id) {
        return readName(id);
    }

    // 可重读
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String readRepeatable(Long id) {
        return readName(id);
    }

    // 不允许脏读
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String readCommitted(Long id) {
        return readName(id);
    }

    // 允许脏读
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public String readUncommitted(Long id) {
        return readName(id);
    }

    protected String readName(Long id) {
        Foo foo = fooRepository.findOne(id);
        return foo == null ? null : foo.getName();
    }
}
