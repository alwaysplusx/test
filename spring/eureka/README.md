### 服务启动顺序

1. 启动服务注册中心
2. 启动客户端服务
3. 启动网关
4. 启动zipkin

### zipkin启动方式
> https://github.com/openzipkin/zipkin

默认启动in-memory模式
```java
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```

通过添加启动参数可以切换存储模式, 详情查看[zipkin-server-shared.yml](https://github.com/openzipkin/zipkin/blob/master/zipkin-server/src/main/resources/zipkin-server-shared.yml)
```
java -jar zipkin.jar --STORAGE_TYPE=mysql --MYSQL_USER=XX --MYSQL_PASS=XX
```