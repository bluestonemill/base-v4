package com.bluestonemill.base.v4.features.auth.api.service;


import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public interface AuthService {

    /**
     * 获取当前登录用户
     * @param <T> 用户登录信息封装类
     * @return 获取当前登录用户
     */
    <T extends AuthUser> T getUser();

    /**
     * 获取当前登录用户id
     * @return 获取当前登录用户id
     */
    default Long getUserId() {
        return getUser().getUid();
    }
}
