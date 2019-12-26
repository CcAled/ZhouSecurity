package com.zhou.security.core.properties;

/**
 * zhousecurity
 * 2019/11/6 10:26
 * SpringBoot2.0中删除的SocialProperties
 *
 * @author ChristChou
 * @since
 **/
public abstract class SpringBootSocialProperties {
    private String appId;
    private String appSecret;
    public SpringBootSocialProperties() {
    }
    public String getAppId() {
        return this.appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getAppSecret() {
        return this.appSecret;
    }
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}