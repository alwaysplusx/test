package org.harmony.test.javaee.jta.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.harmony.test.javaee.jta.service.UserService;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    UserRepository userRepository;

    @Override
    public String batchSave(List<User> users) {
        for (User u : users) {
            userRepository.saveUser(u);
        }
        return SUCCESS;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public String throwRuntimeExceptionAfterBatchSave(List<User> users) {
        int count = 0;
        for (User u : users) {
            userRepository.saveUser(u);
            if ((count++) == 3)
                throw new RuntimeException("测试异常");
        }
        return SUCCESS;
    }

    @Override
    public String batchSaveWithTransactionAttributeMandatory(List<User> users) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String batchSaveWithTransactionAttributeNever(List<User> users) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String batchSaveWithTransactionAttributeNotSupport(List<User> users) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String batchSaveWithTransactionAttributeRequired(List<User> users) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String batchSaveWithTransactionAttributeSupports(List<User> users) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String batchSaveWithTransactionAttributeRequestNew(List<User> users) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String sleepLongTimeWithTransaction(User user, long sleepTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String sleepLongTimeWithoutTransaction(User user, long sleepTime) {
        throw new UnsupportedOperationException();
    }

}
