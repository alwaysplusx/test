package org.moon.test.http.ws;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(targetNamespace = "http://soap.moon.test/ws/")
public class HelloWs implements Hello {

    @Resource
    WebServiceContext wsctx;

    @Override
    public String sayHi(@WebParam(name = "name") String name) {
        MessageContext msctx = wsctx.getMessageContext();
        Map<?, ?> header = (Map<?, ?>) msctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        showProperties(header);
        return "Hi " + name;
    }

    public static void showProperties(Map<?, ?> map) {
        Iterator<?> it = map.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            System.out.println(String.format("%s    %s", key, map.get(key)));
        }
    }
}