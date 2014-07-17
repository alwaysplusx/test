package org.moon.ii.rmi.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.moon.ii.rmi.persistence.User;
import org.moon.ii.rmi.service.UserService;

public class SimpleUserService extends UnicastRemoteObject implements UserService {

	private static final long serialVersionUID = 1L;

	public SimpleUserService() throws RemoteException {
	}

	@Override
	public User getUser(String name) throws RemoteException {
		return new User(name, 21);
	}

	@Override
	public User returnSlef(User user) throws RemoteException {
		return user;
	}

}
