package org.harmony.test.javaee.jta;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.harmony.test.javaee.jta.transactionmanagement.TransactionManagement_Container_UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 生命为Mandatory的session bean容器不会开启方法事务, 只能运行在调用端的事务中.
 * 
 * @author wuxii@foxmail.com
 */
public class TransactionAttributeMandatoryTest {

    private static EJBContainer container;

    @EJB
    private TransactionManagement_Container_UserService userService;

    @EJB
    private UserRepository userRepository;

    @Resource
    private UserTransaction ux;

    @BeforeClass
    public static void beforeClass() {
        Properties props = new Properties();
        props.setProperty("openejb.conf.file", "src/main/resources/openejb.xml");
        container = EJBContainer.createEJBContainer(props);
    }

    @Before
    public void before() throws NamingException {
        container.getContext().bind("inject", this);
    }

    @Test(expected = EJBException.class)
    public void testSaveWithMandatory() {
        // 方法运行无事务支持, 无法提交事务异常
        userService.saveWithMandatory(new User("wuxii-1"));
    }

    @Test
    public void testSaveWithMandatoryInClientTransaction() throws Exception {
        // 在调用端开启事务可以提交方法声明为Madatory的事务
        User user = null;
        try {
            ux.begin();
            user = userService.saveWithMandatory(new User("wuxii-2"));
            ux.commit();
        } catch (Exception e) {
            try {
                ux.rollback();
            } catch (Exception e1) {
            }
            throw e;
        }
        assertEquals("wuxii-2", userRepository.findUserById(user.getUserId()).getUsername());
    }

    @Test(expected = EJBException.class)
    public void testEchoWithMandtory() {
        userService.echoWithMandatory("wuxii-3");
    }

    @Test
    public void testEchoWithMandtoryInClientTransaction() throws Exception {
        // 在调用端开启事务可以提交方法声明为Madatory的事务
        try {
            ux.begin();
            userService.echoWithMandatory("wuxii-4");
            ux.commit();
        } catch (Exception e) {
            try {
                ux.rollback();
            } catch (Exception e1) {
            }
            throw e;
        }
    }
}
