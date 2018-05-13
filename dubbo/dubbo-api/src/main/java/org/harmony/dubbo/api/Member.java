package org.harmony.dubbo.api;

import java.io.Serializable;

/**
 * @author wuxii@foxmail.com
 */
public class Member implements Serializable {

    private static final long serialVersionUID = -703057460053048349L;
    private String name;
    private int age;

    public Member() {
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member {name:" + name + ", age:" + age + "}";
    }

}
