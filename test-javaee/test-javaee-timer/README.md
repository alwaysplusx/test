## Enterprise JavaBean Timer

### javax.ejb.TimerService

> 使用openejb编写测试代码

```xml
<dependency>
  <groupId>org.apache.openejb</groupId>
  <artifactId>openejb-core</artifactId>
  <version>${openejb.version}</version>
</dependency>
```
测试代码如下

`javax.ejb.TimerService`

The TimerService interface provides enterprise bean components with access to the container-provided Timer Service.

`javax.ejb.TimerConfi`

TimerConfig is used to specify additional timer configuration settings during timer creation.

`javax.ejb.Timer`

The Timer interface contains information about a timer that was created through the EJB Timer Service.

```java
@Startup
@Singleton
public class SimpleTimerBean {

  private static Logger LOG = LoggerFactory.getLogger(SimpleTimerBean.class);
  @Resource
  TimerService timerService;

  @PostConstruct
  public void postConstruct() {
    LOG.debug("postConstruct");
    TimerConfig timerConfig = new TimerConfig("default_task", false);
    ScheduleExpression schedule = new ScheduleExpression().year("*")
                                                          .month("*")
                                                          .dayOfMonth("*")
                                                          .hour("*")
                                                          .minute("*/1");
    timerService.createCalendarTimer(schedule, timerConfig);
  }

  @Timeout
  public void timeout(Timer timer) {
    Serializable task = timer.getInfo();
    if ("default_task".equals(task)) {
      LOG.info("default task execute");
    }
  }

  @PreDestroy
  public void perDestroy() {
    LOG.debug("preDestroy");
  }

}
```

通过注解来实现定时器

```java
@Singleton
public class ScheduleMethod {

	static Logger LOG = LoggerFactory.getLogger(ScheduleMethod.class);

	@Schedule(hour = "*", minute = "*", second = "*/7")
	public void handle() {

	}
}
```
