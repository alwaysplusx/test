package org.moon.test.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "Simple", targetNamespace = "http://www.moon.com/simple")
public interface Simple {

    @WebMethod
    String sayHi(@WebParam(name = "name") String name);
    
}
