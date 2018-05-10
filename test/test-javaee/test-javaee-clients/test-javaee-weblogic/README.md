Weblogic默认不提供客户端运行环境, 只能通过特定的方式生成客户端运行环境所需要的运行环境(JAR). 其步骤如下:

启动命令行,切换到`{weblogic_home}/wlserver_[version]/server/lib`下

输入命令

```shell
java -jar wljarbuilder.jar
```

在当前目录下将生成wlfullclient.jar(50+MB)即为客户端的最小运行环境

使用同样的方式可以生成对应的Webloigic Maven工具

```shell
java -jar wljarbuilder.jar -profile weblogic-maven-plugin
```