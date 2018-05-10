package org.harmony.test.javaee.jta.transactionmanagement;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * saveXX - 需要提交事务的方法
 * <p>
 * echoXX - 不需要提交事务的方法
 * 
 * @author wuxii@foxmail.com
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TransactionManagement_Container_UserService {

    private static final Logger log = LoggerFactory.getLogger(TransactionManagement_Container_UserService.class);

    @PersistenceContext(unitName = "harmony")
    private EntityManager em;

    @EJB
    private UserRepository userRepository;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public User saveWithMandatory(User user) {
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void echoWithMandatory(String text) {
        log.info("mandatory > {}", text);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public User saveWithNever(User user) {
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public User saveWithNeverInRepositoryBean(User user) {
        return userRepository.saveUser(user);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void echoWithNever(String text) {
        log.info("never > {}", text);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public User saveWithNotSupported(User user) {
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public User saveWithNotSupportedInRepositoryBean(User user) {
        return userRepository.saveUser(user);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void echoWithNotSupported(String text) {
        log.info("not_supported > {}", text);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User saveWithRequired(User user) {
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void echoWithRequired(String text) {
        log.info("required > {}", text);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public User saveWithRequiresNew(User user) {
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User saveWithSupports(User user) {
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void echoWithSupports(String text) {
        log.info("supports > {}", text);
    }

}
