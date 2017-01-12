package org.harmony.test.javaee.clients;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.company.FooRemote;

/**
 * @author wuxii@foxmail.com
 */
public class WeblogicRemoteLookupTest {

    /**
     * weblogic支持jndi格式有:
     * <ul>
     * <li><code>java:global/{app-name}/{bean-name}!{interface-name}<code>
     * <li><code>{bean-name}#{interface-name}</code>
     * </ul>
     */
    public static void main(String[] args) throws NamingException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, weblogic.jndi.WLInitialContextFactory.class.getName());
        props.setProperty(Context.PROVIDER_URL, "t3://localhost:7001");
        InitialContext context = new InitialContext(props);
        String jndi = args.length > 0 ? args[0] : "FooBean#" + FooRemote.class.getName();
        String name = args.length > 1 ? args[1] : "World";
        FooRemote foo = (FooRemote) context.lookup(jndi);
        System.out.println("foo service sayHi:" + foo.sayHi(name));
    }

}
