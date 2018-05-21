package org.harmony.test.spring.transaction.service;

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

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String readUncommitted(Long id) {
		return readName(id);
	}

	protected String readName(Long id) {
		return fooRepository.findOne(id).getName();
	}
}
