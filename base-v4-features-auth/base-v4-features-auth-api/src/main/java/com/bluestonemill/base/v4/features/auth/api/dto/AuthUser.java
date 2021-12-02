package com.bluestonemill.base.v4.features.auth.api.dto;

import java.util.Set;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public interface AuthUser {

    /**
     *登录用户id
     * @return 登录用户id
     */
    Long getUid();

    /**
     *登录用户名称
     * @return 登录用户名称
     */
    String getName();


    /**
     * 登录用户权限
     * @return
     */
    Set<String> getPermsSet();
}
