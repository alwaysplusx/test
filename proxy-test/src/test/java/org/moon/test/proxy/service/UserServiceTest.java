package org.moon.test.proxy.service;

import org.moon.test.proxy.service.impl.UserServiceImpl;

public class UserServiceTest {

	public static void main(String[] args) {
		UserService reflectProxyUserService, cglibProxyUserService;
		cglibProxyUserService = (UserService) new org.moon.test.proxy.cglib.UserServiceProxy().bind(new UserServiceImpl());
		reflectProxyUserService = (UserService) new org.moon.test.proxy.reflect.UserServiceProxy().bind(new UserServiceImpl());
		
		cglibProxyUserService.speak("Hello World!");
		reflectProxyUserService.speak("Good Morning");
		
		cglibProxyUserService.run();
		reflectProxyUserService.run();
	}
	

}
