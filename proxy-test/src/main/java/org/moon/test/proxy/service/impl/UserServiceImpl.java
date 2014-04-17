package org.moon.test.proxy.service.impl;

import org.moon.test.proxy.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public void speak(String word) {
		System.err.println("user speak about : \""+ word +"\"");
	}

	@Override
	public void run() {
		System.err.println("user runing!");
	}

}
