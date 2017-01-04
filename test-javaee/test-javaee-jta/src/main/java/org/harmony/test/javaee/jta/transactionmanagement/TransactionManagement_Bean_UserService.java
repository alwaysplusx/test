package org.harmony.test.javaee.jta.transactionmanagement;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransactionManagement_Bean_UserService {

    @PersistenceContext(unitName = "harmony")
    private EntityManager em;

    @EJB
    private UserRepository userRepository;

    @Resource
    private UserTransaction ux;

    public User saveByEntityManager(User user) {
        em.persist(user);
        return user;
    }

    public User saveByUserRepositoryBean(User user) {
        return userRepository.saveUser(user);
    }

    public User saveByEntityManagerAndUserTransaction(User user) {
        try {
            ux.begin();
            em.persist(user);
            ux.commit();
        } catch (Exception e) {
            try {
                ux.rollback();
            } catch (Exception e1) {
            }
        }
        return user;
    }

    // Ignoring 1 invalid @TransactionAttribute annotations. Bean not using Container-Managed Transactions.
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void withTransactionAttribute() {
    }

}