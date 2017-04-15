package org.moon.test.json;

import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moon.test.json.persistence.User;

import com.google.gson.Gson;

public class GoogleGsonTest {

    private Gson gson;
    private User user;
    
    @Before
    public void setUp() {
        gson = new Gson();
        user = new User("david", "abc123");
        user.setBirthday(new Date());
        user.setSex("F");
    }

    @Test
    public void test() {
        assertNotEquals(null, gson);
        System.out.println(gson.toJson(user));
    }

    @After
    public void treaDown() {

    }

}
