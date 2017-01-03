package org.harmony.test.javaee.jta.repository;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.harmony.test.javaee.jta.persistence.User;
import org.harmony.test.javaee.jta.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class UserRepositoryTest {

    private EJBContainer container;
    @EJB
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.put("openejb.conf.file", "src/main/resources/openejb.xml");
        container = EJBContainer.createEJBContainer(props);
        container.getContext().bind("inject", this);
    }

    @After
    public void tearDown() throws Exception {
        container.close();
    }

    @Test
    public void testSaveUser() {
        assertNotNull(userRepository);
        User user = new User("admin");
        userRepository.saveUser(user);
        assertEquals(1, userRepository.count());
    }

}
