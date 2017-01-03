### Remote Method Invocation

早在Java 1.1时期Remote Method Invocation(RMI)就已近存在

Java简单RMI实现
<ul>
<li>接口继承自`java.rmi.Remote`
<li>子类继承自`java.rmi.server.UnicastRemoteObject`
<li>接口中的方法必须都声明抛出异常`java.rmi.RemoteException`
</ul>

发布RMI服务
```java
{
  UserService service = new SimpleUserService();
  LocateRegistry.createRegistry(9696);
  Naming.rebind(address, service);
}
```

客户端调用
```java
{
  UserService service = (UserService) Naming.lookup(address);
}
```

> 值得注意的是，当服务端直接返回一个客户端对象时候，将会重新创建一个客户端的哈希值


修改安全策略：${JAVA_HOME}/jre/lib/security/java.policy

    grant{
          permission java.security.AllPermission "","";
    };
