package org.harmony.test.farmework.activemq;

import org.apache.activemq.broker.BrokerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wuxii@foxmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/activemq-test.xml" })
public class BrokerServiceTest {

    @Autowired
    private BrokerService brokerService;

    @Test
    public void test() {
        System.out.println(brokerService);
    }

}
