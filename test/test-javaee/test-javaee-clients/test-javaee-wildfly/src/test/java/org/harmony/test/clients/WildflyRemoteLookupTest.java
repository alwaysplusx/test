package org.harmony.test.clients;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.company.FooRemote;

/**
 * @author wuxii@foxmail.com
 */
public class WildflyRemoteLookupTest {

    public static void main(String[] args) throws NamingException {
        Properties props = new Properties();
        //  org.jboss.naming.remote.client.InitialContextFactory
        //  org.wildfly.naming.client.WildFlyInitialContextFactory
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
        props.setProperty(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        InitialContext context = new InitialContext(props);
        String jndi = args.length > 0 ? args[0] : "FooBean#" + FooRemote.class.getName();
        String name = args.length > 1 ? args[1] : "World";
        FooRemote foo = (FooRemote) context.lookup(jndi);
        System.out.println("foo service sayHi:" + foo.sayHi(name));
    }

}
