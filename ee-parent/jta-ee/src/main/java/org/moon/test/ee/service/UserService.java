package org.moon.test.ee.service;

import java.util.List;

import org.moon.test.ee.persistence.User;

public interface UserService {
	
	public static final String SUCCESS = "ok";
	public static final String ERROR = "error";
	
	String batchSave(List<User> users);

}
