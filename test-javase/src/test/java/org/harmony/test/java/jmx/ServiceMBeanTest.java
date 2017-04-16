package org.harmony.test.java.jmx;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.URL;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.harmony.test.java.jmx.mbean.Service;
import org.junit.Test;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class ServiceMBeanTest {

    public static void main(String[] args) throws Exception {
        // createMBeanServer()// not in jconsole
        // MBeanServer server = MBeanServerFactory.createMBeanServer();
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new Service(), new ObjectName("jmx:type=SimpleService"));
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void testHtmpAdaptor() throws Exception {
        int port = 9001;
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        HtmlAdaptorServer adapter = new HtmlAdaptorServer(port);
        server.registerMBean(adapter, new ObjectName("base_domain:type=HtmlAdapter"));
        adapter.start();
        InputStream is = new URL("http://localhost:" + port).openConnection().getInputStream();
        assertNotEquals(0, is.available());
    }

}
