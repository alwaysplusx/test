package org.harmony.test.java.jmx.mxbean;

import org.harmony.test.java.jmx.persistence.User;

public class Service implements ServiceMXBean {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
