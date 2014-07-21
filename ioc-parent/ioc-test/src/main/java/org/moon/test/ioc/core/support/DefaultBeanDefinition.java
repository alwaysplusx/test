package org.moon.test.ioc.core.support;

import org.moon.test.ioc.core.BeanDefinition;

public class DefaultBeanDefinition implements BeanDefinition {

	@Override
	public String getBeanClassName() {
		return null;
	}

	@Override
	public void setBeanClassName(String beanClassName) {
		
	}

	@Override
	public String getFactoryBeanName() {
		return null;
	}

	@Override
	public void setFactoryBeanName(String factoryBeanName) {
		
	}

	@Override
	public String getFactoryMethodName() {
		return null;
	}

	@Override
	public void setFactoryMethodName(String factoryMethodName) {
		
	}

	@Override
	public String getScope() {
		return null;
	}

	@Override
	public void setScope(String scope) {
		
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public boolean isPrototype() {
		return false;
	}

}
