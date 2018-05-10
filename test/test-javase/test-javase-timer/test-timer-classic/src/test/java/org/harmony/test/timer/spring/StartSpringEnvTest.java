package org.harmony.test.timer.spring;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class StartSpringEnvTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void testStart() throws Exception {
        assertNotNull(context);
        Thread.sleep(Long.MAX_VALUE);
    }

}
