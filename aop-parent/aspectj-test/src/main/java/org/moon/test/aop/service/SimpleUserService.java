package org.moon.test.aop.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleUserService implements UserService {

	@Override
	public String sayHi(String name) {
		return "Hi " + name;
	}

}
