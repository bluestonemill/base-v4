package com.bluestonemill.base.v4.features.auth.shiro.service.impl;

import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;
import com.bluestonemill.base.v4.features.auth.api.service.AuthService;
import org.apache.shiro.SecurityUtils;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class ShiroAuthServiceImpl implements AuthService {
    @Override
    public <T extends AuthUser> T getUser() {
        return (T) SecurityUtils.getSubject().getPrincipal();
    }
}
