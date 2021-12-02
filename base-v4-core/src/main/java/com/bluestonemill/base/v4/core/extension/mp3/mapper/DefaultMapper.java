package com.bluestonemill.base.v4.core.extension.mp3.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/10/9 10:07
 */
public interface DefaultMapper<T> extends BaseMapper<T> {

    /**
     * 根据id查询 忽略删除标记
     * @param id id
     * @return
     */
    T selectByIdIgnoreDeleteFlag(Serializable id);

    /**
     * 根据id查询多个 忽略删除标记
     * @param ids ids
     * @return
     */
    List<T> selectByIdsIgnoreDeleteFlag(@Param(Constants.COLLECTION) Collection<? extends Serializable> ids);


    List<T> selectListIgnoreDeleteFlag(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


}
