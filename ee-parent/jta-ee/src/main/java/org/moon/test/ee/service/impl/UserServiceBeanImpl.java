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

}
