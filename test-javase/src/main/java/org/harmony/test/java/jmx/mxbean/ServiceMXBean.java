package org.harmony.test.java.jmx.mxbean;

import javax.management.MXBean;

import org.harmony.test.java.User;

@MXBean
public interface ServiceMXBean {

    User getUser();

    void setUser(User user);
}
