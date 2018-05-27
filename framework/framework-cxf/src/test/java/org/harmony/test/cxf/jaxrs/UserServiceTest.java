package org.harmony.test.cxf.jaxrs;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.harmony.test.cxf.jaxrs.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest {

    String user1 = "<user><id>1</id><username>wuxii</username><age>23</age></user>";
    String user1_update = "<user><id>1</id><username>wuxii</username><age>23</age></user>";
    String user2 = "<user><id>2</id><username>test</username><age>23</age></user>";
    Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    HttpClient client;

    @BeforeClass
    public static void beforeClass() {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(UserService.class);
        sf.setResourceProvider(UserService.class, new SingletonResourceProvider(new UserService()));
        sf.setAddress("http://localhost:9000");
        sf.create();
    }

    @Before
    public void setUp() {
        client = new HttpClient();
    }

    @Test
    public void testGetUser() throws Exception {
        GetMethod get = new GetMethod("http://localhost:9000/users/1");
        int result = client.executeMethod(get);
        log.info("get user response http status:{}", result);
        log.info("get user info from jaxrs :{}", get.getResponseBodyAsString());
    }

    @Test
    public void testAddUser() throws Exception {
        PostMethod post = new PostMethod("http://localhost:9000/users");
        // post.addRequestHeader("Accept", "text/xml");
        StringRequestEntity entity = new StringRequestEntity(user2, "text/xml", "UTF-8");
        post.setRequestEntity(entity);
        int result = client.executeMethod(post);
        log.info("add user response http status:{}", result);
        log.info("add user response body:{}", post.getResponseBodyAsString());
    }

    @Test
    public void testUpdateUser() throws Exception {
        PutMethod put = new PutMethod("http://localhost:9000/users");
        put.setRequestEntity(new StringRequestEntity(user1_update, "text/xml", "UTF-8"));
        int result = client.executeMethod(put);
        log.info("update user response http status:{}", result);
        log.info("update user response body:{}", put.getResponseBodyAsString());
    }

    @Test
    public void testDeleteUser() throws Exception {
        DeleteMethod delete = new DeleteMethod("http://localhost:9000/users/0");
        int result = client.executeMethod(delete);
        log.info("delete user response http status:{}", result);
        log.info("delete user response body:{}", delete.getResponseBodyAsString());
    }

}
