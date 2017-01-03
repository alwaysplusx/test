package org.moon.test.timer;

import static org.junit.Assert.*;

import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class StartEJBContainerTest {

    EJBContainer container;

    @Before
    public void setUp() throws Exception {
        container = EJBContainer.createEJBContainer();
    }

    @Test
    public void testStartContainer() throws Exception {
        assertNotNull(container);
        Thread.sleep(Long.MAX_VALUE);
    }

    @After
    public void tearDown() {
        container.close();
    }

}
