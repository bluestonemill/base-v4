package com.bluestonemill.base.v4.core.base.simple.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluestonemill.base.v4.core.base.simple.request.BaseSimpleSearch;
import com.bluestonemill.base.v4.core.base.simple.response.BaseSimplePage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/11/29 15:36
 */
public class MybatisPlusPageUtils {

    /**
     * 分页结构转换
     * @param page 后台分页
     * @param <T>
     * @return 前端分页
     */
    public static <T> BaseSimplePage<T> adapter(Page<T> page) {
        BaseSimplePage<T> simplePage = new BaseSimplePage<T>();
        simplePage.setData(page.getRecords());
        simplePage.setTotal(page.getTotal());
        simplePage.setPages(page.getPages());
        simplePage.setPageSize(page.getSize());
        simplePage.setPageNum(page.getCurrent());
        return simplePage;
    }

    /**
     * 分页结构转换
     * @param page 后台分页
     * @param data 数据
     * @param <T> 分页类型
     * @param <V> 数据类型
     * @return
     */
    public static <T, V> BaseSimplePage<V> adapter(Page<T> page, List<V> data) {
        BaseSimplePage<V> simplePage = new BaseSimplePage<V>();
        simplePage.setData(data);
        simplePage.setTotal(page.getTotal());
        simplePage.setPages(page.getPages());
        simplePage.setPageSize(page.getSize());
        simplePage.setPageNum(page.getCurrent());
        return simplePage;
    }

    public static <T> Page<T> getMybatisPlusPage(BaseSimpleSearch pageQueryParam) {
        return new Page<>(pageQueryParam.getPage(), pageQueryParam.getPageSize());
    }
}
