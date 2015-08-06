package org.moon.test.json.persistence;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String password;
    private Date birthday;
    private String sex;
    public static User user = new User(1l, "default", "abc123", new Date(), "F");

    public User() {
    }

    public User(Long userId, String username, String password, Date birthday, String sex) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
