package org.harmony.test.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockAndConditionTest {

    public static void main(String[] args) {

        final Factory factory = new Factory(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    factory.produce("P-1 " + i++);
                    try {
                        Thread.sleep(new Random().nextInt(2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "P-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    factory.produce("P-2 " + i++);
                    try {
                        Thread.sleep(new Random().nextInt(1500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "P-2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    factory.produce("P-3 " + i++);
                    try {
                        Thread.sleep(new Random().nextInt(1250));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "P-3").start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    factory.consume();
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "C-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    factory.consume();
                    try {
                        Thread.sleep(new Random().nextInt(800));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "C-2").start();        
        
    }

    static class Factory {
        Logger log = LoggerFactory.getLogger(Factory.class);
        Object[] item;
        Lock lock = new ReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition notFull = lock.newCondition();
        int produceIndex;
        int consumeIndex;
        int count;

        public Factory(int capacity) {
            this.item = new Object[capacity];
        }

        public Object consume() {
            lock.lock();
            Object result = null;
            try {
                while (count == 0) {
                    log.info("queue is empty wait producer produce data");
                    notEmpty.await();
                }
                result = item[consumeIndex];
                consumeIndex = inc(consumeIndex);
                count--;
                // log.info("consume data " + result + ", " + factoryState());
                log.info("C " + result);
                notFull.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return result;
        }

        public void produce(Object obj) {
            lock.lock();
            try {
                while (count == item.length) {
                    log.info("queue is full wait consumer consume data");
                    notFull.await();
                }
                item[produceIndex] = obj;
                produceIndex = inc(produceIndex);
                ++count;
                log.info("P " + obj);
                // log.info("produce data " + obj + ", " + factoryState());
                notEmpty.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        private int inc(int index) {
            return ++index == item.length ? 0 : index;
        }

        public int dec(int index) {
            return (index == 0 ? item.length : index) - 1;
        }

        protected String factoryState() {
            return "produceIndex=" + produceIndex + ", consumeIndex=" + consumeIndex + ", count=" + count;
        }

    }

}
