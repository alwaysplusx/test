package org.harmony.test.jmx.mbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.harmony.test.jmx.mbean.Service;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class ServiceMBeanTest {

    public static void main(String[] args) throws Exception {
        //MBeanServer server = MBeanServerFactory.createMBeanServer();
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new Service(), new ObjectName("jmx:type=SimpleService"));
        HtmlAdaptorServer adapter = new HtmlAdaptorServer(8080);
        server.registerMBean(adapter, new ObjectName("base_domain:type=HtmlAdapter"));
        adapter.start();
        Thread.sleep(Long.MAX_VALUE);
    }

}
