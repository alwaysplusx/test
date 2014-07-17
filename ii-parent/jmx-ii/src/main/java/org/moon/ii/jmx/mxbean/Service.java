package org.moon.ii.jmx.mxbean;

import org.moon.ii.jmx.persistence.User;

public class Service implements ServiceMXBean {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
