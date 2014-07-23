package org.moon.test.ee.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.moon.test.ee.persistence.User;
import org.moon.test.ee.repository.UserRepository;
import org.moon.test.ee.service.UserService;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceBeanImpl implements UserService {

	@Resource
	UserTransaction ux;
	@EJB
	UserRepository userRepository;

	@Override
	public String batchSave(List<User> users) {
		try {
			ux.begin();
			for (User u : users) {
				userRepository.saveUser(u);
			}
			ux.commit();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ux.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			return ERROR;
		}
	}

	@Override
	public String throwRuntimeExceptionAfterBatchSave(List<User> users) {
		try {
			ux.begin();
			for (User u : users) {
				userRepository.saveUser(u);
			}
			ux.commit();
		} catch (Exception e) {
			try {
				ux.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
		throw new RuntimeException("测试异常");
	}

	// TransactionManagementType.BEAN ejb容器将忽略TransactionAttribute
	// @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String batchSaveWithTransactionAttributeRequestNew(List<User> users) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String batchSaveWithTransactionAttributeMandatory(List<User> users) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String batchSaveWithTransactionAttributeNever(List<User> users) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String batchSaveWithTransactionAttributeNotSupport(List<User> users) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String batchSaveWithTransactionAttributeRequired(List<User> users) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String batchSaveWithTransactionAttributeSupports(List<User> users) {
		throw new UnsupportedOperationException();
	}

}
