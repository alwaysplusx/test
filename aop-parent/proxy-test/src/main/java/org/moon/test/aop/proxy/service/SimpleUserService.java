package org.moon.test.aop.proxy.service;

public class SimpleUserService implements UserService {

	@Override
	public String sayHi(String name) {
		return "Hi " + name;
	}

}
