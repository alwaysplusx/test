package org.moon.test.cxf.publish.hello;

import javax.jws.WebService;

@WebService(serviceName = "HelloServie", targetNamespace = "http://www.moon.org/ws")
public class HelloService implements Hello {

	@Override
	public String sayHello(Person person) {
		return "Hello " + person.getName();
	}

}
