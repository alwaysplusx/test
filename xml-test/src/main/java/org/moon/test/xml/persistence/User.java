package org.moon.test.xml.persistence;

import java.io.Serializable;
import java.util.Date;

@com.thoughtworks.xstream.annotations.XStreamAlias(value = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String password;
	private Date birthday;
	private Integer age;
	private String sex;

	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", birthday=" + birthday + ", age=" + age + ", sex=" + sex + "]";
	}

}
