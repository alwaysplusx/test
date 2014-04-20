package org.moon.test.jpa.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

/**
 * {@link EntityManager} 代理类
 * @author wuxin
 * <p>2014年1月14日 下午10:27:35</p>
 */
public class EntityManagerProxy implements InvocationHandler {

	private EntityManager em;
	private boolean transactionRequired = false;
	
	public EntityManagerProxy(EntityManager em) {
		this.em = em;
	}

	private void preExecute() {
		if (transactionRequired) {
			em.getTransaction().begin();
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		methodFilter(method, args);
		preExecute();
		Object result = method.invoke(em, args);
		postExecute();
		return result;
	}
	
	private void postExecute() {
		if (transactionRequired) {
			em.getTransaction().commit();
		}
	}
	
	private void methodFilter(Method method, Object[] args) {
		transactionRequired = false;
		String methodName = method.getName();
		if (("persist".equals(methodName) || "merge".equals(methodName) 
				|| "remove".equals(methodName)) && (args.length == 1)) {
			transactionRequired = true;
		}
	}
}
