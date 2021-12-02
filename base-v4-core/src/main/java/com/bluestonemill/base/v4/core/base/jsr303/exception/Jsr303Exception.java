package com.bluestonemill.base.v4.core.base.jsr303.exception;

import lombok.Data;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
@Data
public class Jsr303Exception extends RuntimeException {

    private String errorMsg;

    public Jsr303Exception(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
