package com.moon.tomee;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(mappedName = "SimpleBean")
@Remote(SimpleRemote.class)
public class SimpleBean implements SimpleRemote {

	@Override
	public String sayHi(String name) {
		return "Hi " + name;
	}

}
