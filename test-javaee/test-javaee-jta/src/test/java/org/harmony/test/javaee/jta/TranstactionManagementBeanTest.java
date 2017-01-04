package org.harmony.test.javaee.jta;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.harmony.test.javaee.jta.transactionmanagement.TransactionManagement_Bean_UserService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class TranstactionManagementBeanTest {

    private static EJBContainer container;

    @EJB
    private TransactionManagement_Bean_UserService userService;

    @EJB
    private UserRepository userRepository;

    @BeforeClass
    public static void beforeClass() {
        Properties props = new Properties();
        props.setProperty("openejb.conf.file", "src/main/resources/openejb.xml");
        container = EJBContainer.createEJBContainer();
    }

    @Before
    public void before() throws NamingException {
        container.getContext().bind("inject", this);
        assertNotNull(userService);
    }

    @Test(expected = EJBException.class)
    public void testSaveByEntityManager() {
        // userService本身处于无事务状态, 采用EntiytManager保存没有事务提交所以触发异常TransactionRequiredException
        userService.saveByEntityManager(new User("wuxii-1"));
    }

    @Test
    public void testSaveByUserRepository() {
        // 虽然userService处于无事务的环境中, 但是采用userRepository的EJB BEAN, 在Repository又自动开启了保存事务.
        User user = userService.saveByUserRepositoryBean(new User("wuxii-2"));
        assertEquals("wuxii-2", userRepository.findUserById(user.getUserId()).getUsername());
    }

    @Test
    public void testSaveByEntityManagerAndUserTransaction() {
        // 在方法中手动开启事务并提交
        User user = userService.saveByEntityManagerAndUserTransaction(new User("wuxii-3"));
        assertEquals("wuxii-3", userRepository.findUserById(user.getUserId()).getUsername());
    }

    public void testWithTransactionAttribute() {
        // 在@TransactionManagement声明为BEAN的会话bean中配置TransactionAttribute是无效的
    }

    @AfterClass
    public static void afterClass() {
        container.close();
    }

}
