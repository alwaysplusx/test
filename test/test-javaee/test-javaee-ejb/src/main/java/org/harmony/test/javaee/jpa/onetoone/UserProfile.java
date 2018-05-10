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
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserProfile [profileId=" + profileId + "]";
    }

}
