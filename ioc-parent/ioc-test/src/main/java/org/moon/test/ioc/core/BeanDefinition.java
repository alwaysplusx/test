package org.moon.test.ioc.core;

public interface BeanDefinition {

	static final String SCOPE_SINGLETON = "singleton";

	static final String SCOPE_PROTOTYPE = "prototype";
	
	String getBeanName();
	
	void setBeanName(String beanName);
	
	String getBeanClassName();

	void setBeanClassName(String beanClassName);

	String getFactoryBeanName();

	void setFactoryBeanName(String factoryBeanName);

	String getFactoryMethodName();

	void setFactoryMethodName(String factoryMethodName);
	
	String getScope();
	
	void setScope(String scope);
	
	boolean isSingleton();
	
	boolean isPrototype();
}
