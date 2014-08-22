## Apache Log4j2

Configuration of Log4j 2 can be accomplished in 1 of 4 ways:
<ul>
<li>Through a configuration file written in XML or JSON.</li>
<li>Programmatically, by creating a ConfigurationFactory and Configuration implementation.</li>
<li>Programmatically, by calling the APIs exposed in the Configuration interface to add components to the default configuration.</li>
<li>Programmatically, by calling methods on the internal Logger class.</li>
</ul>

在默认情况下，系统选择configuration文件的优先级如下：（classpath下）
<ul>
<li>log4j-test.json 或者log4j-test.jsn文件
<li>log4j2-test.xml
<li>log4j.json 或者log4j.jsn文件
<li>log4j2.xml
</ul>

### XML格式配置文件log4j2.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
	<Appenders>
		<Console name="Console" target="SYSTEM_OUT">
			<PatternLayout pattern="[%p{length=4}] [%t] [%d{ISO8601}] %c{1}.%M(%F:%L) - %m%n" />
		</Console>
	</Appenders>
	<Loggers>
		<Root level="info">
			<AppenderRef ref="Console" />
		</Root>
	</Loggers>
</Configuration>
```  
### [Appenders](http://logging.apache.org/log4j/2.x/manual/appenders.html)

#### ConsoleAppender

在控制台命令行中打印日志消息
```xml
<Console name="Console" target="SYSTEM_OUT">
	<PatternLayout pattern="${ConsolePattern}" />
</Console>
```		
#### RandomAccessFileAppender 	

将日志写入到文件中
```xml
<RandomAccessFile name="RandomAccessFile" fileName="${logFileLocation}">
	<PatternLayout pattern="${ConsolePattern}"/>
</RandomAccessFile>
```
#### RollingRandomAccessFileAppender 

可根据时间等因素来将日志文件分割打包
```xml
<!-- will create up to 7 archives on the same day (1-7) that are stored in a directory based on the current year and month-->
<!-- and will compress each archive using gzip -->
<Properties>
	<Property name="filePattern">target/log/RRAF-app-%d{MM-dd-yyyy}-%i.log.gz</Property>
</Properties>
<RollingRandomAccessFile name="RollingRandomAccessFile" fileName="${logFileLocaton}" filePattern="${filePattern}">
	<PatternLayout pattern="${ConsolePattern}" />
	<Policies>
		<!-- 根据时间来触发打包 interval="6" 每间隔指定时间触发一次打包 modulate="true" -->
		<TimeBasedTriggeringPolicy />
		<!-- 达到指定size时候触发一次打包 -->
		<SizeBasedTriggeringPolicy size="10 MB" />
		<!-- rollover strategy that will keep up to 20 files before removing them -->
		<!-- <DefaultRolloverStrategy max="20"/> -->
	</Policies>
	<!-- below item will keep up to 20 files before removing them. -->
	<DefaultRolloverStrategy max="20" />
</RollingRandomAccessFile>
```	
#### JDBCAppender

写日志到数据库中,可以使用三种方式获取数据库连接

<ul>
<li>DriverManager
<li>JNDI DataSource
<li>用户自定义ConnectionFactory
</ul>
```xml		
<JDBC name="JDBC" tableName="t_app_log">
	<!-- 1. use DriverManager -->
	<DriverManager url="jdbc:mysql://localhost:3306/moon" username="wuxin" password="root"/>
	<!-- 2. use jndi DataSource -->
	<!-- <DataSource jndiName="java:/comp/env/jdbc/mysql/moon"></DataSource> -->
	<!-- 3. use ConnectionFactory get Connection -->
	<!-- <ConnectionFactory class="xx.x.x.ConnectionFactory" method="getConnection"></ConnectionFactory> -->
	<Column name="eventDate" isEventTimestamp="true" />
    <Column name="level" pattern="%level" />
    <Column name="logger" pattern="%logger" />
    <Column name="message" pattern="%message" />
    <Column name="exception" pattern="%ex{full}" />
</JDBC>
```
#### SMTPAppender

以邮件形式发送异常信息

>smtpUsername在验证授权时必填
```xml
<!-- 收件人可以用逗号隔开 -->
<SMTP name="SMTP" subject="Error Log" to="yyy@126.com" smtpHost="smtp.126.com"
	smtpPort="25" bufferSize="50" from="xxx@126.com" smtpUsername="xxx@126.com"
	smtpPassword="xxx" smtpDebug="false">
</SMTP>
```
### More Other Appender
<ul>
<li>NoSQLAppender
<li>JMSQueueAppender
<li>JMSTopicAppender
<li>JPAAppender
<li>...
</ul>
		
### [Filters](http://logging.apache.org/log4j/2.x/manual/filters.html)		  
