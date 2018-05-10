package org.harmony.test.javaee.jpa.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author wuxii@foxmail.com
 */
@Entity
public class User3 {

    @Id
    private String username;
    @OneToOne(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    public User3() {
    }

    public User3(String username) {
        this.username = username;
    }

    public User3(String username, UserProfile userProfile) {
        this.username = username;
        this.userProfile = userProfile;
        this.userProfile.setUser(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}
