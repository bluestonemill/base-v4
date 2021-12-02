package com.bluestonemill.base.v4.features.auth.simple.filter;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.bluestonemill.base.v4.core.base.response.SimpleResponse;
import com.bluestonemill.base.v4.core.base.utils.ColUtils;
import com.bluestonemill.base.v4.features.auth.api.dto.AuthUser;
import com.bluestonemill.base.v4.features.auth.api.service.TokenService;
import com.bluestonemill.base.v4.features.auth.simple.context.AuthContext;
import com.bluestonemill.base.v4.support.web.utils.ResponseWriteUtils;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.bluestonemill.base.v4.features.auth.core.config.AuthConfig;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/11/30 14:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthFilter extends OncePerRequestFilter {

    private AuthConfig authConfig;

    private Map<String,Boolean> ignoreUrlMap;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private TokenService tokenService;

    public void init(){
        List<String> ignoreList = authConfig.getIgnoreList();
        if (CollUtil.isEmpty(ignoreList)){
            ignoreUrlMap = Maps.newHashMap();
        }else {
            ignoreUrlMap = ColUtils.simpleMapToList(ignoreList, Function.identity(), t -> true);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (needFilter(request)){
            String header = request.getHeader(authConfig.getHeaderKey());
            AuthUser authUser = tokenService.queryByToken(header);
            if (authUser==null){

            }
            AuthContext.setUser(authUser);
        }else {
            filterChain.doFilter(request,response);
        }
    }

    private void writeError(HttpServletResponse httpResponse) throws IOException{
        SimpleResponse<Void> simpleResponse = SimpleResponse.buildError(SimpleResponse.E401);
        ResponseWriteUtils.write(simpleResponse, httpResponse);
    }


    private boolean needFilter(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        Boolean checkRs = ignoreUrlMap.getOrDefault(requestURI, false);
        if (checkRs){
            return true;
        }
        return authConfig.getIgnoreList().stream()
                .anyMatch(s->{
                    return antPathMatcher.match(s,requestURI);
                });
    }
}
