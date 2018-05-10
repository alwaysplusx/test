package org.harmony.test.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.harmony.test.java.User;

public interface UserService extends Remote {

    // rmi的接口必须声明抛出RemoteException
    User getUser(String name) throws RemoteException;

    User returnSlef(User user) throws RemoteException;
}
