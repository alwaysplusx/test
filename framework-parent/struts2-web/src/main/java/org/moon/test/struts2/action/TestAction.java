package org.moon.test.struts2.action;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.moon.test.struts2.model.Model;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(TestAction.class);
    private Calendar nowTime;
    private Model m;

    public String test() {
        log.info("struts2 test method invok!");
        System.out.println(nowTime);
        System.out.println("m >>> " + m);
        return "success";
    }

    @Action(value = "say", results = { @Result(location = "/success.jsp") })
    public String say() {
        log.info("struts2 say method invok!");
        return "success";
    }

    public Calendar getNowTime() {
        return nowTime;
    }

    public void setNowTime(Calendar nowTime) {
        this.nowTime = nowTime;
    }

    public Model getM() {
        return m;
    }

    public void setM(Model m) {
        this.m = m;
    }
}