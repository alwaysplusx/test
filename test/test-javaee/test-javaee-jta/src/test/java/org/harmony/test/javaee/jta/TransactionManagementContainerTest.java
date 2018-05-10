package org.harmony.test.javaee.jta;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.harmony.test.javaee.jta.transactionmanagement.TransactionManagement_Container_UserService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class TransactionManagementContainerTest {

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

    @Test
    public void testSave() {
        // TransactionAttributeType.REQUIRED也是默认的ejb方法事务attribute
        User user = userService.save(new User("wuxii-1"));
        assertEquals("wuxii-1", userRepository.findUserById(user.getUserId()).getUsername());
    }

    @AfterClass
    public static void afterClass() {
        container.close();
    }
}
