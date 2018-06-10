package org.harmony.eureka.apis;

/**
 * @author wuxii@foxmail.com
 */
public class Movie {

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
