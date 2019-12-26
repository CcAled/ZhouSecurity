package com.zhou.security.core.properties;

/**
 * zhousecurity
 * 2019/11/11 10:20
 *
 * @author ChristChou
 * @since
 **/
public class OAuth2Properties {
    private String jwtSigningKey = "zhou";

    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
