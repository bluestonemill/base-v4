package com.bluestonemill.base.v4.features.auth.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class Token2Token implements AuthenticationToken {
    private String token;

    public Token2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
