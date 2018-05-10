package org.harmony.test.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.harmony.test.rmi.persistence.User;

public interface UserService extends Remote {

    // rmi的接口必须声明抛出RemoteException
    User getUser(String name) throws RemoteException;

    User returnSlef(User user) throws RemoteException;
}
