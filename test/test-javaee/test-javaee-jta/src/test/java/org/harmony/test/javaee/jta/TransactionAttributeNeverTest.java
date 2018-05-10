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
 * 声明为Never的会话bean方法只能运行在无事务的环境中, 得出只能为不需要提交事务的方法声明NEVER
 * 
 * @author wuxii@foxmail.com
 */
public class TransactionAttributeNeverTest {

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
    public void testSaveWithNever() {
        // 方法声明事务attribute为never, 方法本身不运行在容器事务中
        userService.saveWithNever(new User("wuxii-1"));
    }

    @Test(expected = EJBException.class)
    public void testSaveWithNeverInClientTransaction() throws Exception {
        // 强制在客户端开启事务将导致EJBException: transactions not supported
        try {
            ux.begin();
            userService.saveWithNever(new User("wuxii-2"));
            ux.commit();
        } catch (Exception e) {
            try {
                ux.rollback();
            } catch (Exception e1) {
            }
            throw e;
        }
    }

    @Test
    public void testSaveWithNeverInRepositoryBean() {
        User user = userService.saveWithNeverInRepositoryBean(new User("wuxii-3"));
        assertEquals("wuxii-3", userRepository.findUserById(user.getUserId()).getUsername());
    }

    @Test
    public void testEchoWithNever() {
        userService.echoWithNever("wuxii-3");
    }

    @Test(expected = EJBException.class)
    public void testEchoWithNeverInClientTransaction() throws Exception {
        // 强制在客户端开启事务将导致EJBException: transactions not supported
        try {
            ux.begin();
            userService.echoWithNever("wuxii-4");
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
