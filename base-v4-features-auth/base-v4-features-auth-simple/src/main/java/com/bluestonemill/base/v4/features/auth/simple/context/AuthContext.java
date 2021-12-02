package com.bluestonemill.base.v4.features.auth.simple.context;

import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/11/30 16:52
 */
public class AuthContext {

    private static ThreadLocal<? super AuthUser> threadLocal=new ThreadLocal<>();

    public static <T extends AuthUser> T  getUser(){
        return (T) threadLocal.get();
    }

    public static <T extends AuthUser> void setUser(T user){
        threadLocal.set(user);
    }

}
