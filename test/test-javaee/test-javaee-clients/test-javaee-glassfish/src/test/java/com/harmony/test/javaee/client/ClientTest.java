package com.harmony.test.javaee.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import com.sun.enterprise.naming.SerialInitContextFactory;

/**
 * @author wuxii@foxmail.com
 */
public class ClientTest {

    public static void main(String[] args) throws NamingException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, SerialInitContextFactory.class.getName());
        // props.setProperty(Context.PROVIDER_URL, "http://localhost:8081");

        // glassfish中设置provider_url无效,需要特殊设置
        // org.omg.CORBA.ORBInitialHost
        // org.omg.CORBA.ORBInitialPort: 3700(仅限ejb端口)
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

        // props.setProperty(Context.PROVIDER_URL, "iiop://localhost:3700");
        InitialContext context = new InitialContext(props);
        list(context);
        // AppRemote app = (AppRemote) context.lookup("AppBean");
        // AppEntity appEntity = new AppEntity();
        // appEntity.setI(1);
        // appEntity.setIw(1);
        // appEntity.setL(1l);
        // appEntity.setLw(1l);
        // appEntity.setS("s");
        // System.out.println(app.exchange(appEntity));
    }

    protected static void list(Context ctx) {
        try {
            NamingEnumeration<NameClassPair> names = ctx.list("");
            while (names.hasMore()) {
                NameClassPair pair = names.next();
                String name = pair.getName();
                System.out.println(name);
                // Object obj = ctx.lookup(name);
                // if (obj instanceof Context) {
                // list((Context) obj);
                // } else {
                // System.out.println(name + ", is " + obj);
                // }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
