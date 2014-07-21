package org.moon.test.ioc.core;

public interface BeanDefinitionReader {

	int loadBeanDefinitions(String location);
	int loadBeanDefinitions(Resource resource);

}
