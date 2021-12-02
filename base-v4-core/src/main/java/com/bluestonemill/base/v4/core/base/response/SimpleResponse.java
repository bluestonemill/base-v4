package com.bluestonemill.base.v4.core.base.response;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
@Accessors(chain = true)
@Data
public class SimpleResponse<T> implements Serializable {

    public static final String CODE_200 = "200";
    public static final SimpleError E401 = new SimpleError("401", "权限不足");
    public static final SimpleError E405 = new SimpleError("405", "参数错误");
    public static final SimpleError E500 = new SimpleError("500", "系统错误");

    public static final SimpleError H500 = new SimpleError("H500", "系统错误", "服务降级");

    private String code = CODE_200;
    private String message;
    private T data;

    public SimpleResponse() {
    }

    public SimpleResponse(T data) {
        this.code = CODE_200;
        this.data = data;
    }

    public SimpleResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean checkSuccess() {
        return CODE_200.equals(this.code);
    }


    public static <T> SimpleResponse<T> buildSuccess(T t) {
        return new SimpleResponse<T>(t);
    }

    public static SimpleResponse<Void> buildSuccessNull() {
        return new SimpleResponse<Void>(null);
    }

    public static SimpleResponse<Map<String, Object>> buildSuccessMap(String key, Object val) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put(key, val);
        return buildSuccess(map);
    }

    public static <T> SimpleResponse<T> buildError(String code, String message) {
        return new SimpleResponse<T>(code, message);
    }

    public static <T> SimpleResponse<T> buildError(SimpleError simpleError) {
        return new SimpleResponse<T>(simpleError.getCode(), simpleError.getMessage());
    }


}
