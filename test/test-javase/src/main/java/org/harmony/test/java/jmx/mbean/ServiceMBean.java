package org.harmony.test.java.jmx.mbean;

public interface ServiceMBean {

    String sayHi(String name);
    
    String getName();
    
    void setName(String name);
}
