package org.moon.test.cxf.client;

import java.util.logging.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

@SuppressWarnings("unchecked")
public class ProxyFactoryManager {
	
	static Cache cache;
	static CacheManager cacheManager;
	static Logger LOG = Logger.getLogger(ProxyFactoryManager.class.getSimpleName());

	static {
		cache = new Cache(new CacheConfiguration("clientService", 10000));
		cacheManager = CacheManager.create();
		cacheManager.addCache(cache);
	}

	public static <T> T create(Class<T> serviceClass, String address) throws Exception {
		return create(serviceClass, address, null, null);
	}

	public synchronized static <T> T create(Class<T> serviceClass, String address, String username, String password) throws Exception {
		ServiceKey serviceKey = new ServiceKey(serviceClass, address, username, password);
		if (cache.get(serviceKey) == null) {
			LOG.info("未在缓存中找到目标服务,开始创建新服务");
			FactoryKey factoryKey = new FactoryKey(serviceClass, address, username, password);
			if (cache.get(factoryKey) == null) {
				LOG.info("未找到服务工厂,开始创建服务工厂");
				JaxWsProxyFactoryBean proxyFactory = initProxyFactory(serviceClass, address, username, password);
				cache.put(new Element(factoryKey, proxyFactory));
			}
			cache.put(new Element(serviceKey, ((JaxWsProxyFactoryBean) cache.get(factoryKey).getObjectValue()).create()));
			LOG.info("创建服务成功");
		} else {
			LOG.info("在缓存中获取到服务");
		}
		return (T) cache.get(serviceKey).getObjectValue();
	}

	protected static JaxWsProxyFactoryBean initProxyFactory(Class<?> serviceClass, String address, String username, String password) throws Exception {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setServiceClass(serviceClass);
		proxyFactory.setAddress(address);
		proxyFactory.getInInterceptors().add(new LoggingInInterceptor());
		proxyFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		if (username != null && !"".equals(username)) {
			proxyFactory.setUsername(username);
		}
		if (password != null && !"".equals(password)) {
			proxyFactory.setPassword(username);
		}
		return proxyFactory;
	}

}

class ServiceKey {

	String address;
	String username;
	String password;
	Class<?> clazz;

	public ServiceKey(Class<?> clazz, String address, String username, String password) {
		this.clazz = clazz;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceKey other = (ServiceKey) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}

class FactoryKey {

	String address;
	String username;
	String password;
	Class<?> clazz;

	public FactoryKey(Class<?> clazz, String address, String username, String password) {
		this.clazz = clazz;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FactoryKey other = (FactoryKey) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}