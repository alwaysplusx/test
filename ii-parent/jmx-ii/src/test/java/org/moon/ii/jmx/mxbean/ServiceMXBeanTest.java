package org.moon.ii.jmx.mxbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.moon.ii.jmx.persistence.User;

import com.sun.jdmk.comm.HtmlAdaptorServer;


public class ServiceMXBeanTest {

	public static void main(String[] args) throws Exception {
		//MBeanServer server = MBeanServerFactory.createMBeanServer();
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		Service service = new Service();
		service.setUser(new User("abc",12));
		server.registerMBean(service, new ObjectName("jmx:type=SimpleService"));
		HtmlAdaptorServer adapter = new HtmlAdaptorServer(8080);
		server.registerMBean(adapter, new ObjectName("base_domain:type=HtmlAdapter"));
		adapter.start();
		Thread.sleep(Long.MAX_VALUE);
	}
}
