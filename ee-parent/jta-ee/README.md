## Java Transaction API

## [TransactionAttribute](http://docs.oracle.com/javaee/7/api/javax/ejb/TransactionAttribute.html)

### TransactionAttributeType.REQUIRED (Default)
> If a client invokes the enterprise bean's method while the client is associated with a transaction context, the container invokes the enterprise bean's method in the client's transaction context.

> If the client invokes the enterprise bean's method while the client is not associated with a transaction context, the container automatically starts a new transaction before delegating a method call to the enterprise bean method.

调用EJB Bean时，如果客户端已经在一个事务的上下文中时，那么这个EJB Bean的事务将控制在客户端的相关事务中。

调用EJB Bean时，如果客户端不存在，那么EJB容器将启动一个新事务。

### TransactionAttributeType.MANDATORY
> If a client invokes the enterprise bean's method while the client is associated with a transaction context, the container invokes the enterprise bean's method in the client's transaction context.
If there is no existing transaction, an exception is thrown.

调用EJB Bean时，如果客户端已经在一个事务的上下文中时，那么这个EJB Bean的事务将控制在客户端的相关事务中。(与REQUIRED相同)

调用EJB Bean时，如果客户端不存在，将抛出异常。

### TransactionAttributeType.NEVER
> The client is required to call without a transaction context, otherwise an exception is thrown.

调用Bean的客户端中不允许存在事务，否则抛出异常

### TransactionAttributeType.NOT_SUPPORTED
> The container invokes an enterprise bean method whose transaction attribute NOT_SUPPORTED with an unspecified transaction context.

> If a client calls with a transaction context, the container suspends the association of the transaction context with the current thread before invoking the enterprise bean's business method. The container resumes the suspended association when the business method has completed.

声明为`NOT_SUPPORTED`的方法不指定事务。

调用时，客户端中存在事务，容器将挂起客户端的事务待方法执行完成后恢复。

如果客户端没有关联到事务中，容器不会在运行这个方法之前启动一个新的事务。

### TransactionAttributeType.REQUIRES_NEW
> The container must invoke an enterprise bean method whose transaction attribute is set to REQUIRES_NEW with a new transaction context.

> If the client invokes the enterprise bean's method while the client is not associated with a transaction context, the container automatically starts a new transaction before delegating a method call to the enterprise bean business method.

> If a client calls with a transaction context, the container suspends the association of the transaction context with the current thread before starting the new transaction and invoking the method. The container resumes the suspended transaction association after the method and the new transaction have been completed.

设置为`REQUIRES_NEW`的方法必须运行在一个新的事务中。

调用时，如果客户端不存在事务，容器将自动创建一个新事务，Bean的方法将运行在这个新事务中。

如果客户端已经运行在一个事务中，在执行之前容器将会挂起客户端的事务，并在Bean的方法运行结束事务提交后恢复客户端的事务。


### TransactionAttributeType.SUPPORTS
> If the client calls with a transaction context, the container performs the same steps as described in the REQUIRED case.

> If the client calls without a transaction context, the container performs the same steps as described in the NOT_SUPPORTED case.

> The SUPPORTS transaction attribute must be used with caution. This is because of the different transactional semantics provided by the two possible modes of execution. Only enterprise beans that will execute correctly in both modes should use the SUPPORTS transaction attribute.

调用时，如果客户端已经在一个事务的上下文中时，那么这个EJB Bean的事务将控制在客户端的相关事务中。(与REQUIRED相同)

如果客户端没有关联到事务中，容器不会在运行这个方法之前启动一个新的事务。(与NOT_SUPPORTED相同)
