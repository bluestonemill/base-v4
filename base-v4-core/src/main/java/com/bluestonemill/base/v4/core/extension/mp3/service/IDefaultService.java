package com.bluestonemill.base.v4.core.extension.mp3.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public interface IDefaultService<T> extends IService<T> {

    /**
     * 单值删除
     * @param columnField 数据库字段名
     * @param columnId 字段值
     * @return
     */
    public boolean delByColumn(String columnField, Serializable columnId);

    /**
     * 批量删除
     * @param columnField  数据库字段名
     * @param columnIdList 字段值
     * @return
     */
    public boolean delByColumns(String columnField, Collection<? extends Serializable> columnIdList);

    /**
     * 单值查询
     * @param columnField 数据库字段名
     * @param columnId 字段值
     * @return
     */
    public List<T> queryByColumn(String columnField, Serializable columnId);

    /**
     *  单值查询
     * @param columnField 数据库字段名
     * @param columnIdList 字段值
     * @return
     */
    public List<T> queryByColumns(String columnField, Collection<? extends Serializable> columnIdList);

    /**
     * 按条件查询多个
     * @param queryWrapper 查询条件
     * @param listCheck list过滤
     * @param objCheck obj过滤
     * @return
     */
    public T queryOneByQuery(QueryWrapper<T> queryWrapper, Consumer<List<T>> listCheck,Consumer<T> objCheck);


    /**
     * 单个查询 忽略删除标记
     * @param id id
     * @return
     */
    public T selectByIdIgnoreDeleteFlag(Serializable id);

    /**
     * 查询列表 忽略删除标记
     * @param ids
     * @return
     */
    public List<T> selectByIdsIgnoreDeleteFlag(Collection<? extends Serializable> ids);
}
