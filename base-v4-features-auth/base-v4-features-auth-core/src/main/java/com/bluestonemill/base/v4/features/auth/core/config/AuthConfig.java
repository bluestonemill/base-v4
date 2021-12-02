package com.bluestonemill.base.v4.features.auth.core.config;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/11/30 14:54
 */
@Data
public class AuthConfig {

    private String headerKey="token";

    private String tokenKey="features:auth:token:";

    private List<String> ignoreList;
}
