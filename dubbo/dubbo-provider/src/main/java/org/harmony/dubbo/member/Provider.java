package org.harmony.dubbo.member;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wuxii@foxmail.com
 */
public class Provider {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/dubbo-provider.xml");
        context.start();

        Thread.sleep(Long.MAX_VALUE);

        context.close();
    }
}
