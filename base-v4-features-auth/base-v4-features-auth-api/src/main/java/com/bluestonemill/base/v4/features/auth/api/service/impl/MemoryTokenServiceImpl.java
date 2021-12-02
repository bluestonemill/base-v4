package com.bluestonemill.base.v4.features.auth.api.service.impl;


import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;
import com.bluestonemill.base.v4.features.auth.api.service.TokenService;
import com.google.common.collect.Maps;

import java.util.Map;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class MemoryTokenServiceImpl implements TokenService {

    private Map<String, AuthUser> map = Maps.newConcurrentMap();

    @Override
    public <T extends AuthUser> T queryByToken(String accessToken) {
        return (T) map.get(accessToken);
    }

    @Override
    public <T extends AuthUser> boolean saveWithToken(T user, String accessToken, Long timeSend) {
        map.put(accessToken, user);
        return true;
    }

    @Override
    public void delByToken(String accessToken) {
        map.remove(accessToken);
    }
}
