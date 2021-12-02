package com.bluestonemill.base.v4.core.extension.mp3.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluestonemill.base.v4.core.extension.mp3.mapper.DefaultMapper;
import com.bluestonemill.base.v4.core.extension.mp3.service.IDefaultService;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class DefaultServiceImpl<M extends DefaultMapper<T>, T> extends ServiceImpl<M, T> implements IDefaultService<T> {
    @Override
    public boolean delByColumn(String columnField, Serializable columnId) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnField, columnId);
        return this.baseMapper.delete(queryWrapper) == 1;
    }

    @Override
    public boolean delByColumns(String columnField, Collection<? extends Serializable> columnIdList) {
        if (CollectionUtils.isEmpty(columnIdList)) {
            return false;
        }
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(columnField, columnIdList);
        return this.baseMapper.delete(queryWrapper) == columnIdList.size();
    }

    @Override
    public List<T> queryByColumn(String columnField, Serializable columnId) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(columnField, columnId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<T> queryByColumns(String columnField, Collection<? extends Serializable> columnIdList) {
        if (CollectionUtils.isEmpty(columnIdList)) {
            return Collections.emptyList();
        }
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(columnField, columnIdList);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public T queryOneByQuery(QueryWrapper<T> queryWrapper, Consumer<List<T>> listCheck, Consumer<T> objCheck) {
        queryWrapper.last(" limit 2");
        List<T> list = this.baseMapper.selectList(queryWrapper);
        listCheck.accept(list);
        T first = CollUtil.getFirst(list);
        objCheck.accept(first);
        return first;
    }


    @Override
    public T selectByIdIgnoreDeleteFlag(Serializable id) {
        return this.baseMapper.selectByIdIgnoreDeleteFlag(id);
    }

    @Override
    public List<T> selectByIdsIgnoreDeleteFlag(Collection<? extends Serializable> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return this.baseMapper.selectByIdsIgnoreDeleteFlag(ids);
    }

}
