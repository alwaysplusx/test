## Spring & Quartz Timer

配置，通过加载类路径下的`quartz.properties`配置文件加载定时任务

pom.xml
```xml
<dependency>
  <groupId>org.quartz-scheduler</groupId>
  <artifactId>quartz</artifactId>
  <version>${quartz.version}</version>
</dependency>
<dependency>
  <groupId>org.quartz-scheduler</groupId>
  <artifactId>quartz-jobs</artifactId>
  <version>${quartz.version}</version>
</dependency>
```
applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!-- 使用该情况下schedule的job是不属于spring容器内的 -->
	<bean name="scheduleFactory" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
		<property name="configLocation" value="quartz.properties"/>
		<property name="schedulerName" value="Quartz"/>
	</bean>
</beans>
```

使用Spring集成Quartz
```xml
<bean name="simpleService" class="org.moon.test.timer.spring.SimpleService" autowire="byName"/>

<bean name="simpleTask" class="org.moon.test.timer.spring.SimpleTask" autowire="byName"/>

<bean name="simpleQuartzJobBean" class="org.moon.test.timer.spring.quartz.SimpleQuartzJobBean" autowire="byName"/>

<bean name="jobTask" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
	<property name="targetObject" ref="simpleTask"/>
	<property name="targetMethod" value="doWork"/>
</bean>

<bean name="simpleJobDetail" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
	<property name="jobClass" value="org.moon.test.timer.spring.quartz.SimpleQuartzJobBean"/>
	<property name="durability" value="true" />
</bean>

<bean name="cronTrigger1" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
	<property name="jobDetail" ref="simpleJobDetail"/>
	<property name="cronExpression" value="0/5 * * * * ?"/>
</bean>

<bean name="cronTrigger2" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
	<property name="jobDetail" ref="jobTask"/>
	<property name="cronExpression" value="0/5 * * * * ?"/>
</bean>

<bean name="xmlScheduleFactory" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
	<property name="triggers">
		<list>
			<ref bean="cronTrigger1"/>
			<ref bean="cronTrigger2"/>
		</list>
	</property>
</bean>
```

Spring Task
```xml
<task:scheduler id="scheduler" pool-size="10"/>
<bean name="simpleJob" class="org.moon.test.timer.spring.SimpleJob" autowire="byName"/>
<task:scheduled-tasks scheduler="scheduler">
	<task:scheduled ref="simpleJob" method="handle" fixed-delay="5000"/>
</task:scheduled-tasks>
```
