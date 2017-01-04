package org.harmony.test.javaee.jta;

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
public class TransactionAttributeNotSupportedTest {

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
    public void testSaveWithNotSupported() {
        userService.saveWithNotSupported(new User("wuxii-1"));
    }

    @Test
    public void testEchoWithNotSupported() {
        userService.echoWithSupports("wuxii-2");
    }

    @Test(expected = Exception.class)
    public void testSaveWitNotSupportedInClientTransaction() throws Exception {
        try {
            ux.begin();
            userService.saveWithNotSupported(new User("wuxii-2"));
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
    public void testSaveWithNotSupportedInRepositoryBean() {
        userService.saveWithNotSupportedInRepositoryBean(new User("wuxii-1"));
    }

}
