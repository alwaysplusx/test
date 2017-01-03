package org.harmony.test.javaee.jta.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.harmony.test.javaee.jta.service.UserService;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceBeanImpl implements UserService {

    @Resource
    UserTransaction ux;
    @EJB
    UserRepository userRepository;

    @Override
    public String batchSave(List<User> users) {
        try {
            ux.begin();
            for (User u : users) {
                userRepository.saveUser(u);
            }
            ux.commit();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ux.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return ERROR;
        }
    }

    @Override
    public String throwRuntimeExceptionAfterBatchSave(List<User> users) {
        try {
            ux.begin();
            for (User u : users) {
                userRepository.saveUser(u);
            }
            ux.commit();
        } catch (Exception e) {
            try {
                ux.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        throw new RuntimeException("测试异常");
    }

    // TransactionManagementType.BEAN ejb容器将忽略TransactionAttribute
    // @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public String batchSaveWithTransactionAttributeRequestNew(List<User> users) {
        throw new UnsupportedOperationException();
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
    public String sleepLongTimeWithTransaction(User user, long sleepTime) {
        try {
            ux.begin();
            Thread.sleep(sleepTime);
            ux.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ux.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return SUCCESS;
    }

    @Override
    public String sleepLongTimeWithoutTransaction(User user, long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
