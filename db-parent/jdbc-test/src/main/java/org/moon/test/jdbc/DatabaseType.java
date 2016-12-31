package org.moon.test.jdbc;

/**
 * @author wuxii@foxmail.com
 */
public enum DatabaseType {

    Oracle("oracle"), H2("h2"), Mysql("mysql");

    private String name;

    private DatabaseType(String name) {
        this.name = name;
    }

    public String desc() {
        return name;
    }

}
