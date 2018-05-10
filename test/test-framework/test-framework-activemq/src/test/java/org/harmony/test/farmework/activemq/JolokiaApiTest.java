package org.harmony.test.farmework.activemq;

import org.jolokia.client.J4pClient;
import org.jolokia.client.J4pClientBuilderFactory;
import org.jolokia.client.request.J4pListRequest;
import org.jolokia.client.request.J4pResponse;

/**
 * @author wuxii@foxmail.com
 */
public class JolokiaApiTest {

    public static void main(String[] args) throws Exception {
        J4pClient client = J4pClientBuilderFactory//
                .user("admin")//
                .password("admin")//
                .url("http://localhost:8161/api/jolokia/")//
                .build();
        J4pResponse<J4pListRequest> response = client.execute(new J4pListRequest("/"));
        System.out.println(response.getValue() + "");
    }

}
