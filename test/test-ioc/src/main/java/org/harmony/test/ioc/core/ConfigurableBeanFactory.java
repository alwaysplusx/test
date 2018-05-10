package org.harmony.test.ioc.core;

public interface ConfigurableBeanFactory extends BeanFactory {

    void preInstantiateSingletons();

}
