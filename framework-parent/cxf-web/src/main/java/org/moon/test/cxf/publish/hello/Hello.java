package org.moon.test.cxf.publish.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "HelloServie", targetNamespace = "http://www.moon.org/ws")
public interface Hello {

	@WebMethod
	public String sayHello(@WebParam(name = "person") Person person);

}
