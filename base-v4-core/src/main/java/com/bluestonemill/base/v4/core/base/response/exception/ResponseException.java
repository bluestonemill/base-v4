package com.bluestonemill.base.v4.core.base.response.exception;

import com.bluestonemill.base.v4.core.base.response.SimpleError;
import lombok.Data;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
@Data
public class ResponseException extends RuntimeException {

    private String code;
    private String msg;


    public ResponseException(Throwable cause, String code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public ResponseException(SimpleError simpleError) {
        this.code = simpleError.getCode();
        this.msg = simpleError.getMessage();
    }

    public ResponseException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
