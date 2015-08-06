package org.moon.ii.jmx.mbean;

public class Service implements ServiceMBean {

    private String name = "test";

    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
