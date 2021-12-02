package com.bluestonemill.base.v4.core.base.response;

import lombok.Data;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
@Data
public class SimpleError {
    private String code;
    private String message;
    private String innerMessage;

    public SimpleError() {
    }

    public SimpleError(String code, String message) {
        this(code, message, message);
    }

    public SimpleError(String code, String message, String innerMessage) {
        this.code = code;
        this.message = message;
        this.innerMessage = innerMessage;
    }

    public SimpleError(String code) {
        this(code, code);
    }
}
