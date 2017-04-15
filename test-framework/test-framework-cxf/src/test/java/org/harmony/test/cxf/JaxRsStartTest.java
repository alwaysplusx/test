package org.harmony.test.cxf;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.harmony.test.cxf.jaxrs.UserService;

public class JaxRsStartTest {

    public static void main(String[] args) {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(UserService.class);
        sf.setResourceProvider(UserService.class, new SingletonResourceProvider(new UserService()));
        sf.setAddress("http://localhost:9000");
        sf.create();
    }
}
