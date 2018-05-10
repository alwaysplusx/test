package org.harmony.test.java.rmi;

import static org.junit.Assert.*;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.harmony.test.java.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RMIUserServiceTest {

    private static final int port = 9000;
    private static final String address = "rmi://localhost:" + port + "/users";

    @BeforeClass
    public static void beforeClass() throws Exception {
        LocateRegistry.createRegistry(port);
        Naming.rebind(address, new SimpleUserService());
    }

    @Test
    public void testGetUser() throws Exception {
        UserService service = (UserService) Naming.lookup(address);
        assertNotNull(service);
        User user = service.getUser("test");
        assertNotNull(user);
        assertEquals("test", user.getUsername());
    }

    @AfterClass
    public static void afterClass() throws Exception {
        Naming.unbind(address);
    }

}
