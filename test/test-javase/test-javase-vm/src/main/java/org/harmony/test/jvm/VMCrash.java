package org.harmony.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxii@foxmail.com
 */
public class VMCrash {

    private static final List<Object> array = new ArrayList<>();

    // -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=path/to -Xms1M -Xmx1M
    public static void main(String[] args) {
        while (true) {
            array.add(new Object());
        }
    }

}
