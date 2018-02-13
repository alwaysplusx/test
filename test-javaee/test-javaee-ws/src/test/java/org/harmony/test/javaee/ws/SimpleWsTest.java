package org.harmony.test.javaee.ws;

import static org.junit.Assert.*;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.harmony.test.javaee.OpenEJBConfiguration;
import org.harmony.test.javaee.Property;
import org.harmony.test.javaee.runner.OpenEJBRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(OpenEJBRunner.class)
@OpenEJBConfiguration(properties = { //
        @Property(name = "openejb.embedded.remotable", value = "true")//
})
public class SimpleWsTest {

    private static final String ADDRESS = "http://localhost:4204/test-javaee-ws/SimpleWs";

    @Test
    public void testSayHi() throws Exception {
        URL url = new URL(ADDRESS + "?wsdl");
        QName qName = new QName("http://www.harmony.com/simple", "Simple");
        Service service = Service.create(url, qName);
        Simple simple = service.getPort(Simple.class);
        assertEquals("Hi wuxii", simple.sayHi("wuxii"));
    }

    @Test
    public void testCXFSayHi() throws Exception {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setAddress(ADDRESS);
        Simple simple = proxyFactory.create(Simple.class);
        assertEquals("Hi wuxii", simple.sayHi("wuxii"));
    }

    public static void main(String[] args) {
        Endpoint.publish(ADDRESS, new SimpleWs());
    }

}
