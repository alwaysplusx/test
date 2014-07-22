package org.moon.test.ioc.core;

public interface BeanFactory {

	Object getBean(String name);

	<T> T getBean(Class<T> type);

	<T> T getBean(String name, Class<T> type);
	
	boolean containsBean(String name);

}
