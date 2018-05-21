package org.harmony.test.spring.transaction.service;

import org.harmony.test.spring.transaction.entity.Foo;
import org.harmony.test.spring.transaction.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuxii@foxmail.com
 */
@Service
public class FooService {

    @Autowired
    private FooRepository fooRepository;

    @Transactional
    public Foo update(Foo foo, Runnable runner) {
        foo = fooRepository.save(foo);
        runner.run();
        return foo;
    }

    @Transactional
    public Foo update(Foo foo) {
        return fooRepository.save(foo);
    }

}
