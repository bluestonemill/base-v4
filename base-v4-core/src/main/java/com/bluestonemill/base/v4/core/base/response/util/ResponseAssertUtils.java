package com.bluestonemill.base.v4.core.base.response.util;


import com.bluestonemill.base.v4.core.base.response.SimpleError;
import com.bluestonemill.base.v4.core.base.response.SimpleResponse;
import com.bluestonemill.base.v4.core.base.response.exception.ResponseException;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class ResponseAssertUtils {

    public static void mustTrue(boolean check) {
        mustTrue(check, SimpleResponse.E500);
    }

    public static void mustTrue(boolean check, String msg) {
        if (!check) {
            throw new ResponseException(SimpleResponse.E500.getCode(), msg);
        }
    }

    public static void mustTrue(boolean check, SimpleError simpleError) {
        if (!check) {
            throw new ResponseException(simpleError);
        }
    }

    public static void mustTrue(boolean check, String code, String msg) {
        if (!check) {
            throw new ResponseException(code, msg);
        }
    }

    public static void checkResponse(SimpleResponse<?> simpleResponse) {
        if (!simpleResponse.checkSuccess()) {
            throw new ResponseException(simpleResponse.getCode(), simpleResponse.getMessage());
        }
    }


}
