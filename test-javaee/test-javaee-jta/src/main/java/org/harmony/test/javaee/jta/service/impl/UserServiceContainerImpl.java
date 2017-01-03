package org.harmony.test.javaee.jta.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.harmony.test.javaee.jta.service.UserService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserServiceContainerImpl implements UserService {

    @EJB
    UserRepository userRepository;

    /*
     * SEVERE - FAIL ... UserServiceContainerImpl: Container-Managed Transaction beans cannot use UserTransaction: fix ref java:comp/env/org.moon.test.ee.service.impl.UserServiceContainerImpl/ux
     * SEVERE - Invalid EjbModule(name=jta-ee, path=E:\GitRepository\github\test-parent\ee-parent\jta-ee\target\classes)
     * INFO - Set the 'openejb.validation.output.level' system property to VERBOSE for increased validation details.
     * WARNING - configureApplication.loadFailed
     */
    /*@Resource
    UserTransaction ux;*/
    
    @Override
    public String batchSave(List<User> users) {
        for (User u : users) {
            userRepository.saveUser(u);
        }
        return SUCCESS;
    }

    @Override
    public String throwRuntimeExceptionAfterBatchSave(List<User> users) {
        for (User u : users) {
            userRepository.saveUser(u);
        }
        throw new RuntimeException("测试异常");
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String batchSaveWithTransactionAttributeRequestNew(List<User> users) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public String batchSaveWithTransactionAttributeMandatory(List<User> users) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public String batchSaveWithTransactionAttributeNever(List<User> users) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String batchSaveWithTransactionAttributeNotSupport(List<User> users) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String batchSaveWithTransactionAttributeRequired(List<User> users) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String batchSaveWithTransactionAttributeSupports(List<User> users) {
        return null;
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
