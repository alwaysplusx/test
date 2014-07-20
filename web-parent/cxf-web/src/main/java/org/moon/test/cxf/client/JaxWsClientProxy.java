package org.moon.test.cxf.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class JaxWsClientProxy {

	/**
	 * @param namespace
	 *            需要代理的webservice的命名空间
	 * @param serviceName
	 *            代理的服务名称
	 *            <code>&lt;wsdl:service name="serviceName"&gt;<br/></code>
	 * @param wsdlLocation
	 *            wsdl所在目录,默认更目录为classpath,或者直接指定服务器的wsdl
	 * @param serviceClass
	 *            需要代理的webservice接口
	 * @return
	 * @throws Exception
	 */
	public <T> T create(String namespace, String serviceName, String wsdlLocation, Class<T> serviceClass) throws Exception {
		URL url = null;
		if (wsdlLocation.startsWith("http")) {
			url = new URL(wsdlLocation);
		} else {
			url = Thread.currentThread().getContextClassLoader().getResource(wsdlLocation);
		}
		return new Proxy(namespace, serviceName, url).getPort(serviceClass);
	}

	class Proxy extends Service {

		protected Proxy(String namespace, String serviceName, URL url) {
			this(url, new QName(namespace, serviceName));
		}

		protected Proxy(String namespace, String serviceName, String wsdlLocation) throws Exception {
			this(new URL(wsdlLocation), new QName(namespace, serviceName));
		}

		protected Proxy(URL wsdlDocumentLocation, QName serviceName) {
			super(wsdlDocumentLocation, serviceName);
		}

	}

}
