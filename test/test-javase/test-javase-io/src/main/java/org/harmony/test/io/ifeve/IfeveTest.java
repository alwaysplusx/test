/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.harmony.test.io.ifeve;

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
