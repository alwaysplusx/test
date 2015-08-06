package org.moon.ii.concurrent.lock;

public class DeadLockTest {

    public static void main(String[] args) {
        final SimpleService service = new SimpleService(1, 10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("55, sum 1~10 is " + service.sum());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-55, subtract 1~10 is " + service.subtract());
            }
        }).start();
    }

    static class SimpleService {
        private Integer begin;
        private Integer end;

        public SimpleService(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        // 在编写代码时候必须要以相同的顺序获取锁对象
        // 下面两个方法演示为
        /*
         * Thread 1  locks A, waits for B
         * Thread 2  locks B, waits for A
         */
        public int sum() {
            int result = 0;
            synchronized (end) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (begin) {
                    while (end >= begin) {
                        result += begin;
                        try {
                            System.out.println("sum begin is " + begin++);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return result;
        }

        public int subtract() {
            int result = 0;
            synchronized (begin) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (end) {
                    while (end >= begin) {
                        result -= end;
                        try {
                            System.out.println("subtract end is " + end--);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return result;
        }
    }

}
