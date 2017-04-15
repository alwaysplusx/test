package org.harmony.test.ioc.core.support;

import org.harmony.test.ioc.core.BeanFactory;
import org.harmony.test.ioc.core.Resource;
import org.harmony.test.ioc.core.io.ClassPathResource;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private BeanFactory beanFactory;
    private Resource configResource;

    public ClassPathXmlApplicationContext(String loaction) {
        this.configResource = new ClassPathResource(loaction, null);
        setup();
    }

    private void setup() {
        DefaultConfigurableBeanFactory beanFactory = new DefaultConfigurableBeanFactory();
        this.beanFactory = beanFactory;
        loadBeanDefinitions(beanFactory);
        finishInitialization(beanFactory);
    }

    @Override
    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Resource getConfigResource() {
        return this.configResource;
    }

}
