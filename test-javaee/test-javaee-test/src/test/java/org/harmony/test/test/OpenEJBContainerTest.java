package org.harmony.test.test;

import javax.annotation.Resource;
import javax.naming.Context;

import org.harmony.tes.test.Naming;
import org.harmony.tes.test.runner.OpenEJBRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author wuxii@foxmail.com
 */
@RunWith(OpenEJBRunner.class)
public class OpenEJBContainerTest {

    @Naming("java:/")
    private Context context;

    @Resource(lookup = "java:/")
    private Context context2;

    @Test
    public void test() {
        System.out.println(context);
        System.out.println(context2);
    }

}
