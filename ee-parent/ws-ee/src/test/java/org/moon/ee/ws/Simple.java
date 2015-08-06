package org.moon.ee.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "Simple", targetNamespace = "http://www.moon.com/simple")
public interface Simple {

    String sayHi(@WebParam(name = "name") String name);
}
