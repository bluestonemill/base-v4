package com.bluestonemill.base.v4.support.web.utils;

import com.bluestonemill.base.v4.core.base.response.SimpleResponse;
import com.bluestonemill.base.v4.core.extension.spring.SpringContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class ResponseWriteUtils {

    public static void write(SimpleResponse val, HttpServletResponse httpResponse) throws IOException {
        ObjectMapper objectMapper = SpringContextHolder.getApplicationContext().getBean(ObjectMapper.class);
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        String json = objectMapper.writeValueAsString(val);
        httpResponse.getWriter().println(json);
    }
}
