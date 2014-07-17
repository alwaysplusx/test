package org.moon.ii.jmx.mxbean;

import javax.management.MXBean;

import org.moon.ii.jmx.persistence.User;

@MXBean
public interface ServiceMXBean {
	
	User getUser();

	void setUser(User user);
}
