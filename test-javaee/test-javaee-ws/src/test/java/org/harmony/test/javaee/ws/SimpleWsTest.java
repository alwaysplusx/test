package org.harmony.test.javaee.ws;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleWsTest {

    EJBContainer container;

    @Before
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.setProperty("openejb.embedded.remotable", "true");
        container = EJBContainer.createEJBContainer(props);
    }

    @Test
    public void testStart() throws Exception {
        // Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void testSayHi() throws Exception {
        URL url = new URL("http://127.0.0.1:4204/ws-ee/SimpleWs?wsdl");
        QName qName = new QName("http://www.moon.com/simple", "Simple");
        Service service = Service.create(url, qName);
        Simple simple = service.getPort(Simple.class);
        System.out.println(simple.sayHi("wuxii"));
    }

    @Test
    public void testCXFSayHi() throws Exception {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setAddress("http://127.0.0.1:4204/ws-ee/SimpleWs");
        Simple simple = proxyFactory.create(Simple.class);
        assertEquals("Hi wuxii", simple.sayHi("wuxii"));
    }

    @After
    public void tearDown() {
        container.close();
    }

}
