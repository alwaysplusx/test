package org.harmony.spring.streams.event;

/**
 * @author wuxii@foxmail.com
 */
public class UserEvent {

    private String name;
    private String action;
    private int times;

    public UserEvent(String name, String action, int times) {
        this.name = name;
        this.action = action;
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "{name:" + name + ", action:" + action + ", times:" + times + "}";
    }

}
