package org.moon.test.ioc.core;

public interface ConfigurableBeanFactory extends BeanFactory {

	void preInstantiateSingletons();

}
