package org.harmony.test.jmx.mxbean;

import javax.management.MXBean;

import org.harmony.test.jmx.persistence.User;

@MXBean
public interface ServiceMXBean {
    
    User getUser();

    void setUser(User user);
}
