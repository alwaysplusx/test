package org.harmony.test.java.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.harmony.test.java.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class XStreamTest {
    XStream xStream;

    @Before
    public void setUp() throws Exception {
        xStream = new XStream();
    }

    @Test
    public void objectToXmlTest() {
        xStream.processAnnotations(User.class);
        List<User> users = new ArrayList<User>();
        User user = new User("David", "abc123");
        users.add(user);
        users.add(user);
        users.add(user);
        xStream.setMode(XStream.NO_REFERENCES);
        System.out.println(xStream.toXML(users));
    }

    @Test
    public void fileToObjectTest() {
        xStream.alias("user", User.class);
        xStream.alias("users", User.class);
        Object obj = xStream.fromXML(new File("src/main/resources/users.xml"));
        System.out.println(obj);
    }

    @After
    public void tearDown() throws Exception {
    }

}
