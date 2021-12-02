//package com.bluestonemill.base.v4.features.auth.shiro.token;
//
//import com.bluestonemill.base.v4.core.base.response.SimpleResponse;
//import com.bluestonemill.base.v4.core.base.response.exception.ResponseException;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
///**
//*
//* date 2021-06-08 13:12:30
//* @author 吴志林
//*
//**/
//@Slf4j
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Token2Filter extends AuthenticatingFilter {
//
//
//    private ShiroProperties shiroProperties;
//
//    private Map<String, Boolean> staticUrlMap = new HashMap<>();
//
//    private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    private String tokenKey;
//
//    public Token2Filter(ShiroProperties shiroProperties, String tokenKey) {
//        this.shiroProperties = shiroProperties;
//        this.tokenKey = tokenKey;
//    }
//
//    private boolean checkStaticUrl(ServletRequest request) {
//        if (request instanceof HttpServletRequest) {
//            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//            String uri = httpServletRequest.getRequestURI();
//            Boolean checkRs = staticUrlMap.get(uri);
//            if (checkRs != null) {
//                return checkRs;
//            } else {
//                Optional<String> checkOptional = shiroProperties.getStaticList()
//                        .parallelStream()
//                        .filter(s -> antPathMatcher.match(s, uri)).findAny();
//                if (checkOptional.isPresent()) {
//                    staticUrlMap.put(uri, true);
//                    return true;
//                } else {
//                    staticUrlMap.put(uri, false);
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
//        //获取请求token
//        String token = RequestTokenUtils.getRequestToken((HttpServletRequest) request, tokenKey);
//        boolean staticUrl = checkStaticUrl(request);
//        if (staticUrl) {
//            return null;
//        }
//
//        if (!StringUtils.hasLength(token)) {
//            return null;
//        }
//
//        return new Token2Token(token);
//    }
//
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        return ((HttpServletRequest) request).getMethod().equalsIgnoreCase("OPTIONS");
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        //获取请求token，如果token不存在，直接返回401
//        String token = RequestTokenUtils.getRequestToken((HttpServletRequest) request, tokenKey);
//        boolean staticUrl = checkStaticUrl(request);
//        if (staticUrl) {
//            return true;
//        }
//        if (!StringUtils.hasLength(token)) {
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            SimpleResponse<Void> simpleResponse = SimpleResponse.buildError(SimpleResponse.E401);
//            ResponseWriteUtils.write(simpleResponse, httpResponse);
//            return false;
//        }
//
//        return executeLogin(request, response);
//    }
//
//    @Override
//    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        try {
//            //处理登录失败的异常
//            Throwable throwable = e.getCause() == null ? e : e.getCause();
//            SimpleResponse<Void> simpleResponse;
//            if (throwable instanceof ResponseException) {
//                ResponseException ex = (ResponseException) throwable;
//                simpleResponse = SimpleResponse.buildError(ex.getCode(), ex.getMsg());
//            } else {
//                simpleResponse = SimpleResponse.buildError(SimpleResponse.E500);
//            }
//            ResponseWriteUtils.write(simpleResponse, httpResponse);
//        } catch (IOException e1) {
//            log.error("", e1);
//        }
//
//        return false;
//    }
//
//    @Override
//    public String toString() {
//        return "Token2Filter{" +
//                "shiroProperties.staticList=" + shiroProperties.getStaticList() +
//                '}';
//    }
//}
