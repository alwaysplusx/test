package org.harmony.test.spring.transaction.service;

import org.harmony.test.spring.transaction.entity.Foo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wuxii@foxmail.com
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FooServiceTest {

    static final Logger log = LoggerFactory.getLogger(FooService.class);

    @Autowired
    private FooService fooService;
    @Autowired
    private BarService barService;
    private Long id;

    @Before
    public void setup() {
        Foo foo = fooService.update(new Foo(1l, "foo"));
        this.id = foo.getId();
    }

    @Test
    public void testDirtyRead() throws InterruptedException {

        new Thread(() -> {
            try {
                fooService.update(new Foo("bar"), () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("rollback exception");
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        log.info("  uncommitted: {}", barService.readUncommitted(id));
    }

}
