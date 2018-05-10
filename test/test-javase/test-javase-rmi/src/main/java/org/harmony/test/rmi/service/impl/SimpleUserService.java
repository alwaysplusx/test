package org.harmony.test.rmi.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.harmony.test.rmi.persistence.User;
import org.harmony.test.rmi.service.UserService;

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
