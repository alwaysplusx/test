package org.moon.test.ee.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.moon.test.ee.persistence.User;
import org.moon.test.ee.repository.UserRepository;
import org.moon.test.ee.service.UserService;

@Stateless
public class UserServiceDefaultImpl implements UserService {

	@EJB
	UserRepository userRepository;
	/*
	 * SEVERE - FAIL ... UserServiceDefaultImpl: Container-Managed Transaction beans cannot use UserTransaction: fix ref java:comp/env/org.moon.test.ee.service.impl.UserServiceDefaultImpl/ux
	 * SEVERE - Invalid EjbModule(name=jta-ee, path=E:\GitRepository\github\test-parent\ee-parent\jta-ee\target\classes)
	 * INFO - Set the 'openejb.validation.output.level' system property to VERBOSE for increased validation details.
	 * WARNING - configureApplication.loadFailed
	 */
	/*@Resource
	UserTransaction ux;*/
	
	@Override
	public String batchSave(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}

	@Override
	public String throwRuntimeExceptionAfterBatchSave(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		throw new RuntimeException("测试异常");
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String batchSaveWithTransactionAttributeRequestNew(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public String batchSaveWithTransactionAttributeMandatory(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public String batchSaveWithTransactionAttributeNever(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String batchSaveWithTransactionAttributeNotSupport(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String batchSaveWithTransactionAttributeRequired(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public String batchSaveWithTransactionAttributeSupports(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}
	
}
