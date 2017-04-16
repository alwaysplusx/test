package org.harmony.test.java.io.ifeve;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxii@foxmail.com
 */
public class IfeveTest {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String _N = "\n";
    private static final Map<String, String> TRANS = new HashMap<String, String>();
    static {
        TRANS.put(A, B);
        TRANS.put(B, C);
        TRANS.put(C, _N);
        TRANS.put(_N, A);
    }

    private static volatile String currentInfo = A;

    private static String nextInfo(String info) {
        return TRANS.get(info);
    }

    public static void main(String[] args) {
        new Thread(new PrintTask(A)).start();
        new Thread(new PrintTask(B)).start();
        new Thread(new PrintTask(C)).start();
        // 增加一个打印回车的线程，这样的话打印效果更好
        new Thread(new PrintTask(_N)).start();
    }

    private static class PrintTask implements Runnable {

        private final String info;

        public PrintTask(String info) {
            this.info = info;
        }

        public void run() {
            for (;;) {
                if (currentInfo == info) {// 这里用了双重检查，可以稍微提升性能
                    synchronized (currentInfo) {
                        if (currentInfo == info) {
                            System.out.print(info);
                            currentInfo = nextInfo(info);
                        }
                    }
                }
            }
        }
    }
}
