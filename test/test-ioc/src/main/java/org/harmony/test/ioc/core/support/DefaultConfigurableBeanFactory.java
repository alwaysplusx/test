package org.harmony.test.ioc.core.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.harmony.test.ioc.core.BeanDefinition;
import org.harmony.test.ioc.core.BeanDefinitionRegistry;
import org.harmony.test.ioc.core.BeanLoadException;
import org.harmony.test.ioc.core.ConfigurableBeanFactory;

public class DefaultConfigurableBeanFactory implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> type) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return beanDefinitionMap.containsKey(name);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanLoadException {
        synchronized (this.beanDefinitionMap) {
            BeanDefinition bd = beanDefinitionMap.get(beanName);
            if (bd == null) {
                this.beanDefinitionMap.put(beanName, beanDefinition);
            } else {
                throw new BeanLoadException();
            }
        }
    }

    @Override
    public void preInstantiateSingletons() {
        List<String> beanNames = new ArrayList<String>();
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    }

    protected BeanDefinition getLocalBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

}
