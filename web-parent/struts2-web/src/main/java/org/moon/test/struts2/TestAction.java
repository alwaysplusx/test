package org.moon.test.struts2;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(TestAction.class);
	
	public String test() {
		log.info("struts2 test method invok!");
		return "success";
	}

	@Action(value = "say", results = { @Result(location = "/success.jsp") })
	public String say() {
		log.info("struts2 say method invok!");
		return "success";
	}

}