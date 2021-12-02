package com.bluestonemill.base.v4.features.auth.api.service;


import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public interface TokenService {

    /**
     * 根据token解析用户认证数据
     * @param accessToken token
     * @param <T> 用户数据
     * @return
     */
    <T extends AuthUser> T queryByToken(String accessToken);

    /**
     * 保存认证信息
     * @param user 认证数据
     * @param accessToken token
     * @param timeSend 缓存时间 s
     * @param <T> 类型
     * @return
     */
    <T extends AuthUser> boolean saveWithToken(T user, String accessToken, Long timeSend);

    /**
     * 删除用户认证
     * @param accessToken
     */
    void delByToken(String accessToken);
}
