package com.bluestonemill.base.v4.features.auth.simple.service.impl;

import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;
import com.bluestonemill.base.v4.features.auth.api.service.AuthService;
import com.bluestonemill.base.v4.features.auth.simple.context.AuthContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/11/30 16:56
 */
public class AuthFilterServiceImpl implements AuthService {
    @Override
    public <T extends AuthUser> T getUser() {
        return AuthContext.getUser();
    }
}
