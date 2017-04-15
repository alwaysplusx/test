package org.harmony.test.ioc.core.support;

import org.harmony.test.ioc.core.ApplicationContext;
import org.harmony.test.ioc.core.BeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext {

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return getBeanFactory().getBean(type);
    }

    @Override
    public <T> T getBean(String name, Class<T> type) {
        return getBeanFactory().getBean(name, type);
    }

    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }

    public abstract BeanFactory getBeanFactory();

    public abstract void setBeanFactory(BeanFactory beanFactory);

}
