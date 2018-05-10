package org.harmony.test.cxf;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JaxWsClientManager {

    static Logger log = LoggerFactory.getLogger(JaxWsClientManager.class);

    public <T> T create(Class<T> serviceClass, String address) {
        return create(serviceClass, address, null, null);
    }

    public synchronized <T> T create(Class<T> serviceClass, String address, String username, String password) {
        JaxWsProxyFactoryBean factory = createProxyFactory(serviceClass, address, username, password);
        return factory.create(serviceClass);
    }

    <T> JaxWsProxyFactoryBean createProxyFactory(Class<T> serviceClass, String address, String username, String password) {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress(address);
        factoryBean.setServiceClass(serviceClass);
        factoryBean.setUsername(username);
        factoryBean.setPassword(password);
        // if (log.isDebugEnabled()) {
        factoryBean.getInInterceptors().add(new LoggingInInterceptor());
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
        // }
        return factoryBean;
    }

    private JaxWsClientManager() {
    }

    public static JaxWsClientManager getInstance() {
        return InstanceHolder.instance;
    }

    static class InstanceHolder {
        private static final JaxWsClientManager instance = new JaxWsClientManager();
    }

}
