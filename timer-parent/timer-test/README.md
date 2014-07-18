## Java Classic Timer

#### java.util.Timer

java定时器，使用`scheduler(timerTask, delay)``scheduler(timerTask, delay, period)`

`schedule`与`scheduleAtFixedRate`区别在于：

`scheduleAtFixedRate`将与定时器开始时间作为起点来计算，timer会将所执行的task的平均间隔时间控制在所定义的period范围。<b>`scheduleAtFixedRate`的执行间隔时间有可能小于所定义的`period`时间。</b>

`schedule`则不理会前面所执行的任务是否超过了period时间，都将间隔period时间才执行下次task。<b>`schedule`的间隔时间不可能小于period时间。</b>

最终的结果是`scheduleAtFixedRate`的平均间隔时间将会接近所定义的period，而`schedule`的平均间隔是将有可能远远大于定义的period。
