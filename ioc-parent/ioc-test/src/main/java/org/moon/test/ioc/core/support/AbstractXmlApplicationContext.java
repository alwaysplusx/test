package org.moon.test.ioc.core.support;

import org.moon.test.ioc.core.Resource;
import org.moon.test.ioc.core.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractApplicationContext {

    public abstract Resource getConfigResource();

    public int loadBeanDefinitions(DefaultConfigurableBeanFactory beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        return reader.loadBeanDefinitions(getConfigResource());
    }

    public void finishInitialization(DefaultConfigurableBeanFactory beanFactory) {
        beanFactory.preInstantiateSingletons();
    }

}
