package org.harmony.test.javaee.jpa.onetoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author wuxii@foxmail.com
 */
@Entity
public class UserProfile {

    @Id
    private String profileId;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User3 user;

    public UserProfile() {
    }

    public UserProfile(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public User3 getUser() {
        return user;
    }

    public void setUser(User3 user) {
        this.user = user;
    }

}
