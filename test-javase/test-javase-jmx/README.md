### Java Management Extensions

#### 属性设置

接口中设置getter/setter方法表示实现类中的,这一属性的读/写权限

如果不符合为此条件的方法则表示为一个Operation

### javax.management.ObjectName

格式说明"domain_name:key=value"

```java
public static void main(String[] args) throws Exception {
	MBeanServer server = MBeanServerFactory.createMBeanServer();
	server.registerMBean(new Service(), new ObjectName("jmx:type=SimpleService"));
	HtmlAdaptorServer adapter = new HtmlAdaptorServer(8080);
	server.registerMBean(adapter, new ObjectName("base_domain:type=HtmlAdapter"));
	adapter.start();
	Thread.sleep(Long.MAX_VALUE);
}
```

java.version=1.7.0_21

> 注意:命名规范为接口名XXXMBean,实现类名XXX. 并且这两个类是在同一个包下