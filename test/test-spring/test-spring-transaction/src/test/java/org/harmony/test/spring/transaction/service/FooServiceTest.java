package org.harmony.test.spring.transaction.service;

import org.harmony.test.spring.transaction.entity.Foo;
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

	@Test
	public void test() throws InterruptedException {
		Foo foo = fooService.update(new Foo("foo"));
		fooService.update(new Foo(foo.getId(), "bar"));
	}
}
