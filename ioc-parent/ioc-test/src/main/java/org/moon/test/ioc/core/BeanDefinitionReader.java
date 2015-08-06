package org.moon.test.ioc.core;

public interface BeanDefinitionReader {

    int loadBeanDefinitions(String location) throws BeanLoadException;

    int loadBeanDefinitions(Resource resource) throws BeanLoadException;

}
