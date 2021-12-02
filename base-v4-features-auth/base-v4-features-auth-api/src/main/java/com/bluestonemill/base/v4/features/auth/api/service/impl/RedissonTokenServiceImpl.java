package com.bluestonemill.base.v4.features.auth.api.service.impl;


import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;
import com.bluestonemill.base.v4.features.auth.api.service.TokenService;
import lombok.Data;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;


/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
@Data
public class RedissonTokenServiceImpl implements TokenService {

    private RedissonClient redissonClient;

    private String tokenPrefix = "session:token:";

    @Override
    public <T extends AuthUser> T queryByToken(String accessToken) {
        String key = getTokenKey(accessToken);
        return (T) redissonClient.getBucket(key).get();
    }

    @Override
    public <T extends AuthUser> boolean saveWithToken(T user, String accessToken, Long timeSend) {
        String key = getTokenKey(accessToken);
        redissonClient.getBucket(key).set(user, timeSend, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public void delByToken(String accessToken) {
        String tokenKey = getTokenKey(accessToken);
        redissonClient.getBucket(tokenKey).delete();
    }

    public String getTokenKey(String accessToken) {
        return tokenPrefix + accessToken;
    }
}
