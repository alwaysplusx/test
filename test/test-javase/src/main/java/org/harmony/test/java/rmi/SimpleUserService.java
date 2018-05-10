package org.harmony.test.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.harmony.test.java.User;

public class SimpleUserService extends UnicastRemoteObject implements UserService {

    private static final long serialVersionUID = 1L;

    public SimpleUserService() throws RemoteException {
    }

    @Override
    public User getUser(String name) throws RemoteException {
        return new User(name);
    }

    @Override
    public User returnSlef(User user) throws RemoteException {
        return user;
    }

}
