package org.moon.test.ioc.core.support;

import org.moon.test.ioc.core.BeanDefinitionReader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

	@Override
	public int loadBeanDefinitions(String location) {
		return 0;
	}
	
}
