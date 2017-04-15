### Thread Test

#### Create New Thread

创建线程的方式主要有两种：

1.实现`Runnable`
```java
new Thread(new Runnable() {
    @Override
    public void run() {

    }  
}).start();
```
2.集成自`Thread`类作为`Thread`的子类
```java
new Thread() {
    @Override
    public void run() {

    }
}.start();
```
启动线程`new Thread().start();`

#### Thread Synchronized

使用`synchronized`关键字

```java
synchronized(lockObject) {
  //some synchrony business logic
}
```

使用`ReentrantLock`类
```java
class LockTest {
  ReentrantLock lock = new ReentrantLock();
  public void someBusinessMethod() {
    lock.lock();
    try {
      // some synchrony business logic  
    } finally {
      lock.unlock();  
    }
  }
}
```
#### [Thread Communication](https://github.com/wuxii/ii-parent/blob/master/concurrent-ii/src/test/java/org/moon/ii/concurrent/thread/ThreadCommunicationTest.java)

使用来自`Object`的`wait();`与`notify();`

> 注意：在使用`wait()`与`notify()`要在同步块内使用

```java
synchronized(lockObject) {
  this.wait();
}
synchronized(lockObject) {
  this.notify();
}
```

使用`Condition`类的`await();`和`signal();`[详细](https://github.com/wuxii/ii-parent/blob/master/concurrent-ii/src/test/java/org/moon/ii/concurrent/lock/LockAndConditionTest.java)
```java
{
  Condition condition1 = lock.newCondition();
  Condition condition2 = lock.newCondition();
  {
    //ReentrantLock
    lock.lock();
    condition1.await();
    condition2.signal();
    lock.unlock();
  }
  {
    lock.lock();
    condition2.await();
    condition1.signal();
    lock.unlock();
  }
}
```
