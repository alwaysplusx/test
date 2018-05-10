package org.harmony.test.concurrent;

/**
 * @author wuxii@foxmail.com
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ClassLoader p = cl;
        do {
            System.out.println(p.getClass());
            p = p.getParent();
        } while (p != null);
    }

}
