package org.harmony.test.spring.transaction.service;

import org.harmony.test.spring.transaction.entity.Foo;
import org.harmony.test.spring.transaction.repository.FooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuxii@foxmail.com
 */
@Service
public class FooService {

	private static final Logger log = LoggerFactory.getLogger(FooService.class);

	@Autowired
	private FooRepository fooRepository;

	@Autowired
	private BarService barService;

	@Transactional
	public Foo update(Foo foo) {
		foo = fooRepository.save(foo);
		log.info("foo update to {}", foo.getName());
		log.info("foo read from repository after save {}", barService.readAsDefault(foo.getId()));
		log.info("foo read from repository after save {}", barService.readUncommitted(foo.getId()));
		return foo;
	}

}
