## module相关关键字

* module
* requires
* exports
* transitive
* opens
* uses
* provides

```java
module org.harmony.sample {
    requires java.base;
    requires transitive java.logging; // 传递依赖, 假设org.harmony.sample中的返回值存在java.logging中的类型, 这样在使用org.harmony.sample时就不用在什么requires java.logging
    exports org.harmony.sample;
    exports org.hramony.sample.foo to xx; // 导出仅供模块xx使用
    opens org.harmony.sample.foo; // 声明运行时候能使用反射
    opens org.harmony.sample.foo to xx; // 
    providers org.harmony.sample.impl.XXProviderImpl with org.harmony.sample.spi.XXProvider; // 声明为服务的提供方, 提供了org.harmony.sample.spi.XXProvider实现
}
```


> 其他内容查看: [test-java9-module](../test-java9-module)

## jlink create jre by youself

### look how many modules are there
```
java --list-modules
```

```
jlink --module-path $JAVA_HOME/jmods --add-modules java.base --output /path/to/youself/jre
```

### compress jre

```
jlink --module-path $JAVA_HOME/jmods --add-modules java.base --output /path/to/youself/jre --compress 2 --strip-debug
```

> --vm minimal

### use jre run you application
```
/path/to/youself/jre/java --module-path third-part-libs -m main_class
```