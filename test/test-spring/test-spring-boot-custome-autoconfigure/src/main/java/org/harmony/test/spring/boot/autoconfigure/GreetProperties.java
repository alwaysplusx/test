package org.harmony.test.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author wuxii@foxmail.com
 */
@ConfigurationProperties(prefix = "harmony.greet")
public class GreetProperties {

    private String url = "/greet";

    private boolean enabled = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
