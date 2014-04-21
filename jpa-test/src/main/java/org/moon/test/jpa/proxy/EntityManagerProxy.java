package org.moon.test.jpa.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

public class EntityManagerProxy extends ObjectProxy {

	private Logger log = Logger.getLogger(EntityManagerProxy.class);
	private EntityManager target;
	private String include = "persist,merge,remove,flush,refresh";
	private boolean transactionOpened = false;
	private String methodName = "";
	
	@Override
	public Object bind(Object target) {
		if (target instanceof EntityManager) {
			this.target = (EntityManager) target;
			Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { EntityManager.class }, this);
			log.debug("bind entitymanager " + target);
			return proxyInstance;
		}
		return null;
	}

	@Override
	public void perExecute(Method method) {
		methodName = method.getName();
		if (!transactionOpened && include.contains(methodName)) {
			target.getTransaction().begin();
			log.debug("start transaction for method " + methodName + "!");
		}
	}

	@Override
	public Object execute(Method method, Object[] args) throws Throwable{
		return method.invoke(target, args);
	}

	@Override
	public void postExecute(Method method) {
		if (transactionOpened) {
			target.getTransaction().commit();
			log.debug("commit transaction for method " + methodName + "!");
		}
	}

}
