package org.harmony.test.javaee.clients;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.company.FooRemote;

public class TomeeRemoteLookupTest {

    EJBContainer container;

    @Before
    public void setUp() {
        Properties props = new Properties();
        props.setProperty("openejb.embedded.remotable", "true");
        container = EJBContainer.createEJBContainer(props);
    }

    @After
    public void tearDown() {
        container.close();
    }

    @Test
    public void testStandaloneLookup() throws Exception {
        Properties p = new Properties();
        p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
        new InitialContext(p);
    }

    @Test
    public void testStandaloneLookupByHttp() throws Exception {
        Properties p = new Properties();
        p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
        p.put("java.naming.provider.url", "ejbd://localhost:4201");
        new InitialContext(p);
    }

    public static void main(String[] args) throws NamingException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, org.apache.openejb.client.RemoteInitialContextFactory.class.getName());
        // http://127.0.0.1:8080/tomee/ejb
        props.setProperty(Context.PROVIDER_URL, "ejbd://localhost:4201");
        InitialContext context = new InitialContext(props);
        String jndi = args.length > 0 ? args[0] : "FooBean#" + FooRemote.class.getName();
        String name = args.length > 1 ? args[1] : "World";
        FooRemote foo = (FooRemote) context.lookup(jndi);
        System.out.println("foo service sayHi:" + foo.sayHi(name));
    }

}
