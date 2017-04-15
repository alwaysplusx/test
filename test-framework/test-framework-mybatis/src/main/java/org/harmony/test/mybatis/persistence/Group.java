package org.harmony.test.mybatis.persistence;

import java.util.Collection;

public class Group extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String groupName;
    private Collection<User> users;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

}
