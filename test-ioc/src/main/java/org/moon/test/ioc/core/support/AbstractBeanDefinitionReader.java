package org.moon.test.ioc.core.support;

import org.moon.test.ioc.core.BeanDefinitionReader;
import org.moon.test.ioc.core.BeanLoadException;
import org.moon.test.ioc.core.io.ClassPathResource;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    @Override
    public int loadBeanDefinitions(String location) throws BeanLoadException {
        return loadBeanDefinitions(new ClassPathResource(location, null));
    }

}
