package com.bluestonemill.base.v4.core.base.simple.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
@Data
public class BaseSimplePage<T> implements Serializable {

    private long pageSize;
    private long pageNum;

    /**
     * 总数
     */
    private long total;
    /**
     * 总页
     */
    private long pages;

    /**
     * 数据
     */
    private List<T> data;
}
