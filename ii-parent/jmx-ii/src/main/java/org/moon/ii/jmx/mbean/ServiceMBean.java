package org.moon.ii.jmx.mbean;

public interface ServiceMBean {

    String sayHi(String name);
    
    String getName();
    
    void setName(String name);
}
