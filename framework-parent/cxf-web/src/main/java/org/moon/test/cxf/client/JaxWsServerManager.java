package org.moon.test.cxf.client;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JaxWsServerManager {

	static Logger log = LoggerFactory.getLogger(JaxWsServerManager.class);

	private JaxWsServerManager() {
	}

	public static JaxWsServerManager getInstance() {
		return InstanceHolder.instance;
	}

	public Server publish(Class<?> serviceClass, String address, Object serviceBean) {
		JaxWsServerFactoryBean factory = createServerFactory(serviceClass, address, serviceBean);
		return factory.create();
	}

	protected JaxWsServerFactoryBean createServerFactory(Class<?> serviceClass, String address, Object serviceBean) {
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(serviceClass);
		factory.setAddress(address);
		factory.setServiceBean(serviceBean);
		// factory.setDataBinding(null);
		// if (log.isDebugEnabled()) {
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		// }
		return factory;
	}


	private static class InstanceHolder {
		private final static JaxWsServerManager instance = new JaxWsServerManager();
	}

}
