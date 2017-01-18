package org.harmony.test.java.jmx;

import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.util.Collections;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.harmony.test.java.jmx.mbean.Service;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class MBeanServerTest {

    private static final int port = 9002;
    private static JMXServiceURL url;
    static {
        try {
            url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:" + port + "/jmxrmi");
        } catch (MalformedURLException e) {
        }
    }

    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new Service(), new ObjectName("jmx:type=SimpleService"));
        LocateRegistry.createRegistry(port);
        JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, Collections.emptyMap(), server);
        connectorServer.start();
        System.out.println("jmx start at " + url);
    }

    @Test
    public void test() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MBeanServerTest.main(new String[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(200);
        JMXConnector connector = JMXConnectorFactory.connect(url, Collections.emptyMap());
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        Set<ObjectName> names = connection.queryNames(null, null);
        assertNotNull(names);
        for (ObjectName on : names) {
            System.out.println(on);
        }
        thread.interrupt();
    }
}
