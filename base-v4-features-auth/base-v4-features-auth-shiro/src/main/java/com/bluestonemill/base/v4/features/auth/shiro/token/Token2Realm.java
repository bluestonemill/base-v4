package com.bluestonemill.base.v4.features.auth.shiro.token;


import com.bluestonemill.base.v4.core.base.response.SimpleResponse;
import com.bluestonemill.base.v4.core.base.response.util.ResponseAssertUtils;
import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;
import com.bluestonemill.base.v4.features.auth.api.service.TokenService;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
@Data
public class Token2Realm extends AuthorizingRealm {

    private TokenService tokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token2Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AuthUser user = (AuthUser) principals.getPrimaryPrincipal();

        //用户权限列表
        Set<String> permsSet = user.getPermsSet();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        AuthUser authUser = tokenService.queryByToken(accessToken);
        ResponseAssertUtils.mustTrue(authUser != null, SimpleResponse.E401);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authUser, accessToken, getName());
        return info;
    }
}
