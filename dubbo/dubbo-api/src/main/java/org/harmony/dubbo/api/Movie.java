package org.harmony.dubbo.api;

import java.io.Serializable;

/**
 * @author wuxii@foxmail.com
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = -1672959128099770576L;
    private String name;
    private int limit;

    public Movie() {
    }

    public Movie(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
