package org.moon.test.ee.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.moon.test.ee.persistence.User;
import org.moon.test.ee.repository.UserRepository;
import org.moon.test.ee.service.UserService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserServiceContainerImpl implements UserService {

	@EJB
	UserRepository userRepository;

	@Override
	public String batchSave(List<User> users) {
		for (User u : users) {
			userRepository.saveUser(u);
		}
		return SUCCESS;
	}

}
